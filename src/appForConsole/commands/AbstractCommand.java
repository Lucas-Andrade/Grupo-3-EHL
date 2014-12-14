package appForConsole.commands;

import java.util.List;
import java.util.Map;

import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.exceptions.commandExecptions.InvalidParameterValueException;
import appForConsole.exceptions.commandExecptions.MissingRequiredParameterException;
import appForConsole.exceptions.commandExecptions.WrongLoginPasswordException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class AbstractCommand implements Command {

	// Instance Fields

	/**
	 * The parameters name-value pairs received to execute the command.
	 */
	protected final Map<String, String> parameters;

	protected String result;

	// Constructor

	/**
	 * Stores the container {@code parameters}.
	 * 
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public AbstractCommand(Map<String, String> parameters) {

		this.parameters = parameters;
	}

	// Abstract Methods To Be Implemented

	/**
	 * Performs the specific action associated with this command.
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	protected abstract void internalExecute() throws CommandException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException;

	/**
	 * Returns an array of {@link String strings} that has the names of the parameters without whom
	 * the command cannot execute.
	 * 
	 * @return An array of {@link String strings} that has the names of the parameters without whom
	 *         the command cannot execute.
	 */
	protected abstract String[] getRequiredParameters();

	// Private Methods

	/**
	 * Checks whether the required parameters (obtained using the method
	 * {@link #getRequiredParameters()}) were received.
	 * 
	 * @param requiredParameters
	 */
	private void validateDemandingParameters(String... parameterNames)
			throws MissingRequiredParameterException {

		if (parameterNames == null)
			return;

		for (String name : parameterNames)
			if (!parameters.containsKey(name))
				throw new MissingRequiredParameterException(name);

	}

	// Overrides

	/**
	 * Performs the action associated with this command, inclusively checks the validity of the
	 * received parameters.
	 * 
	 * @throws CommandException
	 *             If the received parameters are not valid (missing parameters, invalid values,
	 *             ...).
	 * @throws NoSuchElementInDatabaseException
	 *             If an element, expected to be in a certain database, was not found.
	 * @throws WrongLoginPasswordException
	 *             If the command needs a login name and a login password and the received password
	 *             is not the right password for the received login name.
	 */
	@Override
	public final void execute() throws CommandException, NoSuchElementInDatabaseException,
			WrongLoginPasswordException {

		validateDemandingParameters(getRequiredParameters());
		// TODO: other validations may be required.

		internalExecute();
	}

	/**
	 * Implementation of the {@code getResult} method from the {@link Command} interface.
	 */
	@Override
	public String getResult() {

		return result;
	}

	// Protected Auxiliar Methods

	/**
	 * Convert and return a {@code String} parameter to {@code double}.
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted
	 */
	protected double getParameterAsDouble(String name) throws InvalidParameterValueException {

		try {

			return Double.parseDouble(parameters.get(name));

		} catch (NullPointerException npe) {
			throw new NullPointerException();

		} catch (NumberFormatException nfe) {
			throw new InvalidParameterValueException(name, parameters.get(name));
		}
	}

	/**
	 * Return a {@code String} parameter
	 * 
	 * @param name
	 * @return the parameter
	 */
	protected String getParameterAsString(String name) throws InvalidParameterValueException {

		return parameters.get(name);
	}

	/**
	 * Convert and return a {@code String} parameter to {@code int}
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted
	 */
	protected int getParameterAsInt(String name) throws InvalidParameterValueException {

		try {

			return Integer.parseInt(parameters.get(name));

		} catch (NullPointerException npe) {
			throw new NullPointerException();

		} catch (NumberFormatException nfe) {
			throw new InvalidParameterValueException(name, parameters.get(name));
		}
	}

	/**
	 * Convert and return a {@code String} parameter to {@code boolean}.
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted
	 */
	protected boolean getParameterAsBoolean(String name) throws InvalidParameterValueException
	{
		String auxString = parameters.get(name);
		if( auxString.equalsIgnoreCase( "yes" ) )
			return true;
		if( auxString.equalsIgnoreCase( "no" ) )
			return false;
		throw new InvalidParameterValueException( name, auxString );
	}

	/**
	 * Convert a given {@code String List} to a String
	 * 
	 * @param stringList
	 *            parameter
	 * @return a string of the given list
	 */
	protected String listToString(List<?> stringList, String message) {

		StringBuilder sb = new StringBuilder();

		if (stringList == null || stringList.isEmpty())
			return message;

		for (Object s : stringList)
			sb.append(s).append("\n");

		return sb.toString();
	}
}