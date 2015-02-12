package gson_entities;


import com.google.gson.Gson;
import entities.SimpleUser;


/**
 * Instances of this class should be created by the {@link Gson#fromJson(String, UserFromJson.Class)}
 * method, and used to be converted in a {@link SimpleUser}.
 * 
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
     * Field not mandatory, can be null.
     */
    private String fullname;
    
    /*
     * The SimpleUser info 
     */
    private String info;
    
    /**
     * Create a {@code GUser}, it should be used by the {@link Gson#fromJson(String, UserFromJson.Class)}
     * method, that will initialize all the necessary fields. After initialized it should be called
     * the {@link UserFromJson#convert()} to get the {@link SimpleUser} that ARE to be used in the
     * Graphics_Componests module (Swing).
     */
    public UserFromJson() {
    
        info = createInfo().toString();
    }
    
    /**
     * Create a {@link SimpleUser} to be used in the Graphics_Componests module (Swing).
     * 
     * @return the converted {@code SimpleUser}
     */
    public SimpleUser convert() {
    
        return new SimpleUser( username, info );
    }
    
    /**
     * Create the {@code SimpleUser} info. If {@code fullname} is null its info will not be added.
     * 
     * @return the {@code SimpleUser} info
     */
    private StringBuilder createInfo() {
    
        StringBuilder result = new StringBuilder( "username: " );
        
        result.append( username ).append( ",\r\nemail: " ).append( email );
        
        return fullname.equals( null ) ? result// .append( "\r\n" )
                                      : result.append( ",\r\nfullName: " ).append( fullname );
        // .append( "\r\n" );
    }
}
