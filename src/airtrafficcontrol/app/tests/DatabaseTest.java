package airtrafficcontrol.app.tests;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import airtrafficcontrol.app.utils.PrivateJet;
import airtrafficcontrol.app.utils.Transport;

public class DatabaseTest {
	
	Database data;
	Airliner airlWithZeroPass;
	Airliner airlWithSameID;
	Transport transOutsideCorr;
	CargoAircraft carg;
	FlightPlan plan;
	
	@Before
	public void constructAirplanesAndDatabase() throws InvalidArgumentException
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
	

	@Test 
	public void shouldGetTheNumberOfAirshipsInTheDatabase()throws InvalidArgumentException, InvalidFlightIDException
	{
		
	Airship	airplane1 = new Airliner ("xpto01", new GeographicalPosition(20, 130, 0), new FlightPlan(new GregorianCalendar(2014, 11, 10, 00, 15), new GregorianCalendar(2014, 11, 10, 04, 15)), 50);
	Airship	airplane2 = new PrivateJet("xpto02",new GeographicalPosition(30, 30, 0), new FlightPlan(new GregorianCalendar(2014, 11, 11, 00, 15), new GregorianCalendar(2014, 11, 11, 04, 15)), 10);
	Airship	airplane3 = new CargoAircraft("xpto03",new GeographicalPosition(40, 30, 0), new FlightPlan(new GregorianCalendar(2014, 11, 12, 00, 15), new GregorianCalendar(2014, 11, 12, 04, 15)));
	Airship	airplane4 = new Transport("xpto04",new GeographicalPosition(20.00, 130.00, 0.00), new FlightPlan(new GregorianCalendar(2014, 11, 13, 00, 15), new GregorianCalendar(2014, 11, 13, 04, 15)), false);
	
	Database newData = new Database();
	
	newData.addAirplane(airplane1);
	newData.addAirplane(airplane2);
	newData.addAirplane(airplane3);
	newData.addAirplane(airplane4);
	
	int numberOfAirships = 0;
	numberOfAirships = newData.countAirships();
	
	assertTrue( 4 == numberOfAirships);
	}

}
