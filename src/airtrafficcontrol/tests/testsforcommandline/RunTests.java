package airtrafficcontrol.tests.testsforcommandline;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import airtrafficcontrol.tests.testsforcommandline.commands.GetAllUsersCommand_Tests;
import airtrafficcontrol.tests.testsforcommandline.commands.GetUserByUsernameCommand_Tests;
import airtrafficcontrol.tests.testsforcommandline.model.Airships_Test;
import airtrafficcontrol.tests.testsforcommandline.model.InMemoryDatabase_Tests;
import airtrafficcontrol.tests.testsforcommandline.model.UserTest;

@RunWith (Suite.class)
@Suite.SuiteClasses ({Airships_Test.class, GetUserByUsernameCommand_Tests.class,
		GetAllUsersCommand_Tests.class, InMemoryDatabase_Tests.class, UserTest.class})

public class RunTests {
	// the class remains empty, used only as a holder for the above annotations
}