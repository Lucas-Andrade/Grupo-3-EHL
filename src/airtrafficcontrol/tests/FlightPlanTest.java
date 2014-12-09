package airtrafficcontrol.tests;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AirCorridorInTime;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;

/**
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
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
	public void constructThePlan() throws InvalidArgumentException
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
		
		plan = new FlightPlan(hourDep, hourLand, 9, 9, 6);
		
		plan.addEvent(gainingAltitude);
		plan.addEvent(firstCorridor);
		plan.addEvent(switchingCorridors);
		plan.addEvent(secondCorridor);
	}
	
	@Test
	public void shouldAddNewEvent() throws InvalidArgumentException {
		assertTrue(plan.addEvent(landing));
	}
	
	@Test
	public void shouldNotAddEventBeforeAirplaneTakesOff() throws InvalidArgumentException {
		Calendar start = new GregorianCalendar();
		start.add(12, -20);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(start, hourDep, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldNotAddEventAfterLanding() throws InvalidArgumentException
	{
		Calendar end = new GregorianCalendar();
		end.add(12, 90);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(endSecond, end, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldNotAddOverlayingEvents() throws InvalidArgumentException
	{
		Calendar start = new GregorianCalendar();
		start.add(12, 60);
		
		AirCorridorInTime airCorridor = new AirCorridorInTime(start, hourLand, null);
		assertFalse(plan.addEvent(airCorridor));
	}
	
	@Test
	public void shouldNotAllowGaps() throws InvalidArgumentException
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
	public void shouldReturnTheFirstCorridor() throws InvalidArgumentException
	{
		Calendar time = new GregorianCalendar();
		time.add(12, 30);
		
		assertEquals(firstAltCorridor, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnTheSecondCorridor() throws InvalidArgumentException
	{
		Calendar time = new GregorianCalendar();
		time.add(12, 60);
		
		assertEquals(secondAltCorridor, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnNullForATestBeforeFlightStarts() throws InvalidArgumentException
	{
		Calendar time = new GregorianCalendar();
		time.add(12, -10);
		
		assertEquals(null, plan.getCorridorAtTime(time));
	}
	
	@Test
	public void shouldReturnNullForATestAfterFlightEnds() throws InvalidArgumentException
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
	public void shouldCorrectlySetTheNewLandingHour() throws InvalidArgumentException
	{
		Calendar newLandHour = new GregorianCalendar();
		newLandHour.add(12, 80);
		
		plan.addEvent(landing);
		plan.setNewArrivalHour(newLandHour);
		
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
	
	
	@Test
	public void shouldGetCorrectNumberOfMinutesToTakeOffOfFligthPlan()throws InvalidArgumentException {
		
		assertEquals(9, plan.getNumberOfMinutesToTakeOff());
	}
	
	@Test
	public void shouldGetCorrectNumberOfMinutesToLandOfFligthPlan() throws InvalidArgumentException {
	
		assertEquals(9, plan.getNumberOfMinutesToLand());
	}
	
	
	@Test
	public void shouldGetCorrectNumberOfMinutesToSwitchCorridorOfFligthPlan() throws InvalidArgumentException {
		
		assertEquals(6, plan.getNumberOfMinutesToSwitchCorridor());
	}
	
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfFligthPlan() throws InvalidArgumentException {
		
		assertEquals(9, plan.getNumberOfMinutesToTakeOff());
		
		plan.setNumberOfMinutesToTakeOff(14);
		assertEquals(14, plan.getNumberOfMinutesToTakeOff());

	}
	
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfFligthPlan() throws InvalidArgumentException {
		assertEquals(9, plan.getNumberOfMinutesToLand());
				
		plan.setNumberOfMinutesToLand(20);
		assertEquals(20, plan.getNumberOfMinutesToLand());
	}
	
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfFligthPlan() throws InvalidArgumentException {
		
		assertEquals(6, plan.getNumberOfMinutesToSwitchCorridor());
		
		plan.setNumberOfMinutesToSwitchCorridor(1);
		assertEquals(1, plan.getNumberOfMinutesToSwitchCorridor());
	}
	
}
