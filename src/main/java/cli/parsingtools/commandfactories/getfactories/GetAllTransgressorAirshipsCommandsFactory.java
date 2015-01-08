package main.java.cli.parsingtools.commandfactories.getfactories;

import java.util.concurrent.Callable;

import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.GetAllTransgressorAirshipsCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands of
 * type {@link GetAllTransgressorAirshipsCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Optional} {@link Iterable
 * Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressorAirshipsCommandsFactory extends
		StringsToCommandsFactory<Optional<Iterable<Airship>>> {

	// INSTANCE FIELDS

	/**
	 * {@code airshipsDatabase} - The database where to search the elements from.
	 */
	private final InMemoryAirshipsDatabase airshipsDatabase;

	// CONSTRUCTOR

	/**
	 * Creates a new {@link GetAllElementsInADatabaseCommandFactory factory} that produces commands
	 * of type {@link GetAllTransgressorAirshipsCommand}.
	 * 
	 * @param airshipsDatabase
	 *            - The airshipsDatabase where to get the elements from.
	 * 
	 * @throws InvalidArgumentException
	 *             If the {@code airshipsDatabase} is null.
	 */
	public GetAllTransgressorAirshipsCommandsFactory(InMemoryAirshipsDatabase airshipsDatabase)
			throws InvalidArgumentException {

		super("Gets all airships that are transgressing their air corridors.");

		if (airshipsDatabase == null)
			throw new InvalidArgumentException("Cannot instantiate factory with null database!");

		this.airshipsDatabase = airshipsDatabase;
	}

	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory

	/**
	 * Returns a command of type {@link GetAllTransgressorAirshipsCommand}.
	 * 
	 * @return A command of type {@link GetAllTransgressorAirshipsCommand}.
	 */
	@Override
	protected Callable<Optional<Iterable<Airship>>> internalNewInstance() {

		try {
			return new GetAllTransgressorAirshipsCommand(airshipsDatabase);

		} catch (InvalidArgumentException e) { // Never happens because database is not null!
			return null;
		}
	}

	/**
	 * Returns an array of strings with the name of the parameters needed to produce the command -
	 * in this case it will return {@code null} because factories of this type need no parameters to
	 * create their commands.
	 * 
	 * @return {@code null}.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}