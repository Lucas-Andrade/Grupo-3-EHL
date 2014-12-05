package airtrafficcontrol.tests;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.utils.airshipplan.AirCorridorInTime;
import airtrafficcontrol.app.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.utils.hangar.AirPlane;
import airtrafficcontrol.app.utils.hangar.Airship;
import airtrafficcontrol.app.utils.hangar.MilitaryAirPlane;
import airtrafficcontrol.app.utils.towerControl.ReadListOfFlights;


/**
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */

public class AirplaneTest_UsingMilitaryAirplaneInstance {

	private Airship airplane;
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
		
		
		int MinutesToTakeOff = 10 ;
		int MinutesToLand = 10;
		int MinutesToSwitchCorridor = 10;
		
		geo = new GeographicalPosition(initialLatitude, initialLongitude, initialAtltitude);
		airplane = new MilitaryAirPlane("rj351", geo, new FlightPlan(date1, date2,
				MinutesToTakeOff, MinutesToLand,MinutesToSwitchCorridor), true);
	}
	
	
	@Test
	public void shouldReturnTheFlightID()
	{
		assertEquals("rj351", airplane.getFlightID());
	}
	
	
	@Test
	public void shouldReturnTheCorrectGeographicalPosition()
	{
		assertEquals(geo, airplane.getGeographicPosition());
	}
	
	
	@Test
	public void shouldUpdateTheGeographicalPositionToANewOne() throws InvalidArgumentException
	{
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(18, 11, 20);
		airplane.updateGeographicPosition(newGeographicalPosition);
		
		assertEquals(newGeographicalPosition, airplane.getGeographicPosition());
	}
	
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsStillGainingAltitude() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(airliner2.getCurrentCorridor(), null);
	}
	
	
	@Test
	public void shouldGetTheCorridorNullBecauseItIsLanding() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(airliner2.getCurrentCorridor(), null);
	}
	
	
	@Test
	public void shouldGetMidFlightCorridor() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-30);
		double maxAlt = airliner2.getCurrentCorridor().getUpperLimit();
		double minAlt = airliner2.getCurrentCorridor().getLowerLimit();
		
		assertEquals(120, (int)maxAlt);
		assertEquals(100, (int)minAlt);
	}
	
	
	@Test
	public void shoudGetTheRightObservation_TakingOff() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-10);
		assertEquals(airliner2.getObservations(), "The air plane has took off and is gaining altitude.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_Landing() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-50);
		assertEquals(airliner2.getObservations(), "The airplane has started its descent in order to land.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_OutsideCorridor() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-30);
		assertEquals(airliner2.getObservations(), "WARNING: The airplane is outside of the corridor.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_NoObservation() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-30);
		GeographicalPosition newGeographicalPosition = new GeographicalPosition(40, 15, 110);
		airliner2.updateGeographicPosition(newGeographicalPosition);

		assertEquals(airliner2.getObservations(), "");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_HasNotTakenOff() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(10);
		assertEquals(airliner2.getObservations(), "The airplane has not taken off yet.");
	}
	
	
	@Test
	public void shouldGetTheRightObservation_HasAlreadyLanded() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(-70);
		assertEquals(airliner2.getObservations(), "The airplane has already landed.");
	}
	
	
	@Test 
	public void shouldCorrectlySetTheNewArrivalDate() throws InvalidArgumentException
	{
		AirPlane airliner2 = makeAnAirplaneWithAPlan(0);
		Calendar newLanding = new GregorianCalendar();
		newLanding.add(12, 80);
		airliner2.setNewArrivalHour(newLanding);
		
		assertEquals(airliner2.getPlan().getLastEvent().getEndingHour(), newLanding);
		
		newLanding.add(12, -20);
		assertEquals(airliner2.getPlan().getLastEvent().getStartingHour(), newLanding);
	}
	
	
	@Test
	public void shouldReturnTheCorrectTakeOffDate() throws InvalidArgumentException
	{
		Calendar hourTakeOff = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		hourLand.add(12, 20);
		
		AirPlane airliner3 = new MilitaryAirPlane("rj351", geo, new FlightPlan(hourTakeOff, hourLand, 4,7,2), true);
		assertEquals(hourTakeOff, airliner3.getTakeOffDate());
	}
	
	
	@Test
	public void shouldReturnTheCorrectLandingDate() throws InvalidArgumentException
	{
		Calendar hourTakeOff = new GregorianCalendar();
		Calendar hourLand = new GregorianCalendar();
		hourLand.add(12, 20);
		
		AirPlane airliner4 = new MilitaryAirPlane("rj351", geo, new FlightPlan(hourTakeOff, hourLand, 10, 12, 5), false);
		assertEquals(hourLand, airliner4.getLandingDate());
	}
	
	
//	@Test
//	public void shouldReturnTheCorrectString() throws InvalidArgumentException
//	{
//		AirPlane airliner2 = makeAnAirplaneWithAPlan(-30);
//		assertEquals("id123 12.0 10.0 1.0 " + airliner2.getObservations(), airliner2.positionToString());
//	}
//	


	
	private static MilitaryAirPlane makeAnAirplaneWithAPlan(int diff) throws InvalidArgumentException
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
		
		return new MilitaryAirPlane("id123", geo, plan, false);
	}

}
