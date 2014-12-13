package airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions;


import java.text.MessageFormat;

/**
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class RequiredParameterNotPresentException extends CommandException
{
	
	public RequiredParameterNotPresentException( String parameterName ) {
		super( MessageFormat
				.format( "Required parameter with name {0} missing.",
						parameterName ) );
	}
	
}
