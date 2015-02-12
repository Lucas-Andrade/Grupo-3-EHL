package exceptions;


import java.text.MessageFormat;
import swingworkers.SwingWorkerFactory;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow.SwingWorker;


/**
 * Exception thrown whenever one tries to get a {@link SwingWorker} from a {@link FunctionalWindow}
 * before setting the {@link SwingWorkerFactory}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class SwingWorkerFactoryMissingException extends Exception {
    
    
    /**
     * Constructs an {@link SwingWorkerFactoryMissingException} with the message <i>«Missing
     * SwingWorkerFactory in class {@code classWhereIsMissing}.»</i> and the specified cause.
     * 
     * @param nameOfTheClass
     *            The name of the class where someone forgot to set the {@link SwingWorkerFactory}
     *            before trying to get a {@link SwingWorker}.
     */
    public SwingWorkerFactoryMissingException( String nameOfTheClass ) {
    
        super( MessageFormat.format( "Missing SwingWorkerFactory in class {0}.", nameOfTheClass ) );
    }
    
    
    /**
     * Constructs an {@link SwingWorkerFactoryMissingException} with the message <i>«Missing
     * SwingWorkerFactory in class {@code classWhereIsMissing}.»</i> and the specified cause.
     * 
     * @param nameOfTheClass
     *            The name of the class where someone forgot to set the {@link SwingWorkerFactory}
     *            before trying to get a {@link SwingWorker}.
     * @param cause
     *            The cause (saved for later retrieval by the {@link #getCause()} method).
     *            {@code null} values are allowed and indicate that the cause is nonexistent or
     *            unknown.
     */
    public SwingWorkerFactoryMissingException( String nameOfTheClass, Throwable cause ) {
    
        super( MessageFormat.format( "Missing SwingWorkerFactory in class {0}.", nameOfTheClass ),
               cause );
    }
    
}
