package elements.airships;


import exceptions.InvalidArgumentException;



/**
 * Class whose instances will represent a specific position in space using a geographic coordinate
 * system. Each point is characterized using a latitude, a longitude and an altitude value.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GeographicPosition {
    
    /**
     * The extreme values for each geographic coordinate.
     */
    static final int MIN_ALTITUDE = 0;
    static final int MAX_ALTITUDE = 20000;
    private static final int MIN_LATITUDE = -90;
    private static final int MAX_LATITUDE = 90;
    private static final int MIN_LONGITUDE = 0;
    private static final int MAX_LONGITUDE = 360;

    // Instance Fields
    
    /**
     * {@code latitude} - The latitude of an {@code Airship}.
     */
    private final GeographicCoordinate latitude;
    
    /**
     * {@code longitude} - The longitude of an {@code Airship}.
     */
    private final GeographicCoordinate longitude;
    
    /**
     * {@code atitude} - The atitude of an {@code Airship}.
     */
    private final GeographicCoordinate altitude;
    
    // Constructor
    
    /**
     * Creates a {@code GeographicPosition} making sure that its {@link GeographicCoordinate
     * geographic coordinates}'s values are to be within the correct limits.
     * 
     * @param latitude
     *            - The value for the airships latitude.
     * @param longitude
     *            - The value for the airships longitude.
     * @param altitude
     *            - The value for the airships altitude.
     * 
     * @throw InvalidArgumentException If the values for latitude, longitude and altitude are not
     *        valid.
     */
    public GeographicPosition( double latitude, double longitude, double altitude )
        throws InvalidArgumentException {
    
        try {
            this.latitude = new GeographicCoordinate( latitude, MAX_LATITUDE, MIN_LATITUDE );            
        }
        catch( InvalidArgumentException e ) {
            throw new InvalidArgumentException( e.getMessage() + " for latitude.", e );
        }
        
        try {
            this.longitude = new GeographicCoordinate( longitude, MAX_LONGITUDE, MIN_LONGITUDE );            
        }
        catch( InvalidArgumentException e ) {
            throw new InvalidArgumentException( e.getMessage() + " for longitude.", e );
        }
        
        try {
            this.altitude = new GeographicCoordinate( altitude, MAX_ALTITUDE, MIN_ALTITUDE );            
        }
        catch( InvalidArgumentException e ) {
            throw new InvalidArgumentException( e.getMessage() + " for altitude.", e );
        }
    }
    
    // Overrides
    
    /**
     * Override of the {@link Object#toString() toString()} method from {@link Object}.
     */
    @Override
    public String toString() {
    
        return new StringBuilder( "\r\nLatitude: " ).append( latitude.getValue() )
                                                    .append( " Longitude: " )
                                                    .append( longitude.getValue() )
                                                    .append( " Altitude: " )
                                                    .append( altitude.getValue() ).toString();
    }
    
    // Get Methods
    
    /**
     * @return the {@code latitude} of the {@code GeographicPosition}.
     */
    public GeographicCoordinate getLatitude() {
    
        return latitude;
    }
    
    /**
     * @return the {@code longitude} of the {@code GeographicPosition}.
     */
    public GeographicCoordinate getLongitude() {
    
        return longitude;
    }
    
    /**
     * @return the {@code altitude} of the {@code GeographicPosition}.
     */
    public GeographicCoordinate getAltitude() {
    
        return altitude;
    }
}
