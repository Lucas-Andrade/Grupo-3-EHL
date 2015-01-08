package main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * A {@link StringsToCommandsFactory factory} that creates commands to get an airship
 * with a certain flightId from an airship database. Commands are
 * {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipByFlightIdCommandsFactory extends
		GetElementByIdentificationCommandsFactory< Airship >
{
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetAirshipByFlightIdCommandFactory} that produces
	 * commands to get an airship with a certain flightId from {@code database}.
	 * That flightId is the value of the parameter with key
	 * {@link CLIStringsDictionary#FLIGHTID} received in the
	 * parameters map.
	 * 
	 * @param database
	 *            The database where to get the airship from.
	 * @throws InvalidArgumentException
	 *             If {@code database} is {@code null}.
	 */
	public GetAirshipByFlightIdCommandsFactory( Database< Airship > database )
			throws InvalidArgumentException {
		
		super( "Gets an airship with a certain flightId.",
				CLIStringsDictionary.FLIGHTID, database );
	}
}