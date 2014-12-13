package airtrafficcontrol.app.appforcommandline;

import java.util.Scanner;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipByIdCommand;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipsByOwnerCommand;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipsWithMinimumPassengersCommand;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAllAirshipsCommand;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetReportOfTransgressionByIdCommand;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetTransgressingAirshipsCommand;
import airtrafficcontrol.app.appforcommandline.commands.getuserscommands.GetAllUsersCommand;
import airtrafficcontrol.app.appforcommandline.commands.getuserscommands.GetUserByUsernameCommand;
import airtrafficcontrol.app.appforcommandline.commands.postcommands.PostAirshipCommand;
import airtrafficcontrol.app.appforcommandline.commands.postcommands.PostUserCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.DuplicateParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.UnknownCommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;

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
					new GetAirshipsByOwnerCommand.Factory(airshipDatabase));
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
