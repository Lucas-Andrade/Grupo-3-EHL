package airtrafficcontrol.tests;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.AirCorridorInTime;
import airtrafficcontrol.app.utils.AltitudeCorridor;

public class AirCorridorInTimeTest {

	AirCorridorInTime corridorInTime;
	AltitudeCorridor corridor;
	Calendar date1;
	Calendar date2;
	
	@Before
	public void instanciateCorridors() throws InvalidArgumentException
	{
		corridor = new AltitudeCorridor(5,10);
		date1 = new GregorianCalendar();
		date2 = new GregorianCalendar();
		date2.add(12, 20);
		corridorInTime = new AirCorridorInTime(date1, date2, corridor);
	}
	
	@Test
	public void shouldReturnStartingHour() {
		assertTrue(date1.equals(corridorInTime.getStartingHour()));
	}
	
	@Test
	public void shouldReturnEndingHour() {
		assertTrue(date2.equals(corridorInTime.getEndingHour()));
	}
	
	@Test
	public void shouldReturnTheCorridor()
	{
		assertTrue(corridor.equals(corridorInTime.getCorridor()));
		assertEquals(corridor.getLowerLimit(), corridorInTime.getCorridor().getLowerLimit(), 0.01);
		assertEquals(corridor.getUpperLimit(), corridorInTime.getCorridor().getUpperLimit(), 0.01);
	}
	
//	@Test
//	public void shouldReturnTrueAndSetTheStartingHour()
//	{
//		Calendar date3 = new GregorianCalendar();
//		date3.add(12, -20);
//		assertTrue(corridorInTime.setStartingHour(date3));
//	}
//	
//	@Test
//	public void shouldReturnFalseAndNotSetTheStartingHour()
//	{
//		Calendar date3 = new GregorianCalendar();
//		date3.add(12, 50);
//		assertFalse(corridorInTime.setStartingHour(date3));
//	}
//	
//	@Test
//	public void shouldSetTheStartingHour()
//	{
//		Calendar date3 = new GregorianCalendar();
//		date3.add(12, -20);
//		corridorInTime.setStartingHour(date3);
//		assertEquals(date3, corridorInTime.getStartingHour());
//	}
//	
//	@Test
//	public void shouldReturnTrueAndSetTheEndingHour()
//	{
//		Calendar date3 = new GregorianCalendar();
//		date3.add(12, 50);
//		assertTrue(corridorInTime.setEndingHour(date3));
//	}
//	
//	@Test
//	public void shouldReturnFalseAndNotSetTheEndingHour()
//	{
//		Calendar date3 = new GregorianCalendar();
//		date3.add(12, - 50);
//		assertFalse(corridorInTime.setEndingHour(date3));
//	}
//	
//	@Test
//	public void shouldSetTheEndingHour()
//	{
//		Calendar date3 = new GregorianCalendar();
//		date3.add(12, 50);
//		corridorInTime.setEndingHour(date3);
//		assertEquals(date3, corridorInTime.getEndingHour());
//	}
	
	@Test
	public void shouldSwitchTheDatesWhenTheyAreIntroducedInTheWrongOrder() throws InvalidArgumentException
	{
		AirCorridorInTime newAirCorridor = new AirCorridorInTime(date2, date1, corridor);
		
		assertEquals(date1, newAirCorridor.getStartingHour());
		assertEquals(date2, newAirCorridor.getEndingHour());
	}
	
}
