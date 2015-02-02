package main.java.gui.functionalcomponents;


import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import main.java.gui.design.windows.WindowBase;


/**
 * Abstract swing window, that have the responsibility to add the {@link ActionListener}s to the
 * given {@link WindowBase} buttons.
 * 
 * @param <T>
 *            return of {@link SwingWorker#doInBackground()}
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalWindow< T > {
    
    /**
     * The {@code WindowBase} where will be added its functionality
     */
    private WindowBase theFunctionalWindow;
    
    /**
     * Add the {@link ActionListener}s to the {@code window buttons}
     * 
     * @param nonFunctionalWindow
     */
    public FunctionalWindow( WindowBase nonFunctionalWindow ) {
        
        theFunctionalWindow = nonFunctionalWindow;
        
        addRightButtonAction();
        addLeftButtonAction();
        
        theFunctionalWindow.setVisible( true );
    }
    
    // Private Methods
    
    /**
     * Right button -> dispose
     */
    private void addRightButtonAction() {
        
        theFunctionalWindow.getButtonsPanel().getRightButton()
                           .addActionListener( Action -> theFunctionalWindow.dispose() );
    }
    
    /**
     * Left button
     * <ul>
     * <li>collect the respective window info;
     * <li>get a {@link SwingWorker} and {@code run} it (i.e. {@code call} the command);
     * <li>get the return command and show it.
     * <ul>
     */
    private void addLeftButtonAction() {
        
        theFunctionalWindow.getButtonsPanel().getLeftButton().addActionListener(
        
        Action -> {
            
            theFunctionalWindow.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
            getSwingWorker().run();
            theFunctionalWindow.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
        } );
    }
    
    // Protected Abstract Method
    
    /**
     * Contract for subclasses to return a {@link SwingWorker} with its {@code doInBackground()}
     * method.
     * 
     * @return a {@link FunctionalWindowSwingWorker}
     */
    protected abstract FunctionalWindowSwingWorker< T > getSwingWorker();
    
    /**
     * @return the window
     */
    public WindowBase getFunctionalWindow() {
        
        return theFunctionalWindow;
    }
}
