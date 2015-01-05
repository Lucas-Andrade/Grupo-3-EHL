package main.java.cli.commandfactories;

import java.util.Map;
import java.util.concurrent.Callable;

import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;

/**
 * A {@link CallablesFactory factory} that creates commands by interpreting
 * string parameters. Commands are {@link Callable} instances.
 * <p>
 * Implementors must define the method {@link #newInstance(Map)} which returns a
 * {@link Callable} instance. The {@link Map} received contains the parameters
 * needed to create the {@link Callable} instance: each map-entry represents a
 * parameter, it has as key the parameter's name and as value the parameter's
 * value. Both key and value are {@link String}s, they are to be validated and
 * interpreted for creating the {@link Callable} instance.
 * </p>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <R>
 *        The type parameter of the {@link Callable} instance returned by the
 *        method {@link #newInstance(Map)}.
 * @see Callable
 * @see Map
 */
public abstract class StringsToCommandsFactory< R >
	implements CallablesFactory< R, String, String >
{

	// INSTANCE FIELDS

	/**
	 * The parameters name-value pairs received to execute the command.
	 */
	protected Map< String, String > parametersMap;

	/**
	 * A short description of the commands produced by this factory.
	 */
	private final String commandsDescription;

	// CONSTRUCTOR
	/**
	 * Creates a new {@link StringsToCommandsFactory factory} that produces
	 * commands to do the action described in {@code commandDescription}.
	 * 
	 * @param commandDescription
	 *        A short description of the commands produced by this factory.
	 * @throws InvalidArgumentException
	 *         If {@code commandDescription} is {@code null}.
	 */
	public StringsToCommandsFactory( String commandDescription ) throws InvalidArgumentException
	{

		if( commandDescription == null )
			throw new InvalidArgumentException( "Cannot instantiate factory without description!" );

		this.commandsDescription = commandDescription;
	}

	// IMPLEMENTATION OF METHODS INHERITED FROM CallablesFactory INTERFACE

	/**
	 * Returns the description of the commands produced by this factory.
	 * 
	 * @return The description of the commands produced by this factory.
	 */
	public String getCommandsDescription()
	{

		return commandsDescription;
	}

	/**
	 * Produces a command. It starts by performing some validation operations
	 * over the received parameters (seeing if there are required parameters
	 * missing in the map, interpreting the parameters into their correct type,
	 * authenticating users passwords, etc) and returns a {@link Callable}
	 * instance ready to be called.
	 * 
	 * @param parameters
	 *        The container of the parameters needed to create the
	 *        {@link Callable} instance.
	 * @return An instance of {@code Callable<R>}.
	 * @throws InvalidArgumentException
	 *         If {@code parameters==null} or the value received in the
	 *         parameters map for a required parameter is invalid.
	 * @throws NoSuchElementInDatabaseException
	 *         If there is no user in {@link #postingUsersDatabase} whose
	 *         username is the login name receive in the parameters map. The
	 *         message of this exception is <i>«{login name} not found in
	 *         {@code postingUsersDatabase.getDatabaseName()}»</i>.
	 * @throws InternalErrorException
	 *         If an internal error occurred (not supposed to happen).
	 * @throws MissingRequiredParameterException
	 *         If the received map does not contain one of the required
	 *         parameters for instantiating the command.
	 * @throws WrongLoginPasswordException
	 *         If the login password received does not match the login
	 *         username's password.
	 * @throws InvalidParameterValueException
	 *         If the value received in the parameters map for a required
	 *         parameter is invalid.
	 */
	public final Callable< R > newInstance( Map< String, String > parameters )
		throws NoSuchElementInDatabaseException, InternalErrorException, InvalidArgumentException,
		MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException
	{

		this.parametersMap = parameters;

		checkIfAllRequiredParametersAreInTheParametersMap( getRequiredParameters() );
		return internalNewInstance();

	}

	// AUXILIARY PRIVATE METHOD used in the method newInstance

	/**
	 * Checks whether the required parameters for performing the
	 * {@link #newInstance()} method (known through the method
	 * {@link #getRequiredParameters()}) are contained in the received
	 * {@link Map}. If all required parameters were found in the map, this
	 * method returns nothing. If a required parameter is missing, an exception
	 * is thrown indicating in its message the name of the first required
	 * parameter not found.
	 * 
	 * @param requiredParameterNames
	 *        The names of the required parameters.
	 * @throws MissingRequiredParameterException
	 *         If a required parameter is missing.
	 */
	private void checkIfAllRequiredParametersAreInTheParametersMap( String... requiredParameterNames )
		throws MissingRequiredParameterException
	{

		if( requiredParameterNames == null ){ return; }

		for( String name : requiredParameterNames )
			if( !parametersMap.containsKey( name ) )
				throw new MissingRequiredParameterException( name );

	}

	// AUXILIARY UNIMPLEMENTED METHODS
	// to be implemented in the factories

	/**
	 * Produces a command and returns it to the method {@link #newInstance()}.
	 * 
	 * @throws InvalidParameterValueException
	 *         If the value received in the parameters map for a required
	 *         parameter invalid (e.g. is not convertible to the expected type).
	 * @throws WrongLoginPasswordException
	 *         If the login password received does not match the login
	 *         username's password.
	 * @throws NoSuchElementInDatabaseException
	 *         If there is no user in {@link #postingUsersDatabase} whose
	 *         username is the login name receive in the parameters map. The
	 *         message of this exception is <i>«{login name} not found in
	 *         {@code postingUsersDatabase.getDatabaseName()}»</i>.
	 * @throws InternalErrorException
	 *         If an internal error that wasn't supposed to happen happened.
	 * @throws MissingRequiredParameterException
	 *         If the received map does not contain one of the required
	 *         parameters for instantiating the command.
	 * @throws InvalidArgumentException
	 *         If the value received in the parameters map for a required
	 *         parameter is invalid.
	 */
	protected abstract Callable< R > internalNewInstance()
		throws InvalidParameterValueException, WrongLoginPasswordException, NoSuchElementInDatabaseException,
		InternalErrorException, MissingRequiredParameterException, InvalidArgumentException;

	/**
	 * Returns an array of {@link String}s with the names of the parameters
	 * without whom the method {@link #newInstance()} cannot create a
	 * {@link Callable} instance.
	 * 
	 * @return An array with the names of the parameters without whom the method
	 *         {@link #newInstance()} cannot create a {@link Callable} instance.
	 */
	protected abstract String[] getRequiredParameters();

	// AUXILIARY PROTECTED METHODS
	// tools for the factories

	/**
	 * Convert and return a {@code String} parameter to {@code double}.
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted to a double
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	protected double getParameterAsDouble( String name )
		throws InvalidParameterValueException, MissingRequiredParameterException
	{

		try
		{
			return Double.parseDouble( parametersMap.get( name ) );

		}
		catch( NullPointerException e )
		{
			throw new MissingRequiredParameterException( name );

		}
		catch( NumberFormatException e )
		{
			throw new InvalidParameterValueException( name, parametersMap.get( name ) );
		}
	}

	/**
	 * Return a {@code String} parameter
	 * 
	 * @param name
	 * @return the parameter
	 */
	protected String getParameterAsString( String name )
	{

		return parametersMap.get( name );
	}

	/**
	 * Convert and return a {@code String} parameter to {@code int}
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted to an integer value
	 * @throws MissingRequiredParameterException
	 *         If {@link #parametersMap} does not contain a parameter with name
	 *         {@code name}
	 */
	protected int getParameterAsInt( String name )
		throws InvalidParameterValueException, MissingRequiredParameterException
	{

		try
		{
			return Integer.parseInt( parametersMap.get( name ) );

		}
		catch( NullPointerException e )
		{
			throw new MissingRequiredParameterException( name );

		}
		catch( NumberFormatException e )
		{
			throw new InvalidParameterValueException( name, parametersMap.get( name ) );
		}
	}

	/**
	 * Convert and return a {@code String} parameter to {@code boolean}.
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted
	 */
	protected boolean getParameterAsBoolean( String parameterName ) throws InvalidParameterValueException
	{

		String parameterValue = parametersMap.get( parameterName );

		if( parameterValue.equalsIgnoreCase( "true" ) || parameterValue.equalsIgnoreCase( "yes" )
				|| parameterValue.equalsIgnoreCase( "y" ) || parameterValue.equalsIgnoreCase( "1" ) )
			return true;
		if( parameterValue.equalsIgnoreCase( "false" ) || parameterValue.equalsIgnoreCase( "no" )
				|| parameterValue.equalsIgnoreCase( "n" ) || parameterValue.equalsIgnoreCase( "0" ) )
			return false;

		throw new InvalidParameterValueException( parameterName, parameterValue );
	}

}
