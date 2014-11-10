package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import app.InvalidArgumentException;
import app.InvalidFlightIDException;
import utils.AirCorridorInTime;
import utils.Airliner;
import utils.AltitudeCorridor;
import utils.CargoAircraft;
import utils.Database;
import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.PrivateJet;
import utils.Transport;

public class DatabaseTest {
	
	Database data;
	Airliner airlWithZeroPass;
	Airliner airlWithSameID;
	Transport transOutsideCorr;
	CargoAircraft carg;
	FlightPlan plan;
	
	@Before
	public void constructAirplanesAndDatabase() throws InvalidArgumentException, InvalidFlightIDException
	{
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
	}
	
	@Test
	public void shouldAddTheNewAirplane() throws InvalidFlightIDException, InvalidArgumentException {
		assertTrue(data.addAirplane(carg));
	}
	
	@Test
	public void shouldNotAddAnAirplaneWithARepeatedID() throws InvalidFlightIDException, InvalidArgumentException
	{
		assertFalse(data.addAirplane(airlWithSameID));
	}
	
	@Test
	public void shouldRemoveAnAirplane() throws InvalidFlightIDException
	{
		assertEquals(data.getDatabase().size(), 2);
		assertTrue(data.removeAirplane("trp123"));
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldNotRemoveAnAirplaneThatDoesNotExist() throws InvalidFlightIDException
	{
		assertFalse(data.removeAirplane("asdfg"));
	}
	
	@Test
	public void shouldRemoveAnAirplane2() throws InvalidFlightIDException, InvalidArgumentException
	{
		assertEquals(data.getDatabase().size(), 2);
		assertTrue(data.removeAirplane(transOutsideCorr));
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldNotRemoveAnAirplaneThatDoesNotExist2() throws InvalidFlightIDException, InvalidArgumentException
	{
		assertFalse(data.removeAirplane(new Airliner("air", new GeographicalPosition(0,0,0), plan, 0)));
	}
	
	@Test
	public void shouldRemoveAirplanesWithZeroPassengers() throws InvalidFlightIDException
	{
		assertEquals(data.getDatabase().size(), 2);
		assertEquals(1, data.removeAirplanesWithZeroPassengers());
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldRemoveTwoAirplanesWithZeroPassengers() throws InvalidArgumentException, InvalidFlightIDException
	{
		PrivateJet priv = new PrivateJet("priv123", new GeographicalPosition(0,0,100), plan, 0);
		data.addAirplane(priv);
		
		assertEquals(data.getDatabase().size(), 3);
		assertEquals(2, data.removeAirplanesWithZeroPassengers());
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldReturnTheCorrespondingAirplane()
	{
		assertEquals(transOutsideCorr, data.getAirplane("trp123"));
		assertEquals(airlWithZeroPass , data.getAirplane("airl123"));
		assertEquals(null, data.getAirplane("asdf"));
	}
	
	@Test
	public void shouldAddAllAirplanesSuccessfully() throws InvalidArgumentException, InvalidFlightIDException
	{
		Airliner new1 = new Airliner("qwert", new GeographicalPosition(0,0,100), plan, 0);
		Airliner new2 = new Airliner("asdff", new GeographicalPosition(0,0,100), plan, 200);
		Transport new3 = new Transport("zxcvb", new GeographicalPosition(0,0,50), plan, false);
		
		Database newData = new Database();
		
		newData.addAirplane(new1);
		newData.addAirplane(new2);
		newData.addAirplane(new3);
		
		assertEquals("All airplanes were added successfully.", data.addDatabase(newData));
		assertTrue(data.contains("qwert"));
		assertTrue(data.contains("asdff"));
		assertTrue(data.contains("zxcvb"));
		assertTrue(data.contains("airl123"));
		assertTrue(data.contains("trp123"));
	}
	
	@Test
	public void shouldNOTAddAllAirplanesSuccessfully() throws InvalidArgumentException, InvalidFlightIDException
	{
		Airliner new1 = new Airliner("qwert", new GeographicalPosition(0,0,100), plan, 0);
		Airliner new2 = new Airliner("trp123", new GeographicalPosition(0,0,100), plan, 200);
		Transport new3 = new Transport("airl123", new GeographicalPosition(0,0,50), plan, false);
		
		Database newData = new Database();
		
		newData.addAirplane(new1);
		newData.addAirplane(new2);
		newData.addAirplane(new3);
		
		assertEquals("Flight ID airl123 already exists in database. Airplane NOT added.\nFlight ID trp123 already exists in database. Airplane NOT added.\n", data.addDatabase(newData));
		assertTrue(data.contains("qwert"));
		assertFalse(data.contains("asdff"));
		assertFalse(data.contains("zxcvb"));
		assertTrue(data.contains("airl123"));
		assertTrue(data.contains("trp123"));
	}
}
