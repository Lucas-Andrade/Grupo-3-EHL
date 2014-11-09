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
	 * its output. Uses the flights' database and the output formatter of the
	 * {@AppForConsole}
	 */
	public abstract void executeToConsole(
			AirTrafficControlAppForConsoleTools app );
	
	
	/**
	 * Performs the action corresponding to the option that the class who
	 * implemented this method represents.
	 * 
	 * @return A string with output from the action.
	 * @throws FlightNotFoundInDatabaseException
	 *             When the method tries to reach a flight in a flights'
	 *             database that doesn't contain it.
	 * @throws DatabaseNotFoundException
	 *             When the method tries to reach a null or inexistent flights'
	 *             database.
	 */
	// * @throws InvalidOptionException
	// * If the option is invalid or unavailable.
	// * @throws InvalidOptionNumberException
	// * If the number of the option is invalid.
	public abstract String execute() throws FlightNotFoundInDatabaseException,
			DatabaseNotFoundException; // throws
	// InvalidOptionException,InvalidOptionNumberException;
	
	
	
	// OVERRIDES PARA METODOS DA CLASSE OBJECT
	
	
	
	/**
	 * Returns a hash code value for this.
	 * 
	 * @return A hash code value for this.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	
	/**
	 * Indicates whether {@code obj} is the same as this. The Object {@code obj}
	 * is said to be "the same as" this if it is an instance of Option with same
	 * {@link Option#title title} and {@link Option#description description} as
	 * this.
	 * 
	 * @param obj
	 *            The reference {@link Object} with which to compare this.
	 * @return {@code true} if this and {@code obj} are the same; {@code false}
	 *         otherwise.
	 */
	@Override
	public boolean equals( Object obj ) {
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		Option other = (Option)obj;
		if( description == null )
		{
			if( other.description != null )
				return false;
		}
		else
			if( !description.equals( other.description ) )
				return false;
		if( title == null )
		{
			if( other.title != null )
				return false;
		}
		else
			if( !title.equals( other.title ) )
				return false;
		return true;
	}
	
	
	
}
