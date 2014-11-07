package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import utils.PrivateJet;
import utils.FlightPlan;
import utils.GeographicalPosition;

public class PrivateJetTest {

	PrivateJet prv;
	PrivateJet prv2;
	PrivateJet prv3;
	PrivateJet prv4;
	
	@Before
	public void constructTwoAirplanes()
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
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() {
		assertEquals(9, prv.getNumberOfMinutesToTakeOff());
		assertEquals(9, prv2.getNumberOfMinutesToTakeOff());
		assertEquals(9, PrivateJet.getNumberOfMinutesToTakeOff());
		
		PrivateJet.setNumberOfMinutesToTakeOff(14);
		
		assertEquals(14, prv.getNumberOfMinutesToTakeOff());
		assertEquals(14, prv2.getNumberOfMinutesToTakeOff());
		assertEquals(14, PrivateJet.getNumberOfMinutesToTakeOff());

		prv3 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		prv4 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(14, prv3.getNumberOfMinutesToTakeOff());
		assertEquals(14, prv4.getNumberOfMinutesToTakeOff());
		assertEquals(14, PrivateJet.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() {
		assertEquals(9, prv.getNumberOfMinutesToLand());
		assertEquals(9, prv2.getNumberOfMinutesToLand());
		assertEquals(9, PrivateJet.getNumberOfMinutesToLand());
		
		PrivateJet.setNumberOfMinutesToLand(20);
		
		assertEquals(20, prv.getNumberOfMinutesToLand());
		assertEquals(20, prv2.getNumberOfMinutesToLand());
		assertEquals(20, PrivateJet.getNumberOfMinutesToLand());

		prv3 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		prv4 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(20, prv3.getNumberOfMinutesToLand());
		assertEquals(20, prv4.getNumberOfMinutesToLand());
		assertEquals(20, PrivateJet.getNumberOfMinutesToLand());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() {
		
		assertEquals(6, prv.getNumberOfMinutesToSwitchCorridor());
		assertEquals(6, prv2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(6, PrivateJet.getNumberOfMinutesToSwitchCorridor());
		
		PrivateJet.setNumberOfMinutesToSwitchCorridor(1);
		
		assertEquals(1, prv.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, prv2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, PrivateJet.getNumberOfMinutesToSwitchCorridor());

		prv3 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		prv4 = new PrivateJet("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(1, prv3.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, prv4.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, PrivateJet.getNumberOfMinutesToSwitchCorridor());
	}

}
