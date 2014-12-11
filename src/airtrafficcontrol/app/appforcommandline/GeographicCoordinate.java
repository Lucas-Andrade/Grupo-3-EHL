package airtrafficcontrol.app.appforcommandline;

public class GeographicCoordinate {

	// Instance Fields

	private final double value;

	// Constructor

	public GeographicCoordinate(double value, double maxValue, double minValue) {

		if (value < minValue || value > maxValue)
			throw new IllegalArgumentException("Given value is not within the valid limits");

		this.value = value;
	}

	// Get Methods

	public double getValue() {

		return value;
	}
}
