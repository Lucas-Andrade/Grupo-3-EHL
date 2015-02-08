package entities;


public class SimpleAirship extends Entity {
    
    
    public final String flightId;
    public final double latitude;
    public final double longitude;
    
    
    public SimpleAirship( String flightId, double latitude, double longitude, String toString ) {
    
        super( toString );
        this.flightId = flightId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
}
