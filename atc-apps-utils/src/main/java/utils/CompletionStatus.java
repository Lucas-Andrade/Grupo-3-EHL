package utils;



/**
 * Class whose instances represent if an operation completed successfully or failed. This completion
 * status carry a {@link String} with extra information.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CompletionStatus {
    
    /**
     * The completion status of an operation: successfully completed if {@code true} and failure if
     * {@code false}.
     */
    private final boolean completionStatus;
    /**
     * Additional info.
     */
    private final String message;
    
    /**
     * Create a new {@code CompletionStatus} to be returned by an {@code operation} completed with a
     * certain {@code status} and a {@code message}.
     * 
     * @param completionStatus
     *            {@code true} if the operation concluded successfully; <br/>
     *            false otherwise.
     * @param message
     *            A message with extra information on this status.
     */
    public CompletionStatus( boolean completionStatus, String message ) {
    
        this.completionStatus = completionStatus;
        this.message = message;
        
    }
    
    /**
     * @return the completionStatus
     */
    public boolean completedSuccessfully() {
    
        return completionStatus;
    }
    
    
    /**
     * @return the message
     */
    public String getMessage() {
    
        return message;
    }
}
