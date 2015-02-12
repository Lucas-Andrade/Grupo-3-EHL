package app;


import java.awt.EventQueue;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import design.windows.popupwindows.FailWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import entities.SimpleUser;
import exceptions.InternalErrorException;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.functionalmainwindow.FunctionalMainWindow;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;


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
     */
    public GUIapp( SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > loginSWFactory ) {
    
        this.loginSWFactory = loginSWFactory;
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
                    System.out.println( e.getMessage() ); //TODO: remove when finish development
                }
                catch( Exception e ) {
                    FunctionalMainWindow.windowBase.getErrorJTextArea().setText( e.getMessage() );
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
    }
    
    
    
}
