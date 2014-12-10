package airtrafficcontrol.app.appforcommandline;

import java.util.Map;


/**
 * Interface that represent the {@code AirTrafficControl} databases:
 * <li> {@link UserDatabse}
 * <li> {@link airShipDatabase}
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 */
public interface Database<T extends Element>
{
	/**
	 * Add an element to the database.
	 * 
	 * @param element
	 * @return true if the element is added, false otherwise
	 */
	public boolean add( T element );

	/**
	 * 
	 * @param identification
	 * @return the element whose identification is given
	 */
	public Element get( String identification );


	/**
	 * @return the {@Code database Map} 
	 */
	public Map<String, T> getAll();

}
