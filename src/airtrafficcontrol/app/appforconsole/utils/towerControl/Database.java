package airtrafficcontrol.app.appforconsole.utils.towerControl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.appforconsole.utils.hangar.AirCraft;
import airtrafficcontrol.app.appforconsole.utils.hangar.Airship;
import airtrafficcontrol.app.appforconsole.utils.hangar.ICivil;


/**
 * Class whose instances are databases of {@link Airship airships}.
 * 
 * <p style="font-size:16">
 * <b>Implementation notes</b>
 * </p>
 * <p>
 * <ul>
 * <li>
 * We privileged the action of accessing an airship from the database, using its
 * flightID, and the feature of not allowing storage of airships with the same
 * flightID; as so, the data structure used is a Map.</li>
 * <li>As no order between the planes is privileged, it's used a HashMap.
 * <li>When needed, an iterator is created to iterate over the HashMap's
 * entries.</li>
 * </ul>
 * </p>
 *
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class Database
{
	
	// CAMPOS
	
	/**
	 * The container of {@link Airship airships}.
	 */
	private Map< String, Airship > database;
	
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new empty database.
	 */
	public Database() {
		database = new HashMap<>();
	}
	
	
	
	// METODOS SOBRE O Map
	
	
	/**
	 * Returns the whole data structure of this instance of Database.
	 * 
	 * @return The whole data structure.
	 */
	public Map< String, Airship > getDatabase() {
		return database;
	}
	
	
	/**
	 * Adds the entries stored in {@code newData} into this.
	 * 
	 * @param newData
	 *            The database whose entries are to be added to this.
	 * @return The message "All airplanes were added successfully." if so; a
	 *         message containing which entries are already part of this and
	 *         were not added again.
	 */
	public String addDatabase( Database newData ) {
		
		Map<String, Airship> newDatabase = newData.getDatabase();
		
		Set< String > idSet = newDatabase.keySet();
		Iterator< String > iterator = idSet.iterator();
		
		String toReturn = "";
		
		while( iterator.hasNext() )
		{
			String id = iterator.next();
			if( !database.containsKey( id ) )
			{
				
				database.put( id, newDatabase.get( id ) );
				// - this and newData share the same data structure, so if this
				// does not allow null keys or values, there's not the risk of
				// newData throwing them
				// - a HashMap accepts null keys and values
			}
			else
			{
				toReturn += "FlightID "
						+ id
						+ " already exists in database.\n  Airship NOT added.\n";
			}
		}
		
		if( toReturn.length() > 0 )
			return toReturn;
		else return "All airplanes were added successfully.";
		
	}
	
	
	// METODOS SOBRE AS ENTRADAS DO Map
	
	
	/**
	 * Adds an airplane to the database, if this does not contain
	 * {@code airplane} yet.
	 * 
	 * @param airship
	 *            The airplane to be added to this.
	 * @return {@code true} if the airplane was successfully added;
	 *         {@code false} if the airplane was not added.
	 * @throws InvalidArgumentException
	 * @throws InvalidFlightIDException
	 */
	public boolean addAirship( Airship airship )
			throws InvalidFlightIDException, InvalidArgumentException {
		
		if( airship == null )
			throw new InvalidArgumentException();
		
		String id = airship.getFlightID();
		if( id == null )
			throw new InvalidFlightIDException();
		
		if( database.containsKey( airship.getFlightID() ) )
		{
			return false;
		}
		else
		{
			database.put( airship.getFlightID(), airship );
			return true;
		}
	}
	
	
	/**
	 * Verifies if the database contains the airplane id
	 * 
	 * @param id
	 *            the airplane id
	 * @return true if the id is on database else returns false
	 */
	public boolean contains( String id ) {
		if( id == null )
			return false;
		return database.containsKey( id );
	}
	
	
	/**
	 * tries to get an airplane with the identification id
	 * 
	 * @param id
	 *            - the flight ID to look for
	 * @return the airplane with the ID passed as parameter
	 * @return null if the airplane was not found
	 */
	public Airship getAirplane( String id ) {
		if( database.containsKey( id ) )
			return database.get( id );
		else return null;
	}
	
	
	/**
	 * Sets the property positionWasUpdated of all the airplanes in the database
	 * to false.
	 */
	public void setAllAirplanesToNotUpdated() {
		
		Set< Map.Entry< String, Airship >> entries = database.entrySet();
		for( Map.Entry< String, Airship > entry : entries )
		{
			entry.getValue().setToNotUpdated();
		}
		
	}
	
	
	/**
	 * Counts the number of airships in the database
	 * 
	 * @return the number of airships in the database
	 */
	public int countAirships() {
		return database.size();
	}
	
	
	/**
	 * Removes the airplane with the specified ID from this instance of
	 * {@link Database}.
	 * 
	 * @param id
	 *            The flightID of the plane we want to remove from this.
	 * @return {@code true} if the airplane was successfully removed;
	 *         {@code false} if the specified ID was not found in the database.
	 */
	public boolean removeAirplane( String id ) throws InvalidFlightIDException {
		
		if( id == null )
			throw new InvalidFlightIDException();
		
		if( database.containsKey( id ) )
		{
			database.remove( id );
			return true;
		}
		else return false;
	}
	
	
	/**
	 * removes the specified airplane from the database
	 * 
	 * @param airplane
	 *            - airplane to remove
	 * @return true if the airplane was successfully removed
	 * @return false if the airplane was not found in the database
	 * @throws InvalidArgumentException
	 */
	public boolean removeAirplane( Airship airplane )
			throws InvalidFlightIDException, InvalidArgumentException {
		
		if( airplane == null )
			throw new InvalidArgumentException();
		
		return removeAirplane( airplane.getFlightID() );
	}
	
	
	/**
	 * Removes from this database the airplanes of the class airliner (or any of
	 * its subclasses) that happen to have 0 passengers.
	 * 
	 * @return The number of airplanes that were removed from the database.
	 */
	public int removeAirplanesWithZeroPassengers() {
		
		int removedCount = 0;
		ArrayList< String > toRemove = new ArrayList<>();
		
		Set< Map.Entry< String, Airship >> entries = database.entrySet();
		for( Map.Entry< String, Airship > entry : entries )
		{
			AirCraft airplane = entry.getValue();
			if( (airplane instanceof ICivil)
					&& ((ICivil) airplane).getPassengersNumber() == 0 )
			{
				toRemove.add( airplane.getFlightID() );
				removedCount++ ;
			}
		}
		
		for( int i = 0; i < toRemove.size(); i++ )
			try
			{
				removeAirplane( toRemove.get( i ) );
			}
			catch( InvalidFlightIDException e )
			{
				e.printStackTrace();
				// this catch block will never happen cause the id introduced in
				// removeAirplane(id) is certainly valid as it came from an
				// existing airship
			}
		
		return removedCount;
		
		
		
		// TENTATIVA 1
		// abandonámos porque o Google nos mostrou q muita gente que é má
		// prática aceder às entradas de um Map iterando o keySet
		
		//
		// Set< String > idSet = database.keySet();
		// Iterator< String > iterator = idSet.iterator();
		// int count = 0;
		// ArrayList< String > toRemove = new ArrayList<>();
		//
		// while( iterator.hasNext() )
		// {
		// Airship airplane = database.get( iterator.next() );
		// if( (airplane instanceof Airliner)
		// && ((Airliner)airplane).getPassengersNumber() == 0 )
		// {
		// String id = airplane.getFlightID();
		// if( id == null )
		// throw new InvalidFlightIDException();
		// toRemove.add( airplane.getFlightID() );
		// count++ ;
		//
		// }
		// }
		//
		// for( int i = 0; i < toRemove.size(); i++ )
		// removeAirplane( toRemove.get( i ) );
		//
		// return count;
	}
	
}