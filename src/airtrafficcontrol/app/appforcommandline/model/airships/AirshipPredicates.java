package airtrafficcontrol.app.appforcommandline.model.airships;

import java.util.function.Predicate;

/**
 * Class which static subclasses implement {@link Predicate}
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipPredicates
{
	/**
	 * Predicate for {@link CivilAirship} 
	 * 
	 *
	 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class IsBelowPassagerNumber implements Predicate<Airship>
	{
		private final double passengerNumber;

		public IsBelowPassagerNumber( double passengerNumber )
		{
			this.passengerNumber = passengerNumber;
		}

		@Override
		public boolean test( Airship airship )
		{
			if( airship instanceof CivilAirship)
				return ((CivilAirship)airship).getPassengers() > passengerNumber;
			else
				return false;
		}
	}
}
