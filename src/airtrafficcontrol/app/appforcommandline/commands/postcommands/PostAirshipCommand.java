package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;


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
