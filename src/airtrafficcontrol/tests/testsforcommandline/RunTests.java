package airtrafficcontrol.tests.testsforcommandline;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith (Suite.class)
@Suite.SuiteClasses ({Airships_Test.class, GetUserByUsernameCommand_Tests.class,
		GetAllUsersCommand_Tests.class, InMemoryAirshipDatabaseTests.class, UserTest.class})

public class RunTests {
	// the class remains empty, used only as a holder for the above annotations
}