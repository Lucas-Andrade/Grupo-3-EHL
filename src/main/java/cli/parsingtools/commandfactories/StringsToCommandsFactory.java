package main.java.cli.parsingtools.commandfactories;

import java.util.Map;
import java.util.concurrent.Callable;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.WrongLoginPasswordException;

/**
 * A {@link CallableFactory factory} that creates commands by interpreting string parameters.
 * Commands are {@link Callable} instances.
 * <p>
 * Implementors must define the method {@link #newInstance(Map)} which returns a {@link Callable}
 * instance. The {@link Map} received contains the parameters needed to create the {@link Callable}
 * instance: each map-entry represents a parameter:
 * <li>The key the parameter's name and the value the parameter's value.</li>
 * </p>
 * Both key and value are {@link String strings} that are to be validated and interpreted for
 * creating the {@link Callable} instance. </p>
 * 
 * @param <R>
 *            - The {@link Callable} instance type of the command returned by the
 *            {@link #newInstance(Map)} method.
 * 
 * @see Callable
 * @see Map
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class StringsToCommandsFactory<R> {

	// INSTANCE FIELDS

	/**
	 * {@code parametersMap} - {@link Map} containing all the name-value pairs of parameters
	 * received to create and execute a specific command.
	 */
	protected Map<String, String> parametersMap;

	/**
	 * {@code commandsDescription} - A short description of the command produced by this factory.
	 */
	private final String commandsDescription;

	// CONSTRUCTOR

	/**
	 * Creates a new {@link StringsToCommandsFactory factory} that produces a command specific
	 * command whose action is described in {@code commandDescription}.
	 * 
	 * @param commandDescription
	 *            - A short description of the command produced by this factory.
	 * 
	 * @throws InvalidArgumentException
	 *             If the {@code commandDescription} is {@code null}.
	 */
	public StringsToCommandsFactory(String commandDescription) throws InvalidArgumentException {

		if (commandDescription == null)
			throw new InvalidArgumentException("Cannot instantiate factory without description!");

		this.commandsDescription = commandDescription;
	}

	/**
	 * Method responsable for producing a command. It starts by performing validating the received
	 * parameters (seeing if there are required parameters missing in the map, interpreting the
	 * parameters into their correct type, authenticating users passwords, etc) and returns a
	 * {@link Callable} instance of the command ready to be executed (called).
	 * 
	 * @param parameters
	 *            - The {@link Map} containing all the name-value pairs of parameters needed to
	 *            create the {@link Callable} command instance.
	 * 
	 * @return An instance of {@code Callable<R>}.
	 * 
	 * @throws InvalidArgumentException
	 *             If the {@code parameters} Map is null or if one or more of the parameter received
	 *             is invalid. (e.g. is not convertible to the expected type).
	 * @throws NoSuchElementInDatabaseException
	 *             For the commands that need a user to be executed if there is no user in
	 *             {@link #usersDatabase} whose username is the login name receive in the parameters
	 *             map. The message of this exception is <i>«{login name} not found in
	 *             {@code usersDatabase.getDatabaseName()}»</i>.
	 * @throws InternalErrorException
	 *             If an internal error occurred (not supposed to happen).
	 * @throws MissingRequiredParameterException
	 *             If the received map does not contain one of the required parameters for
	 *             instantiating the command.
	 * @throws WrongLoginPasswordException
	 *             For the commands that need a user to be executed if the login password received
	 *             does not match the user's password corresponding to the login username received.
	 * @throws InvalidParameterValueException
	 *             If the value received in the parameters map for a required parameter is invalid.
	 */
	public final Callable<R> newInstance(Map<String, String> parameters)
			throws NoSuchElementInDatabaseException, InternalErrorException,
			InvalidArgumentException, MissingRequiredParameterException,
			InvalidParameterValueException, WrongLoginPasswordException {

		this.parametersMap = parameters;

		checkIfAllRequiredParametersAreInTheParametersMap(getRequiredParameters());

		return internalNewInstance();
	}

	// AUXILIARY PRIVATE METHOD - used in the newInstance method!

	/**
	 * Checks whether the required parameters for performing the {@link #newInstance()} method
	 * (known through the method {@link #getRequiredParameters()}) are contained in the received
	 * {@link Map}. If all required parameters were found in the map or there are no required
	 * parameters, this method returns nothing. If a required parameter is missing, an exception is
	 * thrown indicating in its message the name of the first required parameter not found.
	 * 
	 * @param requiredParameterNames
	 *            - The names(that correspond to the {@code parametersMap} keys) of the required
	 *            parameters.
	 * @throws MissingRequiredParameterException
	 *             If a required parameter is missing.
	 */
	private void checkIfAllRequiredParametersAreInTheParametersMap(String... requiredParameterNames)
			throws MissingRequiredParameterException {

		if (requiredParameterNames == null) {
			return;
		}

		for (String name : requiredParameterNames)
			if (!parametersMap.containsKey(name))
				throw new MissingRequiredParameterException(name);
	}

	// UNIMPLEMENTED AUXILIAR METHODS - to be implemented by the factories!

	/**
	 * Method responsible for produces a command and returns it to the {@link #newInstance()}
	 * method.
	 * 
	 * @throws InvalidParameterValueException
	 *             If the value received in the parameters map for a required parameter invalid
	 *             (e.g. is not convertible to the expected type).
	 * @throws WrongLoginPasswordException
	 *             If the login password received does not match the login username's password.
	 * @throws NoSuchElementInDatabaseException
	 *             If there is no user in {@link #usersDatabase} whose username is the login name
	 *             receive in the parameters map. The message of this exception is <i>«{login name}
	 *             not found in {@code usersDatabase.getDatabaseName()}»</i>.
	 * @throws InternalErrorException
	 *             If an internal error that wasn't supposed to happen happened.
	 * @throws MissingRequiredParameterException
	 *             If the received map does not contain one of the required parameters for
	 *             instantiating the command.
	 * @throws InvalidArgumentException
	 *             If the value received in the parameters map for a required parameter is invalid.
	 */
	protected abstract Callable<R> internalNewInstance() throws InvalidParameterValueException,
			WrongLoginPasswordException, NoSuchElementInDatabaseException, InternalErrorException,
			MissingRequiredParameterException, InvalidArgumentException;

	/**
	 * Returns an array of {@link String strings} with the names of the parameters without whose the
	 * {@link #newInstance()} method cannot create a specific {@link Callable} command instance.
	 * 
	 * @return An array with the names of the parameters without whose the {@link #newInstance()}
	 *         method cannot create a specific {@link Callable} command instance.
	 */
	protected abstract String[] getRequiredParameters();

	// PROTECTED UXILIAR METHODS - tools for the factories!

	/**
	 * @param name
	 *            - The name of the parameter.
	 * 
	 * @return the String parameter value.
	 */
	protected String getParameterAsString(String name) {

		return parametersMap.get(name);
	}

	/**
	 * Converts a {@code String} parameter value to a {@code int} and returns it.
	 * 
	 * @param name
	 *            - The name of the parameter.
	 * 
	 * @return the converted String parameter value.
	 * 
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted to an int value
	 * @throws MissingRequiredParameterException
	 *             If {@link #parametersMap} does not contain a parameter with name {@code name}
	 */
	protected int getParameterAsInt(String name) throws InvalidParameterValueException,
			MissingRequiredParameterException {

		try {
			return Integer.parseInt(parametersMap.get(name));

		} catch (NullPointerException e) {
			throw new MissingRequiredParameterException(name);

		} catch (NumberFormatException e) {
			throw new InvalidParameterValueException(name, parametersMap.get(name));
		}
	}

	/**
	 * Converts a {@code String} parameter value to a {@code double} and returns it.
	 * 
	 * @param name
	 *            - The name of the parameter.
	 * 
	 * @return the converted String parameter value.
	 * 
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted to a double.
	 * @throws MissingRequiredParameterException
	 *             If {@link #parametersMap} does not contain a parameter with name {@code name}.
	 */
	protected double getParameterAsDouble(String name) throws InvalidParameterValueException,
			MissingRequiredParameterException {

		try {
			return Double.parseDouble(parametersMap.get(name));

		} catch (NullPointerException e) {
			throw new MissingRequiredParameterException(name);

		} catch (NumberFormatException e) {
			throw new InvalidParameterValueException(name, parametersMap.get(name));
		}
	}

	/**
	 * Converts a {@code String} parameter value to a {@code boolean} and returns it.
	 * 
	 * @param name
	 *            - The name of the parameter.
	 * 
	 * @return the converted String parameter value.
	 * 
	 * @throws InvalidParameterValueException
	 */
	protected boolean getParameterAsBoolean(String parameterName)
			throws InvalidParameterValueException {

		String parameterValue = parametersMap.get(parameterName);

		if (parameterValue.equalsIgnoreCase("true") || parameterValue.equalsIgnoreCase("yes")
				|| parameterValue.equalsIgnoreCase("y") || parameterValue.equalsIgnoreCase("1"))
			return true;
		
		if (parameterValue.equalsIgnoreCase("false") || parameterValue.equalsIgnoreCase("no")
				|| parameterValue.equalsIgnoreCase("n") || parameterValue.equalsIgnoreCase("0"))
			return false;

		throw new InvalidParameterValueException(parameterName, parameterValue);
	}

	// Get Methods

	/**
	 * @return The description of the commands produced by this factory.
	 */
	public String getCommandsDescription() {

		return commandsDescription;
	}
}