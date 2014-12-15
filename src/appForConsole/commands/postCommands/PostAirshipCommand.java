package appForConsole.commands.postCommands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.exceptions.commandExecptions.InvalidParameterValueException;
import appForConsole.exceptions.commandExecptions.MissingRequiredParameterException;
import appForConsole.exceptions.commandExecptions.WrongLoginPasswordException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import appForConsole.model.Param;
import appForConsole.model.airships.Airship;
import appForConsole.model.airships.CivilAirship;
import appForConsole.model.airships.InMemoryAirshipDatabase;
import appForConsole.model.airships.MilitaryAirship;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;


/**
 * Class whose instances have the point to create an Airship.
 * TODO
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommand extends PostCommand<Airship>
{
	/**
	 * Class that implements the {@link CommandFactory}, with the point to
	 * create an instance of {@link PostAirshipCommand}.
	 */
	public static class Factory implements CommandFactory
	{
		private final InMemoryAirshipDatabase airshipDatabase;
		private final InMemoryUserDatabase userDatabase;

		public Factory( InMemoryUserDatabase userDatabase,
				InMemoryAirshipDatabase airshipDatabase )
		{
			this.userDatabase = userDatabase;
			this.airshipDatabase = airshipDatabase;
		}

		@Override
		public Command newInstance( Map<String, String> parameters )
		{
			return new PostAirshipCommand( userDatabase, airshipDatabase,
					parameters );
		}
	}

	/**
	 * TODO
	 * 
	 * @param userDatabase
	 * @param airshipDatabase
	 * @param parameters
	 */
	public PostAirshipCommand( InMemoryUserDatabase userDatabase,
			InMemoryAirshipDatabase airshipDatabase,
			Map<String, String> parameters )
	{
		super( userDatabase, airshipDatabase, parameters );
	}

	/**
	 * Create an {@link Airship}, depending on the pretending {@code TYPE}. If
	 * TYPE is equal to: <li>Civil, create a {@link CivilAirship} <li>Military,
	 * create a {@link MilitaryAirship} Then the {@link Airship} info is passed
	 * to the {@link AbstractCommand} field {@code result}.
	 * @throws CommandException 
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	@Override
	protected void internalPostExecute() throws CommandException
	{
		String methodName = "create" + getParameterAsString( Param.TYPE );

		double latitude = getParameterAsDouble( Param.LATITUDE );
		double longitude = getParameterAsDouble( Param.LONGITUDE );
		double altitude = getParameterAsDouble( Param.ALTITUDE );
		double minAltitude = getParameterAsDouble( Param.MINALTITUDE );
		double maxAltitude = getParameterAsDouble( Param.MAXALTITUDE );



		Class<? extends PostAirshipCommand> c = this.getClass();
		User user = usersDatabase.getElementByIdentification( parameters
				.get( Param.LOGINNAME ) );


		try
		{
			Method creatorMethod = c.getDeclaredMethod( methodName, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE );
			Airship airship = ( Airship )creatorMethod.invoke( this, latitude,
					longitude, altitude, maxAltitude, minAltitude );
			
			database.add( airship, user );
			result = airship.getIdentification();
		}
		catch( InvocationTargetException e )
		{
			try
			{
				throw e.getTargetException();
			}
			catch( MissingRequiredParameterException e1)
			{
				throw e1;
			}
			catch( InvalidParameterValueException e1)
			{
				throw e1;
			}
			catch( Throwable e1 )
			{
				// If this happen make a call to the God of Java
				e1.printStackTrace();
			}
		}
		catch( NoSuchMethodException | SecurityException | IllegalAccessException |  IllegalArgumentException e )
		{
			throw new InvalidParameterValueException( Param.TYPE, getParameterAsString(Param.TYPE) );
		}
	}



	/**
	 * Returns an array of {@code String}s that has the names of the parameters
	 * (to be validate in {@link AbstractCommand}) without whom the command
	 * cannot execute: TYPE, LATITUDE, LONGITUDE, ALTITUDE, MINALTITUDE,
	 * MAXALTITUDE
	 * 
	 * @return An array of {@link String}s that has the names of the parameters
	 *         without whom the command cannot execute.
	 */
	@Override
	protected String[] getSpecificRequiredParameters()
	{
		return Param.REQUIRED_POST_AIRSHIP_PARAMETERS;
	}


	/**
	 * This private method is used in {@Code reflection}.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param maxAltitude
	 * @param minAltitude
	 * @return
	 * @throws MissingRequiredParameterException
	 * @throws InvalidParameterValueException
	 */
	@SuppressWarnings( "unused" )
	// reflection
	private Airship createCivil( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
					throws MissingRequiredParameterException, InvalidParameterValueException
	{
		if( ! parameters.containsKey( Param.PASSENGERSNUMBER ) )
			throw new MissingRequiredParameterException( Param.PASSENGERSNUMBER );

		int nbPassengers = getParameterAsInt( Param.PASSENGERSNUMBER );

		return new CivilAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, nbPassengers );
	}

	/**
	 * This private method is used in {@Code reflection}.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param maxAltitude
	 * @param minAltitude
	 * @return
	 * @throws MissingRequiredParameterException
	 * @throws InvalidParameterValueException
	 */
	@SuppressWarnings( "unused" )
	// reflection
	private Airship createMilitary( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
					throws MissingRequiredParameterException,
					InvalidParameterValueException
	{
		if( ! parameters.containsKey( Param.HASARMOUR ) )
			throw new MissingRequiredParameterException( Param.HASARMOUR );

		boolean hasWeapons = getParameterAsBoolean( Param.HASARMOUR );

		return new MilitaryAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, hasWeapons );
	}
}
