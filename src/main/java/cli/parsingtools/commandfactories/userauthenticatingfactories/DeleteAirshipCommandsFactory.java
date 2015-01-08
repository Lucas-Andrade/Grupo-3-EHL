package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;

import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.domain.commands.DeleteAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;

public class DeleteAirshipCommandsFactory extends UserAuthenticatingFactory<Airship, String> {

	private final Database<Airship> airshipDatabase;

	private final String[] requiredParameters;
	
	private String flightId;

	public DeleteAirshipCommandsFactory(Database<User> usersDatabase,
			Database<Airship> airshipDatabase) throws InvalidArgumentException {

		super("Delete An Airship", usersDatabase, airshipDatabase);

		this.airshipDatabase = airshipDatabase;

		this.requiredParameters = new String[] {CLIStringsDictionary.FLIGHTID};
	}

	@Override
	protected Callable<String> internalInternalNewInstance(User userWhoIsPosting) {

		getFlightId();
		
		try {
			return new DeleteAirshipCommand(airshipDatabase, flightId);

		} catch (InvalidArgumentException e)
		{// never happens for databaseWhereToPost is not null
			return null;
		}
	}

	@Override
	protected String[] getSpecificRequiredParameters() {

		return requiredParameters;
	}
	
	private void getFlightId(){
		
		
		flightId = getParameterAsString(requiredParameters[0]);
	}
	
	
	
}