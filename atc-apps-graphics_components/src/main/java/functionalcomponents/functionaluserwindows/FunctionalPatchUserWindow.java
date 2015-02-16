package functionalcomponents.functionaluserwindows;


import java.awt.event.ActionListener;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import swingworkers.Utils;
import utils.CompletionStatus;
import design.windows.popupwindows.SuccessWindow;
import design.windows.userwindows.PatchUserWindow;
import design.windows.userwindows.PostUserWindow;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;



/**
 * Class whose instances have the purpose of add functionality to a {@link PatchUserWindow}. Giving
 * functionality to a window means adding an {@link ActionListener} to all its buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPatchUserWindow extends
        FunctionalWindow< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > {
    
    
    
    // STATIC FIELDS
    
    /**
     * The {@code FunctionalPatchUserWindow} we want to add functionality to.
     */
    public static final PatchUserWindow baseWindow = new PatchUserWindow();
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link FunctionalPatchUserWindow.SwingWorker}s
     * for the {@link FunctionalPatchUserWindow}s.
     */
    private static SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > swFactory;
    
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock = new Object();
    
    // STATIC METHOD
    /**
     * Sets the {@link SwingWorkerFactory} that produces
     * {@link FunctionalPatchUserWindow.SwingWorker}s for the {@link FunctionalPatchUserWindow}s.
     * 
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link FunctionalPatchUserWindow.SwingWorker}s for the
     *            {@link FunctionalPatchUserWindow}s.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
     *         the {@link FunctionalPatchUserWindow}s; <br/>
     *         {@code false} if there was a factory already set or {@code factory} is {@code null}.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > factory ) {
    
        return Utils.setSWFactory( FunctionalPatchUserWindow.class, "swFactory", factory,
                                   factoryLock );
    }
    
    
    
    // CONSTRUCTOR
    /**
     * Adds functionality to a {@link PatchUserWindow} and displays it.
     */
    public FunctionalPatchUserWindow() {
    
        super( baseWindow );
    }
    
    
    
    // IMPLEMENTATION OF THE METHOD INHERITED FROM FunctionalWindow
    /**
     * @see functionalcomponents.FunctionalWindow#getNewSwingWorker()
     */
    @Override
    protected void runNewSwingWorker() throws SwingWorkerFactoryMissingException {
    
        Utils.runNewSwingWorker( swFactory, FunctionalPatchUserWindow.class.getSimpleName() );
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are {@link ExceptionHandlerSW} able to add functionality to a
     * {@link FunctionalPostUserWindow}.
     * <p>
     * The unimplemented method {@link #doInBackground()} is supposed to replace the password of the
     * user with the username {@link #username}, {@code oldPassword}, with {@code newPassword}. It
     * is expected to return a {@link CompletionStatus} representing the success/failure of the
     * operation or an exception if (1) the {@link #password} and the {@link #confirmPassword} don't
     * match or (2) the operation couldn't perform.
     * </p>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static abstract class SwingWorker extends ExceptionHandlerSW< CompletionStatus > {
        
        
        
        // INSTANCE FIELDS
        /**
         * The {@link PostUserWindow} to be disposed in the {@link #finalizeDone(CompletionStatus)}.
         */
        protected PatchUserWindow window;
        
        /**
         * String representation of the parameters to use in the commands and that are obtained from
         * the window's text fields.
         */
        protected String username;
        protected String oldPassword;
        protected String newPassword;
        protected String newConfirmationPassword;
        
        protected String usernameLabel;
        protected String oldPasswordLabel;
        protected String newPasswordLabel;
        protected String newConfirmationPasswordLabel;
        
        
        
        // CONSTRUCTOR
        public SwingWorker( PatchUserWindow window ) {
        
            super( window.getErrorJTextArea() );
            this.window = window;
            
            this.username = window.getUserPanel().getJTextField().getText();
            this.oldPassword = window.getOldPasswordPanel().getJTextField().getText();
            this.newPassword = window.getNewPasswordPanel().getJTextField().getText();
            this.newConfirmationPassword =
                    window.getNewPasswordConfirmationPanel().getJTextField().getText();
            
            this.usernameLabel = window.getUserPanel().getJLabel().getText();
            this.oldPasswordLabel = window.getOldPasswordPanel().getJLabel().getText();
            this.newPasswordLabel = window.getNewPasswordPanel().getJLabel().getText();
            this.newConfirmationPasswordLabel =
                    window.getNewPasswordConfirmationPanel().getJLabel().getText();
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
                window.getUserPanel().getJTextField().setText("");
                window.getOldPasswordPanel().getJTextField().setText("");
                window.getNewPasswordPanel().getJTextField().setText("");
                window.getNewPasswordConfirmationPanel().getJTextField().setText("");
                //close post window:
                window.dispose();
            }
            else {
                window.getErrorJTextArea().setText( resultOfDoInBackGround.getMessage() );
            }
        }
        
        
    }
    
}
