package main.java.domain.model.airships;

import java.text.MessageFormat;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances will represent a specific geografic coordinate that corresponds to one of
 * the geografical coordinates that characterize the {@link GeographicPosition geografic position}
 * of an {@link Airship} is.
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
	 * Creates a {@code GeographicCoordinate} whose value are to be whitin certain limits.
	 * 
	 * @param value
	 *            - The value of this specific coordinate.
	 * @param maxValue
	 *            - The maximum valid value for this coordinate.
	 * @param minValue
	 *            - The minimum valid value for this coordinate.
	 * 
	 * @throws InvalidArgumentException
	 *             If {@code value} is not within the valid limits for the minimum and maximum
	 *             altitudes.
	 */
	public GeographicCoordinate(double value, double maxValue, double minValue)
			throws InvalidArgumentException {

		if (value < minValue)
			throw new InvalidArgumentException(MessageFormat.format("Invalid value '{0}'"
					+ " is smaller than minimum value allowed ('{1}')", value, minValue));

		if (value > maxValue)
			throw new InvalidArgumentException(MessageFormat.format("Invalid value '{0}'"
					+ " is greater than maximum value allowed ('{1}')", value, maxValue));

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
