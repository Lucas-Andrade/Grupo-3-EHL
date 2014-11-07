package app;


/**
 * This class represents the option
 * "Remove all passenger-flights with zero passengers." from the
 * {@code Option Menu} of this app. The {@code title} of this option is
 * "Remove all passenger-flights with zero passengers." and {@code description}
 * is a string whose content is the text of the next section.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * By choosing this option, //TODO
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>The only instance of this class is the static and final field
 * {@code instance}, which is accessible only with the purposes of allowing
 * other classes to store an instance of this type in data structures and
 * calling the method {@code execute()}.</br> There is no utility in having more
 * instances of this type since they would have no differentiating properties:
 * all fields of this class are static and final.</li>
 * <li>This class's method {@code execute()} is not static so that calls of the
 * method {@code execute()} using variables of type {@link Option} can be
 * resolved using dynamic binding.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class RemoveEmptyAirshipsOption implements Option
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The title of this option.
	 */
	private static String title = "Remove all passenger-flights with zero passengers.";
	
	/**
	 * A description of this option. It will be used by options whose purpose is
	 * to clarify the user about this option's utility or usage.
	 */
	private static String description = "d";
	
	/**
	 * The only instance of this class's type.
	 */
	public static final RemoveEmptyAirshipsOption instance = new RemoveEmptyAirshipsOption();
	
	
	
	// MÉTODO CONSTRUTOR
	
	
	
	/**
	 * Private constructor.
	 */
	private RemoveEmptyAirshipsOption() {};
	
	
	
	// MÉTODOS
	
	
	/**
	 * Returns the class's title.
	 * 
	 * @return The class's title.
	 */
	public String getOptionTitle() {
		return title;
	}
	
	
	/**
	 * Returns the class's description.
	 * 
	 * @return The class's description.
	 */
	public String getOptionDescription() {
		return description;
	}
	
	
	
	public void execute() {
		System.out.println( getOptionTitle() );
	};
	
}