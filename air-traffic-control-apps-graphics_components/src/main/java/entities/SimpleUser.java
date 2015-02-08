package entities;


public class SimpleUser extends Entity {
    
    
    public final String username;
    public final String email;
    public final String fullname;
    
    
    public SimpleUser( String username, String email, String fullname, String toString ) {

        super( toString );
        this.username = username;
        this.email = email;
        this.fullname = fullname;
    }    
    
}
