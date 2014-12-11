package airtrafficcontrol.tests.testsforconsole;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.Altitude;
import airtrafficcontrol.app.appforconsole.utils.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.Latitude;
import airtrafficcontrol.app.appforconsole.utils.Longitude;

public class GeographicalPositionTest {

	GeographicalPosition _geoPosObj;
	GeographicalPosition _geoPosVal;
	Latitude _lat;
	Longitude _lon;
	Altitude _alt;

	
	
	@Before
	public void newGepgraphicalPosition() throws InvalidArgumentException 
	{
		_lat = new Latitude(60.00);
		_lon = new Longitude(160.00);
		_alt = new Altitude(1600.00);
		
		_geoPosObj = new GeographicalPosition(_lat, _lon, _alt);
		
		_geoPosVal = new GeographicalPosition(60.00, 160.00, 1600.00);
	}
	
	@Test
	public void shouldReturnLatitudeValue()
	{
		assertTrue(_geoPosObj.getLatitude() == _geoPosVal.getLatitude());
	}

	@Test
	public void shouldReturnLongitudeValue()
	{
		assertTrue(_geoPosObj.getLongitude() == _geoPosVal.getLongitude());
	}
	
	@Test
	public void shouldReturnAltitudeValue()
	{
		assertTrue(_geoPosObj.getAltitude() == _geoPosVal.getAltitude());
	}
	
	@Test
	public void shouldIncrementGeographicalPositionWithLatitudeLongitudeAndAltitudeObjects() throws InvalidArgumentException
	{
		//Arrange
		Latitude lat = new Latitude(10.00);
		Longitude lon = new Longitude(10.00);
		Altitude alt = new Altitude(100.00);
		
		//Act
		_geoPosObj.incrementPosition(lat, lon, alt);
		
		//Assert
		assertTrue(70 == _geoPosObj.getLatitude());
		assertTrue(170 == _geoPosObj.getLongitude());
		assertTrue(1700 == _geoPosObj.getAltitude());
	}
	
	@Test
	public void shouldIncrementGeographicalPositionWithLatitudeLongitudeAndAltitudeValues() throws InvalidArgumentException
	{
		//Act
		_geoPosObj.incrementPosition(10, 10, 100);
		
		//Assert
		assertTrue(70 == _geoPosObj.getLatitude());
		assertTrue(170 == _geoPosObj.getLongitude());
		assertTrue(1700 == _geoPosObj.getAltitude());
	}
	

	@Test
	public void shouldSetsALatitudeInAGeographicalPositionWithAValue() throws InvalidArgumentException
	{
		//Act
		_geoPosObj.setLatitude(70);
	
		//Assert
		assertTrue(70 == _geoPosObj.getLatitude());
	}
	
	@Test
	public void shouldSetsALatitudeInAGeographicalPositionWithAnObject() throws InvalidArgumentException
	{
		//Arrange
		Latitude lat = new Latitude(70);
		
		//Act
		_geoPosObj.setLatitude(lat);
	
		//Assert
		assertTrue(70 == _geoPosObj.getLatitude());
	}
	
	@Test
	public void shouldSetsALongitudeInAGeographicalPositionWithAValue() throws InvalidArgumentException
	{
		//Act
		_geoPosObj.setLongitude(70);
	
		//Assert
		assertTrue(70 == _geoPosObj.getLongitude());
	}
	
	@Test
	public void shouldSetsALongitudeInAGeographicalPositionWithAnObject() throws InvalidArgumentException
	{
		//Arrange
		Longitude lon = new Longitude(70);
		
		//Act
		_geoPosObj.setLongitude(lon);
	
		//Assert
		assertTrue(70 == _geoPosObj.getLongitude());
	}
	
	@Test
	public void shouldSetsAnAltitudeInAGeographicalPositionWithAValue() throws InvalidArgumentException
	{
		//Act
		_geoPosObj.setAltitude(11000.00);
	
		//Assert
		assertTrue(11000.00 == _geoPosObj.getAltitude());
	}
	
	@Test
	public void shouldSetsAnAltitudeInAGeographicalPositionWithAnObject() throws InvalidArgumentException
	{
		//Arrange
		Altitude alt = new Altitude(11000.00);
		
		//Act
		_geoPosObj.setAltitude(alt);
	
		//Assert
		assertTrue(11000.00 == _geoPosObj.getAltitude());
	}
	
	@Test
	public void shouldIncrementsALatitudeInAGeographicalPositionWithAValue() throws InvalidArgumentException
	{
		//Act
		_geoPosObj.incrementLatitude(10);;
	
		//Assert
		assertTrue(70 == _geoPosObj.getLatitude());
	}
	
	@Test
	public void shouldIncrementsALatitudeInAGeographicalPositionWithAnObject() throws InvalidArgumentException
	{
		//Arrange
		Latitude lat = new Latitude(10);
		
		//Act
		_geoPosObj.incrementLatitude(lat);;
	
		//Assert
		assertTrue(70 == _geoPosObj.getLatitude());
	}
	
	@Test
	public void shouldIncrementsALongitudeInAGeographicalPositionWithAValue()
	{
		//Act
		_geoPosObj.incrementLongitude(10);
	
		//Assert
		assertTrue(170 == _geoPosObj.getLongitude());
	}
	
	@Test
	public void shouldIncrementsALongitudeInAGeographicalPositionWithAnObject() throws InvalidArgumentException
	{
		//Arrange
		Longitude lon = new Longitude(10);
		
		//Act
		_geoPosObj.incrementLongitude(lon);
	
		//Assert
		assertTrue(170 == _geoPosObj.getLongitude());
	}
	
	@Test
	public void shouldIncrementsAnAltitudeInAGeographicalPositionWithAValue()
	{
		//Act
		_geoPosObj.incrementAltitude(1000.00);
	
		//Assert
		assertTrue(2600.00 == _geoPosObj.getAltitude());
	}
	
	@Test
	public void shouldIncrementsAnAltitudeInAGeographicalPositionWithAnObject() throws InvalidArgumentException
	{
		//Arrange
		Altitude alt = new Altitude(1000.00);
		
		//Act
		_geoPosObj.incrementAltitude(alt);;
	
		//Assert
		assertTrue(2600.00 == _geoPosObj.getAltitude());
	}
}
