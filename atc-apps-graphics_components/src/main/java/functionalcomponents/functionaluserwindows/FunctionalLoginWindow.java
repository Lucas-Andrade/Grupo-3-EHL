package functionalcomponents.functionaluserwindows;


import java.awt.event.ActionListener;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import swingworkers.Utils;
import design.windows.userwindows.LogInWindow;
import entities.SimpleLoggedUser;
import entities.SimpleUser;
import exceptions.InternalErrorException;
import exceptions.LoggedInUserMissingException;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.functionalmainwindow.FunctionalMainWindow;


/**
 * Class whose instances add functionality to a {@link LogInWindow}. Giving functionality to a
 * window means adding an {@link ActionListener} to all its buttons.
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
     *         {@code false} if there was a factory already set or {@code factory} is {@code null}.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalLoginWindow.SwingWorker, SimpleUser > factory ) {
    
        return Utils.setSWFactory( FunctionalLoginWindow.class, "swFactory", factory, factoryLock );
    }
    
    
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link LoginWindow} and displays it.
     */
    public FunctionalLoginWindow() {
    
        super( baseWindow );
    }
    
    
    
    // IMPLEMENTATION OF THE METHOD INHERITED FROM FunctionalWindow
    /**
     * @see functionalcomponents.FunctionalWindow#runNewSwingWorker()
     */
    @Override
    protected void runNewSwingWorker() throws SwingWorkerFactoryMissingException {
    
        Utils.runNewSwingWorker( swFactory, FunctionalLoginWindow.class.getSimpleName() );
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW}s able to add functionality to a
     * {@link LogInWindow}.
     * <p>
     * The unimplemented method {@link #doInBackground()} is supposed to authenticate a user with
     * username {@link #username} and password {@link #password} and return its representation as a
     * {@link SimpleUser} if the password is correct or throw an exception otherwise.
     * </p>
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
         * Presents the {@link FunctionalMainWindow} with the user received from the
         * {@link #doInBackground()} as the logged-in user.
         * 
         * @param resultOfDoInBackGround
         *            The result of the method {@link SwingWorker#doInBackground()}.
         */
        @Override
        protected void finalizeDone( SimpleUser resultOfDoInBackGround )
            throws InternalErrorException {
        
            if( resultOfDoInBackGround == null )
                throw new InternalErrorException( "UNEXPECTED null AUTHENTICATED USER RECEIVED"
                                                  + " FROM doInBackground" );
            FunctionalMainWindow.setLoggedUser( new SimpleLoggedUser( resultOfDoInBackGround,
                                                                      password ) );
            
            try {
                // clean up the text fields of login window:
                window.getUserPanel().getJTextField().setText( "" );
                window.getPasswordPanel().getJTextField().setText( "" );
                // dispose login window:
                window.dispose();
                // show main window:
                FunctionalMainWindow.getInstance();
            }
            catch( LoggedInUserMissingException e ) {
                throw new InternalErrorException(
                                                  "EXPECTED THE LOGGED-IN USER TO BE SET BY NOW! O_o",
                                                  e );
            }
        }
        
        
    }
    
    
}
