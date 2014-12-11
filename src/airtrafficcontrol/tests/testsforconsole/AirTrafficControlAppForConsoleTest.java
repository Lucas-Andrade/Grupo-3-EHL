package airtrafficcontrol.tests.testsforconsole;


import org.junit.Test;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.exceptions.*;
import airtrafficcontrol.app.appforconsole.menuoptions.HelpOption_for_EHLsATCAppForConsole;


/**
 * Test class that targets the class {@link AirTrafficControlAppForConsole}.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AirTrafficControlAppForConsoleTest
{
	
	@Test
	public void shouldCreateAnInstanceWithoutThrowingExceptions()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", '-', 1, 1,"m", 
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithNullAppTitle()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( null, "d", '-', 1, 1,"m", 
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateAnInstanceWithNullAppDeveloper()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", null, '-', 1, 1,"m", 
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateAnInstanceWithNullMenuTitle()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d",  '-', 1, 1,null,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithNullOptions()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", '-', 1, 1,"m", 
				new HelpOption_for_EHLsATCAppForConsole(), null );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithZeroLengthOfSectionDelimiter()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", '-', 0, 1,"m", 
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithZeroLinesBetweenSections()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", '-', 1, 0, "m",
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
}
