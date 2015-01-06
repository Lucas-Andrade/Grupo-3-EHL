package main.java.cli.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.CommandLineDictionary;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.getcommands.GetAirshipsWithLessPassengersThanCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce
 * commands of type {@link GetAirshipsWithLessPassengersThanCommand}. Commands
 * are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommandsFactory
	extends StringsToCommandsFactory< Optional< Iterable< Airship >> >
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
	private final InMemoryAirshipsDatabase database;

	/**
	 * The maximum number of passengers allowed.
	 */
	private int maximumNumberOfPassengers;


	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetAllElementsInADatabaseCommandFactory factory}
	 * that produces commands of type
	 * {@link GetAirshipsWithLessPassengersThanCommand}.
	 * 
	 * @param database
	 *        The database where to get the elements from.
	 * @throws InvalidArgumentException
	 *         If {@code database==null}.
	 */
	public GetAirshipsWithLessPassengersThanCommandsFactory( InMemoryAirshipsDatabase database )
		throws InvalidArgumentException
	{

		super( "Gets all airships that are transgressing their air corridors." );

		if( database == null )
			throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );

		this.requiredParametersNames = new String[]{ CommandLineDictionary.NUMBEROFPASSENGERS_UPPERLIMIT };
		this.database = database;
	}



	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory

	/**
	 * Returns a command of type
	 * {@link GetAirshipsWithLessPassengersThanCommand}.
	 * 
	 * @return A command of type
	 *         {@link GetAirshipsWithLessPassengersThanCommand}.
	 * @throws InvalidParameterValueException
	 *         If the value received to be interpreted as the maximum number of
	 *         passengers is not convertible to integer.
	 * @throws InvalidArgumentException
	 *         If {@code database==null} or {@code maximumNumberOfPassengers<0}.
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	@Override
	protected Callable< Optional< Iterable< Airship >>> internalNewInstance()
		throws InvalidParameterValueException, InvalidArgumentException, MissingRequiredParameterException
	{

		getMaxOfPassengersValueOfTheParametersMap();
		return new GetAirshipsWithLessPassengersThanCommand( database, maximumNumberOfPassengers );

	}

	/**
	 * Returns an array of strings with name of the parameters needed to produce
	 * the command: {@code null} because factories of this type need no
	 * parameters to create their commands.
	 * 
	 * @return {@code null}.
	 */
	@Override
	protected String[] getRequiredParameters()
	{

		return null;
	}



	// AUXILIARY PRIVATE METHODS
	/**
	 * Sets the value of the field {@link #maximumNumberOfPassengers} with the
	 * value received in the parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and
	 * this one is called inside
	 * {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that
	 * the field {@link #maximumNumberOfPassengers} is non-{@code null} after
	 * this method finishes its job.
	 * </p>
	 * 
	 * @throws InvalidParameterValueException
	 *         If the value received to be interpreted as the maximum number of
	 *         passengers is not convertible to integer.
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	private void getMaxOfPassengersValueOfTheParametersMap()
		throws InvalidParameterValueException, MissingRequiredParameterException
	{

		maximumNumberOfPassengers = getParameterAsInt( requiredParametersNames[ 0 ] );
	}

}
