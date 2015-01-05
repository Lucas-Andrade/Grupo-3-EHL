package main.java.cli.commands;

import java.util.concurrent.Callable;

import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;

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
	public String call() throws Exception {

		if(airshipDatabase.removeByIdentification(identification))
			return "Airship successfully removed";

		return "Airship doesn't exist in the database";
	}
}