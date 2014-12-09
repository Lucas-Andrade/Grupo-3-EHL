package airtrafficcontrol.app.appforconsole;


import airtrafficcontrol.app.appforconsole.menuoptions.Option;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidOptionException;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;


/**
 * Class whose instances represent an options menu.
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <p>
 * <ul>
 * <li>{@link OptionsMenu} instances are immutable.</li>
 * </ul>
 * </p>
 * 
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public class OptionsMenu
{
	
	// INSTANCE FIELDS
	
	/**
	 * The title of the Options Menu.
	 */
	public final String menuTitle;
	
	/**
	 * The number of options of this menu.
	 */
	public final int numberOfOptions;
	
	/**
	 * The menu's options.
	 */
	private final Option[] options;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new {@link OptionsMenu} with the title {@code menuTitle} and
	 * the list of options {code options}.
	 * 
	 * @param menuTitle
	 *            The title of this menu.
	 * @param options
	 *            The list of options of this menu.
	 * @throws InvalidArgumentException
	 */
	public OptionsMenu( String menuTitle, Option... options )
			throws InvalidArgumentException {
		
		if( menuTitle == null || menuTitle.equals( "" ) )
			throw new InvalidArgumentException();
		for( Option option : options )
			if( option == null )
				throw new InvalidArgumentException();
		
		this.menuTitle = menuTitle;
		this.numberOfOptions = options.length;
		this.options = options;
		
	}
	
	
	
	// METHODS
	
	/**
	 * Returns a string representation of this menu. The string is a numbered
	 * list of the options where each line corresponds to a line.
	 * 
	 * @return A string representation of this menu.
	 */
	public String toString() {
		
		StringBuilder menu = new StringBuilder();
		for( int index = 0; index < options.length; ++index )
			menu.append( "\n" ).append( index + 1 ).append( ". " )
					.append( options[index].title );
		return menu.toString();
	}
	
	/**
	 * Checks whether this {@link OptionsMenu options menu} contains an
	 * {@link Option} that {@link Option#equals(Object) equals} {@code option}.
	 * 
	 * @param option
	 *            The {@link Option option} to be evaluated.
	 * @return {@code true} if this {@link OptionsMenu options menu} contains
	 *         the {@link Option} {@code option}; </br>{@code false} otherwise.
	 */
	public boolean contains( Option option ) {
		for( Option opt : options )
			if( option.equals( opt ) )
				return true;
		
		return false;
	}
	
	/**
	 * Returns the title of the option number {@code numberOfTheOption} of this.
	 * 
	 * @param numberOfTheOption
	 *            The number of the option whose title is to be consulted.
	 * @return The {@link Option#title title} of {@code option}.
	 * @throws InvalidOptionNumberException
	 *             If {@code numberOfTheOption} is not valid.
	 */
	public String getOptionTitle( int numberOfTheOption )
			throws InvalidOptionNumberException {
		
		if( numberOfTheOption < 1 || numberOfTheOption > options.length )
			throw new InvalidOptionNumberException( "INVALID NUMBER OF OPTION!" );
		
		return options[numberOfTheOption - 1].title;
	}
	
	/**
	 * Returns the title of the option {@code option} of this.
	 * 
	 * @param option
	 *            The option whose title is to be consulted.
	 * @return The {@link Option#title title} of {@code option}.
	 * @throws InvalidArgumentException
	 */
	public String getOptionTitle( Option option )
			throws InvalidArgumentException {
		
		for( Option opt : options )
			if( option.equals( opt ) )
				return opt.title;
		
		// if option is not in this menu
		throw new InvalidArgumentException( "INVALID OPTION!" );
	}
	
	/**
	 * Performs the action corresponding to the option number
	 * {@code numberOfOptions} of this.
	 * 
	 * @param numberOfTheOption
	 *            The number of the option to execute.
	 * @return {@code true} if the option executed is the last of the menu;
	 *         {@code false} otherwise.
	 * @throws InvalidOptionNumberException
	 *             If {@code numberOfTheOption} is not valid.
	 */
	public void executeOptionToConsole( int numberOfTheOption,
			AirTrafficControlAppForConsole app )
			throws InvalidArgumentException, InvalidOptionNumberException {
		
		if( numberOfTheOption < 1 || numberOfTheOption > options.length )
			throw new InvalidOptionNumberException( "INVALID NUMBER OF OPTION!" );
		if( app == null )
			throw new InvalidArgumentException( "INVALID APP FOR CONSOLE!" );
		
		options[numberOfTheOption - 1].executeToConsole( app );
		
	}
	
	/**
	 * Performs the action corresponding to the option {@code option} of this.
	 * 
	 * @param option
	 *            The option to execute.
	 * @return {@code true} if the option executed is the last of the menu;
	 *         {@code false} otherwise.
	 * @throws InvalidArgumentException
	 * @throws InvalidOptionException
	 */
	public void executeOptionToConsole( Option option,
			AirTrafficControlAppForConsole app )
			throws InvalidArgumentException, InvalidOptionException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID APP FOR CONSOLE!" );
		
		// execute if option is in the menu
		for( Option opt : options )
			if( option.equals( opt ) )
				opt.executeToConsole( app );
		
		// if not in the menu, invalid option
		throw new InvalidOptionException( "INVALID OPTION, NOT IN THE MENU!" );
		
	}
	
}



// /**
// * Returns the description of the option number {@code numberOfTheOption} of
// * this.
// *
// * @param numberOfTheOption
// * The number of the option whose description is to be consulted.
// * @return The {@link Option#description description} of {@code option}
// * number {@code numberOfTheOption}.
// * @throws InvalidOptionNumberException
// * If {@code numberOfTheOption} is not valid.
// */
// public String getOptionDescription( int numberOfTheOption )
// throws InvalidOptionNumberException {
//
// if( numberOfTheOption < 1 || numberOfTheOption > options.length )
// throw new InvalidOptionNumberException( "INVALID NUMBER OF OPTION!" );
//
// return options[numberOfTheOption - 1].description;
// }
//
// /**
// * Returns the description of the option {@code option} of this.
// *
// * @param option
// * The option whose description is to be consulted.
// * @return The {@link Option#description description} of {@code option}.
// */
// public String getOptionDescription( Option option )
// throws InvalidArgumentException {
//
// for( Option opt : options )
// if( option.equals( opt ) )
// return opt.description;
//
// // if option is not in this menu
// throw new InvalidArgumentException( "INVALID OPTION!" );
// }