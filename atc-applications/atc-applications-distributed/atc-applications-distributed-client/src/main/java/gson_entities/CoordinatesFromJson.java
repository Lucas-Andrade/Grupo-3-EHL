package gson_entities;


public class CoordinatesFromJson {

    protected Coordinate latitude;
    protected Coordinate longitude;
    protected Coordinate altitude;
    
    public StringBuilder createInfo() {
        
        return new StringBuilder().append("Latitude: " )
                .append( latitude.value ).append( " Longitude: " )
                .append( longitude.value ).append( " Altitude: " )
                .append( altitude.value );
    }


    protected static class Coordinate {
        
        public double value;
    }

    
}
