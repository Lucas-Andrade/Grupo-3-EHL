package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;

/** 
 * Class that extends {@link GetAirshipsCommand} to obtain the information if one   
 * airship transgressed their corridor.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */


public class GetTransgressingAirshipsCommand extends GetAirshipsCommand
{

	private final static String FLIGHTID = "flightId";
	
	private final static String[] REQUIREDPARAMETERS={FLIGHTID};
	
	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	
	public static class Factory implements CommandFactory{
		
		
		InMemoryAirshipDatabase airshipDatabaseWhereAirshipDatabase;
		Map<String, String> parameters;

		
		
		public Factory(InMemoryAirshipDatabase airshipDatabaseWhereToSearch, Map<String, String> parameters){
			
			this.airshipDatabaseWhereAirshipDatabase=airshipDatabaseWhereToSearch;
			this.parameters=parameters;
			
		}
		
		public Command newInstance(Map<String,String> parameters){
			
			return new GetTransgressingAirshipsCommand(airshipDatabaseWhereAirshipDatabase ,parameters);
		}

		
	}
	
	/**
	 * Constructor 
	 * 
	 * store the container parameter
	 * 
	 * @param airshipsDatabaseWhereToSearch - airship Database target of search
	 * @param parameters - Container with parameters needed to proceed the search.
	 */
		
	public GetTransgressingAirshipsCommand( InMemoryAirshipDatabase airshipsDatabaseWhereToSearch, Map< String, String > parameters ) {
		
		super( airshipsDatabaseWhereToSearch, parameters );
	
	}
	
	/**
	 * Override of {@link AbstractCommand} 
	 * 
	 * execute the main action associated to this command
	 * 
	 */
	
		
	@Override
	protected void internalExecute() throws CommandException {
	
		String flightdID=parameters.get(FLIGHTID);
		
		if( airshipsDatabaseWhereToSearch.checkIfThisAirshipIsInCorridor(flightdID)) {
			
			result = "It's Trangressing";	
		} else{
			
			result = "It's Not Trangressing";	

		}
		
		
		
	}
	
	/**
	 * Override of {@link AbstractCommand} 
	 * 
	 * Method responsible to get the Obligation parameters associated to this Command
	 * 
	 */
	
	@Override
	protected String[] getRequiredParameters() {
				
		return REQUIREDPARAMETERS;
	}
	
}
