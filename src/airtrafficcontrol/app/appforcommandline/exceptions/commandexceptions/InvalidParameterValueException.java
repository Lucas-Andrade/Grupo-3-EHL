package airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions;


import java.text.MessageFormat;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.CommandParserException;


@SuppressWarnings( "serial" )
public class InvalidParameterValueException extends CommandException
{
	
	/**
	 * Constructs a {@link CommandParserException} with the message <i>«Required
	 * parameter with name {@code parameterName} has invalid value
	 * {@code parameterValue}.»</i>.
	 * 
	 * @param parameterName The parameter's name.
	 * @param parameterValue The parameter's value.
	 */
	public InvalidParameterValueException( String parameterName, String parameterValue ) {
		super( MessageFormat.format(
				"Required parameter with name {0} has invalid value {1}.",
				parameterName, parameterValue ) );
	}	
}
