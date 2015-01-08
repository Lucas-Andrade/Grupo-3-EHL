package main.java.domain.model;


/**
 * Interface that imposes that each instance of the implementing classes have a
 * string identification. It is highly recommended that different elements have
 * different identifications. (E.g. of implementing classes: {@link User} and
 * {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public interface Element
{
	
	/**
	 * Gets the {@code Element}'s identification.
	 */
	public String getIdentification();
	
}
