package functionalcomponents.functionalairshipwindows;


import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import org.omg.CORBA.CompletionStatus;
import design.windows.airshipwindows.PostAirshipsWindow;
import design.windows.popupwindows.SuccessWindow;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.ExceptionHandlerSW;



/**
 * Class whose instances have the purpose of add functionality to a {@link PostAirshipsWindow}.
 * Giving functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPostAirshipWindow extends FunctionalWindow< CompletionStatus > {
    
    /**
     * {@code functionalWindow} - The {@code PatchUserWindow} we want to add functionality to.
     */
    private PostAirshipsWindow functionalWindow;
    
    /**
     * {@code airshipsDatabase} - The airships database.
     */
    private Database< Airship > airshipsDatabase;
    
    /**
     * {@code userWhoIsPosting} - The {@code User} who is posting the new airship.
     */
    private User userWhoIsPosting;
    
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link PostAirshipsWindow} and will display it.
     * 
     * @param nonFunctionalWindow
     *            - The {@code PostUserWindow} we want to add functionality to.
     * @param airshipsDatabase
     *            - The airships database.
     * @param userWhoIsPosting
     *            - The {@code User} who is posting the new user.
     */
    public FunctionalPostAirshipWindow( PostAirshipsWindow nonFunctionalWindow,
                                        Database< Airship > airshipsDatabase, User userWhoIsPosting ) {
        
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        
        this.userWhoIsPosting = userWhoIsPosting;
        this.airshipsDatabase = airshipsDatabase;
    }
    
    /**
     * Method that will return a {@link ExceptionHandlerSW} with an {@code Override}
     * implementation of its {@link SwingWorker#doInBackground() doInBackground()} and
     * {@link ExceptionHandlerSW#finalizeDone(Object) functionalDone(Object)} methods to
     * add the correct functionality to a {@link PostUserWindow}.
     * 
     * @return Returns a {@link ExceptionHandlerSW} with an {@code Override} of its
     *         methods.
     */
    @Override
    protected ExceptionHandlerSW< CompletionStatus > getNewSwingWorker() {
        
        return new ExceptionHandlerSW< CompletionStatus >(
                                                                    functionalWindow.getErrorJTextArea() ) {
            
            /**
             * String representation of the parameters to use in the commands and that are obtained
             * from the window's text fields.
             */
            private String latitude;
            private String longitude;
            private String altitude;
            
            private String minAltitude;
            private String maxAltitude;
            
            private String specificComponent;
            
            /**
             * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method
             * with the purpose of posting a new airship in the database and obtaining its result.
             * 
             * @return Returns a String with information regarding the result of the post operation
             *         will be the same as the return of the command call method.
             * 
             * @throws NumberFormatException
             *             If any of the String parameters cannot cannot be converted to Integer, to
             *             Double or to Boolean.
             * @throws InvalidArgumentException
             *             If any of the values for the parameters given to the commands are
             *             invalid.
             */
            @Override
            protected CompletionStatus doInBackground() throws Exception {
                
                if( functionalWindow.getTypeAirshipTabbedPane().getSelectedIndex() == 0 ) {
                    
                    getCivilAirshipStringParameters();
                    
                    double latitude = Double.parseDouble( this.latitude );
                    double longitude = Double.parseDouble( this.longitude );
                    double altitude = Double.parseDouble( this.altitude );
                    
                    double minAltitude = Double.parseDouble( this.minAltitude );
                    double maxAltitude = Double.parseDouble( this.maxAltitude );
                    
                    int specificComponent = (int)Double.parseDouble( this.specificComponent );
                    
                    return new PostCivilAirshipCommand( latitude, longitude, altitude, maxAltitude,
                                                        minAltitude, specificComponent,
                                                        airshipsDatabase, userWhoIsPosting ).call();
                }
                
                getMilitaryAirshipStringParameters();
                
                double latitude = Double.parseDouble( this.latitude );
                double longitude = Double.parseDouble( this.longitude );
                double altitude = Double.parseDouble( this.altitude );
                
                double minAltitude = Double.parseDouble( this.minAltitude );
                double maxAltitude = Double.parseDouble( this.maxAltitude );
                
                boolean specificComponent = Boolean.parseBoolean( this.specificComponent );
                
                return new PostMilitaryAirshipCommand( latitude, longitude, altitude, maxAltitude,
                                                       minAltitude, specificComponent,
                                                       airshipsDatabase, userWhoIsPosting ).call();
            }
            
            /**
             * Private auxilar method that will obtain the a String representation of the parameters
             * to give to the {@link PostCivilAirshipCommand} from the window text fields.
             */
            private void getCivilAirshipStringParameters() {
                
                latitude =
                        functionalWindow.getCivilAirshipCommonPainel().getGeoCoodinates()
                                        .getLatitude().getJTextField().getText();
                longitude =
                        functionalWindow.getCivilAirshipCommonPainel().getGeoCoodinates()
                                        .getLongitude().getJTextField().getText();
                altitude =
                        functionalWindow.getCivilAirshipCommonPainel().getGeoCoodinates()
                                        .getAltitude().getJTextField().getText();
                
                minAltitude =
                        functionalWindow.getCivilAirshipCommonPainel().getAirCorridor()
                                        .getMinAltitude().getJTextField().getText();
                maxAltitude =
                        functionalWindow.getCivilAirshipCommonPainel().getAirCorridor()
                                        .getMaxAltitude().getJTextField().getText();
                
                specificComponent =
                        functionalWindow.getSpecificCivilPanel().getNumberPassengerJTextField()
                                        .getText();
            }
            
            /**
             * Private auxilar method that will obtain the a String representation of the parameters
             * to give to the {@link PostMilitaryAirshipCommand} from the window text fields.
             */
            private void getMilitaryAirshipStringParameters() {
                
                latitude =
                        functionalWindow.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                                        .getLatitude().getJTextField().getText();
                longitude =
                        functionalWindow.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                                        .getLongitude().getJTextField().getText();
                altitude =
                        functionalWindow.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                                        .getAltitude().getJTextField().getText();
                
                minAltitude =
                        functionalWindow.getMilitaryAirshipCommonPainel().getAirCorridor()
                                        .getMinAltitude().getJTextField().getText();
                maxAltitude =
                        functionalWindow.getMilitaryAirshipCommonPainel().getAirCorridor()
                                        .getMaxAltitude().getJTextField().getText();
                
                specificComponent =
                        functionalWindow.getSpecificMilitaryPanel().getGroupButtons()
                                        .getSelection().getActionCommand();
            }
            
            /**
             * Implementation of the {@link ExceptionHandlerSW#functionalDone()
             * functionalDone()}. This method will receive the result of the
             * {@link SwingWorker#doInBackground() doInBackground()} method and, if this result
             * positive, open new {@link SuccessWindow}, closing this one.
             * 
             * @param resultOfDoInBackGround
             *            - The result of the {@link SwingWorker#doInBackground() doInBackground()}
             *            method.
             */
            @Override
            public void finalizeDone( CompletionStatus resultOfDoInBackGround ) {
                
                new SuccessWindow( resultOfDoInBackGround.getMessage() );
                functionalWindow.dispose();
            }
            
            
        };
    }
}
