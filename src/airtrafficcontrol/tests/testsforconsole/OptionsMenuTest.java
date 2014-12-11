package airtrafficcontrol.tests.testsforconsole;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.OptionsMenu;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.appforconsole.menuoptions.ExitOption;
import airtrafficcontrol.app.appforconsole.menuoptions.HelpOption_for_EHLsATCAppForConsole;
import airtrafficcontrol.app.appforconsole.menuoptions.Option;


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
	Option option1;
	Option option2;
	AirTrafficControlAppForConsole app;
	
	
	
	@Before
	public void constructsOptionsMenu() throws InvalidArgumentException {
		option1 = new HelpOption_for_EHLsATCAppForConsole();
		option2 = new ExitOption();
		menuT = new OptionsMenu( "menu", option1 );
		app = new AirTrafficControlAppForConsole( "at", "d", '*', 1, 1, "mt",
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
	
	@Test( expected = InvalidOptionNumberException.class )
	public void shouldGetInvalidArgumentExceptionWithAInexistentNumberOfOption()
			throws InvalidOptionNumberException, InvalidArgumentException {
		
		menuT.getOptionTitle( 4 );
	}
	
	@Test( expected = InvalidOptionNumberException.class )
	public void shouldGetInvalidArgumentExceptionWithANegativeNumberOfOption()
			throws InvalidOptionNumberException, InvalidArgumentException {
		
		menuT.getOptionTitle( -1 );
	}
}
