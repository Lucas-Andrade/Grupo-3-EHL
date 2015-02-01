package main.java.domain.model.airships;

import java.util.Comparator;

/**
 * Class whose subclasses implement {@link Comparator} whose purpose is to sort an {@link Airship}
 * {@code List}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipComparators {
	
	/**
	 * Static class that implements {@link Comparator} and whose instances compares two given
	 * {@link Airship}'s by their {@link GeographicPosition} in relation to the
	 * {@code GeographicPosition GeographicPositionReference}.
	 * 
	 * The {@link GeographicCoordinate Altitude} is not taken in consideration.
	 * 
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class ComparatorByDistance implements Comparator<Airship> {
		
		private GeographicPosition GeographicPositionReference;
		
		public ComparatorByDistance(GeographicPosition GeographicPositionReference) {
		
			this.GeographicPositionReference = GeographicPositionReference;
		}
		
		/**
		 * Compare the two given {@link Airship}'s by their {@link GeographicPosition} in relation
		 * to the {@code GeographicPositionReference}.
		 * 
		 * The {@link GeographicCoordinate Altitude} is not taken in consideration.
		 * 
		 * @return -1, 0 or 1, when the distance from {@code airship1} to
		 *         {@code GeographicPositionReference} is less than, equal to, or greater than the
		 *         distance from {@code airship2} to {@code GeographicPositionReference}
		 */
		@Override
		public int compare(Airship airship1, Airship airship2) {
		
			double d1 = distanceSquared(GeographicPositionReference, airship1.getCoordinates());
			double d2 = distanceSquared(GeographicPositionReference, airship2.getCoordinates());
			
			return (d1 < d2) ? -1 : (d1 == d2 ? 0 : 1);
		}
		
		/**
		 * Auxiliary private method. The {@link GeographicCoordinate Altitude} is not taken in
		 * consideration.
		 * 
		 * @param gp1
		 * @param gp2
		 * @return the square of the distance from {@code gp1} to {@code gp2}
		 */
		private double distanceSquared(GeographicPosition gp1, GeographicPosition gp2) {
		
			return Math.pow(gp2.getLatitude().getValue() - gp1.getLatitude().getValue(), 2)
				+ Math.pow(gp2.getLongitude().getValue() - gp1.getLongitude().getValue(), 2);
		}
	}
}