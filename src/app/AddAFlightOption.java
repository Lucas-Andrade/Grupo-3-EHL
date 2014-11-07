package app;


/**
 * This class represents the option "Add a flight manually." from the
 * {@code Option Menu} of this app. The {@code title} of this option is
 * "Add a flight manually." and {@code description} is a string whose content is
 * the text of the next section.
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
public class AddAFlightOption extends Option
{
	
	
	// AddAFlightOption instance = new AddAFlightOption();
	// instance.title = "Add";
	// instance.description = "";
	
	
	// CONSTRUTOR E getInstance
	
	// /**
	// * The title of this option.
	// */
	// private static String title = "Add a flight manually.";
	//
	// /**
	// * A description of this option. It will be used by options whose purpose
	// is
	// * to clarify the user about this option's utility or usage.
	// */
	// private static String description = "d";
	
	/**
	 * An instance of type AddAFlightOption.
	 */
	private static AddAFlightOption instance = new AddAFlightOption();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type AddAFlightOption and sets up the final
	 * values of the fields {@code title} and {@code description}.
	 */
	public AddAFlightOption() {
		super( "Add a flight manually.", "d" );
	};
	
	/**
	 * Returns an instance of type AddAFlightOption, not necessarily a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * already created instances instead of always creating new ones.
	 * </p>
	 * 
	 * @return A instance of type AddAFlightOption.
	 */
	public AddAFlightOption getInstance() {
		return instance;
	}
	
	// MÉTODOS
	
	
	// /**
	// * Returns the class's title.
	// *
	// * @return The class's title.
	// */
	// public String getOptionTitle() {
	// return title;
	// }
	//
	//
	// /**
	// * Returns the class's description.
	// *
	// * @return The class's description.
	// */
	// public String getOptionDescription() {
	// return description;
	// }
	
	
	
	public void execute() {
		System.out.println( title );
	};
	
}