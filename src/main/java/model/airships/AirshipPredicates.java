package main.java.model.airships;

import java.util.function.Predicate;

/**
 * Class whose inner static classes implement {@link Predicate}, for the
 * respective criteria.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipPredicates
{
	/**
	 * Class whose criteria will verify in a {@link CivilAirship} has less than
	 * a specific number of passengers.
	 */
	public static class HasPassagersNumberBelowAThreshold implements
			Predicate<Airship>
	{
		/**
		 * {@code passengerNumber} - The threshold number of passengers.
		 * {@link CivilAirship}s with passengers below this threshold, must
		 * return {@code true} in {@link Predicate#test(Object)} method.
		 */
		private final double passengerNumber;

		/**
		 * Creates the {@code Predicate} HasPassagersNumberBelowAThreshold}.
		 * 
		 * @param passengerThreshold
		 */
		public HasPassagersNumberBelowAThreshold( double passengerThreshold )
		{

			this.passengerNumber = passengerThreshold;
		}

		/**
		 * Override of the {@link Predicate#test(Object) test(Object)} method
		 * from the {@link Predicate} Interface.
		 */
		@Override
		public boolean test( Airship airship )
		{
			if( airship instanceof CivilAirship )
				return ( ( CivilAirship )airship ).getPassengers() < passengerNumber;
			else
				return false;
		}
	}
}
