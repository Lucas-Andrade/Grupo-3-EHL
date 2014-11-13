package airtrafficcontrol.app.tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.OptionsMenu;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.deprecated.AddAFlightOption;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.menuoptions.HelpOption_for_EHLsATCAppForConsole;
import airtrafficcontrol.app.menuoptions.ExitOption;


/**
 * Test class that targets the class {@link OptionsMenu}.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class OptionsMenuTest
{
	
	OptionsMenu menuT;
	HelpOption_for_EHLsATCAppForConsole option1;
	AddAFlightOption option2;
	AirTrafficControlAppForConsole app;
	
	
	
	@Before
	public void constructsOptionsMenu() throws InvalidArgumentException {
		option1 = new HelpOption_for_EHLsATCAppForConsole();
		option2 = new AddAFlightOption();
		menuT = new OptionsMenu( "menu", option1 );
		app = new AirTrafficControlAppForConsole( "at", "d", "mt", '*', 1, 1,
				option1 );
	}
	
	
	@Test
	public void shouldReturnTheNumberOfOptions() {
		// Arrange
		String expectedMenu = "\n1. Help!";
		
		// Assert
		assertTrue( expectedMenu.equals( menuT.toString() ) );
	}
	
	@Test
	public void shouldGetOptionTitle() {
		// Arrange
		String expectedTitle = "Help!";
		int numberOfTheOption = 1;
		
		// Assert
		try
		{
			assertTrue( expectedTitle.equals( menuT
					.getOptionTitle( numberOfTheOption ) ) );
		}
		catch( InvalidOptionNumberException e )
		{
			assertEquals( 0, 1 );
		}
	}
	
	@Test
	public void shouldExecuteOption() throws InvalidArgumentException {
		// Assert
		try
		{
			assertTrue( menuT.executeOptionToConsole( 1, app ) );
		}
		catch( InvalidOptionNumberException e )
		{
			assertEquals( 0, 1 );
		}
	}
	
	@Test( expected = InvalidOptionNumberException.class )
	public void shouldGetInvalidArgumentExceptionWithAInexistentNumberOfOption()
			throws InvalidOptionNumberException, InvalidArgumentException {
		// Arrange
		HelpOption_for_EHLsATCAppForConsole option01 = new HelpOption_for_EHLsATCAppForConsole();
		ExitOption option02 = new ExitOption();
		AddAFlightOption option03 = new AddAFlightOption();
		
		OptionsMenu menuTest = (new OptionsMenu( "SLB", option01, option02,
				option03 ));
		
		menuTest.getOptionTitle( 4 );
	}
	
	@Test( expected = InvalidOptionNumberException.class )
	public void shouldGetInvalidArgumentExceptionWithANegativeNumberOfOption()
			throws InvalidOptionNumberException, InvalidArgumentException {
		// Arrange
		HelpOption_for_EHLsATCAppForConsole option01 = new HelpOption_for_EHLsATCAppForConsole();
		ExitOption option02 = new ExitOption();
		AddAFlightOption option03 = new AddAFlightOption();
		
		OptionsMenu menuTest = (new OptionsMenu( "SLB", option01, option02,
				option03 ));
		
		menuTest.getOptionTitle( -1 );
	}
}
