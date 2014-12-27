package main.java.cli;


import java.util.Scanner;
import java.util.concurrent.Callable;
import main.java.cli.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import main.java.cli.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import main.java.cli.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import main.java.cli.commandfactories.getfactories.GetAllTransgressorAirshipsCommandsFactory;
import main.java.cli.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import main.java.cli.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import main.java.cli.commandfactories.postfactories.PostAirshipCommandsFactory;
import main.java.cli.commandfactories.postfactories.PostUserCommandsFactory;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.model.airships.AirCorridor;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;


/**
 * AirTrafficControl Application for command line. All available commands are
 * resisted first. Then the user can use each command till it writes "EXIT" -
 * end of the program
 * <ul>
 * Command list:
 * 
 * <li>"GET /airships" - return all {@link Airship}s info.
 * 
 * <li>"GET /airships/flightId" - return a {@link Airship} info with the given
 * {@code flightId}
 * 
 * <li>"GET /airships/owner/owner" - return all {@link Airship}s info inserted
 * by the {@link User} with the userName {@code owner}
 * 
 * <li>"GET /airships/nbPassengers/nbP/bellow" - return all {@link Airship}s
 * info with passengers number under the given number {@code nbP}
 * 
 * <li>"GET /airships/reports" - return all {@link Airship}s info that are
 * transgressing the {@link AirCorridor}
 * 
 * <li>"GET /airships/reports/flightId" - return a {@link Airship} with the
 * given {@code flightId} info that are transgressing the {@link AirCorridor}
 * 
 * <li>"POST /users username={@value}&password={@value}&email={@value}
 * &fullname={@value} &loginName={@value}&loginPassword={@value}" - create a
 * {@link User}, fullname is optional.
 * 
 * <li>"POST /airships/type latitude={@value}&longitude={@value}
 * &altitude={@value} &minAltitude={@value}&maxAltitude={@value}&
 * {@code airshipCharacteristic}={@value}&loginName={@value}&loginPassword={@value}
 * ={@value}&loginName={@value}&loginPassword={@value}" - create an
 * {@link Airship} with the type {@code Civil} or {@code Military}. If the
 * type:={@code Civil}, {@code airshipCharacteristic}:= nbPassengers. If the
 * type:={@code Military}, {@code airshipCharacteristic}:== hasArmour -> (yes or
 * no).
 * <ul>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class App
{
	
	private static final CommandParser cmdParser = new CommandParser();
	private static final Scanner scanner = new Scanner( System.in );
	
	private static InMemoryUsersDatabase usersDatabase;
	private static InMemoryAirshipsDatabase airshipsDatabase;
	static
	{// static block needed to catch exceptions while initializing databases
		try
		{
			usersDatabase = new InMemoryUsersDatabase( "users database" );
			airshipsDatabase = new InMemoryAirshipsDatabase(
					"airships database" );
		}
		catch( InvalidArgumentException e )
		{// never happens cause the strings given as arguments are non-null
			System.out.println( e.getMessage() );
		}
	}
	
	
	
	public static void main( String[] args ) {
		
		
		// Register commands phase
		registerCommands();
		
		
		// App's behavior if arguments are given in app's execution command
		if( args.length !=0 )
			execute( args );
		
		
		// App's behavior if app is executed with no arguments
		else
		{
			System.out.print( "> " );
			String input = scanner.nextLine();
			
			while( !input.equals( "EXIT" ) )
			{
				
				execute( input.split( " " ) );
				System.out.print( "\n> " );
				input = scanner.nextLine();
			}
		}
	}
	
	
	
	private static void registerCommands() {
		
		try
		{
			// Register Get Users
			cmdParser.registerCommand( "GET", "/users",
					new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
			cmdParser.registerCommand( "GET", "/users/{username}",
					new GetUserByUsernameCommandsFactory( usersDatabase ) );
			
			// Register Get Airships
			cmdParser.registerCommand( "GET", "/airships",
					new GetAllAirshipsInADatabaseCommandsFactory(
							airshipsDatabase ) );
			cmdParser
					.registerCommand( "GET", "/airships/{flightId}",
							new GetAirshipByFlightIdCommandsFactory(
									airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/owner/{owner}",
					new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
			cmdParser.registerCommand( "GET",
					"/airships/nbPassengers/{nbP}/bellow",
					new GetAirshipsWithLessPassengersThanCommandsFactory(
							airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/reports",
					new GetAllTransgressorAirshipsCommandsFactory(
							airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/reports/{flightId}",
					new CheckIfAirshipIsTransgressingCommandsFactory(
							airshipsDatabase ) );
			
			// Register Posts For Users
			cmdParser
					.registerCommand( "POST", "/users",
							new PostUserCommandsFactory( usersDatabase,
									usersDatabase ) );
			
			// Register Posts For Airships
			cmdParser.registerCommand( "POST", "/airships/{type}",
					new PostAirshipCommandsFactory( usersDatabase,
							airshipsDatabase ) );
			
		}
		catch( InvalidRegisterException e )
		{
			System.out.println( e.getMessage() );
		}
		catch( InvalidArgumentException e )
		{// never happens cause usersDatabase and airshipsDatabase are not null
			System.out.println( e.getMessage() );
		}
	}
	
	//TODO
	private static void execute( String[] args ) {
		try
		{
			Parser parser = new Parser(cmdParser, args);
			Callable< ? > command = parser.getCommand( args );
			System.out.println( command.call() );
			//TODO
		}
		catch( Exception e )
		{
			System.out.println(e.getMessage());
		}
	}
	
}