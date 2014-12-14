package appForConsole.model.airships;

import java.util.function.Predicate;

/**
 * Class whose inner static classes implement {@link Predicate} and each will implement a different
 * criteria.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipPredicates {

	/**
	 * Class whose criteria will verify in a {@link CivilAirship} has less than a specific number of
	 * passengers.
	 */
	public static class IsBelowPassagerNumber implements Predicate<Airship> {

		// Instance Fields

		/**
		 * {@code passengerNumber} - The maximum numbers of passengers a {@code CivilAirship} can
		 * have to verify the criteria represented by the {@link Predicate#test(Object)
		 * test(Object)} method from the {@link Predicate} Interface.
		 */
		private final double passengerNumber;

		// Constructor

		/**
		 * Creates the criteria {@code IsBelowPassagerNumber}.
		 * 
		 * @param passengerNumber
		 */
		public IsBelowPassagerNumber(double passengerNumber) {

			this.passengerNumber = passengerNumber;
		}
		
		// Overrides

		/**
		 * Override of the {@link Predicate#test(Object) test(Object)} method from the
		 * {@link Predicate} Interface.
		 */
		@Override
		public boolean test(Airship airship) {

			if (airship instanceof CivilAirship)
				return ((CivilAirship) airship).getPassengers() < passengerNumber;
			else
				return false;
		}
	}
}