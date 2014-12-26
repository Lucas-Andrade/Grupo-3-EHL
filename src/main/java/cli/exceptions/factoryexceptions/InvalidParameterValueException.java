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
}
