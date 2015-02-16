package functionalcomponents.functionalmainwindow;



import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import swingworkers.AirshipsGetterSW;
import swingworkers.SwingWorkerFactory;
import swingworkers.Utils;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import design.windows.MainWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import entities.SimpleAirship;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsCloserToWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;


/**
 * Class whose instances add functionality to a {@link JFooterPanelForMainWindow}. Giving
 * functionality to a panel means adding {@link ActionListener}s to all its buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalFooterPanel {
    
    
    // STATIC MEMBERS
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link SwingWorker}s for the button that gets
     * all the transgressing airships registered in the app.
     */
    private static SwingWorkerFactory< GetTrangressingAirshipsSW, Iterable< SimpleAirship > > getTransgressingAirships_SWFactory;
    
    /**
     * A lock for the {@link #getTransgressingAirships_SWFactory}.
     */
    private static Object getTransgressingAirships_SWFactoryLock = new Object();
    
    /**
     * Sets the {@link SwingWorkerFactory} that produces {@link GetTrangressingAirshipsSW}s for the
     * button that gets all the transgressing airships registered in the app.
     * 
     * @param swFactory
     *            The {@link SwingWorkerFactory} that produces {@link GetTrangressingAirshipsSW}s
     *            for the button that gets all the transgressing airships registered in the app.
     * @return {@code true} if {@code swFactory} was set as the factory that produces swingworkers
     *         for the button; <br/>
     *         {@code false} if there was a factory already set or {@code swFactory} is {@code null}
     *         .
     */
    public static
            boolean
            setAllUsersFactory( SwingWorkerFactory< GetTrangressingAirshipsSW, Iterable< SimpleAirship > > swFactory ) {
    
        return Utils.setSWFactory( FunctionalFooterPanel.class,
                                   "getTransgressingAirships_SWFactory", swFactory,
                                   getTransgressingAirships_SWFactoryLock );
    }
    
    
    
    // INNER CLASS TODO
    /**
     * Class whose instances are {@link SwingWorker}s able to add functionality to a button that
     * gets all the transgressing airships registered in the app.
     * <p>
     * The unimplemented method {@link #doInBackground()} is supposed to return an {@link Iterable}
     * with the representations as {@link SimpleAirship}s of all the airships registered in the app
     * that are transgressing their predefined altitude air corridor; it is not supposed to throw
     * exceptions.
     * </p>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public abstract static class GetTrangressingAirshipsSW extends AirshipsGetterSW {
        
        //protected BodyPanelFunctionalizer bodyFunct;
        
        public GetTrangressingAirshipsSW( ){//BodyPanelFunctionalizer bodyPanelFunctionalizer ) {
        
            super( MainWindow.getInstance().getErrorJTextArea() );
            //bodyFunct = bodyPanelFunctionalizer;
        }
        
        
        
    }
    
    
    
    // INSTANCE FIELDS
    
    /**
     * The {@link JFooterPanelForMainWindow} receiving functionality.
     */
    private JFooterPanelForMainWindow footerPanel;
    
    /**
     * The {@link BodyPanelFunctionalizer} that is used by some buttons of this
     * {@link FunctionalFooterPanel} to update a {@link JBodyPanelForMainWindow} with information
     * about airships.
     */
    private BodyPanelFunctionalizer bodyPanelFunctionalizer;
    
    
    
    // CONSTRUCTOR & PRIVATE METHODS
    
    /**
     * Adds functionality to the non-functional {@link JFooterPanelForMainWindow}
     * {@code footerPanel}.
     * 
     * @param footerPanel
     *            The {@link JFooterPanelForMainWindow} to which we will had functionality.
     * @param bodyPanelFunctionalizer
     *            The {@link BodyPanelFunctionalizer} that is used by some buttons of this
     *            {@link FunctionalFooterPanel} to update a {@link JBodyPanelForMainWindow} with
     *            information about airships.
     */
    public FunctionalFooterPanel( JFooterPanelForMainWindow footerPanel,
                                  BodyPanelFunctionalizer bodyPanelFunctionalizer ) {
    
        this.footerPanel = footerPanel;
        this.bodyPanelFunctionalizer = bodyPanelFunctionalizer;
        addFunctionalityToTheButtons();
    }
    
    /**
     * Adds functionality to the get-all-airships button, the get-airships-closer-to-a-point button,
     * the get-transgressing-airships button, the get-airships-with-less-passengers-than-a-threshold
     * button, the add-airship button, the change-airship-properties button and the remove-airship
     * button.
     */
    private void addFunctionalityToTheButtons() {
    
        addGetAllAirshipsButtonAction();
        addGetNearestAirshipsButtonAction();
        addGetTransgressingAirshipsButtonAction();
        addGetAirshipsWithLessPassengerThanButtonAction();
        addPostAirshipButtonAction();
        addPatchAirshipButtonAction();
        addDeleteAirshipButtonAction();
    }
    
    /**
     * Adds functionality to the get-all-airships button; this button updates the
     * {@link JBodyPanelForMainWindow} associated with the {@link BodyPanelFunctionalizer} received
     * in the constructor to show the information about all the airships.
     */
    private void addGetAllAirshipsButtonAction() {
    
        footerPanel.getShowAllAirships()
                   .addActionListener( action -> bodyPanelFunctionalizer.updateBodyPanel() );
    }
    
    /**
     * Adds functionality to the get-airships-closer-to-a-point button; this button opens a new
     * {@link FunctionalGetAirshipsCloserToWindow}.
     */
    private void addGetNearestAirshipsButtonAction() {
    
        footerPanel.getNearestAirships()
                   .addActionListener( action -> new FunctionalGetAirshipsCloserToWindow() );
    }
    
    /**
     * Adds functionality to the get-transgressing-airships button; this button updates the
     * {@link JBodyPanelForMainWindow} associated with the {@link BodyPanelFunctionalizer} received
     * in the constructor to show the information about the transgressing airships.
     */
    private void addGetTransgressingAirshipsButtonAction() {
    
        footerPanel.getTransgressingAirships()
                   .addActionListener( action -> runNew_GetTransgressingAirships_SwingWorker() );
    }
    
    /**
     * Adds functionality to the get-airships-with-less-passengers-than-a-threshold button; this
     * button opens a new {@link FunctionalGetAirshipsWithLessPassengerThanWindow}.
     */
    private void addGetAirshipsWithLessPassengerThanButtonAction() {
    
        footerPanel.getAirshipsWithLessPassengerThan()
                   .addActionListener( action -> new FunctionalGetAirshipsWithLessPassengerThanWindow() );
    }
    
    /**
     * Adds functionality to the add-airship button; this button opens a new
     * {@link FunctionalPostAirshipWindow}.
     */
    private void addPostAirshipButtonAction() {
    
        footerPanel.getPostAirship()
                   .addActionListener( action -> new FunctionalPostAirshipWindow() );
    }
    
    /**
     * This functionality is not yet supported; this button opens a new
     * {@link UnderConstrutionWindow}.
     */
    private void addPatchAirshipButtonAction() {
    
        footerPanel.getPatchAirship().addActionListener( action -> new UnderConstrutionWindow() );
    }
    
    /**
     * This functionality is not yet supported; this button opens a new
     * {@link UnderConstrutionWindow}.
     */
    private void addDeleteAirshipButtonAction() {
    
        footerPanel.getDeleteAirship().addActionListener( action -> new UnderConstrutionWindow() );
    }
    
    /**
     * Runs a new {@link SwingWorker} created by {@link #getTransgressingAirships_SWFactory}. If the
     * {@link #getTransgressingAirships_SWFactory} is {@code null}, it is opened a new
     * {@link UnderConstrutionWindow}.
     */
    private void runNew_GetTransgressingAirships_SwingWorker() {
    
        try {
            Utils.runNewSwingWorker( getTransgressingAirships_SWFactory,
                                     FunctionalFooterPanel.class.getSimpleName() );
        }
        catch( SwingWorkerFactoryMissingException e ) {
            new UnderConstrutionWindow();
        }
    }
    
    
    
    // PUBLIC METHOD
    /**
     * Returns the base {@link JFooterPanelForMainWindow} that this {@link FunctionalFooterPanel} is
     * giving functionality to.
     * 
     * @return The base {@link JFooterPanelForMainWindow} that this {@link FunctionalFooterPanel} is
     *         giving functionality to.
     */
    public JFooterPanelForMainWindow getFooterPanel() {
    
        return footerPanel;
    }
    
    
}
