package airtrafficcontrol.app.appforcommandline.model.airships;

public class GeographicPosition {

	// Instance Fields

	private final GeographicCoordinate latitude;
	private final GeographicCoordinate longitude;
	private final GeographicCoordinate altitude;

	// Constructor

	public GeographicPosition(double latitude, double longitude, double altitude) {

		this.latitude = new GeographicCoordinate(latitude, 90, -90);
		this.longitude = new GeographicCoordinate(longitude,  360, 0);
		this.altitude = new GeographicCoordinate(altitude, 20000, 0);
	}

	// Overrides

	@Override
	public String toString() {

		return new StringBuilder("\nLatitude: ").append(latitude.getValue()).append(" Longitude: ")
				.append(longitude.getValue()).append(" Altitude: ").append(altitude.getValue())
				.toString();
	}

	// Get Methods

	public GeographicCoordinate getLatitude() {

		return latitude;
	}

	public GeographicCoordinate getLongitude() {

		return longitude;
	}

	public GeographicCoordinate getAltitude() {

		return altitude;
	}
}
