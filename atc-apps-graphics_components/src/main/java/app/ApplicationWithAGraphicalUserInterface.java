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
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsCloserToWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import functionalcomponents.functionalmainwindow.BodyPanelFunctionalizer;
import functionalcomponents.functionalmainwindow.FunctionalFooterPanel;
import functionalcomponents.functionalmainwindow.FunctionalHeaderPanel;
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
public class ApplicationWithAGraphicalUserInterface {
    
    
    
    // CONSTRUCTOR
    /**
     * Creates a new {@link ApplicationWithAGraphicalUserInterface} that operates in the background
     * according to the {@link SwingWorker}s produced by the factories given as arguments.
     * <p>
     * This constructor immediately sets the {@link SwingWorkerFactory}s that produce
     * {@link FunctionalLoginWindow.SwingWorker}s for each subtype of {@link FunctionalWindow}. If
     * {@code null}-factories are given as argument, {@link UnderConstrutionWindow}s will be
     * presented whenever the functionality of the {@link SwingWorker}s they were to produce is
     * requested.
     * </p>
     * 
     * @param loginSWFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalLoginWindow.SwingWorker}s.
     * @param postUserSWFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalPostUserWindow.SwingWorker}s.
     * @param patchUserSWFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalPatchUserWindow.SwingWorker}s.
     * @param getUsersSWFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalHeaderPanel.GetAllUsersSW}s.
     * @param getUserByIdSWFactory
     *            A {@link SwingWorkerForButtonFactory} that produces swing workers that get users
     *            given their usernames.
     * @param getAllAirshipsFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link BodyPanelFunctionalizer.SwingWorker}s.
     * @param getAirshipByIdSWFactory
     *            A {@link SwingWorkerForButtonFactory} that produces swing workers that get
     *            airships given their flightIds.
     * @param getAirshipsCloserToFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalGetAirshipsCloserToWindow.SwingWorker}s.
     * @param getTransgressingAirshipsFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalFooterPanel.GetTrangressingAirshipsSW}s.
     * @param getAirshipsWithLessPassengersFact
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker}s.
     * @param postAirshipFactory
     *            A {@link SwingWorkerFactory} that produces
     *            {@link FunctionalPostAirshipWindow.SwingWorker}s.
     */
    public ApplicationWithAGraphicalUserInterface( SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > loginSWFactory,
                                                   SwingWorkerFactory< FunctionalPostUserWindow.SwingWorker, CompletionStatus > postUserSWFactory,
                                                   SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > patchUserSWFactory,
                                                   SwingWorkerFactory< FunctionalHeaderPanel.GetAllUsersSW, Iterable< SimpleUser > > getUsersSWFactory,
                                                   SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > getUserByIdSWFactory,
                                                   SwingWorkerFactory< BodyPanelFunctionalizer.SwingWorker, Iterable< SimpleAirship > > getAllAirshipsFactory,
                                                   SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > getAirshipByIdSWFactory,
                                                   SwingWorkerFactory< FunctionalGetAirshipsCloserToWindow.SwingWorker, Iterable< SimpleAirship >> getAirshipsCloserToFactory,
                                                   SwingWorkerFactory< FunctionalFooterPanel.GetTrangressingAirshipsSW, Iterable< SimpleAirship >> getTransgressingAirshipsFactory,
                                                   SwingWorkerFactory< FunctionalGetAirshipsWithLessPassengerThanWindow.SwingWorker, Iterable< SimpleAirship >> getAirshipsWithLessPassengersFact,
                                                   SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > postAirshipFactory ) {
    
        FunctionalLoginWindow.setSwingWorkerFactory( loginSWFactory );
        FunctionalPostUserWindow.setSwingWorkerFactory( postUserSWFactory );
        FunctionalPatchUserWindow.setSwingWorkerFactory( patchUserSWFactory );
        FunctionalHeaderPanel.setAllUsersFactory( getUsersSWFactory );
        SimpleUserInfoButton.setSwingWorkerFactory( getUserByIdSWFactory );
        
        BodyPanelFunctionalizer.setSwingWorkerFactory( getAllAirshipsFactory );
        SimpleAirshipInfoButton.setSwingWorkerFactory( getAirshipByIdSWFactory );
        FunctionalGetAirshipsCloserToWindow.setSwingWorkerFactory( getAirshipsCloserToFactory );
        FunctionalFooterPanel.setAllUsersFactory( getTransgressingAirshipsFactory );
        FunctionalGetAirshipsWithLessPassengerThanWindow.setSwingWorkerFactory( getAirshipsWithLessPassengersFact );
        FunctionalPostAirshipWindow.setSwingWorkerFactory( postAirshipFactory );
    }
    
    
    
    // PUBLIC METHOD
    /**
     * Runs an application with a graphical user interface that uses the design and work flow
     * established by the classes in this module (atc-apps-graphics_components).
     */
    public void run() {
    
        
        EventQueue.invokeLater( ( ) -> {
            
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
            
        } );
    }
    
    
    
}