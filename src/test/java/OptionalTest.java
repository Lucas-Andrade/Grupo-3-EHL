package test.java;


import java.util.List;
import junit.framework.Assert;
import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import org.junit.Test;


/**
 * Test class that targets the class {@link Optional}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class OptionalTest
{
	
	// CONSTRUCTORS
	
	@Test(expected=InvalidArgumentException.class)
	public void shouldThrowExceptionIfConstructor1ReceivesNullException() throws InvalidArgumentException {
		new Optional<String>("hello world",null);
	}	

	@Test(expected=InvalidArgumentException.class)
	public void shouldThrowExceptionIfConstructor2ReceivesNullException() throws InvalidArgumentException {
		new Optional<String>("hello world",null);
	}
	
}
