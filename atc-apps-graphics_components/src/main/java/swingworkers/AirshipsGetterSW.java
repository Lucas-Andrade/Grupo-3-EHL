package swingworkers;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.windows.MainWindow;
import entities.SimpleAirship;


/**
 * Abstract class whose subclass instances are {@link SwingWorker}s to GET {@link SimpleAirship}s.
 * The {@link ExceptionHandlerSW#finalizeDone(Object) finalizeDone(Object)} method will update the
 * {@link JBodyPanelForMainWindow} with the list of {@code SimpleAirship} returned by the
 * {@link SwingWorker#doInBackground() doInBackground()} method.
 * 
 * Subclasses must implement the {@link SwingWorker#doInBackground() doInBackground()} method to GET
 * a specific {@code SimpleAirship} list.
 * 
 * Extends {@link ExceptionHandlerSW} of {@link Iterable} of {@link Airship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class AirshipsGetterSW extends
        ExceptionHandlerSW< Iterable< SimpleAirship >> {
    
    /**
     * The {@code MainWindow} we want to add functionality to.
     */
    private static final MainWindow windowBase = MainWindow.getInstance();
    
    // Constructor
    /**
     * Public constructor that will receive the {@code bodyPanel} to be updated and a window's error
     * label as parameter where the error messages will be written.
     * 
     * @param bodyPanel
     *            - The {@link MainWindow} body panel that will be updated.
     * @param errorTextArea
     *            - The error text area where the error messages from the thrown exceptions will be
     *            written.
     */
    public AirshipsGetterSW( JTextArea errorTextArea ) {
    
        super( errorTextArea );
        
    }
    
    // IMPLEMENTATION OF THE METHOD finalizeDone( iterable )
    /**
     * Implementation of the {@link ExceptionHandlerSW#finalizeDone(Object) finalizeDone(Object)}
     * method. This method will receive the result of the {@link SwingWorker#doInBackground()
     * doInBackground()} method and use it to update the given {@code bodyPanel}
     * 
     * Implementation decisions: The exceptions thrown by this method are caught by the base class
     * and will be treated in the {@link ExceptionHandlerSW#done() done()} method.
     * 
     * @param resultOfDoInBackGround
     *            - The result of the {@link SwingWorker#doInBackground() doInBackground()} method.
     * 
     * @throws Exception
     *             Depending on the function the window its supposed to do.
     */
    @Override
    protected final void finalizeDone( Iterable< SimpleAirship > resultOfDoInBackGround ) {
    
        windowBase.getBodyPanel().updateBodyPanel( resultOfDoInBackGround );
    } 



}
