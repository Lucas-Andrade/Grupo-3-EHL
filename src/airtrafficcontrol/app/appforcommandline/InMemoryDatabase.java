package airtrafficcontrol.app.appforcommandline;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Abstract class whose instances represent in-memory databases of elements that
 * have an identification. An in-memory database exists only during the
 * runtime
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 *            The type of the elements stored in the database.
 */
public abstract class InMemoryDatabase< T extends Element > implements
		Database< T >
{
	
	/**
	 * The container of elements. In this {@link Map}, the keys are the
	 * elements' identifications (obtained via method
	 * {@link Element#getIdentification()} and the values are the elements.
	 */
	private Map< String, T > database;
	
	/**
	 * Sets an empty database.
	 */
	public InMemoryDatabase() {
		this.database = new HashMap< String, T >();
	}
	
	/**
	 * Stores the {@link Element element} {@code element} in this database,
	 * added by the {@link User user} {@code user}.
	 * 
	 * @param element
	 *            The element to be added to this database.
	 * @param user
	 *            The user who added this element to the database.
	 * @return {@code true} if the element was successfully added;</br>
	 *         {@code false} otherwise.
	 */
	@Override
	public boolean add( T element, User user ) {
		// if( element == null )
		// // TODO
		
		if( database.containsKey( element.getIdentification() ) )
			return false;
		database.put( element.getIdentification(), element );
		return true;
	}
	
	/**
	 * Returns the element of this database whose identification is
	 * {@code identification} (uses method {@link Element#getIdentification()}).
	 * 
	 * @param identification
	 *            The element's identification.
	 * @return The element of this database whose identification is
	 *         {@code identification}; </br> {@code null} if there's no such
	 *         element in this database.
	 */
	@Override
	public T get( String identification ) {
		return database.get( identification );
	}
	
	/**
	 * Returns a map whose values are the elements stored in this database and
	 * whose keys are the corresponding identifications (obtained by the method
	 * {@link Element#getIdentification()}).This map is "read-only"; attempts to
	 * modify the returned map, whether direct or via its collection views,
	 * result in an {@link UnsupportedOperationException}. e
	 * 
	 * @return A "read-only" view of this database.
	 */
	@Override
	public Map< String, T > getAll() {
		return Collections.unmodifiableMap( database );
	}
}
