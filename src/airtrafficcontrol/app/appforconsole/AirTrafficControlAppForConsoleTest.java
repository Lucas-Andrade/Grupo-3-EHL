package airtrafficcontrol.app.appforconsole;


import static org.junit.Assert.*;
import org.junit.Test;
import airtrafficcontrol.app.AirTrafficControlApp;
import airtrafficcontrol.app.exceptions.*;
import airtrafficcontrol.app.menuoptions.HelpOption_for_EHLsATCAppForConsole;


public class AirTrafficControlAppForConsoleTest
{
	
	@Test( expected = InvalidArgumentException.class )
	public void createsInstancesWithNullOptions()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", "m", '-', 1, 1,
				new HelpOption_for_EHLsATCAppForConsole(), null );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void createsInstancesWithNullLengthOfSectionDelimiter()
			throws InvalidArgumentException {
		new AirTrafficControlAppForConsole( "t", "d", "m", '-', 0, 1,
				new HelpOption_for_EHLsATCAppForConsole() );
	}
	
}
