package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.List;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;

/**
 * Class whose instances have the point to return an {@link Airship}
 * {@code List} by a given User owner
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsByOwnerCommand extends GetAirshipsCommand
{
	private static final String USERNAME = "username";
	/**
	 * Class that implements the {@link GetProducts} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory
	{
		private final InMemoryAirshipDatabase dataBase;

		public Factory( InMemoryAirshipDatabase dataBase )
		{
			this.dataBase = dataBase;
		}

		@Override
		public Command newInstance( Map<String, String> parameters )
		{
			return new GetAirshipsByOwnerCommand( dataBase, parameters );
		}
	}

	public GetAirshipsByOwnerCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map<String, String> parameters )
	{
		super( airshipsDatabaseWhereToSearch, parameters );
	}

	@Override
	protected void internalExecute() throws CommandException
	{
		result = listToString(airshipsDatabase.getAirshipsOfUser( parameters.get(USERNAME) ));
	}

	@Override
	protected String[] getRequiredParameters()
	{
		return new String[] {USERNAME};
	}
	
	
	
	/**
	 * Convert a given String List to a String
	 * @param stringList
	 * @return a string of the given list
	 */
	private String listToString( List<String> stringList )
	{
		StringBuilder sb = new StringBuilder();
		for( String s : stringList)
			sb.append( s ).append( "\n" );
		return sb.toString();
	}
}
