package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Collection;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;

/** 
 * Class that extends {@link GetAirshipsCommand} to obtain the information of  
 * airships who transgressed the corridor.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class GetTransgressingAirshipsCommand extends GetAirshipsCommand
{



	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	
	public static class Factory implements CommandFactory{
		
		
		
		InMemoryAirshipDatabase airshipsDatabaseWhereToSearch;
	
		
		public Factory(InMemoryAirshipDatabase airshipsDatabaseWhereToSearch){
			
			this.airshipsDatabaseWhereToSearch=airshipsDatabaseWhereToSearch;
					
		}

		
		public Command newInstance(Map<String, String> parameters){
			
			return new GetTransgressingAirshipsCommand(airshipsDatabaseWhereToSearch,parameters);
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

		Collection<Airship> aircraft=airshipsDatabase.reportTransgressions();


		StringBuilder flightIDs = new StringBuilder();

		if( aircraft.isEmpty() ) flightIDs.append("There are no transgressions records");
		else{
			for(Airship element:aircraft){

				flightIDs.append("\n Airship flightID: ").append(element.getIdentification());
			}
		}
		
		result=flightIDs.toString();

	}
	
	/**
	 * Override of {@link AbstractCommand} 
	 * 
	 * Method responsible to get the Obligation parameters associated to this Command
	 * 
	 */
		
	@Override
	protected String[] getRequiredParameters() {

		return null;
	}	
	
}
