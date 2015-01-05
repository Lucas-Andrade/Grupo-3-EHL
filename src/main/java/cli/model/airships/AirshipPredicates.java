package main.java.cli.model.airships;

import java.util.function.Predicate;
import main.java.cli.model.airships.Airship;

/**
 * Class whose static inner classes implement {@link Predicate airship predicates}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipPredicates {

	/**
	 * Tests if an instance of {@link Airship} is instance of {@link CivilAirship} and its number of
	 * passengers is below {@code passengerNumber}.
	 * 
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class HasPassagersNumberBelowAThreshold implements Predicate<Airship> {

		// Instance Field

		private final int passengerNumber;

		// Constructor

		public HasPassagersNumberBelowAThreshold(int numberOfPassengers) {

			this.passengerNumber = numberOfPassengers;
		}

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

	/**
	 * Class that tests if an instance of {@link Airship} is transgressing its pre-established air
	 * corridor.
	 * 
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class IsTrangressingItsAirCorridor implements Predicate<Airship> {

		/**
		 * Override of the {@link Predicate#test(Object) test(Object)} method from the
		 * {@link Predicate} Interface.
		 */
		@Override
		public boolean test(Airship airship) {

			return airship.isTransgressing();
		}
	}
}