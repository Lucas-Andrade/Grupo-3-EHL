package entities;


/**
 * Class whose instances represent an {@code Airship} to be used (with the minimal resources) in the
 * graphics components.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class SimpleAirship extends Entity {
    
    /**
     * Fields needed to use a {@code SimpleAirship} in the graphics components.
     */
   
    public final double latitude;
    public final double longitude;
    
    /**
     * Create a new {@code SimpleAirship} with a {@code flightId}, {@code latitude} and
     * {@code longitude}. 
     * 
     * @param flightId
     *            - the airship's flightId.
     * @param latitude
     *            - the double value corresponding to airship's latitude.
     * @param longitude
     *            - the double value corresponding to airship's longitude.
     * @param info
     *            - The string with the {@code SimpleAirship} info.
     */
    public SimpleAirship( String flightId, double latitude, double longitude, String info ) {
    
        super( info, flightId );
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
}
