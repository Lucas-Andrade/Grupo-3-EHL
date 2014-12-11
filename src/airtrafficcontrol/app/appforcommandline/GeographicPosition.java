package airtrafficcontrol.app.appforcommandline;

public class GeographicPosition {

	private final GeographicCoordinate latitude;
	private final GeographicCoordinate longitude;
	private final GeographicCoordinate altitude;

	public GeographicPosition(double latitude, double longitude, double altitude) {

		this.latitude = new GeographicCoordinate(latitude, -90, 90);
		this.longitude = new GeographicCoordinate(longitude, 0, 360);
		this.altitude = new GeographicCoordinate(altitude, 0, 15000);
	}

	@Override
	public String toString() {

		return new StringBuilder("\nLatitude: ").append(latitude.getValue()).append(" Longitude: ")
				.append(longitude.getValue()).append(" Altitude: ").append(altitude.getValue())
				.toString();
	}

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
