package airtrafficcontrol.app.appforcommandline.model.airships;

/**
 * Class whose instances will represent a specific position in space where an {@link Airship} is.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GeographicPosition {

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
	 * Creates a {@code GeographicPosition} making sure that the its {@link GeographicCoordinate
	 * geographic coordinates}'s values are to be whitin the correct limits.
	 * 
	 * @param latitude
	 *            - The value for the airships latitude.
	 * @param longitude
	 *            - The value for the airships longitude.
	 * @param altitude
	 *            - The value for the airships altitude.
	 */
	public GeographicPosition(double latitude, double longitude, double altitude) {

		this.latitude = new GeographicCoordinate(latitude, 90, -90);
		this.longitude = new GeographicCoordinate(longitude, 360, 0);
		this.altitude = new GeographicCoordinate(altitude, 20000, 0);
	}

	// Overrides

	/**
	 * Override of the {@link Object#toString() toString()} method from {@link Object}.
	 */
	@Override
	public String toString() {

		return new StringBuilder("\nLatitude: ").append(latitude.getValue()).append(" Longitude: ")
				.append(longitude.getValue()).append(" Altitude: ").append(altitude.getValue())
				.toString();
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
