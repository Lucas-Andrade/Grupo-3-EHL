package airtrafficcontrol.app.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.Altitude;

public class AltitudeTest {
	
	Altitude _alt;

	@Before
	public void newAltitudeValue() throws InvalidArgumentException 
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
	public void shouldIncrementAltitudeWithAnAltitudeObject() throws InvalidArgumentException
	{
		//Arrange
		Altitude alt = new Altitude(10.00);
		
		//Act
		_alt.incrementAltitude(alt);
		
		//Assert
		assertTrue(70.00 == _alt.getAltitudeValue());
	}
	
	@Test 
	public void shouldSetAltitudeValueWithADoubleValue() throws InvalidArgumentException
	{
		//Arrange
		double alt = 10.00;
		
		//Act
		_alt.setAltitude(alt);
		
		//Assert
		assertTrue(10.00 == _alt.getAltitudeValue());
	}
	
	@Test 
	public void shouldSetAltitudeWithAnAltitudeObject() throws InvalidArgumentException
	{
		//Arrange
		Altitude alt = new Altitude(10.00);
		
		//Act
		_alt.setAltitude(alt);
		
		//Assert
		assertTrue(10.00 == _alt.getAltitudeValue());
	}
	
}
