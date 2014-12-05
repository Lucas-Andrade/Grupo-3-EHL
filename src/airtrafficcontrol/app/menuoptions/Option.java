package airtrafficcontrol.app.menuoptions;


import java.io.IOException;

import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.FlightNotFoundInDatabaseException;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.OptionsMenu;


/**
 * This abstract class and all its subclasses represent options from an options
 * menu.
 * <p>
 * This abstract class serves the purpose of establishing which are the common
 * properties and methods of all options of an {@link OptionsMenu}: all options
 * must have a final field {@link #title} and a final field {@link #description}
 * , both accessible for reading, and must have an {@link #execute() execute}
 * and an {@link #executeToConsole() executeToConsole} methods.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>When a class extends {@link Option}, its main feature should be the
 * implementation of the method {@link #execute() execute}. Due to that, it is
 * strongly recommended that all instances of a subclass of {@link Option} store
 * the same string in the field {@link #title} (a title that shortly says the
 * objective of this method) and that all instances store the same string in the
 * field {@link #description} (a short description of the actions performed by
 * this method and some usage tips about the input-output interaction). One way
 * of doing that is to create only one constructor method, without arguments,
 * that initializes these fields.</li>
 * <li>Since the fields {@link #title} and {@link #description} must be final
 * fields, their visibility was chosen to be public.</li>
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
	 * {@link #description}. If the arguments introduced are null, the option's
	 * title and description are saved as "TITLE NOT AVAILABLE!" and
	 * "DESCRIPTION NOT AVAILABLE!".
	 * 
	 * @param optionTitle
	 *            The title of the option.
	 * @param optionDescription
	 *            A description of the action performed.
	 */
	public Option( String optionTitle, String optionDescription ) {
		
		if( optionTitle == null )
			this.title = "TITLE NOT AVAILABLE!";
		else this.title = optionTitle;
		
		if( optionDescription == null )
			this.description = "DESCRIPTION NOT AVAILABLE!";
		else this.description = optionDescription;
	}
	
	
	
	// EXECUTORES
	
	
	/**
	 * Manages input data from console needed to perform the {@link #execute()
	 * execute} method and prints its output. May use the input-output settings
	 * from the {@link ConsoleDataToolbox toolbox} of the
	 * {@link AirTrafficControlAppForConsole} {@code app}.
	 * 
	 * @param app
	 *            The app whose input-output settings might be used.
	 * @throws InvalidArgumentException
	 *             If the {@code app} is {@code null}.
	 */
	public abstract void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException;
	
	
	/**
	 * Performs the action corresponding to the option that the subclass who
	 * implemented this method represents.
	 * 
	 * @return A string with output from the action.
	 * @throws FlightNotFoundInDatabaseException
	 * @throws DatabaseNotFoundException
	 * @throws InvalidFlightIDException
	 * @throws InvalidArgumentException 
	 * @throws IOException 
	 */
	public abstract String execute() throws FlightNotFoundInDatabaseException,
			DatabaseNotFoundException, InvalidFlightIDException, InvalidArgumentException, IOException; // throws
	
	
	
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
	 * Indicates whether {@code obj} is "the same as" {@code this}. The Object
	 * {@code obj} is said to be the same as this if it is an instance of Option
	 * with same {@link Option#title title} and {@link Option#description
	 * description} as this.
	 * 
	 * @param obj
	 *            The reference {@link Object} with which to compare
	 *            {@code this}.
	 * @return {@code true} if {@code this} and {@code obj} are the same;
	 *         {@code false} otherwise.
	 */
	@Override
	public boolean equals( Object obj ) {
		
		// if they reference the same object, true
		if( this == obj )
			return true;
		
		// if obj is null or is not of type Option, false
		if( obj == null || getClass() != obj.getClass() )
			return false;
		
		// if obj is instance of Option, cast and compare fields
		Option other = (Option)obj;
		if( !description.equals( other.description ) )
			return false;
		if( !title.equals( other.title ) )
			return false;
		
		return true;
	}
	
	
	
}

// TODO: write these throws in execute or clean the next lines
// * @throws InvalidOptionException
// * If the option is invalid or unavailable.
// * @throws InvalidOptionNumberException
// * If the number of the option is invalid.
// throws InvalidOptionException,InvalidOptionNumberException;
