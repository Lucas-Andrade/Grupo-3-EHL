package main.java.cli.model.airships;


import main.java.cli.exceptions.InvalidArgumentException;


/**
 * Class whose instances will represent a civil airship. This type of airships
 * is distinguished from the others because they have a number of passengers the
 * airship has.
 * 
 * Extends {@link Airship}.
 */
public class CivilAirship extends Airship
{
	
	// Instance Fields
	
	/**
	 * {@code passengers} - int variable that represents the number of
	 * passengers the airship has.
	 */
	private final int passengers;
	
	// Constructor
	
	/**
	 * Class constructor that will create an {@code Civil Airship}. It will receive the geographic
	 * coordinates of the airship as a parameter as well as the maximum and minimum altitudes the
	 * airship is allowed to fly in and the number os passengers it has.
	 * 
	 * @param latitude
	 *            - the double value corresponding to airship's latitude.
	 * @param longitude
	 *            - the double value corresponding to airship's longitude.
	 * @param altitude
	 *            - the double value corresponding to airship's altitude.
	 * @param maxAltitude
	 *            - maximum altitude the airship is allowed to fly.
	 * @param minAltitude
	 *            - minimum altitude the airship is allowed to fly.
	 * @param passengers
	 *            - the int value that represents the numbers of passengers the airship has.
	 * 
	 * @throws InvalidArgumentException
	 *             If some given arguments have invalid values.
	 */
	public CivilAirship( double latitude, double longitude, double altitude,
			double maxAltitude, double minAltitude, int passengers )
			throws InvalidArgumentException {
		
		super( latitude, longitude, altitude, maxAltitude, minAltitude );
		
		if( passengers < 0 )
			throw new InvalidArgumentException(
					"The number of passengers cannot be less than 0." );
		
		this.passengers = passengers;
	}
	
	// Overrides
	
	/**
	 * Override of the {@code toString()} method from {@code Object}.
	 */
	@Override
	public String toString() {
		
		return new StringBuilder( super.toString() )
				.append( "\nNumber of Passengers: " ).append( passengers )
				.toString();
	}
	
	// Get Methods
	/**
	 * @return the {@code passengers}.
	 */
	public int getPassengers() {
		
		return passengers;
	}
}
