package airtrafficcontrol.tests.testsforconsole;

import static org.junit.Assert.assertEquals;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.AirCorridorInTime;
import airtrafficcontrol.app.appforconsole.utils.AltitudeCorridor;
import airtrafficcontrol.app.appforconsole.utils.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.hangar.Airliner;
import airtrafficcontrol.app.appforconsole.utils.hangar.Transport;
import airtrafficcontrol.app.appforconsole.utils.towerControl.ReadListOfFlights;

public class AirshipTest_UsingTransportInstance {

	private Transport transport;
	private static GeographicalPosition geo;
	private int initialAtltitude = 1;
	private int initialLatitude = 12;
	private int initialLongitude = 10;
	Calendar date1;
	Calendar date2;
	
	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException
	{
		date1 = new GregorianCalendar();
		date2 = ReadListOfFlights.createDefensiveCopyOfTheDate(date1);
		date2.add(Calendar.MINUTE, 20);
		
		geo = new GeographicalPosition(initialLatitude, initialLongitude, initialAtltitude);
		transport = new Transport("rj351", geo, new FlightPlan(date1, date2), false);
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
	public void shouldUpdateTheGeographicalPositionToANewOne() throws InvalidArgumentException
	{
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(18, 11, 20);
		transport.updateGeographicPosition(newGeographicalPosition);
		
		assertEquals(newGeographicalPosition, transport.getGeographicPosition());
	}
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsStillGainingAltitude() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(transport2.getCurrentCorridor(), null);
	}
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsLanding() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(transport2.getCurrentCorridor(), null);
	}
	
	@Test
	public void shouldGetMidFlightCorridor() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		double maxAlt = transport2.getCurrentCorridor().getUpperLimit();
		double minAlt = transport2.getCurrentCorridor().getLowerLimit();
		
		assertEquals(120, (int)maxAlt);
		assertEquals(100, (int)minAlt);
	}
	
	@Test
	public void shoudGetTheRightObservation_TakingOff() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(transport2.getObservations(), "The air plane has took off and is gaining altitude.");
	}
	
	@Test
	public void shouldGetTheRightObservation_Landing() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(transport2.getObservations(), "The airplane has started its descent in order to land.");
	}
	
	@Test
	public void shouldGetTheRightObservation_OutsideCorridor() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		assertEquals(transport2.getObservations(), "WARNING: The airplane is outside of the corridor.");
	}
	
	@Test
	public void shouldGetTheRightObservation_NoObservation() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(40, 15, 110);
		transport2.updateGeographicPosition(newGeographicalPosition);

		assertEquals(transport2.getObservations(), "");
	}
	
	@Test
	public void shouldGetTheRightObservation_HasNotTakenOff() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(20);
		assertEquals(transport2.getObservations(), "The airplane has not taken off yet.");
	}
	
	@Test
	public void shouldGetTheRightObservation_HasAlreadyLanded() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-100);
		assertEquals(transport2.getObservations(), "The airplane has already landed.");
	}
	
	@Test 
	public void shouldCorrectlySetTheNewArrivalDate() throws InvalidArgumentException
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
	public void shouldReturnTheCorrectString() throws InvalidArgumentException
	{
		Transport transport2 = makeAnAirplaneWithAPlan(-30);
		assertEquals("id123 12.0 10.0 1.0 " + transport2.getObservations(), transport2.positionToString());
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffDate() throws InvalidArgumentException
	{
		Calendar hourTakeOff = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		hourLand.add(12, 20);
		
		Airliner airliner3 = new Airliner("rj351", geo, new FlightPlan(hourTakeOff, hourLand), 100);
		assertEquals(hourTakeOff, airliner3.getTakeOffDate());
	}
	
	@Test
	public void shouldReturnTheCorrectLandingDate() throws InvalidArgumentException
	{
		Calendar hourTakeOff = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		hourLand.add(12, 20);
		
		Airliner airliner3 = new Airliner("rj351", geo, new FlightPlan(hourTakeOff, hourLand), 100);
		assertEquals(hourLand, airliner3.getLandingDate());
	}
	
	private static Transport makeAnAirplaneWithAPlan(int diff) throws InvalidArgumentException
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
