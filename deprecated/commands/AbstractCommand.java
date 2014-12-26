package airtrafficcontrol.deprecated.appforcommandline.commands;


import java.util.List;
import java.util.Map;
import airtrafficcontrol.app.appforcommandline.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.InvalidParameterValueException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.MissingRequiredParameterException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.WrongLoginPasswordException;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class AbstractCommand implements Command
{
	
	// INSTANCE FIELDS
	/**
	 * The parameters name-value pairs received to execute the command.
	 */
	protected final Map< String, String > parameters;
	
	protected String result;
	
	// CONSTRUCTOR
	/**
	 * Stores the container {@code parameters}.
	 * 
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public AbstractCommand( Map< String, String > parameters ) {
		
		this.parameters = parameters;
	}
	
	// EXECUTE METHOD
	/**
	 * Performs the action associated with this command, inclusively checks the
	 * validity of the received parameters.
	 * 
	 * @throws NoSuchElementInDatabaseException
	 *             If an element, expected to be in a certain database, was not
	 *             found.
	 * @throws WrongLoginPasswordException
	 *             If the command needs a login name and a login password and
	 *             the received password is not the right password for the
	 *             received login name.
	 * @throws MissingRequiredParameterException
	 *             If a parameter needed to perform an operation wasn't
	 *             received.
	 * @throws InvalidArgumentException
	 * @throws InvalidParameterValueException
	 *             If a parameter needed to perform an operation was received
	 *             with an invalid value.
	 */
	public final void execute() throws NoSuchElementInDatabaseException,
			WrongLoginPasswordException, MissingRequiredParameterException,
			InvalidParameterValueException, InvalidArgumentException {
		
		validateDemandingParameters( getRequiredParameters() );
		// TODO: other validations may be required.
		
		internalExecute();
		
	}
	
	
	
	// UNIMPLEMENTED METHODS
	
	/**
	 * Performs the specific action associated with this command.
	 * 
	 * @throws InvalidParameterValueException
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 * @throws InvalidArgumentException
	 * @throws MissingRequiredParameterException 
	 */
	protected abstract void internalExecute()
			throws NoSuchElementInDatabaseException,
			WrongLoginPasswordException, InvalidArgumentException,
			InvalidParameterValueException, MissingRequiredParameterException;
	
	/**
	 * Returns an array of {@link String strings} that has the names of the
	 * parameters without whom the command cannot execute.
	 * 
	 * @return An array of {@link String strings} that has the names of the
	 *         parameters without whom the command cannot execute.
	 */
	protected abstract String[] getRequiredParameters();
	
	
	
	// AUXILIAR PRIVATE METHODS
	
	/**
	 * Checks whether the required parameters (obtained using the method
	 * {@link #getRequiredParameters()}) were received.
	 * 
	 * @param requiredParameters
	 */
	private void validateDemandingParameters( String... parameterNames )
			throws MissingRequiredParameterException {
		
		if( parameterNames == null )
			return;
		
		for( String name : parameterNames )
			if( !parameters.containsKey( name ) )
				throw new MissingRequiredParameterException( name );
		
	}
	
	/**
	 * Returns the result of the execution.
	 */
	public String getResult() {
		
		return result;
	}
	
	
	
	// AUXILIARY PROTECTED METHODS - by G.
	// for interpreting string-parameters
	
	/**
	 * Convert and return a {@code String} parameter to {@code double}.
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted to a double
	 */
	protected double getParameterAsDouble( String name )
			throws InvalidParameterValueException {
		
		try
		{
			return Double.parseDouble( parameters.get( name ) );
		}
		catch( NullPointerException | NumberFormatException e )
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
	protected String getParameterAsString( String name ) {
		
		return parameters.get( name );
	}
	
	/**
	 * Convert and return a {@code String} parameter to {@code int}
	 * 
	 * @param name
	 * @return the converted String parameter
	 * @throws InvalidParameterValueException
	 *             If the String can not be converted to an integer value
	 */
	protected int getParameterAsInt( String name )
			throws InvalidParameterValueException {
		
		try
		{
			return Integer.parseInt( parameters.get( name ) );
		}
		catch( NullPointerException | NumberFormatException e )
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
	 *             If the String can not be converted
	 */
	protected boolean getParameterAsBoolean( String parameterName )
			throws InvalidParameterValueException {
		
		String parameterValue = parameters.get( parameterName );
		
		if( parameterValue.equalsIgnoreCase( "true" )
				|| parameterValue.equalsIgnoreCase( "yes" )
				|| parameterValue.equalsIgnoreCase( "y" )
				|| parameterValue.equalsIgnoreCase( "1" ) )
			return true;
		if( parameterValue.equalsIgnoreCase( "false" )
				|| parameterValue.equalsIgnoreCase( "no" )
				|| parameterValue.equalsIgnoreCase( "n" )
				|| parameterValue.equalsIgnoreCase( "0" ) )
			return false;
		
		throw new InvalidParameterValueException( parameterName, parameterValue );
	}
	
	/**
	 * Convert a given {@link List list} of {@link Object objects} to a single
	 * {@link String string}. Entries of the original list are converted to
	 * strings (using the method {@link Object#toString() to String}) and those
	 * string-representations appear in the new string separated by a
	 * paragraph-character, '\n'.
	 * 
	 * @param list
	 *            The list to be converted.
	 * @param message
	 *            The string to return if the {@code stringList} is either
	 *            {@code null} or {@code message}.
	 * @return A string representation of {@code list} where the string
	 *         representations of the entries are separated by
	 *         paragraph-characters '\n'.
	 */
	protected String listToString( List< ? > list, String message ) {
		
		if( list == null || list.isEmpty() )
			return message;
		
		StringBuilder sb = new StringBuilder();
		for( Object s : list )
			sb.append( s ).append( "\n" );
		return sb.toString();
	}
}
