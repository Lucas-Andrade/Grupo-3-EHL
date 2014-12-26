package airtrafficcontrol.deprecated.appforcommandline.commands.getairshipscommands;


import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;


/**
 * Class that extends {@link GetAirshipsCommand} to obtain the information if
 * one airship transgressed their corridor.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class GetTransgressingAirshipsCommand extends GetAirshipsCommand
{
	
	private final static String FLIGHTID = "flightId";
	
	private final static String[] REQUIREDPARAMETERS = { FLIGHTID };
	
	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	
	public static class Factory implements CommandFactory
	{
		
		InMemoryAirshipDatabase airshipDatabaseWhereAirshipDatabase;
		
		public Factory( InMemoryAirshipDatabase airshipDatabaseWhereToSearch ) {
			
			this.airshipDatabaseWhereAirshipDatabase = airshipDatabaseWhereToSearch;
			
		}
		
		public Command newInstance( Map< String, String > parameters ) {
			
			return new GetTransgressingAirshipsCommand(
					airshipDatabaseWhereAirshipDatabase, parameters );
		}
		
	}
	
	/**
	 * Constructor
	 * 
	 * store the container parameter
	 * 
	 * @param airshipsDatabaseWhereToSearch
	 *            - airship Database target of search
	 * @param parameters
	 *            - Container with parameters needed to proceed the search.
	 */
	
	public GetTransgressingAirshipsCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map< String, String > parameters ) {
		
		super( airshipsDatabaseWhereToSearch, parameters );
		
	}
	
	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * execute the main action associated to this command
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * 
	 */
	
	@Override
	protected void internalExecute() throws NoSuchElementInDatabaseException {
		
		String flightdID = parameters.get( FLIGHTID );
		
		if( airshipsDatabase.checkIfThisAirshipIsInCorridor( flightdID ) )
		{
			
			result = "It's Transgressing";
		}
		else
		{
			
			result = "It's Not Transgressing";
			
		}
		
	}
	
	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * Method responsible to get the Obligation parameters associated to this
	 * Command
	 * 
	 */
	
	@Override
	protected String[] getRequiredParameters() {
		
		return REQUIREDPARAMETERS;
	}
	
}
