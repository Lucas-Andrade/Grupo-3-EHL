package functionalcomponents.functionalairshipwindows;


import java.awt.event.ActionListener;
import app.Utils;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import swingworkers.AirshipsOperatorSW;
import utils.CompletionStatus;
import design.windows.airshipwindows.PostAirshipsWindow;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;



/**
 * Class whose instances have the purpose of add functionality to a {@link PostAirshipsWindow}.
 * Giving functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPostAirshipWindow extends FunctionalWindow< FunctionalPostAirshipWindow.SwingWorker,CompletionStatus > {
    
    // STATIC FIELDS
    private static PostAirshipsWindow baseWindow = new PostAirshipsWindow();
    private static SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > swFactory;
    private static Object factoryLock;
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link PostAirshipsWindow} and displays it.
     */
    public FunctionalPostAirshipWindow( ) {
        
        super( baseWindow );
              
        }
    
    /**
     * Method that will return a {@link ExceptionHandlerSW} with an {@code Override}
     * implementation of its {@link SwingWorker#doInBackground()} and
     * {@link ExceptionHandlerSW#finalizeDone(Object) functionalDone(Object)} methods to
     * add the correct functionality to a {@link PostAirshipsWindow}.
     * 
     * @return Returns a {@link ExceptionHandlerSW} with an {@code Override} of its
     *         methods.
     */         
            
        @Override
        protected void runNewSwingWorker() throws SwingWorkerFactoryMissingException {
            
            
            Utils.runNewSwingWorker( swFactory, this.getClass().getSimpleName() );
        
    }
        
     // STATIC METHOD
        /**
         * Sets the {@link SwingWorkerFactory} that produces
         *  {@link FunctionalPostAirshipWindow.SwingWorker}s for the 
         *  {@link FunctionalPostAirshipWindow}s.
         *   
         * @param factory
         *            The {@link SwingWorkerFactory} that produces
         *            {@link FunctionalPostAirshipWindow.SwingWorker}s 
         *            for the {@link FunctionalPostAirshipWindow}s.
         * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
         *         the {@link FunctionalPostAirshipWindow}s; <br/>
         *         {@code false} if there was a factory already set.
         */
        public static boolean
                setSwingWorkerFactory( SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, 
                                       CompletionStatus > factory ) {
            
            return Utils.setSWFactory( FunctionalGetAirshipsWithLessPassengerThanWindow.class , "swFactory", factory, factoryLock );

            
        }
                
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW} able to add funcitonality to a
     * {@link FunctionalPostAirshipWindow}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */    
    public static abstract class SwingWorker extends AirshipsOperatorSW {
        
        // INSTANCE FIELD
        private PostAirshipsWindow window;
        
        // TextField Information
        protected int typeTypeAirshipTabbedPane;
        protected String latitude;
        protected String longitude;
        protected String altitude;
        protected String minAltitude;
        protected String maxAltitude;        
        protected String specificComponent;
        
        // JLabel Information
        
        protected String latitudeLabel;
        protected String longitudeLabel;
        protected String altitudeLabel;
        protected String minAltitudeLabel;
        protected String maxAltitudeLabel;        
        protected String specificComponentLabel;
        
     // CONSTRUCTOR
        public SwingWorker( PostAirshipsWindow window ) {
        
            super( window, window.getErrorJTextArea() );
            this.window = window;
            
            if( (typeTypeAirshipTabbedPane = window.getTypeAirshipTabbedPane().getSelectedIndex() ) == 0 )                 
                getCivilAirshipStringParameters();             
            
                getMilitaryAirshipStringParameters();
                                    
        }
        
        /**
         * Private auxilar method that will obtain the a String representation of the parameters
         * to give to the {@link PostCivilAirshipCommand} from the window text fields.
         */
        private void getCivilAirshipStringParameters() {
                
                latitude =
                        window.getCivilAirshipCommonPainel().getGeoCoodinates()
                                        .getLatitude().getJTextField().getText();
                longitude =
                        window.getCivilAirshipCommonPainel().getGeoCoodinates()
                                        .getLongitude().getJTextField().getText();
                altitude =
                        window.getCivilAirshipCommonPainel().getGeoCoodinates()
                                        .getAltitude().getJTextField().getText();
                
                minAltitude =
                        window.getCivilAirshipCommonPainel().getAirCorridor()
                                        .getMinAltitude().getJTextField().getText();
                maxAltitude =
                        window.getCivilAirshipCommonPainel().getAirCorridor()
                                        .getMaxAltitude().getJTextField().getText();
                
                specificComponent =
                        window.getSpecificCivilPanel().getNumberPassengerJTextField()
                                        .getText();
            }
        
        /**
         * Private auxilar method that will obtain the a String representation of the parameters
         * to give to the {@link PostMilitaryAirshipCommand} from the window text fields.
         */    
        private void getMilitaryAirshipStringParameters() {
      
            latitude =
                    window.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                              .getLatitude().getJTextField().getText();
            longitude =
                    window.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                              .getLongitude().getJTextField().getText();
            altitude =
                    window.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                              .getAltitude().getJTextField().getText();
      
            minAltitude =
                    window.getMilitaryAirshipCommonPainel().getAirCorridor()
                              .getMinAltitude().getJTextField().getText();
            maxAltitude =
                    window.getMilitaryAirshipCommonPainel().getAirCorridor()
                              .getMaxAltitude().getJTextField().getText();
      
            specificComponent =
                    window.getSpecificMilitaryPanel().getGroupButtons()
                              .getSelection().getActionCommand();
  }
        
    }
    
}
