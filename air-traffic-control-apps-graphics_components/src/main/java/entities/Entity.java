package entities;


/**
 * Class whose instances represent an {@code Entity} to be used (with the minimal resources) in the
 * graphics components.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class Entity {
    
    /**
     * The string with the {@code Entity} info
     */
    public final String toString;
    
    /**
     * Create a new {@code Entity} with a String-info Field.
     * 
     * @param toString
     *            The string with the {@code Entity} info.
     */
    public Entity( String toString ) {
    
        this.toString = toString;
    }
    
    /**
     * Return the {@code Entity} info.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return toString;
    }
}
