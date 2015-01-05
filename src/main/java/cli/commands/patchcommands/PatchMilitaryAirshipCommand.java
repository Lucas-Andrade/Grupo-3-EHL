package main.java.cli.commands.patchcommands;

import java.util.concurrent.Callable;

import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;

public class PatchMilitaryAirshipCommand implements Callable<String> {

	private final Database<Airship> airshipDatabase;
	private final String identification;

	private final User user;

	private final double latitude;
	private final double longitude;
	private final double altitude;
	private final double maxAltitude;
	private final double minAltitude;

	private final boolean hasWeapons;

	public PatchMilitaryAirshipCommand(Database<Airship> airshipDatabase, String identification,
			User user, double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, boolean hasWeapons) throws InvalidArgumentException {

		if (airshipDatabase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		if (identification == null)
			throw new InvalidArgumentException(
					"Cannot instantiate command with null identification.");

		if (user == null)
			throw new InvalidArgumentException();

		this.airshipDatabase = airshipDatabase;
		this.identification = identification;
		this.user = user;

		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.maxAltitude = maxAltitude;
		this.minAltitude = minAltitude;
		this.hasWeapons = hasWeapons;
	}

	@Override
	public String call() throws Exception {

		if (airshipDatabase.removeByIdentification(identification)) {

			Airship airship = new MilitaryAirship(latitude, longitude, altitude, maxAltitude,
					minAltitude, hasWeapons);

			airshipDatabase.add(airship, user);

			return "Airship successfully altered";
		}

		return "Airship does not exist in the database";
	}
}