package app;


/**
 * This abstract class and all its subclasses represent options from the
 * {@code Options Menu} of this app. When the user chooses one of the options,
 * the app starts executing the corresponding action.
 * <p>
 * This abstract class serves the purpose of establishing which are the common
 * properties and methods of all options from the {@code Options Menu}: all
 * options must have a field {@code title} and a field {@code description}, both
 * accessible for reading, and must have an {@code execute()} method.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>This class is abstract; the options of the {@code Options Menu} are not
 * instances of this class. The decision of promoting the options of the
 * {@code Options Menu} from instances to individual classes enables the
 * implementation of a method {@code public void execute()} for each option (in
 * each class). It also simplifies the process of giving the order to execute
 * one of the options: with this decision, all the implementations of
 * {@code execute()} can now be called by variables of type Option and the
 * problem of choosing which implementation of this method will be used is
 * resolved using dynamic binding.</li>
 * <li>When a class extends {@link Option}, its main feature should be the
 * implementation of the method {@code execute()}. Due to that, it is strongly
 * recommended that all instances store the same string in the field
 * {@code title} (a title that shortly says the objective of this method) and
 * that all instances store the same string in the field {@code description} (a
 * short description of the actions performed by this method and some usage tips
 * about the input-output interaction between the user and the app). One way of
 * doing that is to create only one constructor method, without arguments, that
 * initializes these.</li>
 * <li>Since the fields {@code title} and {@code description} are immutable
 * fields, their visibility is public.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class Option
{
	
	
	
	/**
	 * The title of the option. It is immutable.
	 */
	public final String title;
	
	
	/**
	 * A description of the option. It is immutable. Descriptions will be used
	 * by other options whose purpose is to clarify the user about this option's
	 * utility or usage.
	 */
	public final String description;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Sets up the final values of the fields {@code title} and
	 * {@code description}.
	 * 
	 * @param optionTitle
	 *            The title of the option.
	 * @param optionDescription
	 *            A description of the action performed
	 */
	public Option( String optionTitle, String optionDescription ) {
		this.title = optionTitle;
		this.description = optionDescription;
	}
	
	
	// /**
	// * Returns the class's title.
	// *
	// * @return The class's title.
	// */
	// public abstract String getOptionTitle();
	//
	//
	// /**
	// * Returns the class's description.
	// *
	// * @return The class's description.
	// */
	// public abstract String getOptionDescription();
	
	
	/**
	 * Executes the action corresponding to the option from the
	 * {@code Option Menu} that the implementing class represents.
	 */
	public abstract void execute();
	
}
