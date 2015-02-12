package functionalcomponents.functionalairshipwindows;



import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import swingworkers.ExceptionHandlerSW;
import swingworkers.FunctionalGetWindowSwingWorker;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.FunctionalWindow;



/**
 * Class whose instances have the purpose of add functionality to a
 * {@link GetAirshipsWithLessPassengerThanWindow}. Giving functionality to a window means adding an
 * {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link Iterable} of {@link Airship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalGetAirshipsWithLessPassengerThanWindow extends
        FunctionalWindow< Iterable< Airship > > {
    
    /**
     * {@code functionalWindow} - The {@code PatchUserWindow} we want to add functionality to.
     */
    private GetAirshipsWithLessPassengerThanWindow functionalWindow;
    
    /**
     * {@code airshipsDatabase} - The airships database.
     */
    private Database< Airship > airshipsDatabase;
    
    /**
     * {@code bodyPanel} - The central panel of a {@link MainWindow} where the list of airships will
     * be displayed in a map and in a scroll box.
     */
    private JBodyPanelForMainWindow bodyPanel;
    
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link GetAirshipsWithLessPassengerThanWindow} and will display it.
     * 
     * @param nonFunctionalWindow
     *            - The {@code GetAirshipsWithLessPassengerThanWindow} we want to add functionality
     *            to.
     * @param airshipsDatabase
     *            - The airships database.
     * @param bodyPanel
     *            - The central panel of a {@link MainWindow} where the list of airships will be
     *            displayed in a map and in a scroll box.
     */
    public FunctionalGetAirshipsWithLessPassengerThanWindow( GetAirshipsWithLessPassengerThanWindow nonFunctionalWindow,
                                                             Database< Airship > airshipsDatabase,
                                                             JBodyPanelForMainWindow bodyPanel ) {
        
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        this.airshipsDatabase = airshipsDatabase;
        this.bodyPanel = bodyPanel;
    }
    
    /**
     * Method that will return a {@link ExceptionHandlerSW} with an {@code Override}
     * implementation of its {@link SwingWorker#doInBackground() doInBackground()} and
     * {@link ExceptionHandlerSW#finalizeDone(Object) functionalDone(Object)} methods to
     * add the correct functionality to a {@link GetAirshipsWithLessPassengerThanWindow}.
     * 
     * @return Returns a {@link ExceptionHandlerSW} with an {@code Override} of its
     *         methods.
     */
    @Override
    protected ExceptionHandlerSW< Iterable< Airship >> getNewSwingWorker() {
        
        return new FunctionalGetWindowSwingWorker( airshipsDatabase, bodyPanel,
                                                   functionalWindow.getErrorJTextArea() ) {
            
            /**
             * String representation of the parameters to use in the commands and that are obtained
             * from the window's text fields.
             */
            private String passengersNumber = functionalWindow.getNumberOfPassengers()
                                                              .getJTextField().getText();
            
            /**
             * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method
             * with the purpose the purpose of executing a
             * {@link GetAirshipsWithLessPassengersThanCommand} and obtaining its result.
             * 
             * @return Returns an {@code Iterable<Airship>} containing all the airships that have
             *         less passengers than the number specified in the window text field.
             * 
             * @throws NumberFormatException
             *             If the String {@code passengersNumber} cannot be converted to Integer.
             */
            @Override
            protected Iterable< Airship > doInBackground() throws Exception {
                
                return new GetAirshipsWithLessPassengersThanCommand(
                                                                     (InMemoryAirshipsDatabase)airshipsDatabase,
                                                                     StringUtils.parameterToInteger( "Maximum Number of Passengers",
                                                                                                     passengersNumber ) ).call()
                                                                                                                         .get();
            }
            
            /**
             * Override of the {@link FunctionalGetWindowSwingWorker#functionalDone()
             * functionalDone()}. This method will receive the result of the
             * {@link SwingWorker#doInBackground() doInBackground()} method and use it to update the
             * body panel of the {@code MainWindow} and close this window.
             * 
             * @param resultOfDoInBackGround
             *            - The result of the {@link SwingWorker#doInBackground() doInBackground()}
             *            method.
             */
            @Override
            public final void functionalDone( Iterable< Airship > resultOfDoInBackGround )
                throws Exception {
                
                super.finalizeDone( resultOfDoInBackGround );
                functionalWindow.dispose();
            }
        };
        
    };
}
