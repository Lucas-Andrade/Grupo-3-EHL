package main.java.domain.commands;

import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;

public class DeleteAirshipCommand implements Callable<String> {

	private final Database<Airship> airshipDatabase;
	
	private final String identification;

	public DeleteAirshipCommand(Database<Airship> airshipDatabase, String identification)
			throws InvalidArgumentException {

		if (airshipDatabase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		if (identification == null)
			throw new InvalidArgumentException(
					"Cannot instantiate command with null identification.");

		this.airshipDatabase = airshipDatabase;
		this.identification = identification;
	}

	@Override
	public String call() throws DatabaseException {

		try
		{
			if(airshipDatabase.removeByIdentification(identification))
				return "Airship successfully removed";
		}
		catch( InvalidArgumentException e )
		{//never happens because identification is non null
		}

		return "Airship doesn't exist in the database";
	}
}