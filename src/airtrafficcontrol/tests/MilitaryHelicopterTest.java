package airtrafficcontrol.tests;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AirCorridorInTime;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.hangar.MilitaryHelicopter;
import airtrafficcontrol.app.appforconsole.utils.towerControl.ReadListOfFlights;

public class MilitaryHelicopterTest {

	private static GeographicalPosition geo;
	private int initialAtltitude = 1;
	private int initialLatitude = 12;
	private int initialLongitude = 10;
	
	MilitaryHelicopter mheli1;
	MilitaryHelicopter mheli2;
	Calendar date1;
	Calendar date2;

	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException
	{
		date1 = new GregorianCalendar();
		date2 = ReadListOfFlights.createDefensiveCopyOfTheDate(date1);
		date2.add(Calendar.MINUTE, 20);
		
		geo = new GeographicalPosition(initialLatitude, initialLongitude, initialAtltitude);
		
		mheli1 = new MilitaryHelicopter("mgrf", geo, new FlightPlan(date1, date2, 9 ,9, 6), true);
		mheli2 = new MilitaryHelicopter("mgrf1", geo,	new FlightPlan(date1, date2, 9 ,9, 6), false);
	}
	
	
	@Test
	public void shouldReturnIfThisHelicopterHasArmament()
	{
		assertTrue(mheli1.hasArmament());
		assertFalse(mheli2.hasArmament());
	}

	
	@Test
	public void shouldReturnTheFlightID()
	{
		assertEquals("mgrf", mheli1.getFlightID());
		assertEquals("mgrf1", mheli2.getFlightID());
	}
	
	
	@Test
	public void shouldReturnTheCorrectGeographicalPosition()
	{
		assertEquals(geo, mheli1.getGeographicPosition());
		assertEquals(geo, mheli2.getGeographicPosition());
	}
	
	
	@Test
	public void shouldUpdateTheGeographicalPositionToANewOne() throws InvalidArgumentException
	{
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(18, 11, 20);
		mheli1.updateGeographicPosition(newGeographicalPosition);
		
		assertEquals(newGeographicalPosition, mheli1.getGeographicPosition());
	}
	
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsStillGainingAltitude() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-10);
		assertEquals(mheli3.getCurrentCorridor(), null);
	}
	
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsLanding() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-50);
		assertEquals(mheli3.getCurrentCorridor(), null);
	}
	
	
	@Test
	public void shouldGetMidFlightCorridor() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-30);
		double maxAlt = mheli3.getCurrentCorridor().getUpperLimit();
		double minAlt = mheli3.getCurrentCorridor().getLowerLimit();
		
		assertEquals(120, (int)maxAlt);
		assertEquals(100, (int)minAlt);
	}
	
	
	@Test
	public void shoudGetTheRightObservation_TakingOff() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-10);
		assertEquals(mheli3.getObservations(), "The air plane has took off and is gaining altitude.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_Landing() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-50);
		assertEquals(mheli3.getObservations(), "The airplane has started its descent in order to land.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_OutsideCorridor() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-30);
		assertEquals(mheli3.getObservations(), "WARNING: The airplane is outside of the corridor.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_NoObservation() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-30);
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(40, 15, 110);
		mheli3.updateGeographicPosition(newGeographicalPosition);

		assertEquals(mheli3.getObservations(), "");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_HasNotTakenOff() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(10);
		assertEquals(mheli3.getObservations(), "The airplane has not taken off yet.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_HasAlreadyLanded() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(-70);
		assertEquals(mheli3.getObservations(), "The airplane has already landed.");
	}
	
	
	@Test 
	public void shouldCorrectlySetTheNewArrivalDate() throws InvalidArgumentException
	{
		MilitaryHelicopter mheli3 = makeAnAirplaneWithAPlan(0);
		Calendar newLanding = new GregorianCalendar();
		newLanding.add(12, 80);
		mheli3.setNewArrivalHour(newLanding);
		
		assertEquals(mheli3.getPlan().getLastEvent().getEndingHour(), newLanding);
		
		newLanding.add(12, -20);
		assertEquals(mheli3.getPlan().getLastEvent().getStartingHour(), newLanding);
	}
	
	
	@Test
	public void shouldReturnTheCorrectTakeOffDate() throws InvalidArgumentException
	{
		Calendar hourTakeOff = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		hourLand.add(12, 20);
		
		MilitaryHelicopter mheli4 = new MilitaryHelicopter("rj351", geo, new FlightPlan(hourTakeOff, hourLand, 4,7,2), true);
		assertEquals(hourTakeOff, mheli4.getTakeOffDate());
	}
	
	
	@Test
	public void shouldReturnTheCorrectLandingDate() throws InvalidArgumentException
	{
		Calendar hourTakeOff = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		hourLand.add(12, 20);
		
		MilitaryHelicopter mheli3 = new MilitaryHelicopter("rj351", geo, new FlightPlan(hourTakeOff, hourLand, 10, 12, 5), false);
		assertEquals(hourLand, mheli3.getLandingDate());
	}
	
	
//	@Test
//	public void shouldReturnTheCorrectString() throws InvalidArgumentException
//	{
//		AirPlane airliner2 = makeAnAirplaneWithAPlan(-30);
//		assertEquals("id123 12.0 10.0 1.0 " + airliner2.getObservations(), airliner2.positionToString());
//	}
//	


	
	private static MilitaryHelicopter makeAnAirplaneWithAPlan(int diff) throws InvalidArgumentException
	{
		Calendar hourDep = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		Calendar startCorr = new GregorianCalendar();
		Calendar endCorr = new GregorianCalendar();
		
		hourDep.add(12, diff);
		startCorr.add(12, 20 + diff);
		endCorr.add(12, 40 + diff);
		hourLand.add(12, 60 + diff);
		
		int maxAlt = 100;
		int minAlt = 120;
		AltitudeCorridor corr = new AltitudeCorridor(maxAlt, minAlt);
		
		AirCorridorInTime gainingAltitude = new AirCorridorInTime(hourDep, startCorr, null);
		AirCorridorInTime corridor = new AirCorridorInTime(startCorr, endCorr, corr);
		AirCorridorInTime landing = new AirCorridorInTime(endCorr, hourLand, null);
		
		FlightPlan plan = new FlightPlan(hourDep, hourLand, 9, 9, 6);
		
		plan.addEvent(gainingAltitude);
		plan.addEvent(corridor);
		plan.addEvent(landing);
		
		return new MilitaryHelicopter("id123", geo, plan, false);
	}

}
