package main.java.commands.postCommands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import main.java.commands.AbstractCommand;
import main.java.commands.Command;
import main.java.commands.CommandFactory;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.exceptions.commandExecptions.InvalidParameterValueException;
import main.java.exceptions.commandExecptions.MissingRequiredParameterException;
import main.java.exceptions.commandExecptions.WrongLoginPasswordException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.Param;
import main.java.model.airships.Airship;
import main.java.model.airships.CivilAirship;
import main.java.model.airships.InMemoryAirshipDatabase;
import main.java.model.airships.MilitaryAirship;
import main.java.model.users.InMemoryUserDatabase;
import main.java.model.users.User;


/**
 * Class whose instances have the point to create an Airship.
 * 
 * This command instances are created and used according to the AbstratFactory design pattern,
 * making use of a class {@link Factory} that implements the {@link CommandFactory} Interface whose
 * only method is the {@link CommandFactory#newInstance(Map) newInstance(Map)} method that will
 * allow new command instances to be created.
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
	 * Create a {@code PostAirshipCommand}.
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
