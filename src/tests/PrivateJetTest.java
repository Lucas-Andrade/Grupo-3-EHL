package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import app.InvalidArgumentException;
import utils.PrivateJet;
import utils.FlightPlan;
import utils.GeographicalPosition;

public class PrivateJetTest {

	PrivateJet prv;
	PrivateJet prv2;
	PrivateJet prv3;
	PrivateJet prv4;
	
	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException
	{
		prv = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()), 10);
		prv2 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()), 12);
	}
	
	@Test
	public void shouldReturnTheCorredtNumberOfPassengers()
	{
		assertEquals(10, prv.getPassengersNumber());
		assertEquals(12, prv2.getPassengersNumber());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(9, prv.getNumberOfMinutesToTakeOff());
		assertEquals(9, prv2.getNumberOfMinutesToTakeOff());
		assertEquals(9, prv.getNumberOfMinutesToTakeOff());
		
		prv.setNumberOfMinutesToTakeOff(14);
		
		assertEquals(14, prv.getNumberOfMinutesToTakeOff());
		assertEquals(14, prv2.getNumberOfMinutesToTakeOff());
		assertEquals(14, prv.getNumberOfMinutesToTakeOff());

		prv3 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		prv4 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(14, prv3.getNumberOfMinutesToTakeOff());
		assertEquals(14, prv4.getNumberOfMinutesToTakeOff());
		assertEquals(14, prv.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(9, prv.getNumberOfMinutesToLand());
		assertEquals(9, prv2.getNumberOfMinutesToLand());
		assertEquals(9, prv.getNumberOfMinutesToLand());
		
		prv.setNumberOfMinutesToLand(20);
		
		assertEquals(20, prv.getNumberOfMinutesToLand());
		assertEquals(20, prv2.getNumberOfMinutesToLand());
		assertEquals(20, prv.getNumberOfMinutesToLand());

		prv3 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		prv4 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(20, prv3.getNumberOfMinutesToLand());
		assertEquals(20, prv4.getNumberOfMinutesToLand());
		assertEquals(20, prv.getNumberOfMinutesToLand());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		
		assertEquals(6, prv.getNumberOfMinutesToSwitchCorridor());
		assertEquals(6, prv2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(6, prv.getNumberOfMinutesToSwitchCorridor());
		
		prv.setNumberOfMinutesToSwitchCorridor(1);
		
		assertEquals(1, prv.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, prv2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, prv.getNumberOfMinutesToSwitchCorridor());

		prv3 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		prv4 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(1, prv3.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, prv4.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, prv.getNumberOfMinutesToSwitchCorridor());
	}

}
