package functionalcomponents.functionaluserwindows;


import java.awt.event.ActionListener;
import app.Utils;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import utils.CompletionStatus;
import design.windows.popupwindows.SuccessWindow;
import design.windows.userwindows.PostUserWindow;
import entities.SimpleLoggedUser;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.functionalmainwindow.FunctionalMainWindow;



/**
 * Class whose instances have the purpose of add functionality to a {@link PostUserWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * Extends {@link FunctionalWindow} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPostUserWindow extends
        FunctionalWindow< FunctionalPostUserWindow.SwingWorker, CompletionStatus > {
    
    
    
    // STATIC FIELDS
    
    /**
     * The {@code PostUserWindow} we want to add functionality to.
     */
    public static final PostUserWindow baseWindow = new PostUserWindow();
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link FunctionalPostUserWindow.SwingWorker}s
     * for the {@link FunctionalPostUserWindow}s.
     */
    private static SwingWorkerFactory< FunctionalPostUserWindow.SwingWorker, CompletionStatus > swFactory;
    
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock = new Object();
    
    // STATIC METHOD
    /**
     * Sets the {@link SwingWorkerFactory} that produces
     * {@link FunctionalPostUserWindow.SwingWorker}s for the {@link FunctionalPostUserWindow}s.
     * 
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link FunctionalPostUserWindow.SwingWorker}s for the
     *            {@link FunctionalPostUserWindow}s.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
     *         the {@link FunctionalPostUserWindow}s; <br/>
     *         {@code false} if there was a factory already set or {@code factory} is {@code null}.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalPostUserWindow.SwingWorker, CompletionStatus > factory ) {
    
        return Utils.setSWFactory( FunctionalPostUserWindow.class, "swFactory", factory,
                                   factoryLock );
    }
    
    
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link PostUserWindow} and displays it.
     */
    public FunctionalPostUserWindow() {
    
        super( baseWindow );
    }
    
    
    
    // IMPLEMENTATION OF THE METHOD INHERITED FROM FunctionalWindow
    /**
     * @see functionalcomponents.FunctionalWindow#runNewSwingWorker()
     */
    @Override
    protected void runNewSwingWorker() throws SwingWorkerFactoryMissingException {
    
        Utils.runNewSwingWorker( swFactory, FunctionalPostUserWindow.class.getSimpleName() );
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW} able to add functionality to a
     * {@link FunctionalPostUserWindow}.
     * <p>
     * The unimplemented method {@link #doInBackground()} is supposed to create and store a new user
     * with the username {@link #username}, the password {@link #password}, the email {@link #email}
     * and, if given, the full name {@link #fullName}. It is expected to return a
     * {@link CompletionStatus} representing the success/failure of the operation or an exception if
     * (1) the {@link #password} and the {@link #confirmPassword} don't match or (2) the operation
     * couldn't perform.
     * </p>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static abstract class SwingWorker extends ExceptionHandlerSW< CompletionStatus > {
        
        
        
        // INSTANCE FIELDS
        /**
         * The {@link PostUserWindow} to be disposed in the {@link #finalizeDone(CompletionStatus)}.
         */
        protected PostUserWindow window;
        
        /**
         * String representation of the parameters to use in the commands and that are obtained from
         * the window's text fields.
         */
        protected String username;
        protected String password;
        protected String confirmPassword;
        protected String email;
        protected String fullName;
        
        protected String usernameLabel;
        protected String passwordLabel;
        protected String confirmPasswordLabel;
        protected String emailLabel;
        protected String fullNameLabel;
        
        protected SimpleLoggedUser simpleLoggedUser = FunctionalMainWindow.getLoggedUser();
        
        
        
        // CONSTRUCTOR
        public SwingWorker( PostUserWindow window ) {
        
            super( window.getErrorJTextArea() );
            this.window = window;
            
            this.username = window.getUsername().getJTextField().getText();
            this.password = window.getPassword().getJTextField().getText();
            this.confirmPassword = window.getConfirmPassword().getJTextField().getText();
            this.email = window.getEmail().getJTextField().getText();
            this.fullName = window.getFullname().getJTextField().getText();
            
            this.usernameLabel = window.getUsername().getJLabel().getText();
            this.passwordLabel = window.getPassword().getJLabel().getText();
            this.confirmPasswordLabel = window.getConfirmPassword().getJLabel().getText();
            this.emailLabel = window.getEmail().getJLabel().getText();
            this.fullNameLabel = window.getFullname().getJLabel().getText();
        }
        
        
        // IMPLEMENTATION OF METHODS INHERITED FROM SwingWorker and FunctionalWindowSwingWorker
        /**
         * Presents a {@link SuccessWindow} and disposes the {@link #window} if the method
         * {@link #doInBackground()} occurred successfully or writes the error message in the error
         * label.
         */
        @Override
        protected void finalizeDone( CompletionStatus resultOfDoInBackGround ) throws Exception {
        
            if( resultOfDoInBackGround.completedSuccessfully() ) {
                
                //open success window:
                new SuccessWindow( resultOfDoInBackGround.getMessage() );
                //clean text fields:
                window.getUsername().getJTextField().setText("");
                window.getPassword().getJTextField().setText("");
                window.getConfirmPassword().getJTextField().setText("");
                window.getEmail().getJTextField().setText("");
                window.getFullname().getJTextField().setText("");
                //close post window:
                window.dispose();
            }
            else {
                window.getErrorJTextArea().setText( resultOfDoInBackGround.getMessage() );
            }
        }
        
        
    }
    
}
