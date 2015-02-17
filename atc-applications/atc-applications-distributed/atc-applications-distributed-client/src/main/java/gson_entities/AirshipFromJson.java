package gson_entities;


import com.google.gson.Gson;
import entities.SimpleAirship;


/**
 * 
 * Instances of this class are the bridge between an {@code Airship} in {@code Json} format and a
 * {@link SimpleAirship}, that should be created by the
 * {@link Gson#fromJson(String, AirshipFromJson.Class)} method, and after initialization converted
 * to a {@link SimpleAirship} using the {@link AirshipFromJson#convert()} method.
 * 
 * Because instances of this class should created with {@link Gson} {@code reflection}, this class
 * does not need a constructor to initialize its fields.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipFromJson{
    
    /*
     * Fields needed to create a {@link SimpleAirship}
     */
    private String flightId;
    private CoordinatesFromJson coordinates;
    private AirCorridorFromJson airCorridor;
    private boolean isTransgressing;
    
    
    /*
     * Field used for a "Civil" SimpleAirship, it can be null.
     */
    private Integer passengers;
    
    /*
     * Field used for a "Military" SimpleAirship, it can be null.
     */
    private Boolean hasWeapons;
    
    
    
    // Public method
    /**
     * Create a {@link SimpleAirship} to be used in the Graphics_Componests module (Swing).
     * 
     * @return the converted {@code SimpleAirship}
     */
    public SimpleAirship convert() {
    
        return new SimpleAirship( flightId, coordinates.latitude.value,
                                  coordinates.longitude.value, createInfo() );
    }
    
    
    
    // Private method
    /**
     * Returns the {@code SimpleAirship} info. If {@code passengers} or {@code hasWeapons} are null
     * its info will not be added.
     * 
     * @return the {@code SimpleAirship} info
     */
    private String createInfo() {
    
        StringBuilder strBuiler =
                new StringBuilder( "Flight ID: " ).append( flightId ).append( "\r\n" )
                                                  .append( coordinates.createInfo() )
                                                  .append( "\r\n" )
                                                  .append( "Is Outside The Corridor " )
                                                  .append( "[ " )
                                                  .append( airCorridor.maxAltitude )
                                                  .append( " ] : " )
                                                  .append( isTransgressing ? true : false );


        if( passengers != null )
            strBuiler.append( "\nNumber of Passengers: " ).append( passengers );
        if( hasWeapons != null )
            strBuiler.append( "\nCarries Weapons: " ).append( hasWeapons );
        return strBuiler.toString();
    }
    
}
