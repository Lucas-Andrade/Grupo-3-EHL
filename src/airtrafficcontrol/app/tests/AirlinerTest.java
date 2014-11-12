package airtrafficcontrol.app.tests;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.utils.Airliner;
import airtrafficcontrol.app.utils.FlightPlan;
import airtrafficcontrol.app.utils.GeographicalPosition;

public class AirlinerTest {
	
	Airliner arl;
	Airliner arl2;
	Airliner arl3;
	Airliner arl4;
	
	@Before
	public void constructTwoAirplanes()
	{
		arl = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()), 202);
		arl2 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()), 441);
	}
	
	@Test
	public void shouldReturnTheCorredtNumberOfPassengers()
	{
		assertEquals(202, arl.getPassengersNumber());
		assertEquals(441, arl2.getPassengersNumber());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() {
		assertEquals(8, arl.getNumberOfMinutesToTakeOff());
		assertEquals(8, arl2.getNumberOfMinutesToTakeOff());
		assertEquals(8, Airliner.getNumberOfMinutesToTakeOff());
		
		Airliner.setNumberOfMinutesToTakeOff(14);
		
		assertEquals(14, arl.getNumberOfMinutesToTakeOff());
		assertEquals(14, arl2.getNumberOfMinutesToTakeOff());
		assertEquals(14, Airliner.getNumberOfMinutesToTakeOff());

		arl3 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		arl4 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(14, arl3.getNumberOfMinutesToTakeOff());
		assertEquals(14, arl4.getNumberOfMinutesToTakeOff());
		assertEquals(14, Airliner.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() {
		assertEquals(10, arl.getNumberOfMinutesToLand());
		assertEquals(10, arl2.getNumberOfMinutesToLand());
		assertEquals(10, Airliner.getNumberOfMinutesToLand());
		
		Airliner.setNumberOfMinutesToLand(20);
		
		assertEquals(20, arl.getNumberOfMinutesToLand());
		assertEquals(20, arl2.getNumberOfMinutesToLand());
		assertEquals(20, Airliner.getNumberOfMinutesToLand());

		arl3 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		arl4 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(20, arl3.getNumberOfMinutesToLand());
		assertEquals(20, arl4.getNumberOfMinutesToLand());
		assertEquals(20, Airliner.getNumberOfMinutesToLand());
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture() {
		
		assertEquals(4, arl.getNumberOfMinutesToSwitchCorridor());
		assertEquals(4, arl2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(4, Airliner.getNumberOfMinutesToSwitchCorridor());
		
		Airliner.setNumberOfMinutesToSwitchCorridor(1);
		
		assertEquals(1, arl.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, arl2.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, Airliner.getNumberOfMinutesToSwitchCorridor());

		arl3 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		arl4 = new Airliner("mgrf", new GeographicalPosition(0,0,0), 
				new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()),1);
		
		assertEquals(1, arl3.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, arl4.getNumberOfMinutesToSwitchCorridor());
		assertEquals(1, Airliner.getNumberOfMinutesToSwitchCorridor());
	}
}
