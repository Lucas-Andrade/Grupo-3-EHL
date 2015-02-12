package entities;


/**
 * Represents a user that is logged in the app.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class SimpleLoggedUser extends SimpleUser {
    
    /**
     * This logged user's password.
     */
    private String password;
    
    /**
     * Creates a new {@link SimpleLoggedUser} that have the same properties as {@code user} and
     * whose password is {@code password}.
     * 
     * @param user
     *            The user whose properties equal the new simple logged user.
     * @param password
     *            The password of this user.
     */
    public SimpleLoggedUser( SimpleUser user, String password ) {
    
        super( user.getIdentification(), user.toString() );
        this.password = password;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
    
        return password;
    }
    
}
