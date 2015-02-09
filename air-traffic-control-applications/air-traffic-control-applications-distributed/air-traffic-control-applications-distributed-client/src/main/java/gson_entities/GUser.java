package gson_entities;

import entities.SimpleUser;


public class GUser {
    
    private String username;
    private String email;
    private String fullname;
    
    public String toString() {
    
        StringBuilder result = new StringBuilder( "username: " );
        
        result.append( username ).append( ",\r\nemail: " ).append( email );
        
        return fullname.equals( null ) ? result.append( "\r\n" ).toString()
                                    : result.append( ",\r\nfullName: " ).append( fullname )
                                            .append( "\r\n" ).toString();
    }
    
    
    public SimpleUser convert()
    {
        return new SimpleUser( username, email, fullname, toString() );
    }
}
