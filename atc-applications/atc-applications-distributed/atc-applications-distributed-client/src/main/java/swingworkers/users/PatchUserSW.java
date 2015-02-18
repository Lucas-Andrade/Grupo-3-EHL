package swingworkers.users;


import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.CompletionStatus;
import utils.ClientNonGETRequest;
import utils.StringCommandsDictionary;
import utils.StringUtils;
import utils.ClientNonGETRequest.NonGetMethods;
import com.google.gson.Gson;
import design.windows.userwindows.PatchUserWindow;
import exceptions.InvalidArgumentException;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;


/**
 * Concrete implementation of {@link FunctionalPatchUserWindow.SwingWorker}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserSW extends FunctionalPatchUserWindow.SwingWorker {
    
    
    
    public PatchUserSW( PatchUserWindow window ) {
    
        super( window );
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostUserWindow.SwingWorker
    /**
     * Replaces the password of the user with the username {@link #username}, {@code oldPassword},
     * with {@code newPassword}. It is expected to return a {@link CompletionStatus} representing
     * the success/failure of the operation or an exception if the operation couldn't perform.
     * 
     * @return Returns a {@link CompletionStatus} representing the success/failure of the operation.
     * 
     * @throws Exception
     *             If (1) the {@link #password} and the {@link #confirmPassword} don't match or (2)
     *             the operation couldn't perform.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        if( !newPassword.equals( newConfirmationPassword ) )
            throw new InvalidArgumentException( "New passwords don't match!" );
        
        ClientRequest request = new ClientNonGETRequest( NonGetMethods.PATCH, "users/" + username ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
                addNewParameter( StringCommandsDictionary.OLDPASSWORD,
                                 StringUtils.parameterToString( oldPasswordLabel, oldPassword ) );
                addNewParameter( StringCommandsDictionary.NEWPASSWORD,
                                 StringUtils.parameterToString( newPasswordLabel, newPassword ) );
            }
        };
        
        
        return new Gson().fromJson( request.getResponse(), CompletionStatus.class );
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are responsible for producing {@link PatchUserSW}s.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory implements
            SwingWorkerFactory< FunctionalPatchUserWindow.SwingWorker, CompletionStatus > {
        
        
        private PatchUserWindow window;
        
        
        public Factory( PatchUserWindow window ) {
        
            this.window = window;
        }
        
        
        @Override
        public PatchUserSW newInstance() {
        
            return new PatchUserSW( window );
        }
        
    }
    
}
