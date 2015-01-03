package main.java.cli.commands.getcommands;

import java.util.concurrent.Callable;

import main.java.cli.Optional;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.GeographicPosition;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
	
	/**
	 * Class whose instance represent commands to get the nearest airships of
	 * the specific geographic coordinate.  
	 * This Class implements {@code Callable<<Iterable<Airship>>>}
	 * 
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public class GetTheNearestAirshipsOfTheGeographicCoordinateCommand implements Callable< Optional< Iterable<Airship> > > {

	// INSTANCE FIELDS
		
	/**
	* The airships database.
	*/
	private final InMemoryAirshipsDatabase airshipsDatabase;
	
	/**
	* The number of airships to get nearest to the geographic coordinates.
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
	 *  Creates a new instance of this command that gets the airships nearest
	 * to the specific geographic coordinate.
	 * 
	 * @param airshipsDatabase
	 * 			 The airships database.
	 * @param airshipsNumber
	 * 			The number of airships to get nearest to the geographic coordinates.
	 * @param latitude
	 * 			The latitude geographic coordinate.
	 * @param longitude
	 * 			The longitude geographic coordinate.
	 * 
	 * @throws InvalidArgumentException 
	 * 			if airshipsDatabase are null or airshipsNumber lower than 0 or latitude it's not 
	 * 			between -90 and 90 and longitude is higher than 360 and lower than 0.
	 *
	 */
	
	public GetTheNearestAirshipsOfTheGeographicCoordinateCommand(InMemoryAirshipsDatabase airshipsDatabase,
									int airshipsNumber, double latitude, double longitude) throws InvalidArgumentException{
		
		if(airshipsDatabase==null)
			throw new InvalidArgumentException("Cannot instantiate command with null airship database.");
		
		if(airshipsNumber < 0)
			throw new InvalidArgumentException("Cannot instantiate command with airship number lower than 0.");
		
		if( !(latitude <= 90 && latitude >= -90) )
			throw new InvalidArgumentException("Cannot instantiate command with latitude higher than 90 and lower than -90.");
		
		if( !(longitude >= 0 && longitude <= 360) )
			throw new InvalidArgumentException("Cannot instantiate command with longitude higher than 360 and lower than 0.");
		
		this.airshipsDatabase = airshipsDatabase;
		this.airshipsNumber = airshipsNumber;
		this.latitude =  latitude; 
		this.longitude = longitude;
	}
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE

	/**
	 * Returns a Iterable of airships thats are nearest to the geographic coordinates
	 * 
	 *  <p>
	 * This result is wrapped in an {@link Optional} instance. If
	 * {@code database} is instance of {@link InMemoryDatabase}, this
	 * {@link Optional}'s method {@link Optional#get()} throws
	 * {@link InternalErrorException} if the result is {@code null} (since this
	 * is never supposed to happen) and whose method {@link Optional#toString()}
	 * returns the string <i>«No such element in {@code getDatabaseName()}»</i>
	 * if the result is an empty list.
	 * </p>
	 * 
	 */
	
	@Override
	public Optional<Iterable<Airship>> call() throws Exception {
		
		return  airshipsDatabase.getAirshipsCloserTo(new GeographicPosition(latitude, longitude, 0), airshipsNumber);
	
	}

		
	
}
