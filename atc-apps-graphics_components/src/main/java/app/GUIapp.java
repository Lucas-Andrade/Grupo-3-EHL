package app;


import java.awt.EventQueue;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import swingworkers.SwingWorkerForButtonFactory;
import utils.CompletionStatus;
import design.windows.MainWindow;
import design.windows.popupwindows.FailWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import entities.SimpleAirship;
import entities.SimpleUser;
import exceptions.InternalErrorException;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import functionalcomponents.functionalmainwindow.BodyPanelFunctionalizer;
import functionalcomponents.functionalmainwindow.FunctionalFooterPanel;
import functionalcomponents.functionalmainwindow.FunctionalHeaderPanel;
import functionalcomponents.functionalmainwindow.FunctionalHeaderPanel.GetAllUsersSW;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;
import functionalcomponents.infobuttons.SimpleAirshipInfoButton;
import functionalcomponents.infobuttons.SimpleUserInfoButton;


/**
 * Class whose instances represent applications with graphical user interfaces that use the design
 * and work flow established by the classes in this module (atc-apps-graphics_components).
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GUIapp {
    
    
    
    // INSTANCE FIELDS
    /**
     * The factories of {@link SwingWorker}s to be used by this {@link GUIapp}.
     */
    
    private SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > loginSWFactory;
    private SwingWorkerFactory< functionalcomponents.functionaluserwindows.FunctionalPostUserWindow.SwingWorker, CompletionStatus > postUserSWFactory;
    private SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > patchUserSWFactory;
    private SwingWorkerFactory< GetAllUsersSW, Iterable< SimpleUser >> getUsersSWFactory;
    private SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > getUserByIdSWFactory;
    
    private SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship >> getAllAirshipsFactory;
    private SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > getAirshipByIdSWFactory;
    private SwingWorkerFactory< functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, Iterable< SimpleAirship >> getAirshipsCloserToFactory;
    private SwingWorkerFactory< FunctionalFooterPanel.GetTrangressingAirshipsSW, Iterable< SimpleAirship >> getTransgressingAirshipsFactory;
    private SwingWorkerFactory< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker, Iterable< SimpleAirship >> getAirshipsWithLessPassengersFact;
    private SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > postAirshipFactory;
    
    
    
    // CONSTRUCTOR
    /**
     * Creates a new {@link GUIapp} that operates in the background according to the
     * {@link SwingWorker}s produced by the factories given as arguments. If {@code null}-factories
     * are given as argument, {@link UnderConstrutionWindow}s will be presented whenever the
     * functionality of the {@link SwingWorker}s they were to produce is requested.
     * 
     * @param loginSWFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalLoginWindow.SwingWorker}s.
     * @param getAllAirshipsFactory
     */
    public GUIapp( SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > loginSWFactory,
                   SwingWorkerFactory< FunctionalPostUserWindow.SwingWorker, CompletionStatus > postUserSWFactory,
                   SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > patchUserSWFactory,
                   SwingWorkerFactory< FunctionalHeaderPanel.GetAllUsersSW, Iterable< SimpleUser > > getUsersSWFactory,
                   SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > getUserByIdSWFactory,
                   SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > getAllAirshipsFactory,
                   SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > getAirshipByIdSWFactory,
                   SwingWorkerFactory< FunctionalGetGeographicalCoordinatesParametersWindow.SwingWorker, Iterable< SimpleAirship >> getAirshipsCloserToFactory,
                   SwingWorkerFactory< FunctionalFooterPanel.GetTrangressingAirshipsSW, Iterable< SimpleAirship >> getTransgressingAirshipsFactory,
                   SwingWorkerFactory< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker, Iterable< SimpleAirship >> getAirshipsWithLessPassengersFact,
                   SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > postAirshipFactory ) {
    
        this.loginSWFactory = loginSWFactory;
        this.postUserSWFactory = postUserSWFactory;
        this.patchUserSWFactory = patchUserSWFactory;
        this.getUsersSWFactory = getUsersSWFactory;
        this.getUserByIdSWFactory = getUserByIdSWFactory;
        
        this.getAllAirshipsFactory = getAllAirshipsFactory;
        this.getAirshipByIdSWFactory = getAirshipByIdSWFactory;
        this.getAirshipsCloserToFactory = getAirshipsCloserToFactory;
        this.getTransgressingAirshipsFactory = getTransgressingAirshipsFactory;
        this.getAirshipsWithLessPassengersFact = getAirshipsWithLessPassengersFact;
        this.postAirshipFactory = postAirshipFactory;
        
        setSwingWorkerFactoriesInTheFunctionalWindows();
    }
    
    
    
    // PUBLIC METHOD
    /**
     * Runs an application with a graphical user interface that uses the design and work flow
     * established by the classes in this module (atc-apps-graphics_components).
     */
    public void run() {
    
        
        EventQueue.invokeLater( new Runnable() {
            
            /**
             * Runs the app.
             */
            @Override
            public void run() {
            
                try {
                    new FunctionalLoginWindow();
                    
                }
                catch( InternalErrorException e ) {
                    
                    if( e.getCause() instanceof SwingWorkerFactoryMissingException )
                        new UnderConstrutionWindow();
                    else new FailWindow( "INTERNAL ERROR!" );
                    System.out.println( e.getMessage() ); // TODO: remove when finish development
                }
                catch( Exception e ) {
                    MainWindow.getInstance().getErrorJTextArea().setText( e.getMessage() );
                }
            }
            
        } );
    }
    
    
    
    // PRIVATE METHOD
    
    // used in the constructor
    /**
     * Sets the {@link SwingWorkerFactory}s that produce {@link FunctionalLoginWindow.SwingWorker}s
     * for each subtype of {@link FunctionalWindow}.
     */
    private void setSwingWorkerFactoriesInTheFunctionalWindows() {
    
        FunctionalLoginWindow.setSwingWorkerFactory( loginSWFactory );
        FunctionalPostUserWindow.setSwingWorkerFactory( postUserSWFactory );
        FunctionalPatchUserWindow.setSwingWorkerFactory( patchUserSWFactory );
        FunctionalHeaderPanel.setAllUsersFactory( getUsersSWFactory );
        SimpleUserInfoButton.setSwingWorkerFactory( getUserByIdSWFactory );
        
        BodyPanelFunctionalizer.setSwingWorkerFactory( getAllAirshipsFactory );
        SimpleAirshipInfoButton.setSwingWorkerFactory( getAirshipByIdSWFactory );
        FunctionalGetGeographicalCoordinatesParametersWindow.setSwingWorkerFactory( getAirshipsCloserToFactory );
        FunctionalFooterPanel.setAllUsersFactory( getTransgressingAirshipsFactory );
        FunctionalGetAirshipsWithLessPassengerThanWindow.setSwingWorkerFactory( getAirshipsWithLessPassengersFact );
        FunctionalPostAirshipWindow.setSwingWorkerFactory( postAirshipFactory );
    }
    
    
    
}
