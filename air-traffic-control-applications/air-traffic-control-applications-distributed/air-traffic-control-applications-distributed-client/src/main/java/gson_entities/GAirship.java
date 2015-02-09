package gson_entities;


public class GAirship {
    
    private String flightId;
    private double latitude;
    private double longitude;
    private double altitude;
    private double maxAltitude;
    private double minAltitude;
    
    private Integer passengers;
    private Boolean hasWeapons;
    
    private String toString;
    
    public GAirship() {
    
        StringBuilder strBuiler =
                new StringBuilder( "Flight ID: " ).append( flightId ).append( "\r\nLatitude: " )
                                                  .append( latitude ).append( " Longitude: " )
                                                  .append( longitude ).append( " Altitude: " )
                                                  .append( altitude ).append( "\r\nIs Outside The Given Corridor: " );
        
        if( altitude < minAltitude || altitude > maxAltitude )
            strBuiler.append( true );
        else
            strBuiler.append( false );
        
        if( passengers != null )
            strBuiler.append( "\nNumber of Passengers: " ).append( passengers );
        if( hasWeapons != null )
            strBuiler.append( "\nCarries Weapons: " ).append( hasWeapons );
        toString = strBuiler.toString();
    }
    
    public String toString() {
    
        return toString;
    }
}
