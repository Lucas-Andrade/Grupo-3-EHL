package main.java.cli;


import java.util.Scanner;
import java.util.concurrent.Callable;

import main.java.cli.outputformatters.Translatable;
import main.java.cli.outputformatters.totranslatableconversors.ToTranslatableConversor;
import main.java.cli.outputformatters.translators.Translator;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAllTransgressingAirshipsCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.GetTheNearestAirshipsToGeographicPositionCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.DeleteAirshipCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories.PatchAirshipCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories.PostAirshipCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories.PostUserCommandsFactory;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.formattersexceptions.UnknownTranslatableException;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;


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

	private static final CommandParser cmdParser = new CommandParser();
	private static final Scanner scanner = new Scanner( System.in );

	private static InMemoryUsersDatabase usersDatabase;
	private static InMemoryAirshipsDatabase airshipsDatabase;
	static
	{// static block needed to catch exceptions while initializing databases
		try
		{
			usersDatabase = new InMemoryUsersDatabase( "users database" );
			airshipsDatabase = new InMemoryAirshipsDatabase( "airships database" );
		}
		catch( InvalidArgumentException e )
		{// never happens cause the strings given as arguments are non-null
			System.out.println( e.getMessage() );
		}
	}


	/**
	 * Execute the inputed {@code args} or {@code input}, see
	 * {@link #execute(String[])}.
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{


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


	/**
	 * Registration phase
	 * 
	 * All the {@link Callable Commads} must be registered to be used by the
	 * user. Each {@code Commad} are registered with it
	 * {@link CommandParser.Node path}.
	 * 
	 * No {@code Exception} should be catch!
	 */
	private static void registerCommands()
	{

		try
		{

			// DELETE

			cmdParser.registerCommand( "DELETE", "/airships/{flightId}", new DeleteAirshipCommandsFactory(
					usersDatabase, airshipsDatabase ) );

			// GET /airships

			cmdParser.registerCommand( "GET", "/airships", new GetAllAirshipsInADatabaseCommandsFactory(
					airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/{flightId}",
					new GetAirshipByFlightIdCommandsFactory( airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/owner/{owner}",
					new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/nbPassengers/{nbP}/bellow",
					new GetAirshipsWithLessPassengersThanCommandsFactory( airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/reports",
					new GetAllTransgressingAirshipsCommandsFactory( airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/reports/{flightId}",
					new CheckIfAirshipIsTransgressingCommandsFactory( airshipsDatabase ) );
			cmdParser.registerCommand( "GET", "/airships/find",
					new GetTheNearestAirshipsToGeographicPositionCommandsFactory( airshipsDatabase ) );

			// GET /users

			cmdParser.registerCommand( "GET", "/users", new GetAllUsersInADatabaseCommandsFactory(
					usersDatabase ) );
			cmdParser.registerCommand( "GET", "/users/{username}", new GetUserByUsernameCommandsFactory(
					usersDatabase ) );

			// OPTION

			cmdParser.registerCommand( "OPTION", "/", new HelpCommandsFactory( cmdParser ) );

			// PATCH

			cmdParser.registerCommand( "PATCH", "/users/{username}", new PatchUserPasswordCommandsFactory(
					usersDatabase ) );

			cmdParser.registerCommand( "PATCH", "/airships/{flightId}", new PatchAirshipCommandsFactory(
					usersDatabase, airshipsDatabase ) );

			// POST

			cmdParser.registerCommand( "POST", "/users", new PostUserCommandsFactory( usersDatabase,
					usersDatabase ) );

			cmdParser.registerCommand( "POST", "/airships/{type}", new PostAirshipCommandsFactory(
					usersDatabase, airshipsDatabase ) );

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


	/**
	 * Execute phase:
	 * <ul>
	 * <li>1 - {@link Parser#getCommand()}: Get the {@link Callable command} by
	 * the given {@code args}, and execute it.
	 * <li>2 - The received information by the {@code command} is
	 * {@link ToTranslatableConversor#convert converted} to a
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
				Translatable intermediateRepr = ToTranslatableConversor.convert( command.call() );
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
