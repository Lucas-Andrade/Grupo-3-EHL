package airtrafficcontrol.tests;


import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.hangar.Airship;
import airtrafficcontrol.app.appforconsole.utils.towerControl.Database;
import airtrafficcontrol.app.appforconsole.utils.towerControl.ReadListOfFlights;


public class ReadListOfFlightsTest
{
	
	ReadListOfFlights reader;
	Database database;
	Map< String, Airship > data;
	
	@Before
	public void buildTheDatabase() throws IOException,
			InvalidFlightIDException, InvalidArgumentException {
		reader = new ReadListOfFlights();
		database = reader.readFlights( "listOfFlightsTest.txt" );
		data = database.getDatabase();
	}
	
	@Test
	public void testNumberOfAirplanes() {
		assertEquals( 6, data.size() );
	}
	
//	@Test
//	public void shouldHaveTheRightTypeOfAirplanes() {
//		assertTrue( data.get( "xptofligth01" ) instanceof airtrafficcontrol.app.utils.hangar.Transport );
//		assertTrue( data.get( "xptofligth02" ) instanceof airtrafficcontrol.app.utils.hangar.Airliner );
//		assertTrue( data.get( "xptofligth03" ) instanceof airtrafficcontrol.app.utils.hangar.Airliner );
//		assertTrue( data.get( "xptofligth04" ) instanceof airtrafficcontrol.app.utils.hangar.PrivateJet );
//		assertTrue( data.get( "xptofligth05" ) instanceof airtrafficcontrol.app.utils.hangar.CargoAircraft );
//		assertTrue( data.get( "xptofligth06" ) instanceof airtrafficcontrol.app.utils.hangar.CargoAircraft );
//	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf1() {
		Airship transport = data.get( "xptofligth01" );
		
		Calendar takeOff = transport.getTakeOffDate();
		Calendar landing = transport.getLandingDate();
		
		assertEquals( 2014, takeOff.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, takeOff.get( Calendar.MONTH ) );
		assertEquals( 6, takeOff.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 9, takeOff.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 22, takeOff.get( Calendar.MINUTE ) );
		
		assertEquals( 2014, landing.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, landing.get( Calendar.MONTH ) );
		assertEquals( 6, landing.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 11, landing.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 12, landing.get( Calendar.MINUTE ) );
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf2() {
		Airship airliner = data.get( "xptofligth02" );
		
		Calendar takeOff = airliner.getTakeOffDate();
		Calendar landing = airliner.getLandingDate();
		
		assertEquals( 2014, takeOff.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, takeOff.get( Calendar.MONTH ) );
		assertEquals( 6, takeOff.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 11, takeOff.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 3, takeOff.get( Calendar.MINUTE ) );
		
		assertEquals( 2014, landing.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, landing.get( Calendar.MONTH ) );
		assertEquals( 6, landing.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 15, landing.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 43, landing.get( Calendar.MINUTE ) );
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf3() {
		Airship airliner = data.get( "xptofligth03" );
		
		Calendar takeOff = airliner.getTakeOffDate();
		Calendar landing = airliner.getLandingDate();
		
		assertEquals( 2014, takeOff.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, takeOff.get( Calendar.MONTH ) );
		assertEquals( 6, takeOff.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 14, takeOff.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 57, takeOff.get( Calendar.MINUTE ) );
		
		assertEquals( 2014, landing.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, landing.get( Calendar.MONTH ) );
		assertEquals( 6, landing.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 19, landing.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 48, landing.get( Calendar.MINUTE ) );
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf4() {
		Airship jet = data.get( "xptofligth04" );
		
		Calendar takeOff = jet.getTakeOffDate();
		Calendar landing = jet.getLandingDate();
		
		assertEquals( 2014, takeOff.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, takeOff.get( Calendar.MONTH ) );
		assertEquals( 6, takeOff.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 20, takeOff.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 34, takeOff.get( Calendar.MINUTE ) );
		
		assertEquals( 2014, landing.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, landing.get( Calendar.MONTH ) );
		assertEquals( 7, landing.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 4, landing.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 43, landing.get( Calendar.MINUTE ) );
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf5() {
		Airship cargo = data.get( "xptofligth05" );
		
		Calendar takeOff = cargo.getTakeOffDate();
		Calendar landing = cargo.getLandingDate();
		
		assertEquals( 2014, takeOff.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, takeOff.get( Calendar.MONTH ) );
		assertEquals( 6, takeOff.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 21, takeOff.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 38, takeOff.get( Calendar.MINUTE ) );
		
		assertEquals( 2014, landing.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, landing.get( Calendar.MONTH ) );
		assertEquals( 7, landing.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 4, landing.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 12, landing.get( Calendar.MINUTE ) );
	}
	
	@Test
	public void shouldReturnTheRightDatesOfLandingAndTakeOffOf6() {
		Airship cargo = data.get( "xptofligth06" );
		
		Calendar takeOff = cargo.getTakeOffDate();
		Calendar landing = cargo.getLandingDate();
		
		assertEquals( 2014, takeOff.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, takeOff.get( Calendar.MONTH ) );
		assertEquals( 7, takeOff.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 0, takeOff.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 0, takeOff.get( Calendar.MINUTE ) );
		
		assertEquals( 2014, landing.get( Calendar.YEAR ) );
		assertEquals( 11 - 1, landing.get( Calendar.MONTH ) );
		assertEquals( 7, landing.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( 4, landing.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 22, landing.get( Calendar.MINUTE ) );
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf1() {
		Airship transport = data.get( "xptofligth01" );
		GeographicalPosition pos = transport.getGeographicPosition();
		
		assertEquals( 39.3, pos.getLatitude(), 0.01 );
		assertEquals( 8, pos.getLongitude(), 0.01 );
		assertEquals( 0, pos.getAltitude(), 0.01 );
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf2() {
		Airship airliner = data.get( "xptofligth02" );
		GeographicalPosition pos = airliner.getGeographicPosition();
		
		assertEquals( -39.3, pos.getLatitude(), 0.01 );
		assertEquals( 16, pos.getLongitude(), 0.01 );
		assertEquals( 0, pos.getAltitude(), 0.01 );
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf3() {
		Airship airliner = data.get( "xptofligth03" );
		GeographicalPosition pos = airliner.getGeographicPosition();
		
		assertEquals( 52.89, pos.getLatitude(), 0.01 );
		assertEquals( -60, pos.getLongitude(), 0.01 );
		assertEquals( 0, pos.getAltitude(), 0.01 );
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf4() {
		Airship jet = data.get( "xptofligth04" );
		GeographicalPosition pos = jet.getGeographicPosition();
		
		assertEquals( 0.3, pos.getLatitude(), 0.01 );
		assertEquals( 45.04, pos.getLongitude(), 0.01 );
		assertEquals( 0, pos.getAltitude(), 0.01 );
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf5() {
		Airship cargo = data.get( "xptofligth05" );
		GeographicalPosition pos = cargo.getGeographicPosition();
		
		assertEquals( -67.39, pos.getLatitude(), 0.01 );
		assertEquals( -10, pos.getLongitude(), 0.01 );
		assertEquals( 0, pos.getAltitude(), 0.01 );
	}
	
	@Test
	public void shouldReturnTheCorrectTakeOffCoordinatesOf6() {
		Airship cargo = data.get( "xptofligth06" );
		GeographicalPosition pos = cargo.getGeographicPosition();
		
		assertEquals( 17.33, pos.getLatitude(), 0.01 );
		assertEquals( 52.05, pos.getLongitude(), 0.01 );
		assertEquals( 0, pos.getAltitude(), 0.01 );
	}
	
//	@Test
//	public void shouldReturnTrueBecauseTheFirstAirplaneHasArmament() {
//		assertTrue( ((Transport)data.get( "xptofligth01" )).hasArmament() );
//	}
//	
//	@Test
//	public void shouldReturnTheRightNumberOfPassengers() {
//		assertEquals( 203,
//				((Airliner)data.get( "xptofligth02" )).getPassengersNumber() );
//		assertEquals( 0,
//				((Airliner)data.get( "xptofligth03" )).getPassengersNumber() );
//		assertEquals( 24,
//				((PrivateJet)data.get( "xptofligth04" )).getPassengersNumber() );
//	}
	
	@Test
	public void shouldReturnTheObservationThatTheAirplaneHasAlreadyLanded()
			throws InvalidArgumentException {
		assertEquals( "The airplane has already landed.",
				data.get( "xptofligth01" ).getObservations() );
	}
	
	@Test
	public void shouldReturnTheRightCorridorFor1()
			throws InvalidArgumentException {
		Airship transport = data.get( "xptofligth01" );
		FlightPlan plan = transport.getPlan();
		
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 9, 25 ) ) );
		assertEquals(
				11500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 10, 30 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				10500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 10, 30 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 10, 34 ) ) );
		assertEquals(
				12000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 10, 40 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				11000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 10, 40 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 11, 11 ) ) );
	}
	
	@Test
	public void shouldReturnTheRightCorridorFor2()
			throws InvalidArgumentException {
		Airship airliner = data.get( "xptofligth02" );
		FlightPlan plan = airliner.getPlan();
		
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 11, 4 ) ) );
		assertEquals(
				11000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 12, 00 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				10500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 12, 00 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 12, 10 ) ) );
		assertEquals(
				13000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 12, 59 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				12000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 12, 59 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 13, 01 ) ) );
		assertEquals(
				17000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 13, 40 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				15000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 13, 40 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 15, 42 ) ) );
	}
	
	@Test
	public void shouldReturnTheRightCorridorFor3()
			throws InvalidArgumentException {
		Airship airliner = data.get( "xptofligth03" );
		FlightPlan plan = airliner.getPlan();
		
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 14, 58 ) ) );
		assertEquals(
				11500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 16, 00 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				10500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 16, 00 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 12, 04 ) ) );
		assertEquals(
				12000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 19, 00 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				11000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 19, 00 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 19, 47 ) ) );
	}
	
	@Test
	public void shouldReturnTheRightCorridorFor4()
			throws InvalidArgumentException {
		Airship jet = data.get( "xptofligth04" );
		FlightPlan plan = jet.getPlan();
		
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 20, 35 ) ) );
		assertEquals(
				11000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 23, 59 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				10500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 23, 59 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 0, 03 ) ) );
		assertEquals(
				13000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 4, 00 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				12000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 4, 00 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 4, 42 ) ) );
	}
	
	@Test
	public void shouldReturnTheRightCorridorFor5()
			throws InvalidArgumentException {
		Airship cargo = data.get( "xptofligth05" );
		FlightPlan plan = cargo.getPlan();
		
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 6, 21, 39 ) ) );
		assertEquals(
				11500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 23, 56 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				10500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 6, 23, 56 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 0, 0 ) ) );
		assertEquals(
				12000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 3, 00 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				11000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 3, 00 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 4, 11 ) ) );
	}
	
	@Test
	public void shouldReturnTheRightCorridorFor6()
			throws InvalidArgumentException {
		Airship cargo = data.get( "xptofligth06" );
		FlightPlan plan = cargo.getPlan();
		
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 0, 1 ) ) );
		assertEquals(
				11500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 2, 0 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				10500,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 2, 0 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 2, 4 ) ) );
		assertEquals(
				12000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 4, 00 ) )
						.getUpperLimit(), 0.01 );
		assertEquals(
				11000,
				plan.getCorridorAtTime(
						new GregorianCalendar( 2014, 10, 7, 4, 00 ) )
						.getLowerLimit(), 0.01 );
		assertEquals( null, plan.getCorridorAtTime( new GregorianCalendar(
				2014, 10, 7, 4, 21 ) ) );
	}
}
