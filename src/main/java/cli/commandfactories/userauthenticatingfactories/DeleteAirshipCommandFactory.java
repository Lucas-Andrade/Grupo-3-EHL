package main.java.cli.commandfactories.userauthenticatingfactories;

import java.util.concurrent.Callable;

import main.java.cli.StringsDictionary;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.users.User;

public class DeleteAirshipCommandFactory extends UserAuthenticatingFactory<Airship, String> {

	private final Database<Airship> airshipDatabase;

	private final String[] requiredParameters;

	public DeleteAirshipCommandFactory(Database<User> usersDatabase,
			Database<Airship> airshipDatabase) throws InvalidArgumentException {

		super("Delete An Airship", usersDatabase, airshipDatabase);

		this.airshipDatabase = airshipDatabase;

		this.requiredParameters = new String[] {StringsDictionary.FLIGHTID};
	}

	@Override
	protected Callable<String> internalInternalNewInstance(User userWhoIsPosting)
			throws InternalErrorException, MissingRequiredParameterException,
			InvalidParameterValueException {

		try {
			return new DeleteAirshipCommand(airshipDatabase, StringsDictionary.FLIGHTID);

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}

	@Override
	protected String[] getSpecificRequiredParameters() {

		return requiredParameters;
	}
}