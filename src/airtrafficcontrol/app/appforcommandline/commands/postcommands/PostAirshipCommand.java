package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import java.lang.reflect.Method;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.*;
import airtrafficcontrol.app.appforcommandline.model.airships.*;
import airtrafficcontrol.app.appforcommandline.model.users.*;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommand extends PostCommand
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

	@Override
	protected void internalPostExecute() throws CommandException
	{
		String methodName = "create" + TYPE;

		String airshipType = parameters.get( TYPE );
		double latitude = Double.parseDouble( parameters.get( LATITUDE ) );
		double longitude = Double.parseDouble( parameters.get( LONGITUDE ) );
		double altitude = Double.parseDouble( parameters.get( ALTITUDE ) );
		double minAltitude = Double.parseDouble( parameters.get( MINALTITUDE ) );
		double maxAltitude = Double.parseDouble( parameters.get( MAXALTITUDE ) );

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
			throw new CommandException( "Error finding method to create a "
					+ TYPE, e );
		}
	}


	@Override
	protected String[] getSpecificRequiredParameters()
	{
		return new String[]{TYPE, LATITUDE, LONGITUDE, ALTITUDE, MINALTITUDE,
				MAXALTITUDE};
	}


	private Airship createCivil( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
	{
		int nbPassengers = Integer
				.parseInt( parameters.get( PASSENGERSNUMBER ) );
		return new CivilAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, nbPassengers );
	}

	private Airship createMilitary( double latitude, double longitude,
			double altitude, double maxAltitude, double minAltitude )
	{
		boolean hasWeapons = Boolean.parseBoolean( parameters.get( HASARMOUR ) );
		return new MilitaryAirship( latitude, longitude, altitude, maxAltitude,
				minAltitude, hasWeapons );
	}
}
