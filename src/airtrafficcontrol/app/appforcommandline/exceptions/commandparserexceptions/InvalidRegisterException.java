package airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions;


import java.text.MessageFormat;


@SuppressWarnings( "serial" )
public class InvalidRegisterException extends CommandParserException
{
	
	public InvalidRegisterException( String msg, String msg2 ) {
		super(
				MessageFormat
						.format(
								"Command registred with a placeholder with name '{0}', at node with an already existant placeholder child with name '{1}'",
								msg, msg2 ) );
	}
}
