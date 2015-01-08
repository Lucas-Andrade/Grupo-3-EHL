package main.java.cli.parsingtools.commandfactories.getfactories.getallfactories;


import java.util.concurrent.Callable;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * A {@link StringsToCommandsFactory factory} that creates commands to get all the
 * airships in an airships database. Commands are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllAirshipsInADatabaseCommandsFactory extends
		GetAllElementsInADatabaseCommandsFactory< Airship >
{
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetAllAirshipsFromADatabaseCommandFactory factory}
	 * that produces commands to get all the airships in {@code database}.
	 * 
	 * @param database
	 *            The database where to get the airships from.
	 * @throws InvalidArgumentException
	 *             If {@code database} is {@code null}.
	 */
	public GetAllAirshipsInADatabaseCommandsFactory( Database< Airship > database )
			throws InvalidArgumentException {
		
		super( "Gets the list of all airships.", database );
	}
}