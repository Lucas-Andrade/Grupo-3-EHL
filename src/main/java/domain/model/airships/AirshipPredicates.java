package main.java.domain.model.airships;

import java.util.function.Predicate;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;

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

		// Instance Fields

		private final int numberOfPassengers;

		// Constructor

		public HasPassagersNumberBelowAThreshold(int numberOfPassengers) {

			this.numberOfPassengers = numberOfPassengers;
		}

		// Overrides of Predicate interface

		/**
		 * Override of the {@link Predicate#test(Object) test(Object)} method from the
		 * {@link Predicate} Interface.
		 */
		@Override
		public boolean test(Airship airship) {

			if (airship instanceof CivilAirship)
				return ((CivilAirship) airship).getPassengers() < numberOfPassengers;
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