
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import exceptions.WrongLoginPasswordException;


/**
 * Test case that targets the {@link WrongLoginPasswordException} class.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class WrongLoginPasswordExceptionTest {
    
    @Test
    public void shouldHaveTheExpectedMessage() {
        
        assertEquals( "Wrong password: u1's password is not p1.",
                      new WrongLoginPasswordException( "u1", "p1" ).getMessage() );
    }
    
}
