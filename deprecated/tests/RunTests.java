package test.java.commandlineuserinterface;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.commandlineuserinterface.commands.GetAllUsersCommand_Tests;
import test.java.commandlineuserinterface.commands.GetUserByUsernameCommand_Tests;
import test.java.commandlineuserinterface.model.Airships_Test;
import test.java.commandlineuserinterface.model.InMemoryDatabase_Tests;
import test.java.commandlineuserinterface.model.UserTest;

@RunWith (Suite.class)
@Suite.SuiteClasses ({Airships_Test.class, GetUserByUsernameCommand_Tests.class,
		GetAllUsersCommand_Tests.class, InMemoryDatabase_Tests.class, UserTest.class})

public class RunTests {
	// the class remains empty, used only as a holder for the above annotations
}