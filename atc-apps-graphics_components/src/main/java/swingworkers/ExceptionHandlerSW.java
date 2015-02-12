package swingworkers;


import java.util.concurrent.ExecutionException;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import utils.StringUtils;


/**
 * Abstract class that extends {@link SwingWorker} and implements part of the method
 * {@link SwingWorker#done()} so that the exceptions thrown by the non-implemented methods
 * {@link SwingWorker#doInBackground()} and {@link #finalizeDone(Object)} are caught and their error
 * message is written in the {@code errorLabel}.
 * 
 * @param <R>
 *            The type of the results returned by the methods {@link #doInBackground()} and
 *            {@link #get()}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class ExceptionHandlerSW< R > extends SwingWorker< R, Void > {
    
    
    
    // INSTANCE FIELD
    /**
     * {@code errorLabel} - The error label where the error messages from the thrown exceptions will
     * be written.
     */
    private final JTextArea errorJtextArea;
    
    
    
    // CONSTRUCTOR
    /**
     * Public constructor that will receive a window's error label as parameter where the error
     * messages will be written.
     * 
     * @param errorLabel
     *            - The window error label.
     */
    public ExceptionHandlerSW( JTextArea errorJTextArea ) {
    
        this.errorJtextArea = errorJTextArea;
    }
    
    
    
    // IMPLEMENTATION OF THE METHOD done() INHERITED FROM THE SwingWorker ABSTRACT CLASS
    /**
     * Override of the {@link SwingWorker#done() done()} method that will call
     * {@link #finalizeDone(Object)} method and where the exceptions thrown by the
     * {@link SwingWorker#doInBackground() doInBackground()} and {@link #finalizeDone(Object)}
     * methods are caught and their error message its written in the {@code errorLabel}.
     */
    @Override
    final protected void done() {
    
        try {
            errorJtextArea.setText( " " );
            finalizeDone( get() );
        }
        catch( ExecutionException e ) {
            errorJtextArea.setText( StringUtils.errorStringParser( e.getCause().getMessage(), 50 ) );
        }
        catch( Exception e ) {
            errorJtextArea.setText( StringUtils.errorStringParser( e.getMessage(), 50 ) );
        }
    }
    
    
    
    // UNIMPLEMENTED METHODS
    /**
     * Protected method to be implemented by the subclasses of this class. This method will receive
     * the result of the {@link SwingWorker#doInBackground() doInBackground()} method and treat it
     * differently according to the pretended function.
     * 
     * Implementation decisions: The execeptions thrown by this method are caught and will be
     * treated in the {@link ExceptionHandlerSW#done() done()} method.
     * 
     * @param resultOfDoInBackGround
     *            - The result of the {@link SwingWorker#doInBackground() doInBackground()} method.
     * 
     * @throws Exception
     *             Depending on the function the window its supposed to do.
     */
    protected abstract void finalizeDone( R resultOfDoInBackGround ) throws Exception;
    
    
    
}
