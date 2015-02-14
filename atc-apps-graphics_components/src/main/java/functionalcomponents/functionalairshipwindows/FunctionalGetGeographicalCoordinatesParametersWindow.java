package functionalcomponents.functionalairshipwindows;



import app.Utils;
import swingworkers.ExceptionHandlerSW;
import swingworkers.FunctionalGetWindowSwingWorker;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
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
public class FunctionalGetGeographicalCoordinatesParametersWindow extends
        FunctionalWindow< FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, Iterable< SimpleAirship > > {
    
 // STATIC FIELDS
    
    /**
     * The {@link GetGeographicalCoordinatesParametersWindow} we want to add functionality to.
     */
    public static final GetGeographicalCoordinatesParametersWindow baseWindow =
            new GetGeographicalCoordinatesParametersWindow();
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker}s for
     * the {@link GetGeographicalCoordinatesParametersWindow}s.
     */
    private static SwingWorkerFactory< FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, 
                                                                        Iterable< SimpleAirship > > swFactory;
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock;
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link GetGeographicalCoordinatesParametersWindow} and displays it.
     */
    public FunctionalGetGeographicalCoordinatesParametersWindow() {
    
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
     *  {@link FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker}s for the 
     *  {@link FunctionalGetGeographicalCoordinatesParametersWindow}s.
     *   
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker}s 
     *            for the {@link FunctionalGetGeographicalCoordinatesParametersWindow}s.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
     *         the {@link FunctionalGetGeographicalCoordinatesParametersWindow}s; <br/>
     *         {@code false} if there was a factory already set.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, Iterable< SimpleAirship > > factory ) {
        
        return Utils.setSWFactory( FunctionalGetAirshipsWithLessPassengerThanWindow.class , "swFactory", factory, factoryLock );

        
    }
        
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW} able to add funcitonality to a
     * {@link FunctionalGetGeographicalCoordinatesParametersWindow}.
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
        protected String latitude;
        protected String longitude;
        protected String airshipsNumber;
        
        // JLabel Information
        
        protected String latitudeLabel;
        protected String longitudeLabel;
        protected String airshipsNumberLabel;
        
     // CONSTRUCTOR
        public SwingWorker( GetGeographicalCoordinatesParametersWindow window ) {
        
            super( window.getErrorJTextArea() );
            
            latitude = window.getLatitude().getJTextField().getText();
            longitude = window.getLongitude().getJTextField().getText();
            airshipsNumber = window.getAirshipsNumber().getJTextField().getText();
            
            latitudeLabel = window.getLatitude().getJLabel().getText();
            longitudeLabel = window.getLongitude().getJLabel().getText();
            airshipsNumberLabel = window.getAirshipsNumber().getJLabel().getText();
        }
        
     
        
    }
    
    
}
