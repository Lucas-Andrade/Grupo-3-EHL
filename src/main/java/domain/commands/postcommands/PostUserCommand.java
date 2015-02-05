package main.java.domain.commands.postcommands;


import java.util.concurrent.Callable;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that post users in a given database.
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserCommand implements Callable< CompletionStatus > {
    
    // INSTANCE FIELDS
    
    /**
     * The users' database that stores the user who's posting the user.
     */
    private User userWhoIsPosting;
    
    /**
     * The database where to post the new user.
     */
    private Database< User > databaseWhereToPost;
    
    /**
     * The properties of the user to be created and added to the database.
     */
    private String username;
    private String password;
    private String email;
    private String fullName;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that adds a user with these properties to
     * {@link #databaseWhereToPost}.
     * 
     * @param username
     * @param password
     * @param email
     * @param fullname
     * @param databaseWhereToPost
     *            - The database where to add the new user.
     * @param userWhoIsPosting
     *            - The user whose login name was given in the post command.
     * @throws InvalidArgumentException
     *             If the {@code databaseWhereToPost} is null.
     */
    public PostUserCommand( String username, String password, String email, String fullname,
                            Database< User > databaseWhereToPost, User userWhoIsPosting )
        throws InvalidArgumentException {
        
        if( databaseWhereToPost == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        this.userWhoIsPosting = userWhoIsPosting;
        this.databaseWhereToPost = databaseWhereToPost;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullname;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Adds a new user with the properties given in the constructor to the database given in the
     * constructor. Returns a message saying if the user was successfully added or not.
     * 
     * @return A message of success if the user was successfully posted; </br>a message of failure
     *         if it wasn't.
     * 
     * @throws Exception
     *             If the value given for {@code username}, {@code password} or {@code email} is
     *             invalid.
     */
    @Override
    public CompletionStatus call() throws Exception {
        
        User theUser =
                (fullName != null)
                                  ? new User( username, password, email, fullName )
                                  : new User( username, password, email );
        
        if( databaseWhereToPost.add( theUser, userWhoIsPosting ) )
            return new CompletionStatus( true, "New user successfully added: " + theUser.toString() );
        
        return new CompletionStatus( false, new StringBuilder( "User not added. Either the username «" ).append( username )
                                                                           .append( "» or\nthe email «" )
                                                                           .append( email )
                                                                           .append( "» already exist in " )
                                                                           .append( databaseWhereToPost.getDatabaseName() )
                                                                           .toString());
    }
}