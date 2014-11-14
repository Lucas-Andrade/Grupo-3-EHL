package airtrafficcontrol.app.tests;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.utils.AirCorridorInTime;
import airtrafficcontrol.app.utils.Airliner;
import airtrafficcontrol.app.utils.Airship;
import airtrafficcontrol.app.utils.AltitudeCorridor;
import airtrafficcontrol.app.utils.CargoAircraft;
import airtrafficcontrol.app.utils.Database;
import airtrafficcontrol.app.utils.FlightPlan;
import airtrafficcontrol.app.utils.GeographicalPosition;
import airtrafficcontrol.app.utils.ReadAirplanesCoordinates;
import airtrafficcontrol.app.utils.ReportGenerator;
import airtrafficcontrol.app.utils.Transport;

public class ReadAirplanesCoordinatesTest {

	ReadAirplanesCoordinates read;
	ReportGenerator rep;
	Database data;
	Airliner airlWithZeroPass;
	Airliner airlWithSameID;
	Transport transOutsideCorr;
	CargoAircraft carg;
	FlightPlan plan;
	Map<String, Airship> dataMap;
	String source = "newCoordinatesTest.txt";
	
	@Before
	public void constructAirplanesAndDatabase() throws InvalidArgumentException, InvalidFlightIDException
	{
		rep = new ReportGenerator();
		
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
	public void shouldGetEmptyFieldsFromFile() throws InvalidFlightIDException, InvalidArgumentException, IOException
	{
		// Arrange
		String sourceOfFlights = "newCoordinatesTest.txt";
		read = new ReadAirplanesCoordinates();
		String emptyFieldsExpected = "Empty Fields at Line: 5" + "\n";
		
		// Act
		read.readFromFile(sourceOfFlights, data);
		
		// Assert
		Assert.assertTrue(emptyFieldsExpected.equals(read.getEmptyFields()));
	}

}
