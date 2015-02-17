package swingworkers.users;


import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.CompletionStatus;
import utils.ClientNonGETRequest;
import utils.ClientNonGETRequest.NonGetMethods;
import utils.StringCommandsDictionary;
import utils.StringUtils;
import com.google.gson.Gson;
import design.windows.userwindows.PostUserWindow;
import exceptions.InvalidArgumentException;
import exceptions.MissingRequiredParameterException;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;


/**
 * Concrete implementation of {@link FunctionalPostUserWindow.SwingWorker}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserSW extends FunctionalPostUserWindow.SwingWorker {
    
    
    
    public PostUserSW( PostUserWindow window ) {
    
        super( window );
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostUserWindow.SwingWorker
    /**
     * Creates and stores a new user with the username {@link #username}, the password
     * {@link #password}, the email {@link #email} and, if given, the full name {@link #fullName}.
     * It is expected to return a {@link CompletionStatus} representing the success/failure of the
     * operation or an exception if the operation couldn't perform.
     * 
     * @return Returns a {@link CompletionStatus} representing the success/failure of the operation.
     * 
     * @throws Exception
     *             If (1) the {@link #password} and the {@link #confirmPassword} don't match or (2)
     *             the operation couldn't perform.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        if( !password.equals( confirmPassword ) )
            throw new InvalidArgumentException( "Passwords don't match!" );
        
        ClientRequest request = new ClientNonGETRequest( NonGetMethods.POST, "users/" ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
                addNewParameter( StringCommandsDictionary.USERNAME,
                                 StringUtils.parameterToString( usernameLabel, username ) );
                addNewParameter( StringCommandsDictionary.PASSWORD,
                                 StringUtils.parameterToString( passwordLabel, password ) );
                
                addNewParameter( StringCommandsDictionary.EMAIL,
                                 StringUtils.parameterToString( emailLabel, email ) );
                System.out.println(fullName);
                if( !fullName.equals( "" ) ){
                    addNewParameter( StringCommandsDictionary.FULLNAME, fullName );
                }
                
                addAuthenticateParameters( simpleLoggedUser.getIdentification(), simpleLoggedUser.getPassword() );
                
            }
        };
        
        return new Gson().fromJson( request.getResponse(), CompletionStatus.class );
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are responsible for producing {@link PostUserSW}s.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    public static class Factory implements
            SwingWorkerFactory< FunctionalPostUserWindow.SwingWorker, CompletionStatus > {
        
        
        private PostUserWindow window;
        
        
        public Factory( PostUserWindow window ) {
        
            this.window = window;
        }
        
        
        @Override
        public PostUserSW newInstance() {
        
            return new PostUserSW( window );
        }
        
    }
    
}
