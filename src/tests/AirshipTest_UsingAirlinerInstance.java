package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import utils.AirCorridorInTime;
import utils.Airliner;
import utils.Airship;
import utils.AltitudeCorridor;
import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.PrivateJet;

public class AirshipTest_UsingAirlinerInstance {

	private Airship airliner;
	private static GeographicalPosition geo;
	private int initialAtltitude = 1;
	private int initialLatitude = 12;
	private int initialLongitude = 10;
	
	@Before
	public void constructTwoAirplanes()
	{
		geo = new GeographicalPosition(initialLatitude, initialLongitude, initialAtltitude);
		airliner = new Airliner("rj351", geo, new FlightPlan(new GregorianCalendar() ,new GregorianCalendar()), 100);
	}
	
	@Test
	public void shouldReturnTheFlightID()
	{
		assertEquals("rj351", airliner.getFlightID());
	}
	
	@Test
	public void shouldReturnTheCorrectGeographicalPosition()
	{
		assertEquals(geo, airliner.getGeographicPosition());
	}
	
	@Test
	public void shouldUpdateTheGeographicalPositionToANewOne()
	{
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(18, 11, 20);
		airliner.updateGeographicPosition(newGeographicalPosition);
		
		assertEquals(newGeographicalPosition, airliner.getGeographicPosition());
	}
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsStillGainingAltitude()
	{
		Airliner airliner2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(airliner2.getCurrentCorridor(), null);
	}
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsLanding()
	{
		Airliner airliner2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(airliner2.getCurrentCorridor(), null);
	}
	
	@Test
	public void shouldGetMidFlightCorridor()
	{
		Airliner airliner2 = makeAnAirplaneWithAPlan(-30);
		int maxAlt = airliner2.getCurrentCorridor().getUpperLimit();
		int minAlt = airliner2.getCurrentCorridor().getLowerLimit();
		
		assertEquals(120, maxAlt);
		assertEquals(100, minAlt);
	}
	
	@Test
	public void shoudGetTheRightObservation_TakingOff()
	{
		Airliner airliner2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(airliner2.getObservations(), "The air plane has took off and is gaining altitude.");
	}
	
	@Test
	public void shouldGetTheRightObservation_Landing()
	{
		Airliner airliner2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(airliner2.getObservations(), "The airplane has started its descent in order to land.");
	}
	
	@Test
	public void shouldGetTheRightObservation_OutsideCorridor()
	{
		Airliner airliner2 = makeAnAirplaneWithAPlan(-30);
		assertEquals(airliner2.getObservations(), "WARNING: The airplane is outside of the corridor.");
	}
	
	private static Airliner makeAnAirplaneWithAPlan(int diff)
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
		
		return new Airliner("id123", geo, plan, 316);
	}
}
