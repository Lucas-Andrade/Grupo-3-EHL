package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import utils.Airliner;
import utils.Airship;
import utils.Database;
import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.PrivateJet;
import utils.ReadListOfFlights;
import utils.Transport;

public class ReadListOfFlightsTest {

	ReadListOfFlights reader;
	Database database;
	Map<String, Airship> data;
	
	@Before
	public void buildTheDatabase() throws IOException {
		reader  = new ReadListOfFlights();
		database = reader.readFlights("listOfFlightsTest.txt");
		data = database.getDatabase();
	}
	
	@Test
	public void testNumberOfAirplanes()
	{
		assertEquals(6, data.size());
	}
	
	@Test
	public void shouldHaveTheRightTypeOfAirplanes()
	{
		assertTrue(data.get("xptofligth01") instanceof utils.Transport);
		assertTrue(data.get("xptofligth02") instanceof utils.Airliner);
		assertTrue(data.get("xptofligth03") instanceof utils.Airliner);
		assertTrue(data.get("xptofligth04") instanceof utils.PrivateJet);
		assertTrue(data.get("xptofligth05") instanceof utils.CargoAircraft);
		assertTrue(data.get("xptofligth06") instanceof utils.CargoAircraft);
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf1()
	{
		Airship transport = data.get("xptofligth01");
		
		Calendar takeOff = transport.getTakeOffDate();
		Calendar landing = transport.getLandingDate();
		
		assertEquals(2014, takeOff.get(1));
		assertEquals(11, takeOff.get(2));
		assertEquals(6, takeOff.get(5));
		assertEquals(9, takeOff.get(11));
		assertEquals(22, takeOff.get(12));
		
		assertEquals(2014, landing.get(1));
		assertEquals(11, landing.get(2));
		assertEquals(6, landing.get(5));
		assertEquals(11, landing.get(11));
		assertEquals(12, landing.get(12));
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf2()
	{
		Airship airliner = data.get("xptofligth02");
		
		Calendar takeOff = airliner.getTakeOffDate();
		Calendar landing = airliner.getLandingDate();
		
		assertEquals(2014, takeOff.get(1));
		assertEquals(11, takeOff.get(2));
		assertEquals(6, takeOff.get(5));
		assertEquals(11, takeOff.get(11));
		assertEquals(3, takeOff.get(12));
		
		assertEquals(2014, landing.get(1));
		assertEquals(11, landing.get(2));
		assertEquals(6, landing.get(5));
		assertEquals(15, landing.get(11));
		assertEquals(43, landing.get(12));
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf3()
	{
		Airship airliner = data.get("xptofligth03");
		
		Calendar takeOff = airliner.getTakeOffDate();
		Calendar landing = airliner.getLandingDate();
		
		assertEquals(2014, takeOff.get(1));
		assertEquals(11, takeOff.get(2));
		assertEquals(6, takeOff.get(5));
		assertEquals(14, takeOff.get(11));
		assertEquals(57, takeOff.get(12));
		
		assertEquals(2014, landing.get(1));
		assertEquals(11, landing.get(2));
		assertEquals(6, landing.get(5));
		assertEquals(19, landing.get(11));
		assertEquals(48, landing.get(12));
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf4()
	{
		Airship jet = data.get("xptofligth04");
		
		Calendar takeOff = jet.getTakeOffDate();
		Calendar landing = jet.getLandingDate();
		
		assertEquals(2014, takeOff.get(1));
		assertEquals(11, takeOff.get(2));
		assertEquals(6, takeOff.get(5));
		assertEquals(20, takeOff.get(11));
		assertEquals(34, takeOff.get(12));
		
		assertEquals(2014, landing.get(1));
		assertEquals(11, landing.get(2));
		assertEquals(7, landing.get(5));
		assertEquals(4, landing.get(11));
		assertEquals(43, landing.get(12));
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf5()
	{
		Airship cargo = data.get("xptofligth05");
		
		Calendar takeOff = cargo.getTakeOffDate();
		Calendar landing = cargo.getLandingDate();
		
		assertEquals(2014, takeOff.get(1));
		assertEquals(11, takeOff.get(2));
		assertEquals(6, takeOff.get(5));
		assertEquals(21, takeOff.get(11));
		assertEquals(38, takeOff.get(12));
		
		assertEquals(2014, landing.get(1));
		assertEquals(11, landing.get(2));
		assertEquals(7, landing.get(5));
		assertEquals(4, landing.get(11));
		assertEquals(12, landing.get(12));
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf6()
	{
		Airship cargo = data.get("xptofligth06");
		
		Calendar takeOff = cargo.getTakeOffDate();
		Calendar landing = cargo.getLandingDate();
		
		assertEquals(2014, takeOff.get(1));
		assertEquals(11, takeOff.get(2));
		assertEquals(7, takeOff.get(5));
		assertEquals(0, takeOff.get(11));
		assertEquals(0, takeOff.get(12));
		
		assertEquals(2014, landing.get(1));
		assertEquals(11, landing.get(2));
		assertEquals(7, landing.get(5));
		assertEquals(4, landing.get(11));
		assertEquals(22, landing.get(12));
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf1()
	{
		Airship transport = data.get("xptofligth01");
		GeographicalPosition pos = transport.getGeographicPosition();
		
		assertEquals(39.3, pos.getLatitude(), 0.01);
		assertEquals(8, pos.getLongitude(), 0.01);
		assertEquals(0, pos.getAltitude(), 0.01);
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf2()
	{
		Airship airliner = data.get("xptofligth02");
		GeographicalPosition pos = airliner.getGeographicPosition();
		
		assertEquals(-39.3, pos.getLatitude(), 0.01);
		assertEquals(16, pos.getLongitude(), 0.01);
		assertEquals(0, pos.getAltitude(), 0.01);
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf3()
	{
		Airship airliner = data.get("xptofligth03");
		GeographicalPosition pos = airliner.getGeographicPosition();
		
		assertEquals(52.89, pos.getLatitude(), 0.01);
		assertEquals(-60, pos.getLongitude(), 0.01);
		assertEquals(0, pos.getAltitude(), 0.01);
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf4()
	{
		Airship jet = data.get("xptofligth04");
		GeographicalPosition pos = jet.getGeographicPosition();
		
		assertEquals(0.3, pos.getLatitude(), 0.01);
		assertEquals(45.04, pos.getLongitude(), 0.01);
		assertEquals(0, pos.getAltitude(), 0.01);
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf5()
	{
		Airship cargo = data.get("xptofligth05");
		GeographicalPosition pos = cargo.getGeographicPosition();
		
		assertEquals(-67.39, pos.getLatitude(), 0.01);
		assertEquals(-10, pos.getLongitude(), 0.01);
		assertEquals(0, pos.getAltitude(), 0.01);
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf6()
	{
		Airship cargo = data.get("xptofligth06");
		GeographicalPosition pos = cargo.getGeographicPosition();
		
		assertEquals(17.33, pos.getLatitude(), 0.01);
		assertEquals(52.05, pos.getLongitude(), 0.01);
		assertEquals(0, pos.getAltitude(), 0.01);
	}
	
	@Test
	public void shouldReturnTrueBecauseTheFirstAirplaneHasArmament()
	{
		assertTrue(((Transport)data.get("xptofligth01")).hasArmament());
	}
	
	@Test
	public void shouldReturnTheRightNumberOfPassengers()
	{
		assertEquals(203, ((Airliner)data.get("xptofligth02")).getPassengersNumber());
		assertEquals(0, ((Airliner)data.get("xptofligth03")).getPassengersNumber());
		assertEquals(24, ((PrivateJet)data.get("xptofligth04")).getPassengersNumber());
	}
	
	@Test
	public void shouldReturnTheRightObservationOf1()
	{
		Airship airliner = data.get("xptofligth01");
		FlightPlan plan = airliner.getPlan();
		
	//	plan.getCorridorAtTime(new GregorianCalendar());
		assertEquals("", airliner.getObservations());
		
	}
}
