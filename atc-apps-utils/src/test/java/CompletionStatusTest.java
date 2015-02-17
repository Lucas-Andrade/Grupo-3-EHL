import org.junit.Assert;
import org.junit.Test;
import utils.CompletionStatus;



public class CompletionStatusTest {
    
    @Test
    public void shouldCreateACompletionStatusRepresentingAFailureWithAMessage() {
    
        String message = "FAILED ON PURPOSE!";
        CompletionStatus failedWithMessage = new CompletionStatus( false, message );
        
        Assert.assertFalse( failedWithMessage.completedSuccessfully() );
        Assert.assertEquals( message, failedWithMessage.getMessage() );
    }
    
    @Test
    public void shouldCreateACompletionStatusRepresentingAFailureWithoutMessage() {
    
        CompletionStatus failedWithoutMessage = new CompletionStatus( false, null );
        
        Assert.assertFalse( failedWithoutMessage.completedSuccessfully() );
        Assert.assertNull( failedWithoutMessage.getMessage() );
    }
    
    @Test
    public void shouldCreateACompletionStatusRepresentingSuccessWithAMessage() {
    
        String message = "Project is almost over!";
        CompletionStatus successfulWithMessage = new CompletionStatus( true, message );
        
        Assert.assertTrue( successfulWithMessage.completedSuccessfully() );
        Assert.assertEquals( message, successfulWithMessage.getMessage() );
    }
    
    @Test
    public void shouldCreateACompletionStatusRepresentingSuccessWithoutMessage() {
    
        CompletionStatus sucessfulWithoutMessage = new CompletionStatus( true, null );
        
        Assert.assertTrue( sucessfulWithoutMessage.completedSuccessfully() );
        Assert.assertNull( sucessfulWithoutMessage.getMessage() );
    }

}
