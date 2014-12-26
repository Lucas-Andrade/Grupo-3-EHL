package main.java.cli.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.PlaceholdersAndParametersDictionary;
import main.java.cli.commandfactories.CallablesFactory;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.getcommands.CheckIfAirshipIsTransgressingCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce
 * commands of type {@link CheckIfAirshipIsTransgressingCommand}. Commands are
 * {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommandsFactory extends
		StringsToCommandsFactory< Optional< Airship > >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The array of strings with the names of the parameters needed to produce
	 * the command.
	 */
	private final String[] requiredParametersNames;
	
	/**
	 * The database where to search.
	 */
	private final Database< Airship > database;
	
	/**
	 * The flightId of the airships to be evaluated.
	 */
	private String flightId;
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link CheckIfAirshipIsTransgressingCommandFactory} that
	 * produces commands of type {@link CheckIfAirshipIsTransgressingCommand}.
	 * 
	 * @param database
	 *            The database where to search.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public CheckIfAirshipIsTransgressingCommandsFactory(
			Database< Airship > database ) throws InvalidArgumentException {
		
		super( "Checks whether an airship is transgressing its air corridor." );
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null database!" );
		
		this.requiredParametersNames = new String[]{ PlaceholdersAndParametersDictionary.FLIGHTID };
		this.database = database;
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Returns a command of type {@link CheckIfAirshipIsTransgressingCommand}.
	 * 
	 * @return A command of type {@link CheckIfAirshipIsTransgressingCommand}.
	 */
	@Override
	protected Callable< Optional< Airship >> internalNewInstance() {
		
		getFlightIdValueOfTheParametersMap();
		try
		{
			return new CheckIfAirshipIsTransgressingCommand( database, flightId );
		}
		catch( InvalidArgumentException e )
		{ // never happens because database is not null
			return null;
		}
	}
	
	/**
	 * Returns an array of strings with name of the parameter needed to produce
	 * the command: the name of the parameter that contains the owner's
	 * username.
	 * 
	 * @return An array of strings with the name of the parameter that contains
	 *         the the owner's username.
	 */
	@Override
	protected String[] getRequiredParameters() {
		
		return requiredParametersNames;
	}
	
	
	
	// AUXILIARY PRIVATE METHODS
	/**
	 * Sets the value of the field {@link #flightId} with the value received in
	 * the parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and
	 * this one is called inside
	 * {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that
	 * the field {@link #flightId} is non-{@code null} after this method
	 * finishes its job.
	 * </p>
	 */
	private void getFlightIdValueOfTheParametersMap() {
		
		flightId = getParameterAsString( requiredParametersNames[0] );
	}
	
}
