package functionalcomponents.functionalairshipwindows;



import app.Utils;
import swingworkers.ExceptionHandlerSW;
import swingworkers.FunctionalGetWindowSwingWorker;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import entities.SimpleAirship;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;


/**
 * Class whose instances have the purpose of update {@link JBodyPanelForMainWindow} 
 * using for that a {@link SwingWorker}.
 * 
 * Extends {@link FunctionalWindow} of {@link Iterable} of {@link SimpleAirship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalGetAirshipsWithLessPassengerThanWindow extends
        FunctionalWindow< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker, Iterable< SimpleAirship > > {
    
 // STATIC FIELDS
    
    /**
     * The {@link GetAirshipsWithLessPassengerThanWindow} we want to add functionality to.
     */
    public static final GetAirshipsWithLessPassengerThanWindow baseWindow =
            new GetAirshipsWithLessPassengerThanWindow();
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link FunctionalGetAirshipsWithLessPassengersThanWindow.SwingWorker}s for
     * the {@link GetAirshipsWithLessPassengerThanWindow}s.
     */
    private static SwingWorkerFactory< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker, 
                                                                        Iterable< SimpleAirship > > swFactory;
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock;
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link GetAirshipsWithLessPassengerThanWindow} and displays it.
     */
    public FunctionalGetAirshipsWithLessPassengerThanWindow() {
    
        super( baseWindow );
    }
    
    // PUBLIC METHOD
    /**
     * @see functionalcomponents.FunctionalWindow#runNewSwingWorker()
     */ 
    @Override
    protected void runNewSwingWorker() throws SwingWorkerFactoryMissingException {
        
        
        Utils.runNewSwingWorker( swFactory, this.getClass().getSimpleName() );
        
    }
    
    // STATIC METHOD
    /**
     * Sets the {@link SwingWorkerFactory} that produces
     *  {@link FunctionalGetAirshipsWithLessPassengersThanWindow.SwingWorker}s for the 
     *  {@link FunctionalGetAirshipsWithLessPassengersThanWindow}s.
     *   
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link FunctionalGetAirshipsWithLessPassengersThanWindow.SwingWorker}s 
     *            for the {@link FunctionalGetAirshipsWithLessPassengersThanWindow}s.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
     *         the {@link FunctionalGetAirshipsWithLessPassengersThanWindow}s; <br/>
     *         {@code false} if there was a factory already set.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker,
                                   Iterable< SimpleAirship > > factory ) {
       
        return Utils.setSWFactory( FunctionalGetAirshipsWithLessPassengerThanWindow.class , "swFactory", factory, factoryLock );
        
        
    }
        
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW} able to add functionality to a
     * {@link FunctionalGetAirshipsWithLessPassengersThanWindow}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */    
    public static abstract class SwingWorker extends FunctionalGetWindowSwingWorker {
        
     // INSTANCE FIELDS
      
        /**
         * String representation of the parameters to use in the commands and that are obtained from
         * the window's text fields.
         */
        
        // TextField Information
        protected String passengersNumber;
    
        // JLabel Information        
        protected String passengersNumberLabel;

        
     // CONSTRUCTOR
        public SwingWorker( GetAirshipsWithLessPassengerThanWindow window ) {
        
            super( window.getErrorJTextArea() );
            
            passengersNumber = window.getNumberOfPassengers().getJTextField().getText();
           
            passengersNumberLabel = window.getNumberOfPassengers().getJLabel().getText();
        
        }
               
    }

    
    
}