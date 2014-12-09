package airtrafficcontrol.tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.Altitude;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.Latitude;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.Longitude;

public class AirCraftCoordinatesTests
{
	private GeographicalPosition gPosition;
	
	@Before
	public void before()
	{
		//Arrange
		try
		{
			Latitude latitude = new Latitude( 10 );
			Longitude longitude = new Longitude( 20 );
			Altitude altitude = new Altitude( 100 );
			gPosition = new GeographicalPosition( latitude, longitude, altitude );

		}
		catch( InvalidArgumentException e )
		{
			e.printStackTrace();
		}
	}
	@Test
	public void test()
	{	
		//Assert
		assertTrue( gPosition.getLatitude() == 10);
		assertTrue( gPosition.getLongitude() == 20);
		assertTrue( gPosition.getAltitude() == 100);
	}

}
