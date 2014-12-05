package airtrafficcontrol.tests;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.utils.airshipplan.AirCorridorInTime;
import airtrafficcontrol.app.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.utils.hangar.AirCraft;
import airtrafficcontrol.app.utils.hangar.AirPlane;
import airtrafficcontrol.app.utils.hangar.CivilAirPlane;
import airtrafficcontrol.app.utils.hangar.MilitaryAirPlane;
import airtrafficcontrol.app.utils.hangar.MilitaryHelicopter;
import airtrafficcontrol.app.utils.towerControl.Database;
import airtrafficcontrol.app.utils.towerControl.ReportGenerator;

public class ReportEmitterTest {
	
	ReportGenerator rep;
	Database data;
	AirPlane airl1;
	AirPlane airl2;
	MilitaryHelicopter transp;
	MilitaryAirPlane carg;
	FlightPlan plan;
	Map<String, AirCraft> dataMap;
	String source = "newCoordinatesTest.txt";
	
	@Before
	public void constructAirplanesAndDatabase() throws InvalidArgumentException, InvalidFlightIDException
	{
		rep = new ReportGenerator();
		
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		date2.add(12, 10);
		
		AltitudeCorridor corr = new AltitudeCorridor(10000, 12000);
		plan = new FlightPlan(date1, date2,9,9,6);
		plan.addEvent(new AirCorridorInTime(date1, date2, corr));
		
		airl1 = new CivilAirPlane("mh237", new GeographicalPosition(0,0,100), plan, 0);
		airl2 = new CivilAirPlane("fw321", new GeographicalPosition(0,0,100), plan, 200);
		transp = new MilitaryHelicopter("3456", new GeographicalPosition(0,0,50), plan, false);
		carg = new MilitaryAirPlane("2345", new GeographicalPosition(0,0,100), plan, true);
		
		data = new Database();
		
		data.addAirship(airl1);
		data.addAirship(airl2);
		data.addAirship(transp);
		data.addAirship(carg);
		
		dataMap = data.getDatabase();
	}
	
	@Test
	public void shouldReportTheAirplanesOutsideOfTheirCorridors() throws IOException, InvalidFlightIDException, InvalidArgumentException
	{
		rep.reportAll(data, source);
		String[] airplanesOut = rep.reportAirplanesOutOfCorridor(data);
		
		assertEquals("mh237", airplanesOut[0]);
		assertEquals("3456", airplanesOut[1]);
		assertEquals("2345", airplanesOut[2]);
		
		rep.reportAirplanesOutOfCorridorToTxt(data);
	}

	@Test
	public void shouldReportAllTheFlightsWithUnknownFlightID() throws IOException, InvalidFlightIDException, InvalidArgumentException
	{
		rep.reportAll(data, source);
		assertEquals("Unrecognized flight ID: sdfb Latitude: 50.0 Longitude: 50.0 Altitude: 13000.0\n", rep.reportAirplanesWithUnknownFlightID());
		rep.reportAirplanesWithUnknownFlightIDToTxt();
	}
	
	@Test
	public void shouldReportAllTheFlightsWithErrors() throws IOException, InvalidFlightIDException, InvalidArgumentException
	{
		rep.reportAll(data, source);
		assertEquals("Empty Fields at Line: 5\n", rep.reportemptyFields());
		rep.reportemptyFieldsToTxt();
	}
	
//	@Test
//	public void shouldReportAllRegularFlights() throws IOException, InvalidFlightIDException, InvalidArgumentException
//	{
//		String[] lista = rep.reportAll(data, source);
//		
//		assertEquals("mh237 60.0 120.0 9000.0 WARNING: The airplane is outside of the corridor.", lista[3]);
//		assertEquals("fw321 60.0 100.0 11000.0 ", lista[0]);
//		assertEquals("2345 70.0 20.0 19000.0 WARNING: The airplane is outside of the corridor.", lista[2]);
//		assertEquals("3456 50.0 50.0 13000.0 WARNING: The airplane is outside of the corridor.", lista[1]);
//		
//		rep.reportAllToTxt(data, source);
//	}
	
}

