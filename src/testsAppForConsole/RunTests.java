package testsAppForConsole;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import testsAppForConsole.commands.GetAllUsersCommand_Tests;
import testsAppForConsole.commands.GetUserByUsernameCommand_Tests;
import testsAppForConsole.commands.PostUserCommand_Tests;
import testsAppForConsole.model.Airships_Test;
import testsAppForConsole.model.InMemoryDatabase_Tests;
import testsAppForConsole.model.UserTest;

@RunWith (Suite.class)
@Suite.SuiteClasses ({Airships_Test.class, InMemoryDatabase_Tests.class, UserTest.class,
		GetAllUsersCommand_Tests.class, GetUserByUsernameCommand_Tests.class,
		PostUserCommand_Tests.class})
public class RunTests {
	// the class remains empty, used only as a holder for the above annotations
}