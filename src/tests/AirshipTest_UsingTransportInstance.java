package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import utils.AirCorridorInTime;
import utils.Airliner;
import utils.AltitudeCorridor;
import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.Transport;

public class AirshipTest_UsingTransportInstance {

	private Transport transport;
	private static GeographicalPosition geo;
	private int initialAtltitude = 1;
	private int initialLatitude = 12;
	private int initialLongitude = 10;
	
	@Before
	public void constructTwoAirplanes()
	{
		geo = new GeographicalPosition(initialLatitude, initialLongitude, initialAtltitude);
		transport = new Transport("rj351", geo, new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()), false);
	}
	
	@Test
	public void shouldReturnTheFlightID()
	{
		assertEquals("rj351", transport.getFlightID());
	}
	
	@Test
	public void shouldReturnTheCorrectGeographicalPosition()
	{
		assertEquals(geo, transport.getGeographicPosition());
	}
	
	@Test
	public void shouldUpdateTheGeographicalPositionToANewOne()
	{
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(18, 11, 20);
		transport.updateGeographicPosition(newGeographicalPosition);
		
		assertEquals(newGeographicalPosition, transport.getGeographicPosition());
	}
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsStillGainingAltitude()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(transport2.getCurrentCorridor(), null);
	}
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsLanding()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(transport2.getCurrentCorridor(), null);
	}
	
	@Test
	public void shouldGetMidFlightCorridor()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		int maxAlt = transport2.getCurrentCorridor().getUpperLimit();
		int minAlt = transport2.getCurrentCorridor().getLowerLimit();
		
		assertEquals(120, maxAlt);
		assertEquals(100, minAlt);
	}
	
	@Test
	public void shoudGetTheRightObservation_TakingOff()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(transport2.getObservations(), "The air plane has took off and is gaining altitude.");
	}
	
	@Test
	public void shouldGetTheRightObservation_Landing()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(transport2.getObservations(), "The airplane has started its descent in order to land.");
	}
	
	@Test
	public void shouldGetTheRightObservation_OutsideCorridor()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		assertEquals(transport2.getObservations(), "WARNING: The airplane is outside of the corridor.");
	}
	
	@Test
	public void shouldGetTheRightObservation_NoObservation()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(40, 15, 110);
		transport2.updateGeographicPosition(newGeographicalPosition);

		assertEquals(transport2.getObservations(), "");
	}
	
	@Test 
	public void shouldCorrectlySetTheNewArrivalDate()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(0);
		Calendar newLanding = new GregorianCalendar();
		newLanding.add(12, 80);
		transport2.setNewArrivalHour(newLanding);
		
		assertEquals(transport2.getPlan().getLastEvent().getEndingHour(), newLanding);
		
		newLanding.add(12, -20);
		assertEquals(transport2.getPlan().getLastEvent().getStartingHour(), newLanding);
	}
	
	@Test
	public void shouldReturnTheCorrectString()
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		assertEquals("id123 12.0 10.0 1.0 " + transport2.getObservations(), transport2.positionToString());
	}
	
	private static Transport makeAnAirplaneWithAPlan(int diff)
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
		
		FlightPlan plan = new FlightPlan(hourDep, hourLand);
		
		plan.addEvent(gainingAltitude);
		plan.addEvent(corridor);
		plan.addEvent(landing);
		
		return new Transport("id123", geo, plan, false);
	}

}
