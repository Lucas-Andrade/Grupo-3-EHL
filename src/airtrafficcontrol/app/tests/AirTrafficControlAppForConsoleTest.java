package airtrafficcontrol.app.tests;


import org.junit.Test;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.*;
import airtrafficcontrol.app.menuoptions.HelpOption_for_EHLsATCAppForConsole;


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
		new AirTrafficControlAppForConsole( "t", "d", "m", '-', 1, 1,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithNullAppTitle()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( null, "d", "m", '-', 1, 1,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateAnInstanceWithNullAppDeveloper()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", null, "m", '-', 1, 1,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateAnInstanceWithNullMenuTitle()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", null, '-', 1, 1,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithNullOptions()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", "m", '-', 1, 1,
				new HelpOption_for_EHLsATCAppForConsole(), null );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithZeroLengthOfSectionDelimiter()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", "m", '-', 0, 1,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotCreateInstancesWithZeroLinesBetweenSections()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", "m", '-', 1, 0,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
}
