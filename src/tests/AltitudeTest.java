package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.Altitude;

public class AltitudeTest {
	
	Altitude _alt;

	@Before
	public void newAltitudeValue() 
	{
		_alt = new Altitude(60.00);
	}

	
	@Test 
	public void shouldReturnAltitudeValue()
	{
		assertTrue(60.00 == _alt.getAltitudeValue());
	}
	
	@Test 
	public void shouldIncrementAltitudeWithDoubleValue()
	{
		//Arrange
		double alt = 10.00;
		
		//Act
		_alt.incrementAltitude(alt);
		
		//Assert
		assertTrue(70.00 == _alt.getAltitudeValue());
	}
	
	@Test 
	public void shouldIncrementAltitudeWithAnAltitudeObject()
	{
		//Arrange
		Altitude alt = new Altitude(10.00);
		
		//Act
		_alt.incrementAltitude(alt);
		
		//Assert
		assertTrue(70.00 == _alt.getAltitudeValue());
	}
	
	@Test 
	public void shouldSetAltitudeValueWithADoubleValue()
	{
		//Arrange
		double alt = 10.00;
		
		//Act
		_alt.setAltitude(alt);
		
		//Assert
		assertTrue(10.00 == _alt.getAltitudeValue());
	}
	
	@Test 
	public void shouldSetAltitudeWithAnAltitudeObject()
	{
		//Arrange
		Altitude alt = new Altitude(10.00);
		
		//Act
		_alt.setAltitude(alt);
		
		//Assert
		assertTrue(10.00 == _alt.getAltitudeValue());
	}
	
}
