import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import outputformatters.Translatable;
import outputformatters.totranslatableconverters.ToTranslatableConverter;
import outputformatters.translators.Translator;
import parsingtools.CommandParser;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.HelpCommandsFactory;
import parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAllTransgressingAirshipsCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsCloserToCommandsFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.AuthenticateCommandFactory;
import parsingtools.commandfactories.userauthenticatingfactories.DeleteAirshipCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PatchAirshipCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PatchUserPasswordCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PostAirshipCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PostUserCommandsFactory;
import utils.StringCommandsDictionary;
import utils.StringCommandsExecutor;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import databases.Database;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import exceptions.InvalidArgumentException;


/**
 * AirTrafficControl Application for command line. All available commands are registered first. Then
 * the user can use each command till it writes "EXIT" - end of the program
 * <ul>
 * Command list:
 * 
 * <li>DELETE /airships/{flightId}<br>
 * Delete An Airship
 * 
 * <li>GET /airships <br>
 * Gets the list of all airships.
 * 
 * <li>GET /airships/find <br>
 * Get the nearest aircrafts of Geographic coordinates
 * 
 * <li>GET /airships/nbPassengers/{nbP}/bellow <br>
 * Gets all airships that are transgressing their air corridors.
 * 
 * <li>GET /airships/owner/{owner} <br>
 * Gets all airships added by a certain user.
 * 
 * <li>GET /airships/reports <br>
 * Gets all airships that are transgressing their air corridors.
 * 
 * <li>GET /airships/reports/{flightId} <br>
 * Checks whether an airship is transgressing its air corridor.
 * 
 * <li>GET /airships/{flightId} <br>
 * Gets an airship with a certain flightId.
 * 
 * <li>GET /users <br>
 * Gets the list of all users.
 * 
 * <li>GET /users/{username} <br>
 * Gets a user with a certain username.
 * 
 * <li>GET /users/authenticate <br>
 * Authenticates the user that is trying to log in.
 * 
 * <li>OPTION / <br>
 * Returns the descriptions of known commands.
 * 
 * <li>PATCH /airships/{flightId} <br>
 * Change an Airship Coordinates and/or AirCorridor
 * 
 * <li>PATCH /users/{username} <br>
 * Change An User Password
 * 
 * <li>POST /airships/{type} <br>
 * Adds a new airship.
 * 
 * <li>POST /users <br>
 * Adds a new user.
 * 
 * <ul>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class App {
    
    
    // STATIC FIELDS & CONSTRUCTOR
    
    private static final Scanner SCANNER = new Scanner( System.in );
    private static final CommandParser COMMAND_PARSER = new CommandParser();
    private static final Map< String, String > COMMANDS_DESCRIPTION =
            new HashMap< String, String >();
    
    private static Database< User > usersDatabase;
    private static Database< Airship > airshipsDatabase;
    static {
        try {
            usersDatabase = new InMemoryUsersDatabase( "users database" );
            airshipsDatabase = new InMemoryAirshipsDatabase( "airships database" );
        }
        catch( InvalidArgumentException e ) {
            System.out.println( e.getMessage() );
            // never happens cause the strings given as arguments are non-null
        }
    }
    
    private static final String DELETE_METHOD = "DELETE";
    private static final String GET_METHOD = "GET";
    private static final String OPTION_METHOD = "OPTION";
    private static final String PATCH_METHOD = "PATCH";
    private static final String POST_METHOD = "POST";
    
    
    
    /**
     * Unused private constructor
     */
    private App() {
    
    }
    
    
    
    // METHODS
    
    /**
     * Execute the inputed {@code args} or {@code input}, see {@link #execute(String[])}.
     * 
     * @param args
     */
    public static void main( String[] args ) {
    
        // Register commands phase
        commandRegister();
        
        // App's behavior if arguments are given when app starts
        if( args.length != 0 )
            execute( args );
        
        
        // App's behavior if app starts with no arguments
        else {
            System.out.print( "> " );
            String input = SCANNER.nextLine();
            
            while( !input.equals( "EXIT" ) ) {
                execute( input.split( " " ) );
                System.out.print( "\n> " );
                input = SCANNER.nextLine();
            }
        }
    }
    
    
    /**
     * Registration phase
     * 
     * All the {@link Callable Commads} must be registered to be used by the user. Each
     * {@code Commad} are registered with it {@link CommandParser.Node path}.
     * 
     * No {@code Exception} should be catch!
     */
    private static void commandRegister() {
    
        try {
            
            // DELETE
            
            commandRegist( DELETE_METHOD, "/airships/{" + StringCommandsDictionary.FLIGHTID + "}",
                           new DeleteAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );
            
            // GET /airships
            
            commandRegist( GET_METHOD, "/airships",
                           new GetAllAirshipsInADatabaseCommandsFactory( airshipsDatabase ) );
            
            commandRegist( GET_METHOD, "/airships/{" + StringCommandsDictionary.FLIGHTID + "}",
                           new GetAirshipByFlightIdCommandsFactory( airshipsDatabase ) );
            
            commandRegist( GET_METHOD, "/airships/owner/{" + StringCommandsDictionary.OWNER + "}",
                           new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
            
            commandRegist( GET_METHOD, "/airships/nbPassengers/{"
                                       + StringCommandsDictionary.NUMBEROFPASSENGERS_UPPERLIMIT
                                       + "}/bellow",
                           new GetAirshipsWithLessPassengersThanCommandsFactory( airshipsDatabase ) );
            
            commandRegist( GET_METHOD, "/airships/reports",
                           new GetAllTransgressingAirshipsCommandsFactory( airshipsDatabase ) );
            
            commandRegist( GET_METHOD, "/airships/reports/{" + StringCommandsDictionary.FLIGHTID
                                       + "}",
                           new CheckIfAirshipIsTransgressingCommandsFactory( airshipsDatabase ) );
            
            commandRegist( GET_METHOD, "/airships/find",
                           new GetAirshipsCloserToCommandsFactory( airshipsDatabase ) );
            // GET /users
            
            commandRegist( GET_METHOD, "/users/authenticate",
                           new AuthenticateCommandFactory( usersDatabase, usersDatabase ) );
            
            commandRegist( GET_METHOD, "/users",
                           new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
            
            commandRegist( GET_METHOD, "/users/{" + StringCommandsDictionary.USERNAME + "}",
                           new GetUserByUsernameCommandsFactory( usersDatabase ) );
            
            // OPTION
            
            commandRegist( OPTION_METHOD, "/", new HelpCommandsFactory( COMMANDS_DESCRIPTION ) );
            
            // PATCH
            
            commandRegist( PATCH_METHOD, "/users/{" + StringCommandsDictionary.USERNAME + "}",
                           new PatchUserPasswordCommandsFactory( usersDatabase ) );
            
            commandRegist( PATCH_METHOD, "/airships/{" + StringCommandsDictionary.FLIGHTID + "}",
                           new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );
            
            // POST
            
            commandRegist( POST_METHOD, "/users", new PostUserCommandsFactory( usersDatabase,
                                                                               usersDatabase ) );
            
            commandRegist( POST_METHOD,
                           "/airships/{" + StringCommandsDictionary.AIRSHIP_TYPE + "}",
                           new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );
            
        }
        catch( InvalidArgumentException e ) {
            System.out.println( e.getMessage() );
            // never happens cause usersDatabase and airshipsDatabase are not
            // null
        }
    }
    
    private static void commandRegist( String method, String path,
                                       CommandFactory< ? > commandFactory ) {
    
        try {
            COMMAND_PARSER.registerCommand( method, path, commandFactory );
            COMMANDS_DESCRIPTION.put( new StringBuilder( method ).append( " " ).append( path )
                                                                 .toString(),
                                      commandFactory.getCommandsDescription() );
        }
        catch( InvalidRegisterException e ) {
            System.out.println( e.getMessage() );
        }
    }
    
    
    /**
     * Execute phase:
     * <ul>
     * <li>1 - {@link StringCommandsExecutor#getCommand()}: Get the {@link Callable command} by the
     * given {@code args}, and execute it.
     * <li>2 - The received information by the {@code command} is
     * {@link ToTranslatableConverter#convert converted} to a {@link Translatable}.
     * <li>3 - {@link StringCommandsExecutor#getTranslator()}: Get the {@link Translator} specified
     * in the {@code args}.Then the {@code Translatable} is {@link Translator#encode() encoded} (to
     * plain text, json or html).
     * <li>4 - {@link StringCommandsExecutor#getStream()}: Get the path specified in the
     * {@code args} where the received information by the command will be written. If no path is
     * given then the info is written in the commandLine.
     * </ul>
     * 
     * @param args
     */
    private static void execute( String[] args ) {
    
        try {
            StringCommandsExecutor parser = new StringCommandsExecutor( COMMAND_PARSER, args );
            parser.writeOutputToStream();
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
    
}
