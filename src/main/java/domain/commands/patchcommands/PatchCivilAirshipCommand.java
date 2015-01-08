package main.java.domain.commands.patchcommands;

import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;

public class PatchCivilAirshipCommand implements Callable<String> {

	private final Database<Airship> airshipDatabase;

	private final String identification;

	private final User user;

	private final double latitude;
	private final double longitude;
	private final double altitude;
	private final double maxAltitude;
	private final double minAltitude;

	private final int passengers;

	public PatchCivilAirshipCommand(Database<Airship> airshipDatabase, String identification,
			User user, double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, int passengers) throws InvalidArgumentException {

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
		this.passengers = passengers;
	}

	@Override
	public String call() throws Exception {

		if (airshipDatabase.removeByIdentification(identification)) {

			Airship airship = CivilAirship.createANewAirshipWithAPreDefinedIdentification(latitude,
					longitude, altitude, maxAltitude, minAltitude, passengers, identification);

			airshipDatabase.add(airship, user);

			return "Airship successfully altered";
		}

		return "Airship does not exist in the database";
	}
}