package main.java.cli;


import java.util.Scanner;
import java.util.concurrent.Callable;
import main.java.cli.model.airships.AirCorridor;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.outputformatters.totranslatableconversors.ToTranslatableConversor;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAllTransgressorAirshipsCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetTheNearestAirshipsOfTheGeographicCoordinateCommandFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories.PostAirshipCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories.PostUserCommandsFactory;
import main.java.cli.utils.exceptions.InternalErrorException;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTranslatableException;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


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
 * ={@value}&loginName={@value}&loginPassword={@value}={@value}
 * &loginName={@value}&loginPassword={@value} ={@value}&loginName={@value}
 * &loginPassword={@value}={@value}&loginName={@value}
 * &loginPassword={@value} ={@value}&loginName={@value}&loginPassword={@value}
 * ={@value} &loginName={@value}&loginPassword={@value} ={@value}
 * &loginName={@value} &loginPassword={@value} " - create an {@link Airship}
 * with the type {@code Civil} or {@code Military}. If the type:={@code Civil},
 * {@code airshipCharacteristic}:= nbPassengers. If the type:={@code Military},
 * {@code airshipCharacteristic}:== hasArmour -> (yes or no).</li>
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
		
		
		// App's behavior if arguments are given when app starts
		if( args.length != 0 )
			execute( args );
		
		
		// App's behavior if app starts with no arguments
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
			
			// DELETE
			
			// cmdParser.registerCommand( "DELETE", "/airships/{flightId}",
			// new DeleteAirshipCommandsFactory( usersDatabase,
			// airshipsDatabase ) );
			
			// GET /airships
			
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
			cmdParser
					.registerCommand(
							"GET",
							"/airships/find",
							new GetTheNearestAirshipsOfTheGeographicCoordinateCommandFactory(
									airshipsDatabase ) );
			
			// GET /users
			
			cmdParser.registerCommand( "GET", "/users",
					new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
			cmdParser.registerCommand( "GET", "/users/{username}",
					new GetUserByUsernameCommandsFactory( usersDatabase ) );
			
			// OPTION
			
			cmdParser.registerCommand( "OPTION", "/", new HelpCommandsFactory(
					cmdParser ) );
			
			// PATCH
			
			// cmdParser.registerCommand( "PATCH", "/users/{username}",
			// new PatchUserCommandsFactory( usersDatabase, usersDatabase ));
			
			// cmdParser.registerCommand( "PATCH", "/airships/{flightId}",
			// new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase
			// ));
			
			// POST
			
			cmdParser
					.registerCommand( "POST", "/users",
							new PostUserCommandsFactory( usersDatabase,
									usersDatabase ) );
			
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
	
	
	// TODO
	private static void execute( String[] args ) {
		
		try
		{
			
			Parser parser = new Parser( cmdParser, args );
			Callable< ? > command = parser.getCommand();
			
			
			String output;
			try
			{
				Translatable intermediateRepr = ToTranslatableConversor
						.convert( command.call() );
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
