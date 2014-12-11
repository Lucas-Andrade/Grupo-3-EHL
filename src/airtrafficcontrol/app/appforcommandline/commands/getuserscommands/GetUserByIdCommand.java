package airtrafficcontrol.app.appforcommandline.commands.getuserscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;


public class GetUserByIdCommand extends GetUsersCommand
{
	
	public GetUserByIdCommand( InMemoryUserDatabase usersDatabaseWhereToSearch,
			Map< String, String > parameters ) {
		super( usersDatabaseWhereToSearch, parameters );
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void internalExecute() throws CommandException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String[] getRequiredParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
