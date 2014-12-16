package main.java.commands.getAirshipsCommands;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.Predicate;

import main.java.commands.AbstractCommand;
import main.java.commands.Command;
import main.java.commands.CommandFactory;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.model.Param;
import main.java.model.airships.AirshipPredicates;
import main.java.model.airships.CivilAirship;
import main.java.model.airships.InMemoryAirshipDatabase;

/**
 * Class whose instances represent a command, that have the point to get the
 * info of the {@link CivilAirship}s in {@link InMemoryAirshipDatabase}, that
 * have less passengers than the given {@code minimum passengers number}
 * 
 * This command instances are created and used according to the AbstratFactory
 * design pattern, making use of a class {@link Factory} that implements the
 * {@link CommandFactory} Interface whose only method is the
 * {@link CommandFactory#newInstance(Map) newInstance(Map)} method that will
 * allow new command instances to be created.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithANumberOfPassengersBelowACertainThresholdCommand
		extends GetAirshipsCommand
{
	/**
	 * Class that implements the {@link CommandFactory} factory, according to
	 * the AbstratFactory design pattern.
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

			return new GetAirshipsWithANumberOfPassengersBelowACertainThresholdCommand(
					dataBase, parameters );
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
	public GetAirshipsWithANumberOfPassengersBelowACertainThresholdCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map<String, String> parameters )
	{

		super( airshipsDatabaseWhereToSearch, parameters );
	}

	/**
	 * Internal execute that will search in the {@code airshipDatabase} for
	 * airships with less passengers than the given
	 * {@code minimum passengers number}. The class {@link Predicate} is used in
	 * {@link AirshipPredicates#IsBelowPassagerNumber} method. The {code
	 * airship} info is then inserted in the {@code result} variable from
	 * {@link GetAirshipsCommand}
	 */
	@Override
	protected void internalExecute() throws CommandException
	{

		Long auxLongParameter = Long.parseLong( parameters
				.get( Param.BELLOW_PASSENGERS_NUMBER ) );
		result = listToString(
				airshipsDatabase.getAirshipsThat( new AirshipPredicates.HasPassagersNumberBelowAThreshold(
						auxLongParameter ) ), MessageFormat.format(
						"There are no Airships with less passengers then {0}",
						auxLongParameter ) );
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
	protected String[] getRequiredParameters()
	{

		return new String[]{Param.BELLOW_PASSENGERS_NUMBER};
	}
}
