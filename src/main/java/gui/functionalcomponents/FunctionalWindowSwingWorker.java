package main.java.gui.functionalcomponents;


import java.util.concurrent.ExecutionException;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import main.java.utils.StringUtils;


/**
 * Abstract class that extends the {@link SwingWorker} class and implements an {@code Override} of
 * its {@link SwingWorker#done() done()} method, where the exceptions thrown by the
 * {@link SwingWorker#doInBackground() doInBackground()} and {@link #functionalDone(Object)} methods
 * are caught and their error message its written in the {@code errorLabel}.
 */
public abstract class FunctionalWindowSwingWorker< T > extends SwingWorker< T, Void > {
    
    /**
     * {@code errorLabel} - The error label where the error messages from the thrown exceptions will
     * be written.
     */
	private final JTextArea errorJtextArea;
    
    /**
     * Public constructor that will receive a window's error label as parameter where the error
     * messages will be written.
     * 
     * @param errorLabel
     *            - The window error label.
     */
	public FunctionalWindowSwingWorker( JTextArea errorJTextArea )
    {
            this.errorJtextArea = errorJTextArea;
    }
    
    /**
     * Override of the {@link SwingWorker#done() done()} method that will call
     * {@link #functionalDone(Object)} method and where the exceptions thrown by the
     * {@link SwingWorker#doInBackground() doInBackground()} and {@link #functionalDone(Object)}
     * methods are caught and their error message its written in the {@code errorLabel}.
     */
    @Override
    final protected void done() {
        try {
            errorJtextArea.setText( " " );
            functionalDone( get() );
        }
        catch( ExecutionException e )
        {
                errorJtextArea.setText( StringUtils.errorStringParser( e.getCause().getMessage(),50 ));
        }
        catch( Exception e )
        {
                errorJtextArea.setText(StringUtils.errorStringParser( e.getMessage(), 50) );
        }
    }
    
    /**
     * Protected method to be implemented by the subclasses of this class. This method will receive
     * the result of the {@link SwingWorker#doInBackground() doInBackground()} method and treat it
     * differently according to the pretended function.
     * 
     * Implementation decisions: The execeptions thrown by this method are caught and will be
     * treated in the {@link FunctionalWindowSwingWorker#done() done()} method.
     * 
     * @param resultOfDoInBackGround
     *            - The result of the {@link SwingWorker#doInBackground() doInBackground()} method.
     * 
     * @throws Exception
     *             Depending on the function the window its supposed to do.
     */
    protected abstract void functionalDone( T resultOfDoInBackGround ) throws Exception;
}