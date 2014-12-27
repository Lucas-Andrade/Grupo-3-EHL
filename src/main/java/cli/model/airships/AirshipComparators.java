package main.java.cli.model.airships;

import java.util.Comparator;

/**
 * Class whose subclasses implements {@link Comparator}, with the point to sort
 * an {@link Airship} {@code List}. 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipComparators
{
	/**
	 * Class that implements {@link Comparator} and whose instances  
	 * 
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class ComparatorByDistance implements Comparator<Airship>
	{
		private GeographicPosition gp;

		public ComparatorByDistance( GeographicPosition gp )
		{
			this.gp = gp;
		}

		@Override
		public int compare( Airship airship1, Airship airship2 )
		{
			double d1 = distanceSquared( gp, airship1.getCoordinates() );
			double d2 = distanceSquared( gp, airship2.getCoordinates() );
			return ( d1 < d2 ) ? - 1 : ( d1 == d2 ? 0 : 1 );
		}


		/**
		 * by G TODO
		 * 
		 * @param gp1
		 * @return
		 */
		private double distanceSquared( GeographicPosition gp1,
				GeographicPosition gp2 )
		{
			return Math.pow( gp2.getLatitude().getValue()
					- gp1.getLatitude().getValue(), 2 )
					+ Math.pow( gp2.getLongitude().getValue()
							- gp1.getLongitude().getValue(), 2 );
		}
	}
}
