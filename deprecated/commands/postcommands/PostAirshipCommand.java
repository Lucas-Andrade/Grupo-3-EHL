package airtrafficcontrol.deprecated.appforcommandline.commands.postcommands;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.InvalidParameterValueException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.CivilAirship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.airships.MilitaryAirship;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommand extends PostCommand< Airship >
{
	
	private static final String TYPE = "type";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String ALTITUDE = "altitude";
	private static final String MINALTITUDE = "minAltitude";
	private static final String MAXALTITUDE = "maxAltitude";
	private static final String HASARMOUR = "hasArmour";
	private static final String PASSENGERSNUMBER = "nbPassengers";
	private static final String LOGINNAME = "loginName";
	
	/**
	 * Class that implements the {@link GetProducts} factory, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory
	{
		
		private final InMemoryAirshipDatabase airshipDatabase;
		private final InMemoryUserDatabase userDatabase;
		
		public Factory( InMemoryUserDatabase userDatabase,
				InMemoryAirshipDatabase airshipDatabase ) {
			this.userDatabase = userDatabase;
			this.airshipDatabase = airshipDatabase;
		}
		
		@Override
		public Command newInstance( Map< String, String > parameters ) {
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
			Map< String, String > parameters ) {
		super( userDatabase, airshipDatabase, parameters );
	}
	
	/**
	 * @throws InvalidParameterValueException
	 *             If either the received latitude, longitude, altitude, minimum
	 *             altitude of the air corridor or maximum altitude of the air
	 *             corridor have invalid values, not convertible to doubles.
	 * @throws InvalidArgumentException
	 *             If the received type of airship to be posted is unknown or
	 *             the user is not in the expected users database.
	 */
	@Override
	protected void internalPostExecute() throws InvalidParameterValueException,
			InvalidArgumentException {
		
		String methodName = "create" + getParameterAsString( TYPE );
		
		double latitude = getParameterAsDouble( LATITUDE );
		double longitude = getParameterAsDouble( LONGITUDE );
		double altitude = getParameterAsDouble( ALTITUDE );
		double minAltitude = getParameterAsDouble( MINALTITUDE );
		double maxAltitude = getParameterAsDouble( MAXALTITUDE );
		
		
		
		Class< ? extends PostAirshipCommand > c = this.getClass();
		User user = usersDatabase.getElementByIdentification( parameters
				.get( LOGINNAME ) );
		try
		{
			Method creatorMethod = c.getDeclaredMethod( methodName,
					Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE,
					Double.TYPE );
			
			Airship airship = (Airship)creatorMethod.invoke( this, latitude,
					longitude, altitude, maxAltitude, minAltitude );
			
			database.add( airship, user );
			result = airship.getIdentification();
		}
		catch( NoSuchMethodException | NullPointerException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ExceptionInInitializerError e )
		{
			throw new InvalidArgumentException( getParameterAsString( TYPE ),
					TYPE );
		}
	}
	
	
	@Override
	protected String[] getSpecificRequiredParameters() {
		return new String[]{ TYPE, LATITUDE, LONGITUDE, ALTITUDE, MINALTITUDE,
				MAXALTITUDE };
	}
	
	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param maxAltitude
	 * @param minAltitude
	 * @return
	 * @throws InvalidParameterValueException
	 *             If the received passengers number is a string not convertible
	 *             to an integer value.
	 * @throws InvalidArgumentException
	 *             If either the received latitude, longitude, altitude, minimum
	 *             altitude of the air corridor or maximum altitude of the air
	 *             corridor have invalid values.
	 */
	private Airship createCivil( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
			throws InvalidParameterValueException, InvalidArgumentException {
		
		int nbPassengers = getParameterAsInt( PASSENGERSNUMBER );
		return new CivilAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, nbPassengers );
	}
	
	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param maxAltitude
	 * @param minAltitude
	 * @return
	 * @throws InvalidParameterValueException
	 *             If the received information about having armours is not
	 *             convertible to a boolean.
	 * @throws InvalidArgumentException
	 *             If either the received latitude, longitude, altitude, minimum
	 *             altitude of the air corridor or maximum altitude of the air
	 *             corridor have invalid values.
	 */
	private Airship createMilitary( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
			throws InvalidParameterValueException, InvalidArgumentException {
		
		boolean hasWeapons = getParameterAsBoolean( HASARMOUR );
		return new MilitaryAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, hasWeapons );
	}
}
