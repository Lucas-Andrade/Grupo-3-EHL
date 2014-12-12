package airtrafficcontrol.app.appforcommandline.exceptions.commands;


import java.text.MessageFormat;


@SuppressWarnings( "serial" )
public class RequiredParameterNotPresentException extends CommandException
{
	
	public RequiredParameterNotPresentException( String parameterName ) {
		super( MessageFormat
				.format( "Demanding parameter with name {0} not present.",
						parameterName ) );
	}
	
}
