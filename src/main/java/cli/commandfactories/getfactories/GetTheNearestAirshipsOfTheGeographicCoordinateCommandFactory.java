package main.java.cli.commandfactories.getfactories;

import java.util.concurrent.Callable;

import main.java.cli.Optional;
import main.java.cli.StringsDictionary;
import main.java.cli.commandfactories.CallablesFactory;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.getcommands.GetTheNearestAirshipsOfTheGeographicCoordinateCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;

/**
 * Class whose instances are {@link CallablesFactory factories} that produce
 * commands of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}
 * . Commands are {@link Callable} instances.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetTheNearestAirshipsOfTheGeographicCoordinateCommandFactory
	extends StringsToCommandsFactory< Optional< Iterable< Airship >>>
{


	// INSTANCE FIELDS

	/**
	 * The database of airships.
	 */

	private final InMemoryAirshipsDatabase airshipsDatabase;

	/**
	 * An array of Strings with all required parameters needed to construct the
	 * command.
	 */
	private final String[] requiredParameters;


	// CONSTRUCTOR

	/**
	 * Constructor of
	 * {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommandFactory} that
	 * produces {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}.
	 * 
	 * @param airshipsDatabase
	 *        The airships Database who stores all the elements.
	 * @throws InvalidArgumentException
	 *         If {@code database==null}.
	 */
	public GetTheNearestAirshipsOfTheGeographicCoordinateCommandFactory(
			InMemoryAirshipsDatabase airshipsDatabase ) throws InvalidArgumentException
	{

		super( "Get the nearest aircrafts of Geographic coordinates" );

		if( airshipsDatabase == null )
			throw new InvalidArgumentException(
					"It's not allow instantiate a factory with null airships database" );

		this.airshipsDatabase = airshipsDatabase;
		this.requiredParameters =
				new String[]{ StringsDictionary.LATITUDE, StringsDictionary.LONGITUDE,
						StringsDictionary.NUMBEROFAIRSHIPSTOFIND };

	}

	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory

	/**
	 * 
	 * Returns a command of type
	 * {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}.
	 * 
	 * @return a command of type
	 *         {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}.
	 * @throws InvalidArgumentException
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	@Override
	protected Callable< Optional< Iterable< Airship > > > internalNewInstance()
		throws InvalidParameterValueException, InvalidArgumentException, MissingRequiredParameterException
	{

		return new GetTheNearestAirshipsOfTheGeographicCoordinateCommand( airshipsDatabase,
				getNumberOfAirshipsTofind(), getLatitude(), getLongitude() );

	}

	/**
	 * Method responsible to return a array who contains all the required
	 * parameters Needed to create a
	 * {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand} command.
	 * 
	 * @return a array with required parameters.
	 * 
	 */
	@Override
	protected String[] getRequiredParameters()
	{

		return requiredParameters;

	}

	// AUXILIARY PRIVATE METHODS

	/**
	 * 
	 * 
	 * Method responsible to set the number of airships to get field needed to
	 * {@code PatchUserPasswordCommands} command.
	 * 
	 * This method calls the
	 * {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the desired
	 * number of airships.
	 * 
	 * @return a int with the desired number of airships value.
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted to an integer value
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	private int getNumberOfAirshipsTofind()
		throws MissingRequiredParameterException, InvalidParameterValueException
	{

		return getParameterAsInt( requiredParameters[ 2 ] );
	}

	/**
	 * 
	 * Method responsible to set the airships longitude field needed to
	 * {@code PatchUserPasswordCommands} command.
	 * 
	 * This method calls the
	 * {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the airships
	 * longitude.
	 * 
	 * @return a double with the desired number of airships longitude value.
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted to an integer value
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	private double getLongitude() throws InvalidParameterValueException, MissingRequiredParameterException
	{

		return getParameterAsDouble( requiredParameters[ 1 ] );
	}

	/**
	 * 
	 * Method responsible to set the airships latitude field needed to
	 * {@code PatchUserPasswordCommands} command.
	 * 
	 * This method calls the
	 * {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the airships
	 * latitude.
	 * 
	 * @return a double with the desired number of airships latitude value.
	 *         If the String can not be converted to an integer value
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	private double getLatitude() throws InvalidParameterValueException, MissingRequiredParameterException
	{

		return getParameterAsDouble( requiredParameters[ 0 ] );
	}



}
