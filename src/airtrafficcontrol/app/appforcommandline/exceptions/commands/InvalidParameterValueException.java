package airtrafficcontrol.app.appforcommandline.exceptions.commands;


import java.text.MessageFormat;


@SuppressWarnings( "serial" )
public class InvalidParameterValueException extends CommandException
{
	
	public InvalidParameterValueException( String name, String value ) {
		super( MessageFormat.format(
				"Demanding parameter with name {0} has invalid value {1}.",
				name, value ) );
	}
	
}
