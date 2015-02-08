package entities;


public class Entity {
    
    
    public final String toString;
    
    
    public Entity( String toString ) {
    
        this.toString = toString;
    }
    
    
    @Override
    public String toString() {
    
        return toString;
    }
    
}
