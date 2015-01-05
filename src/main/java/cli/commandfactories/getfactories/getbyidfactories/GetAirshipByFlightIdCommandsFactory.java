package main.java.cli.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import main.java.cli.StringsDictionary;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;


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
	 * {@link StringsDictionary#FLIGHTID} received in the
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
				StringsDictionary.FLIGHTID, database );
	}
}