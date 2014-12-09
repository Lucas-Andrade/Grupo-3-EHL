package airtrafficcontrol.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.GregorianCalendar;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.ConsoleInputHandler;
import airtrafficcontrol.app.appforconsole.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.hangar.Airship;
import airtrafficcontrol.app.appforconsole.utils.hangar.CivilAirPlane;
import airtrafficcontrol.app.appforconsole.utils.towerControl.Database;



/**
 * Test class that targets the class {@link ConsoleInputHandler}.
 * 
 * <p style="font-size:14">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>Needs input from console to test.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
@SuppressWarnings( "static-access" )
public class ConsoleInputHandlerTest
{
	
	ConsoleInputHandler in = new ConsoleInputHandler();
	
	
	// TESTS ABOUT GETTING int'S
	
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowExceptionIfMinIsBiggerThanMax_getValid()
			throws InvalidArgumentException {
		in.getAValidIntFromUser( 3, 2, "i" );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowExceptionIfMinIsBiggerThanMax_askUser()
			throws InvalidArgumentException {
		in.getAValidIntFromUser( 3, 2, "i" );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowExceptionIfNotBetweenMinAndMax_getValid()
			throws InvalidArgumentException {
		in.getAValidIntFromUser( 1, 2, "insert 3 in console: " );
	}
	
	@Test
	public void shouldReturn3_askUser() throws InvalidArgumentException {
		assertEquals( 3, in.askTheUserForAnInt( 1, 5, "insert 3 in console: " ) );
	}
	
	@Test
	public void shouldContinueAskingTillGettingAValidValue()
			throws InvalidArgumentException {
		in.getAValidIntFromUser( 1, 4,
				"insert at least one num not between 1 and 4\n(to test if he asks again): " );
	}
	
	
	
	// TESTS ABOUT ALPHANUMERIC String'S
	
	
	@Test
	public void nullStringsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler.isNotAnAlphanumericString( null ) );
	}
	
	@Test
	public void emptyStringsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler.isNotAnAlphanumericString( "" ) );
	}
	
	@Test
	public void stringsWithParagraphsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "sakj\nlsekrg" ) );
	}
	
	@Test
	public void stringsWithSpacesShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgsf asdgsd" ) );
	}
	
	@Test
	public void stringsWithSymbolsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgs.asd/gsd" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "af_asdg:sd" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgs#dgs@d" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgsf?g`sd" ) );
		assertTrue( ConsoleInputHandler.isNotAnAlphanumericString( "afg[gsd{" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgs}dgs,d" ) );
	}
	
	@Test
	public void shouldBeAlphaNumericStrings() {
		assertFalse( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgasdgzsd" ) );
		assertFalse( ConsoleInputHandler
				.isNotAnAlphanumericString( "345789rnv1fis9d" ) );
		assertFalse( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgAEFsZgfdiFDSGF3sd" ) );
		assertFalse( ConsoleInputHandler.isNotAnAlphanumericString( "ABORT" ) );
	}
	
	
	
	// TESTS ABOUT GETTING FightID's
	
	@Test( expected = InvalidFlightIDException.class )
	public void shouldThrowExceptionWhenReceivesNonAlphanumericFlightID_askUser()
			throws InvalidFlightIDException {
		in.askTheUserForAFlightID( "give me a non-alphanumeric flightID: " );
	}
	
	@Test
	public void shouldAcceptAnAlphanumericFlightID_askUser()
			throws InvalidFlightIDException {
		assertEquals( "goodID1234",
				in.askTheUserForAFlightID( "insert «goodID1234»: " ) );
	}
	
	@Test
	public void shouldContinueToAskWhenReceivesNonAlphanumericFlightID_getValid()
			throws InvalidFlightIDException {
		in.getAValidFlightIDFromUser( "insert at least one non-alphanumeric string\n(to test if he asks again): " );
	}
	
	@Test
	public void shouldAcceptAnAlphanumericFlightID_getValid()
			throws InvalidFlightIDException {
		assertEquals( "goodID1234",
				in.getAValidFlightIDFromUser( "insert «goodID1234»: " ) );
	}
	
	@Test( expected = DatabaseNotFoundException.class )
	public void shouldThrowExceptionIfInsertedNullDatabase()
			throws InvalidArgumentException, DatabaseNotFoundException,
			InvalidFlightIDException {
		in.get_AFlightIDExistentInACertainDatabase_FromUser( null, "i" );
	}
	
	@Test
	public void shouldAcceptAFlightIDInADatabase()
			throws InvalidArgumentException, DatabaseNotFoundException,
			InvalidFlightIDException {
		
		// assert
		Airship airplane1 = new CivilAirPlane( "xpto01", new GeographicalPosition(
				20, 130, 0 ), new FlightPlan( new GregorianCalendar( 2014, 11,
				10, 00, 15 ), new GregorianCalendar( 2014, 11, 10, 04, 15 ),0,0,0 ),
				50 );
		Database db = new Database();
		db.addAirship( airplane1 );
		
		// act & assert
		assertEquals( "xpto01",
				in.get_AFlightIDExistentInACertainDatabase_FromUser( db,
						"write «xpto01»: " ) );
	}
	
	@Test
	public void shouldContinueAskingAFlightIDInADatabase()
			throws InvalidArgumentException, DatabaseNotFoundException,
			InvalidFlightIDException {
		Airship airplane1 = new CivilAirPlane( "xpto01", new GeographicalPosition(
				20, 130, 0 ), new FlightPlan( new GregorianCalendar( 2014, 11,
				10, 00, 15 ), new GregorianCalendar( 2014, 11, 10, 04, 15 ), 0,0,0),
				50 );
		Database db = new Database();
		db.addAirship( airplane1 );
		
		assertEquals(
				"xpto01",
				in.get_AFlightIDExistentInACertainDatabase_FromUser( db,
						"write something (to see if he keeps \n asking until you put «xpto01»): " ) );
	}
	
	@Test
	public void shouldReturnABORT() throws InvalidArgumentException,
			DatabaseNotFoundException, InvalidFlightIDException {
		Airship airplane1 = new CivilAirPlane( "xpto01", new GeographicalPosition(
				20, 130, 0 ), new FlightPlan( new GregorianCalendar( 2014, 11,
				10, 00, 15 ), new GregorianCalendar( 2014, 11, 10, 04, 15 ), 0,0,0),
				50 );
		Database db = new Database();
		db.addAirship( airplane1 );
		
		assertEquals( "ABORT",
				in.get_AFlightIDExistentInACertainDatabase_FromUser( db,
						"type «ABORT» and press enter: " ) );
	}
	
}
