package app;


/**
 * This abstract class and all its subclasses represent options from the
 * {@code Option Menu} of this app.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public interface Option
{
	
	/**
	 * Returns the class's title.
	 * 
	 * @return The class's title.
	 */
	public abstract String getOptionTitle();
	
	
	/**
	 * Returns the class's description.
	 * 
	 * @return The class's description.
	 */
	public abstract String getOptionDescription();
	
	
	/**
	 * Executes the action corresponding to the option from the
	 * {@code Option Menu} that the implementing class represents.
	 */
	public abstract void execute();
	
}
