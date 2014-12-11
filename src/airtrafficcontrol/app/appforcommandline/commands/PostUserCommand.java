package airtrafficcontrol.app.appforcommandline.commands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;


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
