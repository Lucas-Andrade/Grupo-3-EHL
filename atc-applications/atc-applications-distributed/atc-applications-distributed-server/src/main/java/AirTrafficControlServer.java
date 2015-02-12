import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletMapping;
import parsingtools.CommandParser;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.HelpCommandsFactory;
import parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import parsingtools.commandfactories.getfactories.GetAllTransgressingAirshipsCommandsFactory;
import parsingtools.commandfactories.getfactories.GetTheNearestAirshipsToGeographicPositionCommandsFactory;
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
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import databases.Database;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import exceptions.InvalidArgumentException;


/**
 * An AirTrafficControl Server. All available commands are resisted first.
 * 
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
public class AirTrafficControlServer {
    
    // Public Static Fields
    
    /**
     * {@code CMD_Parser} - This class command parser where all the commands will be registed.
     */
    public static final CommandParser CMD_PARSER = new CommandParser();
    
    // Private Static Fields
    
    /**
     * {@code LISTEN_PORT} - The server port.
     */
    private static final int LISTEN_PORT = 8081;
    
    /**
     * The databases where all the elements created by the commands will be stored.
     */
    private static Database< User > usersDatabase;
    private static Database< Airship > airshipsDatabase;
    
    /**
     * {@code commandsDescription} - The {@link Map} that will contain the description of all the
     * commands registed in the command parser. This {@link #commandsDescription Map} will have as
     * key the method and path of each command and as values the corresponding command descriptions.
     */
    private static final Map< String, String > commandsDescription =
            new HashMap< String, String >();
    
    // Static Fields Constructor
    
    /**
     * Static field constructor where the databases will be created and initialized.
     */
    static {
        try {
            usersDatabase = new InMemoryUsersDatabase( "Users Database" );
            airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        }
        catch( InvalidArgumentException e ) {
            System.out.println( e.getMessage() );
            // never happens cause the strings given as arguments are non-null
        }
    }
    
    // Private Constructor
    
    /**
     * Unused private constructor
     */
    private AirTrafficControlServer() {
    
    }
    
    // Main Methods
    
    /**
     * Main method that will regist all the commands in the {@link #CMD_PARSER} and start the
     * server.
     * 
     * @param args
     */
    public static void main( String[] args ) {
    
        registerCommands();
        
        Server server = new Server( LISTEN_PORT );
        
        startServer( server );
    }
    
    // Private Auxilar Methods
    
    /**
     * Auxiliar private method called by this class {@link #main(String[])} method as the
     * registration phase of all the implemented {@link Callable Commads}.
     * 
     * Each {@code Commad} is registed with its {@link CommandParser.Node path} and
     * {@link CommandFactory}.
     * 
     * No {@link Exception} should be catched.
     */
    private static void registerCommands() {
    
        try {
            
            // DELETE
            
            registCommand( "DELETE", "/airships/{flightId}",
                           new DeleteAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );
            
            // GET /airships
            
            registCommand( "GET", "/airships",
                           new GetAllAirshipsInADatabaseCommandsFactory( airshipsDatabase ) );
            
            registCommand( "GET", "/airships/{flightId}",
                           new GetAirshipByFlightIdCommandsFactory( airshipsDatabase ) );
            
            registCommand( "GET", "/airships/owner/{owner}",
                           new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
            
            registCommand( "GET", "/airships/nbPassengers/{nbP}/bellow",
                           new GetAirshipsWithLessPassengersThanCommandsFactory( airshipsDatabase ) );
            
            registCommand( "GET", "/airships/reports",
                           new GetAllTransgressingAirshipsCommandsFactory( airshipsDatabase ) );
            
            registCommand( "GET", "/airships/reports/{flightId}",
                           new CheckIfAirshipIsTransgressingCommandsFactory( airshipsDatabase ) );
            
            registCommand( "GET",
                           "/airships/find",
                           new GetTheNearestAirshipsToGeographicPositionCommandsFactory(
                                                                                         airshipsDatabase ) );
            // GET /users
            
            registCommand( "GET", "/users/authenticate",
                           new AuthenticateCommandFactory( usersDatabase, usersDatabase ) );
            
            registCommand( "GET", "/users",
                           new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
            
            registCommand( "GET", "/users/{username}",
                           new GetUserByUsernameCommandsFactory( usersDatabase ) );
            
            // OPTION
            
            registCommand( "OPTION", "/", new HelpCommandsFactory( commandsDescription ) );
            
            // PATCH
            
            registCommand( "PATCH", "/users/{username}",
                           new PatchUserPasswordCommandsFactory( usersDatabase ) );
            
            registCommand( "PATCH", "/airships/{flightId}",
                           new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );
            
            // POST
            
            registCommand( "POST", "/users", new PostUserCommandsFactory( usersDatabase,
                                                                          usersDatabase ) );
            
            registCommand( "POST", "/airships/{type}",
                           new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ) );
            
        }
        catch( InvalidArgumentException e ) {
            System.out.println( e.getMessage() );
            // never happens cause usersDatabase and airshipsDatabase are not
            // null
        }
    }
    
    /**
     * Auxiliar method called by {@link #registerCommands()} to regist each of the commands
     * individually and to add to the {@link #commandsDescription} {@link Map} the command
     * description associated with each specific command.
     * 
     * @param method
     *            - The String with the command method.
     * @param path
     *            - The String with the path to the command.
     * @param commandFactory
     *            - The {@link CommandFactory} to be used to create the specific command associated
     *            with the given method and path.
     */
    private static void registCommand( String method, String path,
                                       CommandFactory< ? > commandFactory ) {
    
        try {
            CMD_PARSER.registerCommand( method, path, commandFactory );
            commandsDescription.put( new StringBuilder( method ).append( " " ).append( path )
                                                                .toString(),
                                     commandFactory.getCommandsDescription() );
        }
        catch( InvalidRegisterException e ) {
            System.out.println( e.getMessage() );
        }
    }
    
    /**
     * Auxiliar private method called by this class {@link #main(String[])} method to start the
     * server and associate to it a {@link ServletHandler} with a {@link ServletMapping} of
     * {@link AirTrafficControlServelet}.
     * 
     * @param server
     */
    private static void startServer( Server server ) {
    
        ServletHandler handler = new ServletHandler();
        server.setHandler( handler );
        
        handler.addServletWithMapping( AirTrafficControlServelet.class, "/*" );
        
        try {
            server.start();
            System.out.println( "Server Started!!! YEY!!!" );
        }
        catch( Exception e ) {
        }
    }
}
