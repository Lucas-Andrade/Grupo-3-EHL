package airtrafficcontrol.app.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.Latitude;

public class LatitudeTest {
	
	Latitude _lat;

	@Before
	public void newLatitudeValue() throws InvalidArgumentException 
	{
		_lat = new Latitude(60.00);
	}

	
	@Test 
	public void shouldReturnLatitudeValue()
	{
		assertTrue(60.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldIncrementLatitudeWithDoubleValue() throws InvalidArgumentException
	{
		//Arrange
		double lat = 10.00;
		
		//Act
		_lat.incrementLatitude(lat);
		
		//Assert
		assertTrue(70.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldIncrementLatitudeWithAnLatitudeObject() throws InvalidArgumentException
	{
		//Arrange
		Latitude lat = new Latitude(10.00);
		
		//Act
		_lat.incrementLatitude(lat);
		
		//Assert
		assertTrue(70.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldSetLatitudeValueWithADoubleValue() throws InvalidArgumentException
	{
		//Arrange
		double lat = 10.00;
		
		//Act
		_lat.setLatitude(lat);
		
		//Assert
		assertTrue(10.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldSetLatitudeWithAnLatitudeObject() throws InvalidArgumentException
	{
		//Arrange
		Latitude lat = new Latitude(10.00);
		
		//Act
		_lat.setLatitude(lat);
		
		//Assert
		assertTrue(10.00 == _lat.getLatitudeValue());
	}

}
