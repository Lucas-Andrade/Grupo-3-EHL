package airtrafficcontrol.app.appforcommandline.commands.postcommands;


import java.text.MessageFormat;
import airtrafficcontrol.app.appforcommandline.exceptions.database.DatabaseException;


@SuppressWarnings( "serial" )
public class WrongLoginPasswordException extends DatabaseException
{
	
	public WrongLoginPasswordException( String loginName, String loginPassword ) {
		super( MessageFormat.format(
				"Wrong password: {0}'s password is not {1}.", loginName,
				loginPassword ) );
	}
	
}
