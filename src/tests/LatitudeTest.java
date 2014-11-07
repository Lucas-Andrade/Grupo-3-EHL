package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Latitude;

public class LatitudeTest {
	
	Latitude _lat;

	@Before
	public void newLatitudeValue() 
	{
		_lat = new Latitude(60.00);
	}

	
	@Test 
	public void shouldReturnLatitudeValue()
	{
		assertTrue(60.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldIncrementLatitudeWithDoubleValue()
	{
		//Arrange
		double lat = 10.00;
		
		//Act
		_lat.incrementLatitude(lat);
		
		//Assert
		assertTrue(70.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldIncrementLatitudeWithAnLatitudeObject()
	{
		//Arrange
		Latitude lat = new Latitude(10.00);
		
		//Act
		_lat.incrementLatitude(lat);
		
		//Assert
		assertTrue(70.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldSetLatitudeValueWithADoubleValue()
	{
		//Arrange
		double lat = 10.00;
		
		//Act
		_lat.setLatitude(lat);
		
		//Assert
		assertTrue(10.00 == _lat.getLatitudeValue());
	}
	
	@Test 
	public void shouldSetLatitudeWithAnLatitudeObject()
	{
		//Arrange
		Latitude lat = new Latitude(10.00);
		
		//Act
		_lat.setLatitude(lat);
		
		//Assert
		assertTrue(10.00 == _lat.getLatitudeValue());
	}

}
