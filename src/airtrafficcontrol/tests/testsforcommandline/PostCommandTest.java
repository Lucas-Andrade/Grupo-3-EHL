package airtrafficcontrol.tests.testsforcommandline;


import java.util.HashMap;
import org.junit.Test;
import airtrafficcontrol.app.appforcommandline.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.commands.PostUserCommand;


public class PostCommandTest
{
	
	@Test
	public void test() {
		new PostUserCommand( new InMemoryUserDatabase(),
				new InMemoryUserDatabase(), new HashMap< String, String >() );
	}
}
