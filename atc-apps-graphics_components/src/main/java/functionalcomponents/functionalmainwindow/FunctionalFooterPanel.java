package functionalcomponents.functionalmainwindow;



import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import design.windows.MainWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import entities.SimpleAirship;


/**
 * Class whose instances have the purpose of adding functionality to a
 * {@link JFooterPanelForMainWindow}. Giving functionality to a panel means adding an
 * {@link ActionListener} to all its buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalFooterPanel {
    
    // Factories
    private SwingWorkerFactory< ?, ? > getNearestAirshipsFactory;
    private SwingWorkerFactory< ?, ? > getTransgressingAirshipsFactory;
    private SwingWorkerFactory< ?, ? > getAirshipsWithLessPassengerThanFactory;
    private SwingWorkerFactory< ?, ? > patchAirshipFactory;
    private SwingWorkerFactory< ?, ? > postAirshipFactory;
    private SwingWorkerFactory< ?, ? > deleteAirshipFactory;
    
    //Fields
    /**
     * The {@link MainWindow} footer panel to which we will had functionality.
     */
    private JFooterPanelForMainWindow footerPanel;
    
    /**
     * The {@link MainWindow} {@code functional body panel} that will be updated as part of the
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
     *            - The functional body panel that will be updated as part of the actions performed
     *            by any of the buttons belonging to the {@link #footerPanel}. This panel as to be
     *            from the same {@code MainWindow} as the given {@code footerPanel}.
     */
    public FunctionalFooterPanel( JFooterPanelForMainWindow footerPanel,
                                  JBodyPanelForMainWindow bodyPanel ) {
    
        this.footerPanel = footerPanel;
        this.bodyPanel = bodyPanel;
        
        addFunctionality();
    }
    
    // Private Methods
    /**
     * All the methods that will give functionality to the {@code footerPanel}.
     */
    private void addFunctionality() {
    
        addGetAllAirshipsButtonAction();
        addGetNearestAirshipsButtonAction();
        addGetTransgressingAirshipsButtonAction();
        addGetAirshipsWithLessPassengerThanButtonAction();
        addPatchAirshipButtonAction();
        addPostAirshipButtonAction();
        addDeleteAirshipButtonAction();
    }
    
    // GETs
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#showAllAirships
     * showAllAirships} button.
     * 
     * The propose of this {@code action} is the GET All {@link SimpleAirship}s from the "System"
     * and update the given {@code bodyPanel}.
     */
    private void addGetAllAirshipsButtonAction() {
    
        footerPanel.getShowAllAirships().addActionListener( action -> bodyPanel.updateBodyPanel() );
    }
    
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#nearestAirships
     * nearestAirships} button.
     * 
     * The propose of this {@code action} is the GET the closet {@link SimpleAirship}s from the
     * "System", to a certain geographical position and update the given {@code bodyPanel}.
     */
    private void addGetNearestAirshipsButtonAction() {
    
        footerPanel.getNearestAirships()
                   .addActionListener( action -> action( getNearestAirshipsFactory ) );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#transgressingAirships transgressingAirships} button.
     * 
     * The propose of this {@code action} is the GET transgressing {@link SimpleAirship}s from the
     * "System" and update the given {@code bodyPanel}.
     */
    private void addGetTransgressingAirshipsButtonAction() {
    
        footerPanel.getTransgressingAirships()
                   .addActionListener( action -> action( getTransgressingAirshipsFactory ) );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#airshipsWithLessPassengerThan airshipsWithLessPassengerThan}
     * button.
     * 
     * The propose of this {@code action} is the GET all {@link SimpleAirship} from the "System"
     * with less than a certain number of passengers and update the given {@code bodyPanel}.
     */
    private void addGetAirshipsWithLessPassengerThanButtonAction() {
    
        footerPanel.getAirshipsWithLessPassengerThan()
                   .addActionListener( action -> action( getAirshipsWithLessPassengerThanFactory ) );
    }
    
    // POST
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#postAirship
     * postAirship} button.
     * 
     * The propose of this {@code action} is POST a new {@link SimpleAirship} in the "system".
     */
    private void addPostAirshipButtonAction() {
    
        footerPanel.getPostAirship().addActionListener( action -> action( postAirshipFactory ) );
    }
    
    // PATCH
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#patchAirship
     * patchAirship} button.
     * 
     * The propose of this {@code action} is PATCH a {@link SimpleAirship} on the "system".
     */
    private void addPatchAirshipButtonAction() {
    
        footerPanel.getPatchAirship().addActionListener( action -> {
            action( patchAirshipFactory );
        } );
    }

    // DELETE
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#deleteAirship
     * deleteAirship} button.
     * 
     * The propose of this {@code action} is DELETE a {@link SimpleAirship} from the "system".
     */
    private void addDeleteAirshipButtonAction() {
    
        footerPanel.getDeleteAirship().addActionListener( action -> action( deleteAirshipFactory ) );
    }
    
    /**
     * Create a new {@link SwingWorker} associated with the given {@code factory} and run it.
     * If the {@code factory} is null, then its open the {@link UnderConstrutionWindow}.
     * 
     * @param factory
     *            - The {@link SwingWorker} factory.
     */
    private void action( SwingWorkerFactory< ?, ? > factory ) {
    
        try {
            factory.newInstance().run();
        }
        catch( NullPointerException e ) {
            new UnderConstrutionWindow();
        }
    }
    
    // Public Methods
    /**
     * Returns the {@code footerPanel}.
     * 
     * @return {@code footerPanel}.
     */
    public JFooterPanelForMainWindow getFooterPanel() {
    
        return footerPanel;
    }
    
    // Set factories
    /**
     * Set the {@code NearestAirshipsFactory}.
     * 
     * @param getNearestAirshipsFactory
     *            - The getNearestAirshipsFactory to set
     */
    public void setGetNearestAirshipsFactory( SwingWorkerFactory< ?, ? > getNearestAirshipsFactory ) {
    
        this.getNearestAirshipsFactory = getNearestAirshipsFactory;
    }
    
    /**
     * Set the {@code TransgressingAirshipsFactory}.
     * 
     * @param getTransgressingAirshipsFactory
     *            - The getTransgressingAirshipsFactory to set
     */
    public
            void
            setGetTransgressingAirshipsFactory( SwingWorkerFactory< ?, ? > getTransgressingAirshipsFactory ) {
    
        this.getTransgressingAirshipsFactory = getTransgressingAirshipsFactory;
    }
    
    /**
     * Set the {@code AirshipsWithLessPassengerThanFactory}.
     * 
     * @param getAirshipsWithLessPassengerThanFactory
     *            - The getAirshipsWithLessPassengerThanFactory to set
     */
    public
            void
            setGetAirshipsWithLessPassengerThanFactory( SwingWorkerFactory< ?, ? > getAirshipsWithLessPassengerThanFactory ) {
    
        this.getAirshipsWithLessPassengerThanFactory = getAirshipsWithLessPassengerThanFactory;
    }
    
    /**
     * Set the {@code postAirshipFactory}.
     * 
     * @param postAirshipFactory
     *            - The postAirshipFactory to set
     */
    public void setPostAirshipFactory( SwingWorkerFactory< ?, ? > postAirshipFactory ) {
    
        this.postAirshipFactory = postAirshipFactory;
    }
    
    /**
     * Set the {@code patchAirshipFactory}.
     * 
     * @param patchAirshipFactory
     *            - The patchAirshipFactory to set
     */
    public void setPatchAirshipFactory( SwingWorkerFactory< ?, ? > patchAirshipFactory ) {
    
        this.patchAirshipFactory = patchAirshipFactory;
    }
    
    /**
     * Set the {@code deleteAirshipFactory}.
     * 
     * @param deleteAirshipFactory
     *            - The deleteAirshipFactory to set
     */
    public void setDeleteAirshipFactory( SwingWorkerFactory< ?, ? > deleteAirshipFactory ) {
    
        this.deleteAirshipFactory = deleteAirshipFactory;
    }
}
