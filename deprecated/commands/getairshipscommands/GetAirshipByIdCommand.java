package airtrafficcontrol.deprecated.appforcommandline.commands.getairshipscommands;


import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;


public class GetAirshipByIdCommand extends GetAirshipsCommand
{
	
	private static final String ID = "flightId";
	
	private static final String[] requiredParameters = { ID };
	
	public static class Factory implements CommandFactory
	{
		
		private final InMemoryAirshipDatabase airshipDatabase;
		
		public Factory( InMemoryAirshipDatabase airshipDatabase ) {
			
			this.airshipDatabase = airshipDatabase;
		}
		
		@Override
		public Command newInstance( Map< String, String > parameters ) {
			
			return new GetAirshipByIdCommand( airshipDatabase, parameters );
		}
	}
	
	public GetAirshipByIdCommand( InMemoryAirshipDatabase airshipsDatabase,
			Map< String, String > parameters ) {
		
		super( airshipsDatabase, parameters );
	}
	
	@Override
	protected void internalExecute() {
		
		Airship airship = airshipsDatabase
				.getElementByIdentification( this.parameters.get( ID ) );
		
		if( airship == null )
		{
			
			this.result = "Airship Not Found\n";
			return;
		}
		
		this.result = airship.toString();
	}
	
	@Override
	protected String[] getRequiredParameters() {
		
		return requiredParameters;
	}
}