package tests;

import static org.junit.Assert.*;

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
import utils.PrivateJet;
import utils.ReportEmitter;
import utils.Transport;

public class ReportEmitterTest {
	
	ReportEmitter rep;
	Database data;
	Airliner airlWithZeroPass;
	Airliner airlWithSameID;
	Transport transOutsideCorr;
	CargoAircraft carg;
	FlightPlan plan;
	Map<String, Airship> dataMap;
	
	@Before
	public void constructAirplanesAndDatabase()
	{
		rep = new ReportEmitter();
		
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		date2.add(12, 10);
		
		AltitudeCorridor corr = new AltitudeCorridor(80, 120);
		plan = new FlightPlan(date1, date2);
		plan.addEvent(new AirCorridorInTime(date1, date2, corr));
		
		airlWithZeroPass = new Airliner("airl123", new GeographicalPosition(0,0,100), plan, 0);
		airlWithSameID = new Airliner("airl123", new GeographicalPosition(0,0,100), plan, 200);
		transOutsideCorr = new Transport("trp123", new GeographicalPosition(0,0,50), plan, false);
		carg = new CargoAircraft("crg123", new GeographicalPosition(0,0,100), plan);
		
		data = new Database();
		
		data.addAirplane(airlWithZeroPass);
		data.addAirplane(transOutsideCorr);
		
		dataMap = data.getDatabase();
	}
	
	@Test
	public void shouldReportTheAirplanesOutsideOfTheirCorridors()
	{
		String[] airplanesOut = rep.reportAirplanesOutOfCorridor(dataMap);
		assertEquals(1, airplanesOut.length);
		assertEquals("trp123", airplanesOut[0]);
	}

}
