package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Longitude;

public class LongitudeTest {
	
	Longitude _lon;

	@Before
	public void newLongitudeValue() 
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
	public void shouldIncrementLongitudeWithAnLongitudeObject()
	{
		//Arrange
		Longitude lon = new Longitude(10.00);
		
		//Act
		_lon.incrementLongitude(lon);
		
		//Assert
		assertTrue(70.00 == _lon.getLongitudeValue());
	}
	
	@Test 
	public void shouldSetLongitudeValueWithADoubleValue()
	{
		//Arrange
		double lon = 10.00;
		
		//Act
		_lon.setLongitude(lon);
		
		//Assert
		assertTrue(10.00 == _lon.getLongitudeValue());
	}
	
	@Test 
	public void shouldSetLongitudeWithAnLongitudeObject()
	{
		//Arrange
		Longitude lon = new Longitude(10.00);
		
		//Act
		_lon.setLongitude(lon);
		
		//Assert
		assertTrue(10.00 == _lon.getLongitudeValue());
	}

}
