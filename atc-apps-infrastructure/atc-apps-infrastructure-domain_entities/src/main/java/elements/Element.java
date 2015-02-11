package elements;



/**
 * Interface that imposes that each instance of the implementing classes have a string
 * identification. It is highly recommended that different elements have different identifications.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@FunctionalInterface
public interface Element {
    
    /**
     * Returns the identification of this {@link Element}.
     * 
     * @return The identification of this {@link Element}.
     */
    public String getIdentification();
}
