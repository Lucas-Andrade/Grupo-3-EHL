package airtrafficcontrol.app.utils;


import airtrafficcontrol.app.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent an altitude air corridor, with a maximum and
 * minimum values of altitude.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AltitudeCorridor
{
	
	// CAMPOS
	
	/**
	 * The maximum value of altitude of this corridor.
	 */
	private double upperLimit;
	
	/**
	 * The minimum value of altitude of this corridor.
	 */
	private double lowerLimit;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates a new instance of {@link AltitudeCorridor} by setting up all its
	 * parameters. If the {@code lo} is bigger than the {@code up}, their values
	 * are swapped (the lower parameter always represents the lower limit, and
	 * the higher parameter always represents the higher limit of this air
	 * corridor).
	 * 
	 * @param upperLimit
	 *            The maximum value of altitude of this corridor.
	 * @param lowerLimit
	 *            The minimum value of altitude of this corridor.
	 * @throws InvalidArgumentException
	 *             If one of the parameters goes below the minimum altitude
	 *             //TODO value allowed for an altitude value.
	 */
	public AltitudeCorridor( double upperLimit, double lowerLimit )
			throws InvalidArgumentException {
		
		if( upperLimit > lowerLimit )
		{
			this.upperLimit = upperLimit;
			this.lowerLimit = lowerLimit;
		}
		else
		{
			this.upperLimit = lowerLimit;
			this.lowerLimit = upperLimit;
		}
		
		if(this.lowerLimit < 0)
			throw new InvalidArgumentException();
	}
	
	
	
	// GETTER's
	
	
	/**
	 * Returns the upper limit of altitude of this AirCorridor.
	 * 
	 * @return The upper limit.
	 */
	public double getUpperLimit() {
		return upperLimit;
	}
	
	/**
	 * Returns the lower limit of altitude of this AirCorridor.
	 * 
	 * @return The lower limit.
	 */
	public double getLowerLimit() {
		return lowerLimit;
	}
}
