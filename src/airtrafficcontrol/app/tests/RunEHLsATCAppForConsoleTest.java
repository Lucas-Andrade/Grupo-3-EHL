package airtrafficcontrol.app.tests;


import static org.junit.Assert.*;
import org.junit.Test;
import airtrafficcontrol.RunEHLsATCAppForConsole;


public class RunEHLsATCAppForConsoleTest
{
	
	@Test
	public void databaseShouldBeEmptyBeforeRunning() {
		assertTrue( RunEHLsATCAppForConsole.app.tools.flightsDB.isEmpty() );
	}
	
}
