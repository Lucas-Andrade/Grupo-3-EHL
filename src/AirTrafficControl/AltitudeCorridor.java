package AirTrafficControl;
/**
 * defines an altitude corridor, with a maximum and minimum altitudes
 * @author Lucas
 *
 */
public class AltitudeCorridor {
	
	private int upperLimit;
	private int lowerLimit;
	
	/**
	 * constructs a new AltitudeCorridor by setting up all its parameters. if the
	 * lower limit is lower than the higher, they are swapped (the lower parameter always
	 * represents the lower limit, and the higher parameter always represents the higher limit)
	 * @param up
	 * @param lo
	 */
	public AltitudeCorridor(int up, int lo)
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
	public int getUpperLimit()
	{
		return upperLimit;
	}
	
	/**
	 * @return the lower limit 
	 */
	public int getLowerLimit()
	{
		return lowerLimit;
	}
}
