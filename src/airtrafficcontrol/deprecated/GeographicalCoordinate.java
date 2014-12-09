package airtrafficcontrol.deprecated;


import airtrafficcontrol.app.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent a latitude, a longitude or a altitude's
 * value.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class GeographicalCoordinate
{
	
	
	// CAMPOS
	
	/**
	 * The geographical coordinate's value.
	 */
	private double value;
	
	/**
	 * The geographical coordinate's maximum value.
	 */
	public final double MAX_VALUE;
	
	/**
	 * The geographical coordinate's minimum value.
	 */
	public final double MIN_VALUE;
	
	
	// CONSTRUTOR
	
	/**
	 * Constructs an instance of {@link GeographicalCoordinate} with maximum
	 * value {@code MAX_VALUE} and minimum value {@code MIN_VALUE}.
	 * 
	 * @param value
	 *            The value of this geographical coordinate.
	 * @param MAX_VALUE
	 *            The maximum value for this geographical coordinate.
	 * @param MIN_VALUE
	 *            The minimum value for this geographical coordinate.
	 * @throws InvalidArgumentException
	 *             If {@code MAX_LATITUDE < MIN_LATITUDE}.
	 */
	public GeographicalCoordinate( double value, double MAX_VALUE,
			double MIN_VALUE ) throws InvalidArgumentException {
		
		if( MAX_VALUE < MIN_VALUE )
			throw new InvalidArgumentException(
					"INVALID GEOGRAPHICAL COORDINATE MAX AND MIN VALUES!" );
		if( MAX_VALUE < value || value < MIN_VALUE )
			throw new InvalidArgumentException(
					"INVALID GEOGRAPHICAL COORDINATE VALUE, EXCEEDS LIMITS!" );
		
		this.value = value;
		this.MAX_VALUE = MAX_VALUE;
		this.MIN_VALUE = MIN_VALUE;
	}
	
	
	
	// METODOS
	
	
	/**
	 * Returns the geographical coordinate's value.
	 * 
	 * @return The geographical coordinate's value.
	 */
	public double getValue() {
		return value;
	}
	
	
	/**
	 * Increments the value of the current latitude with the value
	 * {@code increment}.
	 * 
	 * @param increment
	 *            The value with which to increment the current geographical
	 *            coordinate's value.
	 * @throws InvalidArgumentException
	 *             If {@code latitude+lat>MAX_LATITUDE} or
	 *             {@code latitude+lat<MIN_LATITUDE}.
	 */
	public void incrementGeographicalCoordinate( double increment )
			throws InvalidArgumentException {
		
		if( value + increment > MAX_VALUE || value + increment > MAX_VALUE )
			throw new InvalidArgumentException(
					"INVALID INCREMENT VALUE FOR THE CURRENT GEOGRAPHIC COORDINATE!" );
		
		this.value += increment;
	}
	
	
	/**
	 * Sets a new value for the geographical coordinate.
	 * 
	 * @param val
	 *            The new value.
	 * @throws InvalidArgumentException
	 *             If {@code val > MAX_VALUE} or {@code val < MIN_VALUE}.
	 */
	public void setGeographicalCoordinate( double val ) throws InvalidArgumentException {
		
		if( val <= MAX_VALUE && val >= MIN_VALUE )
			this.value = val;
		
		else throw new InvalidArgumentException(
				"INVALID GEOGRAPHICAL COORDINATE VALUE!" );
		
	}
}
