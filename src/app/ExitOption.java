package app;


/**
 * This class represents the option with the title {@code Exit app.} of an Air
 * Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of exiting the EHL's Air
 * Traffic Control app. For more information, read the documentation of method
 * {@link #execute() execute}.
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
public class ExitOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type ExitOption.
	 */
	private static ExitOption instance = new ExitOption();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type ExitOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public ExitOption() {
		super( "Exit app.", "Produces a goodbye message and exits the app." );
	};
	
	/**
	 * Returns an instance of type ExitOption, without creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #ExitOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type ExitOption.
	 */
	public static ExitOption getInstance() {
		return instance;
	}
	
	
	
	// ACÇÃO
	
	
	
	/**
	 * Prints a goodbye message to console.
	 * </p>
	 */
	public void executeToConsole() {
		System.out.println( execute() );
	};
	
	
	
	/**
	 * Produces a goodbye message.
	 * 
	 * @return The goodbye message.
	 */
	public String execute() {
		return "Exiting the Air Traffic Control app..."
				+ "\nThanks for using an EHL app! Bye!";
	}
	
	
}