package airtrafficcontrol.app.tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.OptionsMenu;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.menuoptions.AddAFlightOption;
import airtrafficcontrol.app.menuoptions.HelpOption_for_EHLsATCAppForConsole;


public class OptionsMenuTest
{
	
	OptionsMenu menuT;
	HelpOption_for_EHLsATCAppForConsole option1;
	AddAFlightOption option2;
	
	@Before
	public void constructsOptionsMenu() {
		option1 = new HelpOption_for_EHLsATCAppForConsole();
		option2 = new AddAFlightOption();
		menuT = new OptionsMenu( "menu", option1 );
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
	public void shouldExecuteOption() {
		// Assert
		try
		{
			assertTrue( menuT.executeOptionToConsole( 1 ) );
		}
		catch( InvalidOptionNumberException e )
		{
			assertEquals( 0, 1 );
		}
	}
	
	
}
