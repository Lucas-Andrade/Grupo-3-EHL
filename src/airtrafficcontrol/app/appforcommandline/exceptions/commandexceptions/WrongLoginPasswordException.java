package airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions;


import java.text.MessageFormat;


@SuppressWarnings( "serial" )
public class WrongLoginPasswordException extends CommandException
{
	
	public WrongLoginPasswordException( String loginName, String loginPassword ) {
		super( MessageFormat.format(
				"Wrong password: {0}'s password is not {1}.", loginName,
				loginPassword ) );
	}
	
}
