package airtrafficcontrol.tests.testsforcommandline;


import static org.junit.Assert.*;
import org.junit.Test;
import airtrafficcontrol.app.appforcommandline.exceptions.commands.InvalidParameterValueException;


public class InvalidParameterValueExceptionTest
{
	
	@Test
	public void test() {
		assertEquals(
				"Demanding parameter with name NAME has invalid value null.",
				new InvalidParameterValueException( "NAME", null ).getMessage() );
	}
}
