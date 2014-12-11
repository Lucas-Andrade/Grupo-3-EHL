package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;


public class GetAirshipsWithMinimumPassengersNumberCommand extends
		GetAirshipsCommand
{
	
	public GetAirshipsWithMinimumPassengersNumberCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map< String, String > parameters ) {
		super( airshipsDatabaseWhereToSearch, parameters );
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
