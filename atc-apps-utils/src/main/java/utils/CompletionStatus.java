package utils;



/**
 * Class whose instances represent if an operation completed successfully or failed. This completion
 * status carries a {@link String} with extra information.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CompletionStatus {
    
    
    /**
     * The completion status of an operation: successfully completed if {@code true} and failure if
     * {@code false}.
     */
    private final boolean theStatus;
    /**
     * Additional info.
     */
    private final String message;
    
    
    /**
     * Creates a new {@code CompletionStatus} to be returned by an {@code operation} completed with
     * a certain {@code status} and a {@code message}.
     * 
     * @param completionStatus
     *            {@code true} if the operation concluded successfully; <br/>
     *            {@code false} if the operation failed.
     * @param message
     *            A message with extra information on this status.
     */
    public CompletionStatus( boolean completionStatus, String message ) {
    
        this.theStatus = completionStatus;
        this.message = message;
        
    }
    
    
    /**
     * Returns the status of the completed operation.
     * 
     * @return {@code true} if the operation concluded successfully; <br/>
     *         {@code false} if the operation failed.
     */
    public boolean completedSuccessfully() {
    
        return theStatus;
    }
        
    /**
     * Returns a message with extra information.
     * 
     * @return A message with extra information.
     */
    public String getMessage() {
    
        return message;
    }


}
