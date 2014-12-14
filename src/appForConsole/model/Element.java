package appForConsole.model;

/**
 * Interface tha imposes a contract to be supported by {@code User } and {@code Airship}.
 * 
 * <li>NOTE - Different elements have different identifications.
 * 
 * </br></br>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Element {

	/**
	 * Gets the {@code Element} identification
	 */
	public String getIdentification();
}