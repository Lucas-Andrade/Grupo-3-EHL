package gson_entities;


import com.google.gson.Gson;
import entities.SimpleAirship;


/**
 * Instances of this class should be created by the {@link Gson#fromJson(String, AirshipFromJson.Class)}
 * method, and used to be converted to a {@link SimpleAirship}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipFromJson {
    
    /*
     * Fields needed to create a {@link SimpleAirship}
     */
    private String flightId;
    private double latitude;
    private double longitude;
    private double altitude;
    private double maxAltitude;
    private double minAltitude;
    
    /*
     * Field used for a "Civil" SimpleAirship, can be null.
     */
    private Integer passengers;
    
    /*
     * Field used for a "Military" SimpleAirship, can be null.
     */
    private Boolean hasWeapons;
    
    /*
     * The SimpleAirship info 
     */
    private String info;
    
    /**
     * Create a GAirship, it should be used by the {@link Gson#fromJson(String, AirshipFromJson.Class)}
     * method, that will initialize all the necessary fields. After initialized it should be called
     * the {@link AirshipFromJson#convert()} to get the {@link SimpleAirship} that ARE to be used in the
     * Graphics_Componests module (Swing).
     */
    public AirshipFromJson() {
    
        info = createInfo().toString();
    }
    
    /**
     * Create a {@link SimpleAirship} to be used in the Graphics_Componests module (Swing).
     * 
     * @return the converted {@code SimpleAirship}
     */
    public SimpleAirship convert() {
    
        return new SimpleAirship( flightId, latitude, longitude, info );
    }
    
    
    
    /**
     * Create the {@code SimpleAirship} info. If {@code passengers} or {@code hasWeapons} are null
     * its info will not be added.
     * 
     * @return the {@code SimpleAirship} info
     */
    private StringBuilder createInfo() {
    
        StringBuilder strBuiler =
                new StringBuilder( "Flight ID: " ).append( flightId ).append( "\r\nLatitude: " )
                                                  .append( latitude ).append( " Longitude: " )
                                                  .append( longitude ).append( " Altitude: " )
                                                  .append( altitude )
                                                  .append( "\r\nIs Outside The Given Corridor: " );
        
        if( altitude < minAltitude || altitude > maxAltitude )
            strBuiler.append( true );
        else strBuiler.append( false );
        
        if( passengers != null )
            strBuiler.append( "\nNumber of Passengers: " ).append( passengers );
        if( hasWeapons != null )
            strBuiler.append( "\nCarries Weapons: " ).append( hasWeapons );
        return strBuiler;
    }
}
