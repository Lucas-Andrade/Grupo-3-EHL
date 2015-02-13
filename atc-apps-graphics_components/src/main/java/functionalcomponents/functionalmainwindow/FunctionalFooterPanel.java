package functionalcomponents.functionalmainwindow;



import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import swingworkers.ExceptionHandlerSW;
import swingworkers.FunctionalGetWindowSwingWorker;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import design.windows.MainWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;



/**
 * Class whose instances have the purpose of adding functionality to a
 * {@link JFooterPanelForMainWindow}. Giving functionality to a panel means adding an
 * {@link ActionListener} to all its buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalFooterPanel {
    
    // Fields
    
    private SwingWorkerFactory< ?, ? > getNearestAirshipsFactory;
    private SwingWorkerFactory< ?, ? > getTransgressingAirshipsFactory;
    private SwingWorkerFactory< ?, ? > getAirshipsWithLessPassengerThanFactory;
    private SwingWorkerFactory< ?, ? > patchAirshipFactory;
    private SwingWorkerFactory< ?, ? > postAirshipFactory;
    private SwingWorkerFactory< ?, ? > deleteAirshipFactory;
    
    /**
     * {@code footerPanel} - The {@link MainWindow} footer panel to which we will had functionality.
     */
    private JFooterPanelForMainWindow footerPanel;
    
    /**
     * {@code bodyPanel} - The {@link MainWindow} body panel that will be updated as part of the
     * actions performed by any of the buttons belonging to the {@link #footerPanel}.
     */
    private JBodyPanelForMainWindow bodyPanel;
    
    // Constructor
    
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link JFooterPanelForMainWindow}.
     * 
     * @param footerPanel
     *            - The footer panel to which we will had functionality.
     * @param bodyPanel
     *            - The body panel that will be updated as part of the actions performed by any of
     *            the buttons belonging to the {@link #footerPanel}. This panel as to be from the
     *            same {@code MainWindow} as the given {@code footerPanel}.
     * @param airshipsDatabase
     *            - The airships database.
     * @param user
     *            - The user who is currently logged in.
     * @param errorTextArea
     *            - The text area where the error messages will be displayed.
     */
    public FunctionalFooterPanel( JFooterPanelForMainWindow footerPanel,
                                  JBodyPanelForMainWindow bodyPanel,
                                  JTextArea errorTextArea ) {
    
        this.footerPanel = footerPanel;
        this.bodyPanel = bodyPanel;
        
        addGetAllAirshipsButtonAction();
        addGetNearestAirshipsButtonAction();
        addGetTransgressingAirshipsButtonAction();
        addGetAirshipsWithLessPassengerThanButtonAction();
        addPatchAirshipButtonAction();
        addPostAirshipButtonAction();
        addDeleteAirshipButtonAction();
    }
    
    // Private Methods
    
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#showAllAirships
     * showAllAirships} button.
     * 
     * The given action will be to get all the elements from a given airships database using the
     * {@link GetAllElementsInADatabaseCommand} and then update the given {@link #bodyPanel} with
     * the information regarding the obtained airships.
     * 
     * This is done due to the {@code Override} of the
     * {@link ExceptionHandlerSW#finalizeDone(Iterable) functionalDone(Iterable)} method existing in
     * the {@link FunctionalGetWindowSwingWorker} class.
     */
    private void addGetAllAirshipsButtonAction() {
    
        footerPanel.getShowAllAirships().addActionListener( action -> bodyPanel.updateBodyPanel() );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#getNearestAirships getNearestAirships} button.
     * 
     * The given action will be to create a new
     * {@link FunctionalGetGeographicalCoordinatesParametersWindow} with the objective of getting
     * all the airships from a given airships database that are closer to a certain geographical
     * position and then update the given {@link #bodyPanel} with the information regarding the
     * obtained airships.
     */
    private void addGetNearestAirshipsButtonAction() {
    
        footerPanel.getNearestAirships()
                   .addActionListener( action -> action( getNearestAirshipsFactory ));
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#getTransgressingAirships getTransgressingAirships} button.
     * 
     * The given action will be to get all the elements from a given airships database that are
     * transgressing their {@link AirCorridor} using the {@link GetAllTransgressingAirshipsCommand}
     * and then update the given {@link #bodyPanel} with the information regarding the obtained
     * airships.
     * 
     * This is done due to the {@code Override} of the
     * {@link ExceptionHandlerSW#finalizeDone(Iterable) functionalDone(Iterable)} method existing in
     * the {@link FunctionalGetWindowSwingWorker} class.
     */
    private void addGetTransgressingAirshipsButtonAction() {
    
        footerPanel.getTransgressingAirships()
                   .addActionListener( action -> action( getTransgressingAirshipsFactory ) );
    }
    
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#getAirshipsWithLessPassengerThan
     * getAirshipsWithLessPassengerThan} button.
     * 
     * The given action will be to create a new
     * {@link FunctionalGetAirshipsWithLessPassengerThanWindow} with the objective of getting all
     * the airships from a given airships database that have less than a certain number of
     * passengers and then update the given {@link #bodyPanel} with the information regarding the
     * obtained airships.
     */
    private void addGetAirshipsWithLessPassengerThanButtonAction() {
    
        footerPanel.getAirshipsWithLessPassengerThan()
                   .addActionListener( action -> action( getAirshipsWithLessPassengerThanFactory ) );
    }
    
    /**
     * PATCH -> Not Implemented!
     */
    private void addPatchAirshipButtonAction() {
    
        footerPanel.getPatchAirship().addActionListener( action -> {
            action( patchAirshipFactory );
        } );
    }
    
    
    
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#postAirship
     * postAirship} button.
     * 
     * The given action will be to create a new {@link FunctionalPostAirshipWindow} with the
     * objective of posting new {@link Airship} in the given database.
     */
    private void addPostAirshipButtonAction() {
    
        footerPanel.getPostAirship().addActionListener( action -> action( postAirshipFactory ) );
    }
    
    /**
     * DELETE AIRSHIP -> Not Implemented!
     */
    private void addDeleteAirshipButtonAction() {
    
        footerPanel.getDeleteAirship().addActionListener( action -> action(deleteAirshipFactory) );
    }
    
    /**
     * 
     * @param factory
     */
    private void action( SwingWorkerFactory< ?, ? > factory ) {
    
        try {
            factory.newInstance().run();
        }
        catch( NullPointerException e ) {
            new UnderConstrutionWindow();
        }
    }
    
    // Public Get Method
    
    /**
     * @return {@code footerPanel}.
     */
    public JFooterPanelForMainWindow getFooterPanel() {
    
        return footerPanel;
    }
    
    
    /**
     * @param getNearestAirshipsFactory
     *            the getNearestAirshipsFactory to set
     */
    public void setGetNearestAirshipsFactory( SwingWorkerFactory< ?, ? > getNearestAirshipsFactory ) {
    
        this.getNearestAirshipsFactory = getNearestAirshipsFactory;
    }
    
    
    /**
     * @param getTransgressingAirshipsFactory
     *            the getTransgressingAirshipsFactory to set
     */
    public
            void
            setGetTransgressingAirshipsFactory( SwingWorkerFactory< ?, ? > getTransgressingAirshipsFactory ) {
    
        this.getTransgressingAirshipsFactory = getTransgressingAirshipsFactory;
    }
    
    
    /**
     * @param getAirshipsWithLessPassengerThanFactory
     *            the getAirshipsWithLessPassengerThanFactory to set
     */
    public
            void
            setGetAirshipsWithLessPassengerThanFactory( SwingWorkerFactory< ?, ? > getAirshipsWithLessPassengerThanFactory ) {
    
        this.getAirshipsWithLessPassengerThanFactory = getAirshipsWithLessPassengerThanFactory;
    }
    
    
    /**
     * @param patchAirshipFactory
     *            the patchAirshipFactory to set
     */
    public void setPatchAirshipFactory( SwingWorkerFactory< ?, ? > patchAirshipFactory ) {
    
        this.patchAirshipFactory = patchAirshipFactory;
    }
    
    
    /**
     * @param postAirshipFactory
     *            the postAirshipFactory to set
     */
    public void setPostAirshipFactory( SwingWorkerFactory< ?, ? > postAirshipFactory ) {
    
        this.postAirshipFactory = postAirshipFactory;
    }
    
    
    /**
     * @param deleteAirshipFactory
     *            the deleteAirshipFactory to set
     */
    public void setDeleteAirshipFactory( SwingWorkerFactory< ?, ? > deleteAirshipFactory ) {
    
        this.deleteAirshipFactory = deleteAirshipFactory;
    }
}
