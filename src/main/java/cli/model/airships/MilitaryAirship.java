package main.java.cli.model.airships;


import main.java.cli.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent military airships. This type of airships is
 * distinguished from the others because they have the property of knowing if
 * they carry weapons or not.
 * 
 * Extends {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class MilitaryAirship extends Airship
{
	
	// Instance Fields
	
	/**
	 * {@code hasWeapons} - boolean variable that represents if the airship has
	 * weapons or not.
	 */
	private final boolean hasWeapons;
	
	// Constructor
	
	/**
	 * Class constructor that will create an {@code Military Airship}. It will
	 * receive the geographic coordinates of the airship as a parameter as well
	 * as the maximum and minimum altitudes the airship is allowed to fly in and
	 * a boolean that represents if the airship has weapons or not.
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
	 * @param hasWeapons
	 *            - boolean variable that represents if the airship has weapons
	 *            or not.
	 */
	public MilitaryAirship( double latitude, double longitude, double altitude,
			double maxAltitude, double minAltitude, boolean hasWeapons )
			throws InvalidArgumentException {
		
		super( latitude, longitude, altitude, maxAltitude, minAltitude );
		
		this.hasWeapons = hasWeapons;
	}
	
	// Overrides
	
	/**
	 * Override of the {@link Object#toString() toString()} method from
	 * {@link Object}.
	 */
	@Override
	public String toString() {
		
		return new StringBuilder( super.toString() )
				.append( "\nCarries Weapons: " ).append( hasWeapons )
				.toString();
	}
	
	// Get Methods
	
	/**
	 * @return the boolean {@code hasWeapons}.
	 */
	public boolean hasWeapons() {
		
		return hasWeapons;
	}
}
