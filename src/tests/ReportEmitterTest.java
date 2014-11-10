package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import utils.AirCorridorInTime;
import utils.Airliner;
import utils.Airship;
import utils.AltitudeCorridor;
import utils.CargoAircraft;
import utils.Database;
import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.ReportEmitter;
import utils.Transport;

public class ReportEmitterTest {
	
	ReportEmitter rep;
	Database data;
	Airliner airl1;
	Airliner airl2;
	Transport transp;
	CargoAircraft carg;
	FlightPlan plan;
	Map<String, Airship> dataMap;
	String source = "src/filesToRead/newCoordinatesTest.txt";
	
	@Before
	public void constructAirplanesAndDatabase()
	{
		rep = new ReportEmitter();
		
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		date2.add(12, 10);
		
		AltitudeCorridor corr = new AltitudeCorridor(10000, 12000);
		plan = new FlightPlan(date1, date2);
		plan.addEvent(new AirCorridorInTime(date1, date2, corr));
		
		airl1 = new Airliner("mh237", new GeographicalPosition(0,0,100), plan, 0);
		airl2 = new Airliner("fw321", new GeographicalPosition(0,0,100), plan, 200);
		transp = new Transport("3456", new GeographicalPosition(0,0,50), plan, false);
		carg = new CargoAircraft("2345", new GeographicalPosition(0,0,100), plan);
		
		data = new Database();
		
		data.addAirplane(airl1);
		data.addAirplane(airl2);
		data.addAirplane(transp);
		data.addAirplane(carg);
		
		dataMap = data.getDatabase();
	}
	
	@Test
	public void shouldReportAllRegularFlights() throws IOException
	{
		String[] lista = rep.reportAll(data, source);
		
		assertEquals("mh237 60.0 120.0 9000.0 WARNING: The airplane is outside of the corridor.", lista[3]);
		assertEquals("fw321 60.0 100.0 11000.0 ", lista[0]);
		assertEquals("2345 70.0 20.0 19000.0 WARNING: The airplane is outside of the corridor.", lista[2]);
		assertEquals("3456 50.0 50.0 13000.0 WARNING: The airplane is outside of the corridor.", lista[1]);
		
		rep.reportAllToTxt(data, source);
	}
	
	@Test
	public void shouldReportTheAirplanesOutsideOfTheirCorridors() throws IOException
	{
		rep.reportAll(data, source);
		String[] airplanesOut = rep.reportAirplanesOutOfCorridor(data);
		
		assertEquals("mh237", airplanesOut[0]);
		assertEquals("3456", airplanesOut[1]);
		assertEquals("2345", airplanesOut[2]);
		
		rep.reportAirplanesOutOfCorridorToTxt(data);
	}

	@Test
	public void shouldReportAllTheFlightsWithUnknownFlightID() throws IOException
	{
		rep.reportAll(data, source);
		assertEquals("Unrecognized flight ID: sdfb Latitude: 50.0 Longitude: 50.0 Altitude: 13000.0\n", rep.reportAirplanesWithUnknownFlightID());
		rep.reportAirplanesWithUnknownFlightIDToTxt();
	}
	
	@Test
	public void shouldReportAllTheFlightsWithErrors() throws IOException
	{
		rep.reportAll(data, source);
		assertEquals("Empty Fields at Line: 5\n", rep.reportemptyFields());
		rep.reportemptyFieldsToTxt();
	}
	
	
}
