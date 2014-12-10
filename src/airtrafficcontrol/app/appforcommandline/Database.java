package airtrafficcontrol.app.appforcommandline;


import java.util.Map;


/**
 * Interface whose instances represent databases of elements that have an
 * identification.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 *            The type of the elements stored in the database.
 */
public interface Database< T extends Element >
{
	
	/**
	 * Stores the {@link Element element} {@code element} in this database,
	 * added by the {@link User user} {@code user}.
	 * 
	 * @param element
	 * @return true if the element is added, false otherwise
	 */
	public boolean add( T element, User user );
	
	/**
	 * Returns the element of this database whose identification is
	 * {@code identification}.
	 * 
	 * @param identification
	 *            The element's identification.
	 * @return The element of this database whose identification is
	 *         {@code identification}; </br> {@code null} if there's no such
	 *         element in this database.
	 */
	public Element get( String identification );
	
	/**
	 * Returns a map whose values are the elements stored in this database and
	 * whose keys are the corresponding identifications.
	 */
	public Map< String, T > getAll();
}
