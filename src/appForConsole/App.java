package appForConsole;

import java.util.Scanner;

import appForConsole.commands.Command;
import appForConsole.commands.getAirshipsCommands.GetAirshipByIdCommand;
import appForConsole.commands.getAirshipsCommands.GetAirshipsByOwnerCommand;
import appForConsole.commands.getAirshipsCommands.GetAirshipsWithMinimumPassengersCommand;
import appForConsole.commands.getAirshipsCommands.GetAllAirshipsCommand;
import appForConsole.commands.getAirshipsCommands.GetReportOfTransgressionByIdCommand;
import appForConsole.commands.getAirshipsCommands.GetTransgressingAirshipsCommand;
import appForConsole.commands.getUsersCommands.GetAllUsersCommand;
import appForConsole.commands.getUsersCommands.GetUserByUsernameCommand;
import appForConsole.commands.postCommands.PostAirshipCommand;
import appForConsole.commands.postCommands.PostUserCommand;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.exceptions.commandParserExceptions.DuplicateParametersException;
import appForConsole.exceptions.commandParserExceptions.InvalidCommandParametersSyntaxException;
import appForConsole.exceptions.commandParserExceptions.InvalidRegisterException;
import appForConsole.exceptions.commandParserExceptions.UnknownCommandException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import appForConsole.model.airships.InMemoryAirshipDatabase;
import appForConsole.model.users.InMemoryUserDatabase;

public class App {

	private static String input;
	private static Command command;

	private static final CommandParser parser = new CommandParser();
	private static final Scanner scanner = new Scanner(System.in);
	private static final InMemoryUserDatabase userDatabase = new InMemoryUserDatabase();
	private static final InMemoryAirshipDatabase airshipDatabase = new InMemoryAirshipDatabase();

	public static void main(String[] args) {

		registerCommands();

		execute(args);

		input = scanner.nextLine();

		while (!input.equals("EXIT")) {

			execute(input.split(" "));

			input = scanner.nextLine();
		}
	}

	private static void registerCommands() {

		try {

			// Register Get Users
			parser.registerCommand("GET", "/users", new GetAllUsersCommand.Factory(userDatabase));
			parser.registerCommand("GET", "/users/{username}",
					new GetUserByUsernameCommand.Factory(userDatabase));

			// Register Get Airships
			parser.registerCommand("GET", "/airships", new GetAllAirshipsCommand.Factory(
					airshipDatabase));
			parser.registerCommand("GET", "/airships/{flightId}",
					new GetAirshipByIdCommand.Factory(airshipDatabase));
			parser.registerCommand("GET", "/airships/owner/{owner}",
					new GetAirshipsByOwnerCommand.Factory(airshipDatabase, userDatabase));
			parser.registerCommand("GET", "/airships/nbPassengers/{nbP}/bellow",
					new GetAirshipsWithMinimumPassengersCommand.Factory(airshipDatabase));
			parser.registerCommand("GET", "/airships/reports",
					new GetTransgressingAirshipsCommand.Factory(airshipDatabase));
			parser.registerCommand("GET", "/airships/reports/{flightId}",
					new GetReportOfTransgressionByIdCommand.Factory(airshipDatabase));

			// Register Posts For Users
			parser.registerCommand("POST", "/users", new PostUserCommand.Factory(userDatabase,
					userDatabase));

			// Register Posts For Airships
			parser.registerCommand("POST", "/airships/{type}", new PostAirshipCommand.Factory(
					userDatabase, airshipDatabase));

		} catch (InvalidRegisterException e) {

			System.out.println(e.getMessage());
		}
	}

	private static void execute(String[] args) {

		try {

			command = parser.getCommand(args);
			command.execute();
			System.out.println(command.getResult());

		} catch (UnknownCommandException | DuplicateParametersException
				| InvalidCommandParametersSyntaxException | NoSuchElementInDatabaseException
				| CommandException e) {

			System.out.println(e.getMessage());
		}
	}
}