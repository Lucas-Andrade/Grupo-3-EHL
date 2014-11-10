package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import app.InvalidArgumentException;
import utils.AltitudeCorridor;
import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.Transport;

public class TransportTest {
	
	Transport trp;
	Transport trp2;
	Transport trp3;
	Transport trp4;
	
	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException
	{
		trp = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		trp2 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),true);
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(10, trp.getNumberOfMinutesToTakeOff());
		assertEquals(10, trp2.getNumberOfMinutesToTakeOff());
		assertEquals(10, trp.getNumberOfMinutesToTakeOff());
		
		trp.setNumberOfMinutesToTakeOff(15);
		
		assertEquals(15, trp.getNumberOfMinutesToTakeOff());
		assertEquals(15, trp2.getNumberOfMinutesToTakeOff());
		assertEquals(15, trp.getNumberOfMinutesToTakeOff());

		trp3 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		trp4 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		
		assertEquals(15, trp3.getNumberOfMinutesToTakeOff());
		assertEquals(15, trp4.getNumberOfMinutesToTakeOff());
		assertEquals(15, trp.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(12, trp.getNumberOfMinutesToLand());
		assertEquals(12, trp2.getNumberOfMinutesToLand());
		assertEquals(12, trp.getNumberOfMinutesToLand());
		
		trp.setNumberOfMinutesToLand(20);
		
		assertEquals(20, trp.getNumberOfMinutesToLand());
		assertEquals(20, trp2.getNumberOfMinutesToLand());
		assertEquals(20, trp.getNumberOfMinutesToLand());

		trp3 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		trp4 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		
		assertEquals(20, trp3.getNumberOfMinutesToLand());
		assertEquals(20, trp4.getNumberOfMinutesToLand());
		assertEquals(20, trp.getNumberOfMinutesToLand());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		
		assertEquals(5, trp.getNumberOfMinutesToSwitchCorridor());
		assertEquals(5, trp2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(5, trp.getNumberOfMinutesToSwitchCorridor());
		
		trp.setNumberOfMinutesToSwitchCorridor(1);
		
		assertEquals(1, trp.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, trp2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, trp.getNumberOfMinutesToSwitchCorridor());

		trp3 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		trp4 = new Transport("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),false);
		
		assertEquals(1, trp3.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, trp4.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, trp.getNumberOfMinutesToSwitchCorridor());
	}
	
	@Test
	public void shouldReturnWhetherTheAirplaneHasArmamentOrNot()
	{
		assertTrue(trp2.hasArmament());
		assertFalse(trp.hasArmament());
	}
}
