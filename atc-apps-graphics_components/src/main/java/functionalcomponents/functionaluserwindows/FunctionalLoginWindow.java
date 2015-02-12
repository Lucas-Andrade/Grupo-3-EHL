package functionalcomponents.functionaluserwindows;


import java.awt.event.ActionListener;
import org.eclipse.jetty.server.Authentication.User;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import design.windows.popupwindows.UnderConstrutionWindow;
import design.windows.userwindows.LogInWindow;
import entities.SimpleUser;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.functionalmainwindow.FunctionalMainWindow;


/**
 * Class whose instances have the purpose of add functionality to a {@link LogInWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link User}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalLoginWindow extends
        FunctionalWindow< FunctionalLoginWindow.SwingWorker, SimpleUser > {
    
    
    
    // STATIC FIELDS
    
    /**
     * The {@link LogInWindow} we want to add functionality to.
     */
    public static final LogInWindow baseWindow = new LogInWindow();
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link FunctionalLoginWindow.SwingWorker}s for
     * the {@link FunctionalLoginWindow}s.
     */
    private static SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > swFactory;
    
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock = new Object();
    
    // STATIC METHOD
    /**
     * Sets the {@link SwingWorkerFactory} that produces {@link FunctionalLoginWindow.SwingWorker}s
     * for the {@link FunctionalLoginWindow}s.
     * 
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link FunctionalLoginWindow.SwingWorker}s for the {@link FunctionalLoginWindow}s.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
     *         the {@link FunctionalLoginWindow}s; <br/>
     *         {@code false} if there was a factory already set.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > factory ) {
    
        synchronized (factoryLock) {
            if( swFactory == null && factory != null) {
                swFactory = factory;
                return true;
            }
            return false;
        }
    }
    
    
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link LoginWindow} and displays it.
     */
    public FunctionalLoginWindow() {
    
        super( baseWindow );
    }
    
    
    
    // PUBLIC METHOD
    /**
     * @see functionalcomponents.FunctionalWindow#getNewSwingWorker()
     */
    @Override
    protected SwingWorker getNewSwingWorker() throws SwingWorkerFactoryMissingException {
    
        if( swFactory == null )
            throw new SwingWorkerFactoryMissingException( this.getClass().getSimpleName() );
        return swFactory.newInstance();
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW} able to add funcitonality to a
     * {@link LogInWindow}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static abstract class SwingWorker extends ExceptionHandlerSW< SimpleUser > {
        
        
        
        // INSTANCE FIELDS
        
        protected LogInWindow window;
        
        /**
         * String representation of the parameters to use in the commands and that are obtained from
         * the window's text fields.
         */
        protected String usernameLabel;
        protected String passwordLabel;
        
        protected String username;
        protected String password;
        
        
        
        // CONSTRUCTOR
        public SwingWorker( LogInWindow window ) {
        
            super( window.getErrorJTextArea() );
            this.window = window;
            
            this.usernameLabel = window.getUserPanel().getJLabel().getText();
            this.passwordLabel = window.getPasswordPanel().getJLabel().getText();
            
            this.username = window.getUserPanel().getJTextField().getText();
            this.password = window.getPasswordPanel().getJTextField().getText();
        }
        
        
        
        // IMPLEMENTATION OF METHODS INHERITED FROM SwingWorker and FunctionalWindowSwingWorker
        /**
         * Implementation of the {@link FunctionalWindowSwingWorker#functionalDone()
         * functionalDone()}. This method will receive the result of the
         * {@link SwingWorker#doInBackground() doInBackground()} method and open a new
         * {@link FunctionalMainWindow}, closing this window.
         * 
         * @param resultOfDoInBackGround
         *            - The result of the {@link SwingWorker#doInBackground() doInBackground()}
         *            method.
         */
        @Override
        protected void finalizeDone( SimpleUser resultOfDoInBackGround ) throws Exception {
        
//            new FunctionalMainWindow( new MainWindow( airshipsDatabase,
//                                                      airshipsDatabase.getAllElements().get() ),
//                                      usersDatabase, airshipsDatabase, resultOfDoInBackGround );
            new UnderConstrutionWindow();
            baseWindow.dispose();
        }
        
        
    }
    
    
}
