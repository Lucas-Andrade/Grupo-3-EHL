package airtrafficcontrol.app.appforconsole.menuoptions;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.OptionsMenu;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;


/**
 * Abstract class that represents an option of an
 * {@link AirTrafficControlAppForConsole app for console}'s {@link OptionsMenu
 * options menu}. Options have {@link #title}s and are meant to perform some
 * action that may imply receiving input from the user through the console and
 * printing instructions and output back (all which is to be done by the
 * {@link #executeToConsole()} method). </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Field {@link #title} is final and public.</li>
 * </ul>
 *
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public abstract class Option
{
	
	// INSTANCE FIELD
	/**
	 * The option's title.
	 */
	public final String title;
	
	// CONSTRUCTOR
	/**
	 * Sets up the option's {@link #title}. If {@code optionTitle} is
	 * {@code null}, {@link #title}'s value will be the empty string ("").
	 * 
	 * @param optionTitle
	 *            The title of the option.
	 */
	public Option( String optionTitle ) {
		
		if( optionTitle == null )
			title = "";
		else this.title = optionTitle;
	}
	
	// EXECUTE TO CONSOLE
	/**
	 * Manages input data from console needed to perform the expected action and
	 * prints its output. May use the {@code app}'s properties to perform it.
	 * 
	 * @param app
	 *            The {@link AirTrafficControlAppForConsole app for console}
	 *            whose properties might be used.
	 * @throws InvalidArgumentException
	 *             If the {@code app} is {@code null}.
	 */
	public abstract void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException;
	
	
	// OVERRIDES OF Object's METHODS
	
	/**
	 * Returns a hash code value for this.
	 * 
	 * @return A hash code value for this.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	/**
	 * Indicates whether {@code obj} is "the same as" {@code this}. They are the
	 * same if they have the same runtime type and the same {@link Option#title
	 * title}.
	 * 
	 * @param obj
	 *            The reference {@link Object} with which to compare
	 *            {@code this}.
	 * @return {@code true} if {@code this} and {@code obj} are the same;</br>
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
		if( !title.equals( other.title ) )
			return false;
		
		return true;
	}
	
}


// EXECUTE
// /**
// * Performs the action corresponding to the option that the subclass who
// * implemented this method represents.
// *
// * @return A string with output from the action.
// * @throws FlightNotFoundInDatabaseException
// * @throws DatabaseNotFoundException
// * @throws InvalidFlightIDException
// * @throws InvalidArgumentException
// * @throws IOException
// */
// public abstract String execute() throws FlightNotFoundInDatabaseException,
// DatabaseNotFoundException, InvalidFlightIDException,
// InvalidArgumentException, IOException ;
