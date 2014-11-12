package airtrafficcontrol.app.utils;
/**
 * defines an altitude corridor, with a maximum and minimum altitudes
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 *
 */
public class AltitudeCorridor {
	
	private double upperLimit;
	private double lowerLimit;
	
	/**
	 * constructs a new AltitudeCorridor by setting up all its parameters. if the
	 * lower limit is lower than the higher, they are swapped (the lower parameter always
	 * represents the lower limit, and the higher parameter always represents the higher limit)
	 * @param up
	 * @param lo
	 */
	public AltitudeCorridor(double up, double lo)
	{
		if (up > lo)
		{
			upperLimit = up;
			lowerLimit = lo;
		}
		else
		{
			upperLimit = lo;
			lowerLimit = up;
		}
	}

	/**
	 * @return the upper limit
	 */
	public double getUpperLimit()
	{
		return upperLimit;
	}
	
	/**
	 * @return the lower limit 
	 */
	public double getLowerLimit()
	{
		return lowerLimit;
	}
}
