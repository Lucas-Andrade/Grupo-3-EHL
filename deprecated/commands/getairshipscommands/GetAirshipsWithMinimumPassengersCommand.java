package airtrafficcontrol.deprecated.appforcommandline.commands.getairshipscommands;


import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.InvalidParameterValueException;
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
	
	private static final String NUMBERBELLOWPASSENGERS = "nbP";
	
	/**
	 * Class that implements the {@link CommandFactory} factory, according to
	 * the AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory
	{
		
		private final InMemoryAirshipDatabase dataBase;
		
		public Factory( InMemoryAirshipDatabase dataBase ) {
			this.dataBase = dataBase;
		}
		
		@Override
		public Command newInstance( Map< String, String > parameters ) {
			return new GetAirshipsWithMinimumPassengersCommand( dataBase,
					parameters );
		}
	}
	
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
			Map< String, String > parameters ) {
		super( airshipsDatabaseWhereToSearch, parameters );
	}
	
	/**
	 * Internal execute that will search in the {@code airshipDatabase} for
	 * airships with less passengers than the given
	 * {@code minimum passengers number}. The method {@code getAirshipsThat}
	 * from {@link InMemoryAirshipDatabase} will call the
	 * {@link AirshipPredicates#IsBelowPassagerNumber} method. The {code
	 * airship} info is inserted in the {@code result} variable from
	 * {@link GetAirshipsCommand}
	 * 
	 * @throws InvalidParameterValueException
	 *             If the parameter value correspondent to the minimum number of
	 *             passengers isn't convertible to an integer.
	 */
	@Override
	protected void internalExecute() throws InvalidParameterValueException {
		
		Integer passengerNumberThreshold;
		try
		{
			passengerNumberThreshold = Integer.parseInt( parameters
					.get( NUMBERBELLOWPASSENGERS ) );
		}
		catch( NumberFormatException e )
		{
			throw new InvalidParameterValueException( NUMBERBELLOWPASSENGERS,
					parameters.get( NUMBERBELLOWPASSENGERS ) );
		}
		
		try
		{
			List< Airship > list = airshipsDatabase
					.getAirshipsThat( new AirshipPredicates.HasAMaximumNumberOfPassengers(
							passengerNumberThreshold ) );
			String messageToBeReturnedIfListIsEmpty = MessageFormat.format(
					"There are no airships with less than {0} passengers.",
					passengerNumberThreshold );
			result = listToString( list, messageToBeReturnedIfListIsEmpty );
		}
		catch( InvalidArgumentException e )
		{
			// never happens because the predicate given is not null
		}
		
	}
	
	/**
	 * Returns an array of {@code String}s that has the names of the parameters
	 * (to be validate in {@link AbstractCommand}) without whom the command
	 * cannot execute: NUMBERBELLOWPASSENGERS
	 * 
	 * @return An array of {@link String}s that has the names of the parameters
	 *         without whom the command cannot execute.
	 */
	@Override
	protected String[] getRequiredParameters() {
		return new String[]{ NUMBERBELLOWPASSENGERS };
	}
	
	
}
