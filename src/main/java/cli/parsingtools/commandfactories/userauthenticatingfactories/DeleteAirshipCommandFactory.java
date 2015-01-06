package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;

import java.util.concurrent.Callable;
import main.java.cli.commands.DeleteAirshipCommand;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.users.User;
import main.java.cli.utils.CommandLineStringsDictionary;
import main.java.cli.utils.exceptions.InvalidArgumentException;

public class DeleteAirshipCommandFactory extends UserAuthenticatingFactory<Airship, String> {

	private final Database<Airship> airshipDatabase;

	private final String[] requiredParameters;

	public DeleteAirshipCommandFactory(Database<User> usersDatabase,
			Database<Airship> airshipDatabase) throws InvalidArgumentException {

		super("Delete An Airship", usersDatabase, airshipDatabase);

		this.airshipDatabase = airshipDatabase;

		this.requiredParameters = new String[] {CommandLineStringsDictionary.FLIGHTID};
	}

	@Override
	protected Callable<String> internalInternalNewInstance(User userWhoIsPosting) {

		try {
			return new DeleteAirshipCommand(airshipDatabase, CommandLineStringsDictionary.FLIGHTID);

		} catch (InvalidArgumentException e)
		{// never happens for databaseWhereToPost is not null
			return null;
		}
	}

	@Override
	protected String[] getSpecificRequiredParameters() {

		return requiredParameters;
	}
}