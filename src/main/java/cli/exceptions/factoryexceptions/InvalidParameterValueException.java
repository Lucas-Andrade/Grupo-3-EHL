package main.java.cli.exceptions.factoryexceptions;


import java.text.MessageFormat;


@SuppressWarnings( "serial" )
public class InvalidParameterValueException extends CommandFactoryException
{
	
	/**
	 * Constructs a {@link InvalidParameterValueException} with the message
	 * <i>«Required parameter with name {@code parameterName} has invalid value
	 * {@code parameterValue}.»</i>.
	 * 
	 * @param parameterName
	 *            The parameter's name.
	 * @param parameterValue
	 *            The invalid parameter's value.
	 */
	public InvalidParameterValueException( String parameterName,
			String parameterValue ) {
		
		super( MessageFormat.format(
				"Required parameter with name {0} has invalid value {1}.",
				parameterName, parameterValue ) );
	}
	
	/**
	 * Constructs a {@link InvalidParameterValueException} with the message
	 * <pre>
	 *  Required parameter with name <i>parameterName</i> has invalid value <i>parameterValue</i>.
	 *  <i>extraInfo</i></pre>
	 * 
	 * @param parameterName
	 *            The parameter's name.
	 * @param parameterValue
	 *            The invalid parameter's value.
	 * @param extraInfo
	 *            A detail message about the invalidity of the parameter value.
	 */
	public InvalidParameterValueException( String parameterName,
			String parameterValue, String extraInfo ) {
		
		super( MessageFormat.format(
				"Required parameter with name {0} has invalid value {1}."
						+ "\n {2}", parameterName, parameterValue, extraInfo ) );
	}
	
}
