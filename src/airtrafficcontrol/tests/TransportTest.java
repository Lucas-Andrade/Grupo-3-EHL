package airtrafficcontrol.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.FlightPlan;
import airtrafficcontrol.app.utils.GeographicalPosition;
import airtrafficcontrol.app.utils.Transport;
import airtrafficcontrol.app.utils.towerControl.ReadListOfFlights;


public class TransportTest
{
	
	Transport trp;
	Transport trp2;
	Transport trp3;
	Transport trp4;
	Calendar date1;
	Calendar date2;
	
	@Before
	public void constructTwoAirplanes() throws InvalidArgumentException {
		date1 = new GregorianCalendar();
		date2 = ReadListOfFlights.createDefensiveCopyOfTheDate( date1 );
		date2.add( Calendar.MINUTE, 20 );
		
		trp = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		trp2 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), true );
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToTakeOffOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture()
			throws InvalidArgumentException {
		assertEquals( 10, trp.getNumberOfMinutesToTakeOff() );
		assertEquals( 10, trp2.getNumberOfMinutesToTakeOff() );
		assertEquals( 10, trp.getNumberOfMinutesToTakeOff() );
		
		trp.setNumberOfMinutesToTakeOff( 15 );
		
		assertEquals( 15, trp.getNumberOfMinutesToTakeOff() );
		assertEquals( 15, trp2.getNumberOfMinutesToTakeOff() );
		assertEquals( 15, trp.getNumberOfMinutesToTakeOff() );
		
		trp3 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		trp4 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		
		assertEquals( 15, trp3.getNumberOfMinutesToTakeOff() );
		assertEquals( 15, trp4.getNumberOfMinutesToTakeOff() );
		assertEquals( 15, trp.getNumberOfMinutesToTakeOff() );
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToLandOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture()
			throws InvalidArgumentException {
		assertEquals( 12, trp.getNumberOfMinutesToLand() );
		assertEquals( 12, trp2.getNumberOfMinutesToLand() );
		assertEquals( 12, trp.getNumberOfMinutesToLand() );
		
		trp.setNumberOfMinutesToLand( 20 );
		
		assertEquals( 20, trp.getNumberOfMinutesToLand() );
		assertEquals( 20, trp2.getNumberOfMinutesToLand() );
		assertEquals( 20, trp.getNumberOfMinutesToLand() );
		
		trp3 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		trp4 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		
		assertEquals( 20, trp3.getNumberOfMinutesToLand() );
		assertEquals( 20, trp4.getNumberOfMinutesToLand() );
		assertEquals( 20, trp.getNumberOfMinutesToLand() );
	}
	
	@Test
	public void shouldAlterTheNumberOfMinutesToSwitchCorridorOfAllThePreviouslyConstructedAircraftAndAllThatWillBeConstructedInTheFuture()
			throws InvalidArgumentException {
		
		assertEquals( 5, trp.getNumberOfMinutesToSwitchCorridor() );
		assertEquals( 5, trp2.getNumberOfMinutesToSwitchCorridor() );
		assertEquals( 5, trp.getNumberOfMinutesToSwitchCorridor() );
		
		trp.setNumberOfMinutesToSwitchCorridor( 1 );
		
		assertEquals( 1, trp.getNumberOfMinutesToSwitchCorridor() );
		assertEquals( 1, trp2.getNumberOfMinutesToSwitchCorridor() );
		assertEquals( 1, trp.getNumberOfMinutesToSwitchCorridor() );
		
		trp3 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		trp4 = new Transport( "mgrf", new GeographicalPosition( 0, 0, 0 ),
				new FlightPlan( date1, date2 ), false );
		
		assertEquals( 1, trp3.getNumberOfMinutesToSwitchCorridor() );
		assertEquals( 1, trp4.getNumberOfMinutesToSwitchCorridor() );
		assertEquals( 1, trp.getNumberOfMinutesToSwitchCorridor() );
	}
	
	@Test
	public void shouldReturnWhetherTheAirplaneHasArmamentOrNot() {
		assertTrue( trp2.hasArmament() );
		assertFalse( trp.hasArmament() );
	}
}
