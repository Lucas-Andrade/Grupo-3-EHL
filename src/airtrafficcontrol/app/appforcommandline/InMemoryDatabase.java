package airtrafficcontrol.app.appforcommandline;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;

/**
 * Abstract class that represent the {@code AirTrafficControl} databases: <li>
 * {@link UserDatabse} <li> {@link airShipDatabase}
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 */
public abstract class InMemoryDatabase<T extends Element> implements
		Database<Element>
{
	/**
	 * {@code database Map} where key is the element identification and the
	 * value is the element
	 */
	private Map<String, T> database;
	
	public InMemoryDatabase()
	{
		this.database = new HashMap<String, T>();
	}

	/**
	 * Add an element to the database.
	 * 
	 * @param element
	 * @return true if the element is added, false otherwise
	 */
	@Override
	public boolean add( Element element )
	{
//		if( element == null )
//		// TODO

		if( database.containsKey( element.getIdentification ) )
			return false;
		database.put( element.getIdentification(), element );
		return true;
	}

	/**
	 * 
	 * @param identification
	 * @return the element whose identification is given
	 */
	@Override
	public Element get( String identification )
	{
		return database.get( identification );
	}

	/**
	 * @return the {@Code database Map}
	 */
	@Override
	public Map<String, Element> getAll()
	{
		return Collections.unmodifiableMap( database );
	}
}
