package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

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
	public void constructAirplanesAndDatabase()
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
	public void shouldAddTheNewAirplane() {
		assertTrue(data.addAirplane(carg));
	}
	
	@Test
	public void shouldNotAddAnAirplaneWithARepeatedID()
	{
		assertFalse(data.addAirplane(airlWithSameID));
	}
	
	@Test
	public void shouldRemoveAnAirplane()
	{
		assertEquals(data.getDatabase().size(), 2);
		assertTrue(data.removeAirplane("trp123"));
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldNotRemoveAnAirplaneThatDoesNotExist()
	{
		assertFalse(data.removeAirplane("asdfg"));
	}
	
	@Test
	public void shouldRemoveAnAirplane2()
	{
		assertEquals(data.getDatabase().size(), 2);
		assertTrue(data.removeAirplane(transOutsideCorr));
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldNotRemoveAnAirplaneThatDoesNotExist2()
	{
		assertFalse(data.removeAirplane(new Airliner("air", new GeographicalPosition(0,0,0), plan, 0)));
	}
	
	@Test
	public void shouldRemoveAirplanesWithZeroPassengers()
	{
		assertEquals(data.getDatabase().size(), 2);
		assertEquals(1, data.removeAirplanesWithZeroPassengers());
		assertEquals(data.getDatabase().size(), 1);
	}
	
	@Test
	public void shouldRemoveTwoAirplanesWithZeroPassengers()
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
	

}
