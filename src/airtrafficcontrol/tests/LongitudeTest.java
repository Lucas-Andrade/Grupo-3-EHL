package airtrafficcontrol.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.Longitude;

public class LongitudeTest {
	
	Longitude _lon;

	@Before
	public void newLongitudeValue() throws InvalidArgumentException 
	{
		_lon = new Longitude(60.00);
	}

	
	@Test 
	public void shouldReturnLongitudeValue()
	{
		assertTrue(60.00 == _lon.getLongitudeValue());
	}
	
	@Test 
	public void shouldIncrementLongitudeWithDoubleValue()
	{
		//Arrange
		double lon = 10.00;
		
		//Act
		_lon.incrementLongitude(lon);
		
		//Assert
		assertTrue(70.00 == _lon.getLongitudeValue());
	}
	
	@Test 
	public void shouldIncrementLongitudeWithAnLongitudeObject() throws InvalidArgumentException
	{
		//Arrange
		Longitude lon = new Longitude(10.00);
		
		//Act
		_lon.incrementLongitude(lon);
		
		//Assert
		assertTrue(70.00 == _lon.getLongitudeValue());
	}
	
	@Test 
	public void shouldSetLongitudeValueWithADoubleValue() throws InvalidArgumentException
	{
		//Arrange
		double lon = 10.00;
		
		//Act
		_lon.setLongitude(lon);
		
		//Assert
		assertTrue(10.00 == _lon.getLongitudeValue());
	}
	
	@Test 
	public void shouldSetLongitudeWithAnLongitudeObject() throws InvalidArgumentException
	{
		//Arrange
		Longitude lon = new Longitude(10.00);
		
		//Act
		_lon.setLongitude(lon);
		
		//Assert
		assertTrue(10.00 == _lon.getLongitudeValue());
	}

}
