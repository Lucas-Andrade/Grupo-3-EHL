package functionalcomponents.functionalmainwindow;


import javax.swing.JTextArea;
import swingworkers.AirshipsGetterSW;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import swingworkers.Utils;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.windows.MainWindow;
import design.windows.WindowBase;
import entities.SimpleAirship;
import exceptions.SwingWorkerFactoryMissingException;


/**
 * Class whose instances have the purpose of add functionality to a {@link JBodyPanelForMainWindow}.
 * Giving functionality to a window means: <br>
 * -> GET all {@link SimpleAirship}s in the "system" and update the {@code BodyPanel} with that
 * received list using {@link BodyPanelFunctionalizer#updateBodyPanel() updateBodyPanel()} method.<br>
 * 
 * -> Update the {@code BodyPanel} with a new list of {@link SimpleAirship}s, using
 * {@link BodyPanelFunctionalizer#updateBodyPanel(Iterable) updateBodyPanel(iterable)} method.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class BodyPanelFunctionalizer {
    
    
    // Static fields
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock = new Object();
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link BodyPanelFunctionalizer.SwingWorker}s for
     * the {@link BodyPanelFunctionalizer}s.
     */
    private static SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > swFactory;
    
    
    
    // STATIC METHOD
    /**
     * Sets the {@link SwingWorkerFactory} that produces {@link BodyPanelFunctionalizer.SwingWorker}
     * for the {@link BodyPanelFunctionalizer}.
     * 
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link BodyPanelFunctionalizer.SwingWorker}s for the
     *            {@link BodyPanelFunctionalizer}.
     * @return {@code true} if {@code factory} was set as the factory that produces
     *         {@code SwingWorker}s for the {@link BodyPanelFunctionalizer}; <br/>
     *         {@code false} if there was a factory already set.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > factory ) {
    
        return Utils.setSWFactory( BodyPanelFunctionalizer.class, "swFactory", factory, factoryLock );
    }
    
    
    /**
     * 
     * @throws SwingWorkerFactoryMissingException
     */
    public static void runNewSwingWorker() throws SwingWorkerFactoryMissingException {
    
        Utils.runNewSwingWorker( swFactory, "BodyPanelFunctionalizer" );
    }
    
    
    
    // Instance Fields
    /**
     * The {@code text area} where the errors will be written
     */
    private JTextArea errorTextArea;
    
    
    
    // Constructor
    /**
     * Create a new {@code BodyPanel} with a list of {@link SimpleAirship}s in the {@code "System"}.
     * 
     * 
     * @see {@link BodyPanelFunctionalizer#updateBodyPanel()}
     * 
     * @param erroTextArea
     */
    public BodyPanelFunctionalizer( JTextArea erroTextArea ) {
    
        this.errorTextArea = erroTextArea;
    }
    
    
    // Private methods
    /**
     * Create a new {@link SwingWorker} and run it. The {@link SwingWorker#doInBackground()} method
     * should GET a list of {@link SimpleAirship}s.
     * 
     * @see {@link BodyPanelFunctionalizer.SwingWorker}
     * 
     * @throws SwingWorkerFactoryMissingException
     */
    public void updateBodyPanel() {
    
        try {
            runNewSwingWorker();
        }
        catch( SwingWorkerFactoryMissingException e ) {
            
            errorTextArea.setText( "Can not create World Map and Airship List!!" );
        }
    }
    
    // Inner SwingWorker Class
    /**
     * Abstract class that extends {@link ExceptionHandlerSW}s and implements the
     * {@link SwingWorker#finalizeDone(Iterable) finalizeDone(Iterable)}, the {@code BodyPanel} is
     * updated with the result of the {@link SwingWorker#doInBackground() doInBackground()} method.
     * 
     */
    public static abstract class SwingWorker extends AirshipsGetterSW {
        
        /**
         * Create a new {@link SwingWorker}, where the {@code error label} is the {@link WindowBase}
         * {@code error area}.
         * 
         * @param erroTextArea
         * 
         * @see ExceptionHandlerSW
         */
        public SwingWorker() {
        
            super( MainWindow.getInstance().getErrorJTextArea() );
        }
        

        @Override
        protected int getErrorTextLength() {
            
            return 200;
        }
       
       
    }
    
}
