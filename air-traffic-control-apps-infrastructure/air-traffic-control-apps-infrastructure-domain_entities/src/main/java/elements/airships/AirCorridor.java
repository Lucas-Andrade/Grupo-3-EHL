package elements.airships;


import exceptions.InvalidArgumentException;



/**
 * Class whose instances will represent the correct flight path an {@code Airship} is allowed to
 * use.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirCorridor {
    
    // Instance Fields
    
    /**
     * {@code maxAltitude} - the maximum altitude an {@code Airship} is allowed to fly.
     */
    private final double maxAltitude;
    
    /**
     * {@code minAltitude} - the minimum altitude an {@code Airship} is allowed to fly.
     */
    private final double minAltitude;
    
    // Constructor
    
    /**
     * AirCorridor constructor that will receive as parameters the altitude limits an
     * {@code Airship} can fly.
     * 
     * @param maxAltitude
     *            - The air corridor's maximum altitude.
     * @param minAltitude
     *            - The air corridor's minimum altitude.
     * @throws InvalidArgumentException
     *             if the {@code minAltitude} is less than 0 or the {@code maxAltitude} is lower
     *             than the {@code minAltitude}.
     */
    public AirCorridor( double maxAltitude, double minAltitude ) throws InvalidArgumentException {
        
        if( minAltitude < 0 )
            throw new InvalidArgumentException(
                                                "Minimum altitude of an air corridor must be greater than 0." );
        
        if( maxAltitude < minAltitude )
            throw new InvalidArgumentException(
                                                "Maximum altitude of an air corridor must be greater than the minimum altitude." );
        
        this.maxAltitude = maxAltitude;
        this.minAltitude = minAltitude;
    }
    
    // Overrides
    
    /**
     * Override of the {@link Object#toString() toString()} method from {@link Object}.
     */
    @Override
    public String toString() {
        
        return new StringBuilder( "\r\nMaximum Altitude Permited: " ).append( maxAltitude )
                                                                   .append( " Minimum Altitude Permited: " )
                                                                   .append( minAltitude )
                                                                   .append( "\r\n" ).toString();
    }
    
    // Get Methods
    
    /**
     * @return returns the {@code maxAltitude}.
     */
    public double getMaxAltitude() {
        
        return maxAltitude;
    }
    
    /**
     * @return returns the {@code minAltitude}.
     */
    public double getMinAltitude() {
        
        return minAltitude;
    }
}
