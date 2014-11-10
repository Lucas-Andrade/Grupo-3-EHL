package tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import deprecated.AddAFlightOption;
import app.HelpOption;
import app.InvalidOptionNumberException;
import app.OptionsMenu;


public class OptionsMenuTest
{
	
	OptionsMenu menuT;
	HelpOption option1;
	AddAFlightOption option2;
	
	@Before
	public void constructsOptionsMenu() {
		option1 = new HelpOption();
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
