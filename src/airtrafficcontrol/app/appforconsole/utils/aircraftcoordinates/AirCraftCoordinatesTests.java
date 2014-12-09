package airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;


/**
 * This tests verifies if the class AirCraftCoordinates is well defined
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class AirCraftCoordinatesTests
{
	private GeographicalPosition gPosition;
	
	
	/**
	 * This method will be run before any test in the class.
	 */
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
	
	/**
	 * This is the test to the method that we want to verify
	 */
	@Test
	public void test()
	{	
		//Assert
		assertTrue( gPosition.getLatitude() == 10);
		assertTrue( gPosition.getLongitude() == 20);
		assertTrue( gPosition.getAltitude() == 100);
	}

}
