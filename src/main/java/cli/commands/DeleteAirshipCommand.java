package main.java.cli.commands;

import java.util.concurrent.Callable;

import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;

public class DeleteAirshipCommand implements Callable<Optional<Airship>> {

	private final InMemoryAirshipsDatabase database;
	
	private final String identification;

	public DeleteAirshipCommand(InMemoryAirshipsDatabase database, String identification)
			throws InvalidArgumentException {

		if (database == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		if (identification == null)
			throw new InvalidArgumentException(
					"Cannot instantiate command with null identification.");

		this.database = database;
		this.identification = identification;
	}

	@Override
	public Optional<Airship> call() throws Exception {

		
		// TODO Auto-generated method stub
		return null;
	}
}