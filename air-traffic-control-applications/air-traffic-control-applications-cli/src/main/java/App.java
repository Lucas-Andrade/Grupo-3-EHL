
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

import outputformatters.Translatable;
import outputformatters.totranslatableconverters.ToTranslatableConverter;
import outputformatters.translators.Translator;
import parsingtools.*;
import parsingtools.commandfactories.*;
import parsingtools.commandfactories.getfactories.*;
import parsingtools.commandfactories.getfactories.getallfactories.*;
import parsingtools.commandfactories.getfactories.getbyidfactories.*;
import parsingtools.commandfactories.userauthenticatingfactories.*;
import utils.exceptions.formattersexceptions.UnknownTranslatableException;
import utils.exceptions.formattersexceptions.UnknownTypeException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import databases.*;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;


/**
 * AirTrafficControl Application for command line. All available commands are
 * resisted first. Then the user can use each command till it writes "EXIT" -
 * end of the program
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
public class App
{


    // STATIC FIELDS

    private static final CommandParser cmdParser = new CommandParser();
    private static final Scanner SCANNER = new Scanner( System.in );

    private static InMemoryUsersDatabase usersDatabase;
    private static InMemoryAirshipsDatabase airshipsDatabase;

    private static final Map< String, String > commandsDescription = new HashMap< String, String >();

    /**
     * Unused private constructor
     */
    private App()
    {

    }


    // STATIC CONSTRUCTOR

    static
    {
        try
        {
            usersDatabase = new InMemoryUsersDatabase( "users database" );
            airshipsDatabase = new InMemoryAirshipsDatabase( "airships database" );
        }
        catch( InvalidArgumentException e )
        {
            System.out.println( e.getMessage() );
            // never happens cause the strings given as arguments are non-null
        }
    }



    // METHODS

    /**
     * Execute the inputed {@code args} or {@code input}, see
     * {@link #execute(String[])}.
     * 
     * @param args
     */
    public static void main( String[] args )
    {

        // Register commands phase
        commandRegister();

        // App's behavior if arguments are given when app starts
        if( args.length != 0 )
            execute( args );


        // App's behavior if app starts with no arguments
        else
        {
            System.out.print( "> " );
            String input = SCANNER.nextLine();

            while( !input.equals( "EXIT" ) )
            {
                execute( input.split( " " ) );
                System.out.print( "\n> " );
                input = SCANNER.nextLine();
            }
        }
    }


    /**
     * Registration phase
     * 
     * All the {@link Callable Commads} must be registered to be used by the
     * user. Each {@code Commad} are registered with it
     * {@link CommandParser.Node path}.
     * 
     * No {@code Exception} should be catch!
     */
    private static void commandRegister()
    {

        try
        {

            // DELETE

            commandRegist( "DELETE", "/airships/{flightId}",
                           new DeleteAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );

            // GET /airships

            commandRegist( "GET", "/airships",
                           new GetAllAirshipsInADatabaseCommandsFactory( airshipsDatabase ) );
            commandRegist( "GET", "/airships/{flightId}",
                           new GetAirshipByFlightIdCommandsFactory( airshipsDatabase ) );
            commandRegist( "GET", "/airships/owner/{owner}",
                           new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
            commandRegist( "GET", "/airships/nbPassengers/{nbP}/bellow",
                           new GetAirshipsWithLessPassengersThanCommandsFactory( airshipsDatabase ) );
            commandRegist( "GET", "/airships/reports",
                           new GetAllTransgressingAirshipsCommandsFactory( airshipsDatabase ) );
            commandRegist( "GET", "/airships/reports/{flightId}",
                           new CheckIfAirshipIsTransgressingCommandsFactory( airshipsDatabase ) );
            commandRegist( "GET", "/airships/find",
                           new GetTheNearestAirshipsToGeographicPositionCommandsFactory( airshipsDatabase ) );
            // GET /users

            commandRegist( "GET", "/users", new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
            commandRegist( "GET", "/users/{username}", new GetUserByUsernameCommandsFactory( usersDatabase ) );

            // OPTION

            commandRegist( "OPTION", "/", new HelpCommandsFactory( commandsDescription ) );

            // PATCH

            commandRegist( "PATCH", "/users/{username}", new PatchUserPasswordCommandsFactory( usersDatabase ) );

            commandRegist( "PATCH", "/airships/{flightId}",
                           new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );

            // POST

            commandRegist( "POST", "/users", new PostUserCommandsFactory( usersDatabase, usersDatabase ) );

            commandRegist( "POST", "/airships/{type}", new PostAirshipCommandsFactory( usersDatabase,
                                                                                       airshipsDatabase ) );

        }
        catch( InvalidArgumentException e )
        {
            System.out.println( e.getMessage() );
            // never happens cause usersDatabase and airshipsDatabase are not
            // null
        }
    }

    private static void commandRegist( String method, String path, CommandFactory< ? > commandFactory )
    {

        try
        {
            cmdParser.registerCommand( method, path, commandFactory );
            commandsDescription.put( new StringBuilder( method ).append( " " ).append( path ).toString(),
                                     commandFactory.getCommandsDescription() );
        }
        catch( InvalidRegisterException e )
        {
            System.out.println( e.getMessage() );
        }
    }


    /**
     * Execute phase:
     * <ul>
     * <li>1 - {@link Parser#getCommand()}: Get the {@link Callable command} by
     * the given {@code args}, and execute it.
     * <li>2 - The received information by the {@code command} is
     * {@link ToTranslatableConverter#convert converted} to a
     * {@link Translatable}.
     * <li>3 - {@link Parser#getTranslator()}: Get the {@link Translator}
     * specified in the {@code args}.Then the {@code Translatable} is
     * {@link Translator#encode() encoded} (to plain text, json or html).
     * <li>4 - {@link Parser#getStream()}: Get the path specified in the
     * {@code args} where the received information by the command will be
     * written. If no path is given then the info is written in the commandLine.
     * </ul>
     * 
     * @param args
     */
    private static void execute( String[] args )
    {

        try
        {
            Parser parser = new Parser( cmdParser, args );
            Callable< ? > command = parser.getCommand();

            String output;
            try
            {
                Translatable intermediateRepr = ToTranslatableConverter.convert( command.call() );
                output = parser.getTranslator().encode( intermediateRepr );
            }
            catch( UnknownTypeException | UnknownTranslatableException e )
            {
                throw new InternalErrorException( e.getMessage() );
            }

            parser.getStream().print( output );
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage() );
        }
    }
}
