package app;


/**
 * This class represents the option with the title {@code Help!}.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of printing a detailed
 * description of the options available in the menu to the console. For more
 * information, read the documentation of method {@link #execute() execute}.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Instances of this class are immutable.</li>
 * <li>All instances of this class share the same {@code title} and
 * {@code description}.
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class HelpOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type HelpOption.
	 */
	private static HelpOption instance = new HelpOption();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type HelpOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public HelpOption() {
		super( "Help!", "d" );
	};
	
	/**
	 * Returns an instance of type HelpOption, without creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #HelpOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type HelpOption.
	 */
	public static HelpOption getInstance() {
		return instance;
	}
	
	
	
	// execute()
	
	/**
	 * Prints a detailed description of the options available in the menu to the
	 * console.
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	public void execute() {
		System.out.println( title );
	};
	
}