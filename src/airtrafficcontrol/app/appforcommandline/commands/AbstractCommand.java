package airtrafficcontrol.app.appforcommandline.commands;

import java.util.List;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.InvalidParameterValueException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.RequiredParameterNotPresentException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.WrongLoginPasswordException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class AbstractCommand implements Command {

	// INSTANCE FIELDS
	/**
	 * The parameters name-value pairs received to execute the command.
	 */
	protected final Map<String, String> parameters;

	protected String result;
	
	// CONSTRUCTOR
	/**
	 * Stores the container {@code parameters}.
	 * 
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public AbstractCommand(Map<String, String> parameters) {

		this.parameters = parameters;
	}

	
	
	// EXECUTE METHOD
	/**
	 * Performs the action associated with this command, inclusively checks the validity of the
	 * received parameters.
	 * 
	 * @throws CommandException
	 *             If the received parameters are not valid (missing parameters, invalid values,
	 *             ...).
	 * @throws NoSuchElementInDatabaseException
	 *             If an element, expected to be in a certain database, was not
	 *             found.
	 * @throws WrongLoginPasswordException
	 *             If the command needs a login name and a login password and
	 *             the received password is not the right password for the
	 *             received login name.
	 */
	public final void execute() throws CommandException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException {

		validateDemandingParameters(getRequiredParameters());
		// TODO: other validations may be required.

		internalExecute();

	}

	// UNIMPLEMENTED METHODS

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

	// AUXILIAR PRIVATE METHODS

	/**
	 * Checks whether the required parameters (obtained using the method
	 * {@link #getRequiredParameters()}) were received.
	 * 
	 * @param requiredParameters
	 */
	private void validateDemandingParameters(String... parameterNames)
			throws RequiredParameterNotPresentException {
		

		for( String name : parameterNames )
			if( !parameters.containsKey( name ) )
				throw new RequiredParameterNotPresentException( name );
		
	}

	public String getResult() {
		
		return result;
	}

	
//protected method - by G.
	// Do you approve? (y/n)
	
	/**
	 * Convert and return a {@code String} parameter to {@code double}.
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted
	 */
	protected double getParameterAsDouble( String name )
			throws InvalidParameterValueException
	{
		try
		{
			return Double.parseDouble( parameters.get( name ) );
		}
		catch( NullPointerException npe )
		{
			throw new NullPointerException();
		}
		catch( NumberFormatException nfe )
		{
			throw new InvalidParameterValueException( name,
					parameters.get( name ) );
		}
	}

	/**
	 * Return a {@code String} parameter
	 * 
	 * @param name
	 * @return the parameter
	 */
	protected String getParameterAsString( String name )
			throws InvalidParameterValueException
	{
		return parameters.get( name );
	}

	/**
	 * Convert and return a {@code String} parameter to {@code int}
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *         If the String can not be converted
	 */
	protected int getParameterAsInt( String name )
			throws InvalidParameterValueException
	{
		try
		{
			return Integer.parseInt( parameters.get( name ) );
		}
		catch( NullPointerException npe )
		{
			throw new NullPointerException();
		}
		catch( NumberFormatException nfe )
		{
			throw new InvalidParameterValueException( name,
					parameters.get( name ) );
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
	protected boolean getParameterAsBoolean( String name )
			throws InvalidParameterValueException
	{
		try
		{
			return Boolean.parseBoolean( parameters.get( name ) );
		}
		catch( NullPointerException npe )
		{
			throw new NullPointerException();
		}
		catch( NumberFormatException nfe )
		{
			throw new InvalidParameterValueException( name,
					parameters.get( name ) );
		}
	}


	/**
	 * Convert a given {@code String List} to a String
	 * 
	 * @param stringList parameter
	 * @return a string of the given list
	 */
	protected String listToString( List<String> stringList, String message )
	{
		StringBuilder sb = new StringBuilder();
		for( String s : stringList )
			sb.append( s ).append( "\n" );
		return sb.equals( "" )? message : sb.toString();
	}
}
