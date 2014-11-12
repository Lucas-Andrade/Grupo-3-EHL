package airtrafficcontrol.app.tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.utils.AirCorridorInTime;
import airtrafficcontrol.app.utils.AltitudeCorridor;
import airtrafficcontrol.app.utils.FlightPlan;

public class FlightPlanTest {

	Calendar hourDep;
	Calendar hourLand;
	FlightPlan plan;
	
	AirCorridorInTime firstCorridor;
	AirCorridorInTime secondCorridor;
	AirCorridorInTime gainingAltitude;
	AirCorridorInTime switchingCorridors;
	AirCorridorInTime landing;
	
	Calendar startFirst;
	Calendar endFirst;
	Calendar startSecond;
	Calendar endSecond;
	
	AltitudeCorridor firstAltCorridor;
	AltitudeCorridor secondAltCorridor;
	
	int maxAltFirst;
	int minAltFirst;
	int maxAltSecond;
	int minAltSecond;
	
	@Before
	public void constructThePlan()
	{
		maxAltFirst = 100;
		minAltFirst = 120;
		maxAltSecond = 150;
		minAltSecond = 170;
		
		firstAltCorridor = new AltitudeCorridor(maxAltFirst, minAltFirst);
		secondAltCorridor = new AltitudeCorridor(maxAltSecond, minAltSecond);
		
		hourDep = new GregorianCalendar();
		hourLand = new GregorianCalendar();
		startFirst = new GregorianCalendar();
		endFirst = new GregorianCalendar();
		startSecond = new GregorianCalendar();
		endSecond = new GregorianCalendar();
		
		startFirst.add(12,20);
		endFirst.add(12,40);
		startSecond.add(12,50);
		endSecond.add(12,70);
		hourLand.add(12,80);
		
		gainingAltitude = new AirCorridorInTime(hourDep, startFirst, null);
		firstCorridor = new AirCorridorInTime(startFirst, endFirst, firstAltCorridor);
		switchingCorridors = new AirCorridorInTime(endFirst, startSecond, null);
		secondCorridor = new AirCorridorInTime(startSecond, endSecond, secondAltCorridor);
		landing = new AirCorridorInTime(endSecond, hourLand, null);
		
		plan = new FlightPlan(hourDep, hourLand);
		
		plan.addEvent(gainingAltitude);
		plan.addEvent(firstCorridor);
		plan.addEvent(switchingCorridors);
		plan.addEvent(secondCorridor);
	}
	
	@Test
	public void shouldAddNewEvent() {
		assertTrue(plan.addEvent(landing));
	}
	
	@Test
	public void shouldNotAddEventBeforeAirplaneTakesOff() {
		Calendar start = new GregorianCalendar();
		start.add(12, -20);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(start, hourDep, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldNotAddEventAfterLanding()
	{
		Calendar end = new GregorianCalendar();
		end.add(12, 90);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(endSecond, end, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldNotAddOverlayingEvents()
	{
		Calendar start = new GregorianCalendar();
		start.add(12, 60);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(start, hourLand, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldNotAllowGaps()
	{
		Calendar start = new GregorianCalendar();
		start.add(12, 80);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(start, hourLand, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldReturnTheCurrentCorridorWhichIsNull()
	{
		assertNull(plan.getCurrentCorridor());
	}
	
	@Test
	public void shouldReturnTheFirstCorridor()
	{
		Calendar time = new GregorianCalendar();
		time.add(12, 30);
		
		assertEquals(firstAltCorridor, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnTheSecondCorridor()
	{
		Calendar time = new GregorianCalendar();
		time.add(12, 60);
		
		assertEquals(secondAltCorridor, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnNullForATestBeforeFlightStarts()
	{
		Calendar time = new GregorianCalendar();
		time.add(12, -10);
		
		assertEquals(null, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnNullForATestAfterFlightEnds()
	{
		Calendar time = new GregorianCalendar();
		time.add(12, 150);
		
		assertEquals(null, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnTheFirstEvent()
	{
		assertEquals(gainingAltitude, plan.getFirstEvent());
	}
	
	@Test
	public void shouldReturnLastEvent()
	{
		assertEquals(secondCorridor, plan.getLastEvent());
	}
	
	@Test
	public void shouldReturnTheListOfAllTheEvents()
	{
		List<AirCorridorInTime> list = plan.getListOfCorridors();
		
		assertEquals(gainingAltitude, list.get(0));
		assertEquals(firstCorridor, list.get(1));
		assertEquals(switchingCorridors, list.get(2));
		assertEquals(secondCorridor, list.get(3));
	}
	
	@Test
	public void shouldCorrectlySetTheNewLandingHour()
	{
		Calendar newLandHour = new GregorianCalendar();
		newLandHour.add(12, 80);
		
		plan.addEvent(landing);
		plan.setNewArrivalHour(newLandHour, 10);
		
		assertEquals(plan.getLastEvent().getEndingHour(), newLandHour);
		
		newLandHour.add(12, -10);
		assertEquals(plan.getLastEvent().getStartingHour(), newLandHour);
		
		List<AirCorridorInTime> list = plan.getListOfCorridors();
		assertEquals(list.get(3).getEndingHour(), newLandHour);
		assertEquals(list.get(3).getStartingHour(), startSecond);
	}
	
	@Test
	public void shouldGetTheCorrectTakeOffDate()
	{
		assertEquals(hourDep, plan.getTakeOffDate());
	}
	
	@Test
	public void shouldGetTheCorrectLandingDate()
	{
		assertEquals(hourLand, plan.getLandingDate());
	}
}
