package airtrafficcontrol.app.appforcommandline.model.airships;

import airtrafficcontrol.app.appforcommandline.model.Element;

/**
 * Abstract class whose instances will represent an Airship in a specific flight moment.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class Airship implements Element {

	// Instance Fields

	/**
	 * {@code ID} - long static variable that will contain the numeric value of the last atributed
	 * ID. This variable will be updated every time a new {@code Airship} is created.
	 */
	private static long ID = 0;

	/**
	 * {@code flightID} - String representation of the {@code Airship}'s ID.
	 */
	private final String flightId;

	/**
	 * {@code coordinates} - will represent the geographic coordinates of the {@code Airship}.
	 */
	private final GeographicPosition coordinates;

	/**
	 * {@code airCorridor} - the {@link AirCorridor altitude corridor} an {@code Airship} is allowed
	 * to fly in.
	 */
	private final AirCorridor airCorridor;

	/**
	 * {@code isTransgressing} - boolean variable that will represent the status of an
	 * {@code Airship} regarding the impossition of its {@code airCorridor}.
	 */
	private final boolean isTransgressing;

	// Constructor

	/**
	 * Class constructor that will be used by the {@code Airship} subclasses in order to instatiate
	 * the fields that are common to all the airships. It will receive the geographic coordinates of
	 * the airship as a parameter as well as the maximum and minimum altitudes the airship is
	 * allowed to fly in.</br></br>
	 * 
	 * The constructor will allocate a flightID to the airship that will be automatically generated
	 * using the method {@code flightIDGenerator()}.</br></br>
	 * 
	 * If the airship's current altitude is not within the valid interval the blooean variable
	 * {@code isTransgressing} will be initiated with true.
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
	 */
	public Airship(double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude) {

		coordinates = new GeographicPosition(latitude, longitude, altitude);
		airCorridor = new AirCorridor(maxAltitude, minAltitude);
		flightId = flightIDGenerator();

		if (altitude < minAltitude || altitude > maxAltitude)
			isTransgressing = true;
		else
			isTransgressing = false;
	}

	// Private Methods

	/**
	 * Method that will be used by the class constructor to allocate a different ID to all the
	 * instances of {@code Airship}.
	 * 
	 * @return a string representing the airship's flightID.
	 */
	private static String flightIDGenerator() {

		return Long.toString(++ID);
	}

	// Overrides

	/**
	 * Override of the {@link Object#toString() toString()} method from {@link Object}.
	 */
	@Override
	public String toString() {

		return new StringBuilder("Flight ID: ").append(flightId).append(coordinates)
				.append(airCorridor).append("\nIs Outside The Given Corridor: ")
				.append(isTransgressing).toString();
	}

	// Get Methods

	/**
	 * @return the {@code flightId}.
	 */
	@Override
	public String getIdentification() {

		return flightId;
	}

	/**
	 * @return the {@code coordinates}.
	 */
	public GeographicPosition getCoordinates() {

		return coordinates;
	}

	/**
	 * @return the {@code airCorridor}.
	 */
	public AirCorridor getAirCorridor() {

		return airCorridor;
	}

	/**
	 * @return the boolean {@code isTransgressing}.
	 */
	public boolean isTransgressing() {

		return isTransgressing;
	}
}