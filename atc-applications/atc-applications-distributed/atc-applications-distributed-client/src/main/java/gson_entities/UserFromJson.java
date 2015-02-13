package gson_entities;


import com.google.gson.Gson;
import entities.SimpleUser;


/**
 * * Instances of this class are the bridge between an {@code User} in {@code Json} format and a
 * {@link SimpleUser}, that should be created by the
 * {@link Gson#fromJson(String, UserFromJson.Class)} method, and after initialization converted to a
 * {@link SimpleUser} using the {@link UserFromJson#convert()} method.
 * 
 * Because instances of this class should created with {@link Gson} {@code reflection}, this class
 * does not need a constructor to initialize its fields.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class UserFromJson {
    
    /*
     * Fields needed to create a {@link SimpleUser}
     */
    private String username;
    private String email;
    
    /*
     * Not mandatory field, it can be null.
     */
    private String fullname;
    
    
    
    // Public method
    /**
     * Create a {@link SimpleUser} to be used in the Graphics_Componests module (Swing).
     * 
     * @return the converted {@code SimpleUser}
     */
    public SimpleUser convert() {
    
        return new SimpleUser( username, createInfo() );
    }
    
    
    
    
    // Private method
    /**
     * Returns the {@code SimpleUser} info. If {@code fullname} is null its info will not be added.
     * 
     * @return the {@code SimpleUser} info
     */
    private String createInfo() {
    
        StringBuilder result = new StringBuilder( "username: " );
        
        result.append( username ).append( ",\r\nemail: " ).append( email );
        
        return fullname == null ? result.toString()// .append( "\r\n" )
                               : result.append( ",\r\nfullName: " ).append( fullname ).toString();
        // .append( "\r\n" );
    }
}
