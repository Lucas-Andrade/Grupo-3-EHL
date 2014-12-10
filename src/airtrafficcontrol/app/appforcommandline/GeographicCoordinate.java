package airtrafficcontrol.app.appforcommandline;

public class GeographicCoordinate {

	private final double value;

	public GeographicCoordinate(double value, double maxValue, double minValue) {

		if(value < minValue || value > maxValue)
			throw new IllegalArgumentException("Given value is not within the valid limits");
		
		this.value = value;
	}

	public double getValue() {

		return value;
	}
}
