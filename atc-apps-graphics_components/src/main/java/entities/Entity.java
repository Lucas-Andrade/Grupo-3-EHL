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
    
    private final String identification;

    /**
     * Create a new {@code Entity} with a String-info Field.
     * 
     * @param toString
     *            The string with the {@code Entity} info.
     */
    public Entity( String identification, String toString ) {
    
        this.identification = identification;
        this.toString = toString;
    }
    
    
    /**
     * @return the identification
     */
    public String getIdentification() {
    
        return identification;
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
