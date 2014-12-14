package airtrafficcontrol.app.appforcommandline.model.airships;

public class MilitaryAirship extends Airship {

	// Instance Fields

	private final boolean hasWeapons;

	// Constructor

	public MilitaryAirship(double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, boolean hasWeapons) {

		super(latitude, longitude, altitude, maxAltitude, minAltitude);

		this.hasWeapons = hasWeapons;
	}

	// Overrides

		/**
		 * Override of the {@code toString()} method from {@code Object}.
		 */
		@Override
		public String toString() {

			return new StringBuilder(super.toString()).append("\nCarries Weapons: ")
					.append(hasWeapons).toString();
		}
	
	// Get Methods

	public boolean hasWeapons() {

		return hasWeapons;
	}
}
