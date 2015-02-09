package entities;


/**
 * Class whose instances represent a {@code User} to be used (with the minimal resources) in the
 * graphics components.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class SimpleUser extends Entity {
    
    /**
     * The user identification
     */
    public final String username;
    
    /**
     * 
     * @param username
     *            - the {@code User} username.
     * @param toString
     *            - The string with the {@code SimpleUser} info-
     * 
     */
    public SimpleUser( String username, String toString ) {
    
        super( toString );
        this.username = username;
    }
    
}
