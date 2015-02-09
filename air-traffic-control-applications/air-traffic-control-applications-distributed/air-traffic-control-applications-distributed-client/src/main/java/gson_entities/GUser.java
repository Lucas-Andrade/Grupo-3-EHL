package gson_entities;


public class GUser {
    
    private String username;
    private String email;
    private String fullName;
    
    public String toString() {
    
        StringBuilder result = new StringBuilder( "username: " );
        
        result.append( username ).append( ",\r\nemail: " ).append( email );
        
        return fullName.equals( null ) ? result.append( "\r\n" ).toString()
                                    : result.append( ",\r\nfullName: " ).append( fullName )
                                            .append( "\r\n" ).toString();
    }
}
