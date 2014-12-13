package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.*;
import airtrafficcontrol.app.appforcommandline.model.airships.*;
import airtrafficcontrol.app.appforcommandline.model.users.*;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.*;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommand extends PostCommand<Airship>
{
	/**
	 * Bellow all static Strings used in this {@link Command} that will be
	 * needed to create an {@link Airship}.
	 * 
	 * Type -> {@Civil} or {@code Military} Latitude, longitude,
	 * altitude, minAltitude and maxAltitude -> {@link Airship} constructor.
	 * NbPassengers -> {@link CivilAirship} constructor. HasArmour ->
	 * {@link MilitaryAirship} constructor. LoginName -> validate {@link User}.
	 */
	private static final String TYPE = "type";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String ALTITUDE = "altitude";
	private static final String MINALTITUDE = "minAltitude";
	private static final String MAXALTITUDE = "maxAltitude";
	private static final String HASARMOUR = "hasArmour";
	private static final String PASSENGERSNUMBER = "nbPassengers";
	private static final String LOGINNAME = "loginName";

	private static final String[] REQUIREDPARAMETERS = {TYPE, LATITUDE,
			LONGITUDE, ALTITUDE, MINALTITUDE, MAXALTITUDE};

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
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	@Override
	protected void internalPostExecute() throws CommandException
	{
		String methodName = "create" + TYPE;

		double latitude = getParameterAsDouble( LATITUDE );
		double longitude = getParameterAsDouble( LONGITUDE );
		double altitude = getParameterAsDouble( ALTITUDE );
		double minAltitude = getParameterAsDouble( MINALTITUDE );
		double maxAltitude = getParameterAsDouble( MAXALTITUDE );



		Class<? extends PostAirshipCommand> c = this.getClass();
		User user = usersDatabase.getElementByIdentification( parameters
				.get( LOGINNAME ) );
		try
		{
			Method creatorMethod = c.getMethod( methodName, ( Class<?>[] )null );
			Airship airship = ( Airship )creatorMethod.invoke( this, latitude,
					longitude, altitude, maxAltitude, minAltitude );
			database.add( airship, user );
			result = airship.getIdentification();
		}
		catch( Exception e )
		{
			throw new CommandException( MessageFormat.format(
					"Error finding method to create a {0}", TYPE ), e );
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
		return REQUIREDPARAMETERS;
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
	 * @throws RequiredParameterNotPresentException
	 * @throws InvalidParameterValueException
	 */
	@SuppressWarnings( "unused" )
	// reflection
	private Airship createCivil( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
			throws RequiredParameterNotPresentException,
			InvalidParameterValueException
	{
		if( ! parameters.containsKey( PASSENGERSNUMBER ) )
			throw new RequiredParameterNotPresentException( PASSENGERSNUMBER );

		int nbPassengers = getParameterAsInt( PASSENGERSNUMBER );

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
	 * @throws RequiredParameterNotPresentException
	 * @throws InvalidParameterValueException
	 */
	@SuppressWarnings( "unused" )
	// reflection
	private Airship createMilitary( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
			throws RequiredParameterNotPresentException,
			InvalidParameterValueException
	{
		if( ! parameters.containsKey( HASARMOUR ) )
			throw new RequiredParameterNotPresentException( HASARMOUR );

		boolean hasWeapons = getParameterAsBoolean( HASARMOUR );

		return new MilitaryAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, hasWeapons );
	}
}
