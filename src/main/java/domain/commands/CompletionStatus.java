package main.java.domain.commands;


import java.util.concurrent.Callable;


/**
 * Class whose instances should be returned by a {@code operation} (e.g. {@link Callable}) that must
 * return the completion status and a message.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CompletionStatus {
    
    private final boolean completionStatus;
    private final String message;
    
    /**
     * Create a {@code CompletionStatus} to be returned by a {@code operation} with a {@code status}
     * and a {@code message}.
     * 
     * @param completionStatus
     *            true if a {@code operation} concluded successfully, false otherwise
     * @param message
     *            A message representing the {@code completionStatus}
     */
    public CompletionStatus( boolean completionStatus, String message ) {
        this.completionStatus = completionStatus;
        this.message = message;
        
    }

    /**
     * @return the completionStatus
     */
    public boolean operationCompletedSuccessfully() {
        return completionStatus;
    }
    
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
