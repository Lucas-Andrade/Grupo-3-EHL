package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.List;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.AirshipPredicates;
import airtrafficcontrol.app.appforcommandline.model.airships.CivilAirship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;

/**
 * Class whose instances have the point to return an {@link Airship}
 * {@code List} of the {@link CivilAirship}s that have less passengers than the
 * given {@code minimum passengers number}
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithMinimumPassengersCommand extends GetAirshipsCommand
{
	public static final String NUMBERBELLOWPASSENGERS = "nbp";

	/**
	 * Create a {@code GetAirshipsWithMinimumPassengersCommand} that will call
	 * {@link GetAirshipsCommand} with the parameters {@code airshipsDatabase}
	 * and {@code parameters}.
	 * 
	 * @param airshipsDatabaseWhereToSearch
	 * @param parameters
	 */
	public GetAirshipsWithMinimumPassengersCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map<String, String> parameters )
	{
		super( airshipsDatabaseWhereToSearch, parameters );
	}

	/**
	 * Internal execute that will search in the {@code airshipDatabase} for airships with 
	 * less passengers than the given {@code minimum passengers number}.
	 * The method {@code getAirshipsThat} from {@link InMemoryAirshipDatabase} will call
	 * the {@link AirshipPredicates#IsBelowPassagerNumber} method. 
	 * The {code airship} info is inserted in the {@code result} variable from {@link GetAirshipsCommand}
	 */
	@Override
	protected void internalExecute() throws CommandException
	{
		result = listToString( airshipsDatabaseWhereToSearch
				.getAirshipsThat( new AirshipPredicates.IsBelowPassagerNumber(
						Long.parseLong( parameters.get( NUMBERBELLOWPASSENGERS ) ) ) ) );
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

	/**
	 * Get the required parameters that will be validate in {@link AbstractCommand}
	 * @return a String Array with the required Parameters
	 */
	@Override
	protected String[] getRequiredParameters()
	{
		return new String[] {NUMBERBELLOWPASSENGERS};
	}


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
			return new GetAirshipsWithMinimumPassengersCommand( dataBase,
					parameters );
		}
	}
}
