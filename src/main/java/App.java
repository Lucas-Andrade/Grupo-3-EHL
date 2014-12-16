package main.java;

import java.util.Scanner;

import main.java.commands.Command;
import main.java.commands.getAirshipsCommands.GetAirshipByIdCommand;
import main.java.commands.getAirshipsCommands.GetAirshipsByOwnerCommand;
import main.java.commands.getAirshipsCommands.GetAirshipsWithANumberOfPassengersBelowACertainThresholdCommand;
import main.java.commands.getAirshipsCommands.GetAllAirshipsCommand;
import main.java.commands.getAirshipsCommands.GetValidationOfAirshipsTransgressionCommand;
import main.java.commands.getAirshipsCommands.GetValidationOfTransgressionByFlightIdCommand;
import main.java.commands.getUsersCommands.GetAllUsersCommand;
import main.java.commands.getUsersCommands.GetUserByUsernameCommand;
import main.java.commands.postCommands.PostAirshipCommand;
import main.java.commands.postCommands.PostUserCommand;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.exceptions.commandParserExceptions.DuplicateParametersException;
import main.java.exceptions.commandParserExceptions.InvalidCommandParametersSyntaxException;
import main.java.exceptions.commandParserExceptions.InvalidRegisterException;
import main.java.exceptions.commandParserExceptions.UnknownCommandException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.airships.AirCorridor;
import main.java.model.airships.Airship;
import main.java.model.airships.InMemoryAirshipDatabase;
import main.java.model.users.InMemoryUserDatabase;
import main.java.model.users.User;

/**
 * AirTrafficControl Application for command line. All available commands are
 * resisted first. Then the user can use each command till it writes "EXIT" -
 * end of the program
 * <ul>
 * Command list:
 * 
 * <li>"GET /airships" - return all
 * {@link Airship}s info.
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
 * <li>"POST /users username={@value}&password={@value}&email={@value}&fullname={@value}&loginName={@value}&loginPassword={@value}"
 * - create a {@link User}, fullname is optional.
 * 
 * <li>"POST /airships/type latitude={@value}&longitude={@value}&altitude={@value}&minAltitude={@value}&maxAltitude={@value}&{@code airshipCharacteristic}={@value}&loginName={@value}&loginPassword={@value}"
 * - create an {@link Airship} with the type {@code Civil} or {@code Military}.
 * If the type:={@code Civil}, {@code airshipCharacteristic}:= nbPassengers.
 * If the type:={@code Military}, {@code airshipCharacteristic}:== hasArmour -> (yes or no).
 *<ul> 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class App
{

	private static String input;
	private static Command command;

	private static final CommandParser parser = new CommandParser();
	private static final Scanner scanner = new Scanner( System.in );
	private static final InMemoryUserDatabase userDatabase = new InMemoryUserDatabase();
	private static final InMemoryAirshipDatabase airshipDatabase = new InMemoryAirshipDatabase();

	public static void main( String[] args )
	{

		registerCommands();

		execute( args );

		input = scanner.nextLine();

		while( ! input.equals( "EXIT" ) )
		{

			execute( input.split( " " ) );

			input = scanner.nextLine();
		}
	}

	private static void registerCommands()
	{

		try
		{

			// Register Get Users
			parser.registerCommand( "GET", "/users",
					new GetAllUsersCommand.Factory( userDatabase ) );
			parser.registerCommand( "GET", "/users/{username}",
					new GetUserByUsernameCommand.Factory( userDatabase ) );

			// Register Get Airships
			parser.registerCommand( "GET", "/airships",
					new GetAllAirshipsCommand.Factory( airshipDatabase ) );
			parser.registerCommand( "GET", "/airships/{flightId}",
					new GetAirshipByIdCommand.Factory( airshipDatabase ) );
			parser.registerCommand( "GET", "/airships/owner/{owner}",
					new GetAirshipsByOwnerCommand.Factory( airshipDatabase,
							userDatabase ) );
			parser.registerCommand(
					"GET",
					"/airships/nbPassengers/{nbP}/bellow",
					new GetAirshipsWithANumberOfPassengersBelowACertainThresholdCommand.Factory(
							airshipDatabase ) );
			parser.registerCommand( "GET", "/airships/reports",
					new GetValidationOfAirshipsTransgressionCommand.Factory(
							airshipDatabase ) );
			parser.registerCommand( "GET", "/airships/reports/{flightId}",
					new GetValidationOfTransgressionByFlightIdCommand.Factory(
							airshipDatabase ) );

			// Register Posts For Users
			parser.registerCommand( "POST", "/users",
					new PostUserCommand.Factory( userDatabase, userDatabase ) );

			// Register Posts For Airships
			parser.registerCommand( "POST", "/airships/{type}",
					new PostAirshipCommand.Factory( userDatabase,
							airshipDatabase ) );

		}
		catch( InvalidRegisterException e )
		{

			System.out.println( e.getMessage() );
		}
	}

	private static void execute( String[] args )
	{

		try
		{

			command = parser.getCommand( args );
			command.execute();
			System.out.println( command.getResult() );

		}
		catch( UnknownCommandException | DuplicateParametersException
				| InvalidCommandParametersSyntaxException
				| NoSuchElementInDatabaseException | CommandException e )
		{

			System.out.println( e.getMessage() );
		}
	}
}
