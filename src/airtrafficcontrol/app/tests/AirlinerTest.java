package airtrafficcontrol.app.tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.Airliner;
import airtrafficcontrol.app.utils.FlightPlan;
import airtrafficcontrol.app.utils.GeographicalPosition;
import airtrafficcontrol.app.utils.ReadListOfFlights;
import airtrafficcontrol.app.utils.ReportGenerator;

public class AirlinerTest {
	
	Calendar date1;
	Calendar date2;
	Airliner arl;
	Airliner arl2;
	Airliner arl3;
	Airliner arl4;
	
	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException
	{
		date1 = new GregorianCalendar();
		date2 = ReadListOfFlights.createDefensiveCopyOfTheDate(date1);
		date2.add(Calendar.MINUTE, 20);
		
		arl = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2), 202);
		arl2 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2), 441);
	}
	
	@Test
	public void shouldReturnTheCorredtNumberOfPassengers()
	{
		assertEquals(202, arl.getPassengersNumber());
		assertEquals(441, arl2.getPassengersNumber());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(8, arl.getNumberOfMinutesToTakeOff());
		assertEquals(8, arl2.getNumberOfMinutesToTakeOff());
		assertEquals(8, arl.getNumberOfMinutesToTakeOff());
		
		arl.setNumberOfMinutesToTakeOff(14);
		
		assertEquals(14, arl.getNumberOfMinutesToTakeOff());
		assertEquals(14, arl2.getNumberOfMinutesToTakeOff());
		assertEquals(14, arl.getNumberOfMinutesToTakeOff());

		arl3 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2),1);
		arl4 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2),1);
		
		assertEquals(14, arl3.getNumberOfMinutesToTakeOff());
		assertEquals(14, arl4.getNumberOfMinutesToTakeOff());
		assertEquals(14, arl.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		assertEquals(10, arl.getNumberOfMinutesToLand());
		assertEquals(10, arl2.getNumberOfMinutesToLand());
		assertEquals(10, arl.getNumberOfMinutesToLand());
		
		arl.setNumberOfMinutesToLand(20);
		
		assertEquals(20, arl.getNumberOfMinutesToLand());
		assertEquals(20, arl2.getNumberOfMinutesToLand());
		assertEquals(20, arl.getNumberOfMinutesToLand());

		arl3 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2),1);
		arl4 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2),1);
		
		assertEquals(20, arl3.getNumberOfMinutesToLand());
		assertEquals(20, arl4.getNumberOfMinutesToLand());
		assertEquals(20, arl.getNumberOfMinutesToLand());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() throws InvalidArgumentException {
		
		assertEquals(4, arl.getNumberOfMinutesToSwitchCorridor());
		assertEquals(4, arl2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(4, arl.getNumberOfMinutesToSwitchCorridor());
		
		arl.setNumberOfMinutesToSwitchCorridor(1);
		
		assertEquals(1, arl.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, arl2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, arl.getNumberOfMinutesToSwitchCorridor());

		arl3 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2),1);
		arl4 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(date1, date2),1);
		
		assertEquals(1, arl3.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, arl4.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, arl.getNumberOfMinutesToSwitchCorridor());
	}
}
