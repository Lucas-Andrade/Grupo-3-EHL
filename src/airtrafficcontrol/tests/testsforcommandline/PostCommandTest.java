package airtrafficcontrol.tests.testsforcommandline;


import java.util.HashMap;

import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.commands.postcommands.PostUserCommand;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;


public class PostCommandTest
{
	
	@Test
	public void test() {
		new PostUserCommand( new InMemoryUserDatabase(),
				new InMemoryUserDatabase(), new HashMap< String, String >() );
	}
}
