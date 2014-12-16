package main.java.model.airships;

/**
 * Class whose instances will represent a specific geografic coordinate that corresponds to one of
 * the geografical coordinates that characterize the {@link GeographicPosition geografic position} of an {@link Airship} is.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GeographicCoordinate {

	// Instance Fields

	/**
	 * {@code value} - The value of the specific geografic coordinate.
	 */
	private final double value;

	// Constructor

	/**
	 * Creates a {@code GeographicCoordinate} whose value as to be whitin certain limits.
	 * 
	 * @param value
	 *            - The value of this specific coordinate.
	 * @param maxValue
	 *            - The maximum valid value for this coordinate.
	 * @param minValue
	 *            - The minimum valid value for this coordinate.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given value in not whitin the valid limits for the minimum and maximum
	 *             altitudes.
	 */
	protected GeographicCoordinate(double value, double maxValue, double minValue) {

		if (value < minValue || value > maxValue)
			throw new IllegalArgumentException("Given value is not within the valid limits");

		this.value = value;
	}

	// Get Methods

	/**
	 * @return the {@code value} of the {@code GeographicCoordinate}.
	 */
	public double getValue() {

		return value;
	}
}