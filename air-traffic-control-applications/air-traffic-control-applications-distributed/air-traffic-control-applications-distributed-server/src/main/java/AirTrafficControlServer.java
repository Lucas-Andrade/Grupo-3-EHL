import java.util.HashMap;
import java.util.Map;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
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


public class AirTrafficControlServer {
    
    private static final int LISTEN_PORT = 8081;
    
    public static final CommandParser CMD_PARSER = new CommandParser();
    
    private static Database< User > usersDatabase;
    private static Database< Airship > airshipsDatabase;
    
    private static final Map< String, String > commandsDescription =
            new HashMap< String, String >();
    
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
    
    public static void main( String[] args ) throws Exception {
    
        registerCommands();
        
        Server server = new Server( LISTEN_PORT );
        
        startServer( server );
    }
    
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
    
    private static void startServer( Server server ) throws Exception {
    
        ServletHandler handler = new ServletHandler();
        server.setHandler( handler );
        
        handler.addServletWithMapping( AirTrafficControlServelet.class, "/*" );
        
        server.start();
    }
}
