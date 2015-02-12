package functionalcomponents;


import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import design.windows.WindowBase;
import design.windows.popupwindows.UnderConstrutionWindow;
import exceptions.InternalErrorException;
import exceptions.SwingWorkerFactoryMissingException;


/**
 * Abstract class to be extended by the classes that add functionality to a certain
 * {@link WindowBase}. Adding functionality to a window means adding an {@link ActionListener} to
 * all its buttons; all the {@link ActionListener}s of the left buttons of these
 * {@code FunctionalWindow}s depend on concrete classes that extend {@link ExceptionHandlerSW}.
 * <b>Note: method {@link #set
 * 
 * @param <S>
 *            The type of the {@link ExceptionHandlerSW} that add functionality to the left button
 *            of this {@code FunctionalWindow}.
 * @param <R>
 *            The type of the results returned of the {@link SwingWorker#doInBackground()
 *            doInBackground()} method that will depend on the command called.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalWindow< S extends ExceptionHandlerSW< R >, R > {
    
    
    // INSTANCE FIELD
    /**
     * The {@link WindowBase} to which functionality will be given.
     */
    private final WindowBase theFunctionalWindow;
    
    
    
    // CONSTRUCTOR
    /**
     * Adds functionality to the {@link WindowBase} {@code nonFunctionalWindow} and will displays
     * it.
     * 
     * @param nonFunctionalWindow
     *            The {@code WindowBase} to which functionality will be added.
     * @param swFactory
     *            The {@link SwingWorkerFactory} responsible for producing the
     *            {@link ExceptionHandlerSW}s that add functionality to the left button of
     *            {@code nonFunctionalWindow}.
     * @throws InternalErrorException
     *             If either {@code nonFunctionalWindow} or {@code swFactory} are {@code null}.
     */
    public FunctionalWindow( WindowBase nonFunctionalWindow ) {
    
        if( nonFunctionalWindow == null )
            throw new InternalErrorException( "UNEXPECTED ERROR!\nCannot use FunctionalWindow's"
                                              + " constructor with null nonFunctionalWindow!" );
        this.theFunctionalWindow = nonFunctionalWindow;
        
        addActionToButtons();
        theFunctionalWindow.setVisible( true );
    }
    
    
    
    // PRIVATE METHODS
    
    /**
     * Adds functionality to the buttons of this {@link FunctionalWindow} if they had no previous
     * {@link ActionListener}s.
     */
    private void addActionToButtons() {
    
        JButton rightButton = theFunctionalWindow.getButtonsPanel().getRightButton();
        if( rightButton.getActionListeners().length == 0 )
            synchronized (theFunctionalWindow) {
                
                if( rightButton.getActionListeners().length == 0 ) {
                    addRightButtonAction();
                    addLeftButtonAction();
                }
            }
    }
    
    /**
     * Adds the function of disposing the {@link WindowBase} received in the constructor to its
     * right button.
     */
    private void addRightButtonAction() {
    
        theFunctionalWindow.getButtonsPanel().getRightButton()
                           .addActionListener( action -> theFunctionalWindow.dispose() );
    }
    
    /**
     * Method that will add functionality to the left button of all the {@code WindowBase} windows.
     * 
     * Since the functionality of this button will depend on the given window and will often be used
     * to execute actions that will take longer to process and that are not related to the window's
     * design, this method will make use of the {@link SwingWorker} class through the
     * {@link #getNewSwingWorker()} method.
     * 
     * <ul>
     * <li>Collects the respective window info;
     * <li>Obtains a {@link SwingWorker} and using the {@link SwingWorker#run() run()} method a
     * specific command will be called.
     * <li>Obtains the result of the execution of the command and shows it.
     * <ul>
     */
    private void addLeftButtonAction() {
    
        theFunctionalWindow.getButtonsPanel().getLeftButton().addActionListener(
        
        action -> {
            theFunctionalWindow.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
            
            try {
                getNewSwingWorker().run();
            }
            catch( SwingWorkerFactoryMissingException e ) {
                new UnderConstrutionWindow();
            }
            
            theFunctionalWindow.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
        }
        
        );
    }
    
    
    
    // UNIMPLEMENTED METHOD
    /**
     * Returns a new {@link ExceptionHandlerSW}.
     * 
     * @return A new {@link ExceptionHandlerSW}.
     * @throws SwingWorkerFactoryMissingException
     *             If there is no {@link SwingWorkerFactory} set in {@code this}'s class.
     */
    protected abstract S getNewSwingWorker() throws SwingWorkerFactoryMissingException;
    
    
}
