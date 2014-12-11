package airtrafficcontrol.app.appforcommandline.commands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;


public class PostAirshipCommand extends PostCommand
{
	
	public PostAirshipCommand( InMemoryUserDatabase postingUsersDatabase,
			InMemoryAirshipDatabase postedAirshipsDatabase, Map< String, String > parameters ) {
		
		super( postingUsersDatabase, postedAirshipsDatabase, parameters );
		
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
