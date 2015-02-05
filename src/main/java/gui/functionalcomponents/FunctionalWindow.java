package main.java.gui.functionalcomponents;


import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import main.java.gui.design.windows.WindowBase;


/**
 * Abstract class to be extended by the classes that have the purpose of adding functionality to a
 * given {@link WindowBase}. Adding functionality to a window means adding an {@link ActionListener}
 * to all its buttons.
 * 
 * @param <T>
 *            The return of the {@link SwingWorker#doInBackground() doInBackground()} method that
 *            will depend on the command called.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalWindow< T > {
    
    /**
     * {@code theFunctionalWindow} - the {@link WindowBase} to which functionality will be given.
     */
    private WindowBase theFunctionalWindow;
    
    /**
     * Public constructor that will add functionality to a given non functional {@link WindowBase}
     * and will display it.
     * 
     * @param nonFunctionalWindow
     *            - The {@code WindowBase} to which functionality will be added.
     */
    public FunctionalWindow( WindowBase nonFunctionalWindow ) {
        
        theFunctionalWindow = nonFunctionalWindow;
        
        addRightButtonAction();
        addLeftButtonAction();
        
        theFunctionalWindow.setVisible( true );
    }
    
    // Private Methods
    
    /**
     * Method that will add functionality to the right button of all the {@code WindowBase} windows.
     * 
     * Right button -> dispose
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
     * {@link #getSwingWorker()} method.
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
            getSwingWorker().run();
            theFunctionalWindow.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
        } );
    }
    
    // Protected Abstract Method
    
    /**
     * Protected method to be implemented by the subclasses of this class. This method will return a
     * {@link FunctionalWindowSwingWorker} with an {@code Override} implementation of its
     * {@link SwingWorker#doInBackground() doInBackground()} and
     * {@link FunctionalWindowSwingWorker#functionalDone(Object) functionalDone(Object)} methods.
     * 
     * @return Returns a {@link FunctionalWindowSwingWorker} with an {@code Override} of its
     *         methods.
     */
    protected abstract FunctionalWindowSwingWorker< T > getSwingWorker();
}