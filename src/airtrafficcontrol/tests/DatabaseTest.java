package airtrafficcontrol.tests;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AirCorridorInTime;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.hangar.Airship;
import airtrafficcontrol.app.appforconsole.utils.hangar.CivilAirPlane;
import airtrafficcontrol.app.appforconsole.utils.hangar.CivilHelicopter;
import airtrafficcontrol.app.appforconsole.utils.hangar.MilitaryHelicopter;
import airtrafficcontrol.app.appforconsole.utils.towerControl.Database;




/**
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */

public class DatabaseTest {
	
	Database data;
	CivilAirPlane airlWithZeroPass;
	CivilAirPlane airlWithSameID;
	CivilHelicopter transOutsideCorr;
	MilitaryHelicopter carg;
	FlightPlan plan;
	
	@Before
	public void constructAirplanesAndDatabase() throws InvalidArgumentException, InvalidFlightIDException
	{
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		date2.add(12, 10);
		
		AltitudeCorridor corr = new AltitudeCorridor(80, 120);
		plan = new FlightPlan(date1, date2, 9, 9, 6);
		plan.addEvent(new AirCorridorInTime(date1, date2, corr));
		
		airlWithZeroPass = new CivilAirPlane("airl123", new GeographicalPosition(0,0,100), plan, 0);
		airlWithSameID = new CivilAirPlane("airl123", new GeographicalPosition(0,0,100), plan, 200);
		transOutsideCorr = new CivilHelicopter("trp123", new GeographicalPosition(0,0,50), plan, 20);
		carg = new MilitaryHelicopter("crg123", new GeographicalPosition(0,0,100), plan, false);
		
		data = new Database();
		
		data.addAirship(airlWithZeroPass);
		data.addAirship(transOutsideCorr);
	}
	
	@Test
	public void shouldAddTheNewAirplane() throws InvalidFlightIDException, InvalidArgumentException {
		assertTrue(data.addAirship(carg));
	}
	
	@Test
	public void shouldNotAddAnAirplaneWithARepeatedID() throws InvalidFlightIDException, InvalidArgumentException
	{
		assertFalse(data.addAirship(airlWithSameID));
	}
	
	@Test
	public void shouldNotThrowExceptionForUpdatingAnEmptyDatabase() {
		new Database().setAllAirplanesToNotUpdated();
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
		assertFalse(data.removeAirplane(new CivilAirPlane("air", new GeographicalPosition(0,0,0), plan, 0)));
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
		CivilHelicopter priv = new CivilHelicopter("priv123", new GeographicalPosition(0,0,100), plan, 0);
		data.addAirship(priv);
		
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
		
	Airship	airplane1 = new CivilAirPlane ("xpto01", new GeographicalPosition(20, 130, 0), new FlightPlan(new GregorianCalendar(2014, 11, 10, 00, 15), new GregorianCalendar(2014, 11, 10, 04, 15),9,9,6), 50);
	Airship	cHeli = new CivilHelicopter("xpto02",new GeographicalPosition(30, 30, 0), new FlightPlan(new GregorianCalendar(2014, 11, 11, 00, 15), new GregorianCalendar(2014, 11, 11, 04, 15),9,9,6), 10);
	Airship	mHeli = new MilitaryHelicopter("xpto03",new GeographicalPosition(40, 30, 0), new FlightPlan(new GregorianCalendar(2014, 11, 12, 00, 15), new GregorianCalendar(2014, 11, 12, 04, 15),5,6,6), true);
	
	Database newData = new Database();
	
	newData.addAirship(airplane1);
	newData.addAirship(cHeli);
	newData.addAirship(mHeli);
	
	int numberOfAirships = 0;
	numberOfAirships = newData.countAirships();
	
	assertTrue( 3 == numberOfAirships);
	}

}
