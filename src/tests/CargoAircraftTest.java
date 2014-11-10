package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import app.InvalidArgumentException;
import utils.CargoAircraft;
import utils.FlightPlan;
import utils.GeographicalPosition;

public class CargoAircraftTest {

	CargoAircraft crg;
	CargoAircraft crg2;
	CargoAircraft crg3;
	CargoAircraft crg4;
	
	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException
	{
		crg = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		crg2 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(13, crg.getNumberOfMinutesToTakeOff());
		assertEquals(13, crg2.getNumberOfMinutesToTakeOff());
		assertEquals(13, crg.getNumberOfMinutesToTakeOff());
		
		crg.setNumberOfMinutesToTakeOff(17);
		
		assertEquals(17, crg.getNumberOfMinutesToTakeOff());
		assertEquals(17, crg2.getNumberOfMinutesToTakeOff());
		assertEquals(17, crg.getNumberOfMinutesToTakeOff());

		crg3 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		crg4 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		
		assertEquals(17, crg3.getNumberOfMinutesToTakeOff());
		assertEquals(17, crg4.getNumberOfMinutesToTakeOff());
		assertEquals(17, crg.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(15, crg.getNumberOfMinutesToLand());
		assertEquals(15, crg2.getNumberOfMinutesToLand());
		assertEquals(15, crg.getNumberOfMinutesToLand());
		
		crg.setNumberOfMinutesToLand(20);
		
		assertEquals(20, crg.getNumberOfMinutesToLand());
		assertEquals(20, crg2.getNumberOfMinutesToLand());
		assertEquals(20, crg.getNumberOfMinutesToLand());

		crg3 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		crg4 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		
		assertEquals(20, crg3.getNumberOfMinutesToLand());
		assertEquals(20, crg4.getNumberOfMinutesToLand());
		assertEquals(20, crg.getNumberOfMinutesToLand());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		
		assertEquals(7, crg.getNumberOfMinutesToSwitchCorridor());
		assertEquals(7, crg2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(7, crg.getNumberOfMinutesToSwitchCorridor());
		
		crg.setNumberOfMinutesToSwitchCorridor(1);
		
		assertEquals(1, crg.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, crg2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, crg.getNumberOfMinutesToSwitchCorridor());

		crg3 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		crg4 = new CargoAircraft("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()));
		
		assertEquals(1, crg3.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, crg4.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, crg.getNumberOfMinutesToSwitchCorridor());
	}

}
