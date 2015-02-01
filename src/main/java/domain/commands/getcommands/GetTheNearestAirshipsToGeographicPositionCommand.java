package main.java.domain.commands.getcommands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.AirshipComparators;
import main.java.domain.model.airships.GeographicPosition;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances are commands that get the nearest airships of the specific geographic
 * coordinate.
 * 
 * Implements the Interface {@link Callable} of {@link Optional} of {@link Iterable} of
 * {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetTheNearestAirshipsToGeographicPositionCommand implements
	Callable<Optional<Iterable<Airship>>> {
	
	// INSTANCE FIELDS
	
	/**
	 * The airships database.
	 */
	private final Database<Airship> airshipsDatabase;
	
	/**
	 * The number of airships to get that are nearest to the geographic coordinates.
	 */
	private final int airshipsNumber;
	
	/**
	 * The latitude geographic coordinate.
	 */
	private final double latitude;
	
	/**
	 * The longitude geographic coordinate.
	 */
	private final double longitude;
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new instance of this command that gets a specific number of airships (specified by
	 * {@link #airshipsNumber}) that are nearest to the specific geographic coordinate.
	 * 
	 * @param airshipsDatabase
	 *            The airships database.
	 * @param airshipsNumber
	 *            The number of airships to get nearest to the geographic coordinates.
	 * @param latitude
	 *            The latitude geographic coordinate.
	 * @param longitude
	 *            The longitude geographic coordinate.
	 * 
	 * @throws InvalidArgumentException
	 *             if the {@code airshipsDatabase} is null or airshipsNumber lower than 0 or
	 *             latitude it's not between -90 and 90 and longitude it's not between 360 and 0.
	 */
	public GetTheNearestAirshipsToGeographicPositionCommand(
		Database<Airship> airshipsDatabase, int airshipsNumber, double latitude,
		double longitude) throws InvalidArgumentException {
	
		if (airshipsDatabase == null)
			throw new InvalidArgumentException(
				"Cannot instantiate command with null airship database.");
		
		this.airshipsDatabase = airshipsDatabase;
		this.airshipsNumber = airshipsNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	
	/**
	 * Returns a Iterable of airships thats are nearest to the geographic coordinates.
	 * 
	 * ********************************************** WAS ASK US TO SEARCH FOR TE NEAREST AIRSHIPS
	 * TO A GEOGRAPHIC POSITION GIVING ONLY HE LATITUDE AND LONGITUDE FIELDS. BUT THE GEOGRAPHIC
	 * POSITION NEEDS ALSO AN ALTITUDE FIELD, SO IT WAS CONSIDERED THAT ALTITUDE IS ZERO.
	 * **********************************************
	 * 
	 * <p>
	 * This result is wrapped in an {@link Optional} instance. If {@code database} is instance of
	 * {@link InMemoryDatabase}, this {@link Optional}'s method {@link Optional#get()} throws
	 * {@link InternalErrorException} if the result is {@code null} (since this is never supposed to
	 * happen) and whose method {@link Optional#toString()} returns the string <i>«No such element
	 * in {@code getDatabaseName()}»</i> if the result is an empty list.
	 * </p>
	 * 
	 */
	@Override
	public Optional<Iterable<Airship>> call() throws Exception {
	
		if (airshipsNumber < 0)
			throw new InvalidArgumentException("Number of airships cannot be negative.");
		
		Iterable<Airship> elementsList = airshipsDatabase.sortBy(
			new AirshipComparators.ComparatorByDistance(new GeographicPosition(latitude, longitude,
				0))).get();
		
		List<Airship> finalList = new ArrayList<Airship>();
		
		long counter = 0;
		
		for (Airship airship : elementsList)
			if (counter < airshipsNumber) {
				
				finalList.add(airship);
				counter++;
			}
		
		return new Optional<Iterable<Airship>>(finalList, airshipsDatabase.getDatabaseName()
			+ " is empty.");
	}
}