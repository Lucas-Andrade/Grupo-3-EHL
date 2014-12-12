package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;


public class PostUserCommand extends PostCommand
{
	
	public PostUserCommand( InMemoryUserDatabase postingUsersDatabase,
			InMemoryUserDatabase postedUsersDatabase, Map< String, String > parameters ) {
		
		super( postingUsersDatabase, postedUsersDatabase, parameters );
		
		// TODO
	}

	@Override
	protected void internalPostExecute() throws CommandException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String[] getRequiredParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
