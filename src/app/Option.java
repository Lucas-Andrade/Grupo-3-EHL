package app;


/**
 * This abstract class and all its subclasses represent options from an options
 * menu.
 * <p>
 * This abstract class serves the purpose of establishing which are the common
 * properties and methods of all options from the {@code Options Menu}: all
 * options must have a final field {@link #title} and a final field
 * {@link #description}, both accessible for reading, and must have an
 * {@link #execute() execute} and an {@link #executeToConsole()
 * executeToConsole} methods.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>When a class extends {@link Option}, its main feature should be the
 * implementation of the method {@link #execute() execute}. Due to that, it is
 * strongly recommended that all instances store the same string in the field
 * {@link #title} (a title that shortly says the objective of this method) and
 * that all instances store the same string in the field {@link #description} (a
 * short description of the actions performed by this method and some usage tips
 * about the input-output interaction between the user and the app). One way of
 * doing that is to create only one constructor method, without arguments, that
 * initializes these.</li>
 * <li>Since the fields {@link #title} and {@link #description} are final
 * fields, their visibility is public.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class Option
{
	
	// CAMPOS
	
	/**
	 * The title of the option. It is immutable.
	 */
	public final String title;
	
	/**
	 * A description of the option. It is immutable. Describes the action
	 * performed by the method {@link #execute() execute}.
	 */
	public final String description;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Sets up the final values of the fields {@link #title} and
	 * {@link #description}.
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
	
	
	
	// EXECUTORES
	
	
	
	/**
	 * Manages input data needed to perform the action corresponding to the
	 * option that the class who implemented this method represents and prints
	 * its output.
	 */
	public abstract void executeToConsole();
	
	
	/**
	 * Performs the action corresponding to the option that the class who
	 * implemented this method represents.
	 * 
	 * @return A string with output from the action.
	 */
//	 * @throws InvalidOptionException
//	 *             If the option is invalid or unavailable.
//	 * @throws InvalidOptionNumberException
//	 *             If the number of the option is invalid.
	public abstract String execute(); //throws InvalidOptionException,InvalidOptionNumberException;
	
}
