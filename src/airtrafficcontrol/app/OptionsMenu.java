package airtrafficcontrol.app;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.menuoptions.Option;


/**
 * Class whose instances represent an options menu.
 * 
 * <p>
 * The last option of an options menu represented by an instance of
 * {@link OptionsMenu} is somewhat special in the following manner: when the
 * argument of the method {@link #executeOptionToConsole(int) executeOption} is
 * the number of the last option of the menu, it returns {@code true} (all other
 * executions return {@code false}). Yet, in no way the action performed by this
 * option is altered.</br> An example of utility of this feature is to consider
 * an instance of {@link OptionsMenu} with an exiting option: by making this
 * option the last of the menu, classes that work with this menu receive the
 * information that the exiting option was activated.
 * </p>
 * <p>
 * The representation in a string of this instances is a numbered list.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <p>
 * <ul>
 * <li>{@code OptionsMenu} instances are immutable.</li>
 * </ul>
 * </p>
 * 
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class OptionsMenu
{
	
	// CAMPOS DA CLASSE
	
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
	public final Option[] options;
	
	
	
	// CONSTRUTOR
	
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
	
	
	
	// METODOS
	
	
	
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
	 * Returns the description of the option number {@code numberOfTheOption} of
	 * this.
	 * 
	 * @param numberOfTheOption
	 *            The number of the option whose description is to be consulted.
	 * @return The {@link Option#description description} of {@code option}
	 *         number {@code numberOfTheOption}.
	 * @throws InvalidOptionNumberException
	 *             If {@code numberOfTheOption} is not valid.
	 */
	public String getOptionDescription( int numberOfTheOption )
			throws InvalidOptionNumberException {
		
		if( numberOfTheOption < 1 || numberOfTheOption > options.length )
			throw new InvalidOptionNumberException( "INVALID NUMBER OF OPTION!" );
		
		return options[numberOfTheOption - 1].description;
	}
	
	
	/**
	 * Returns the description of the option {@code option} of this.
	 * 
	 * @param option
	 *            The option whose description is to be consulted.
	 * @return The {@link Option#description description} of {@code option}.
	 */
	public String getOptionDescription( Option option )
			throws InvalidArgumentException {
		
		for( Option opt : options )
			if( option.equals( opt ) )
				return opt.description;
		
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
	public boolean executeOptionToConsole( int numberOfTheOption,
			AirTrafficControlAppForConsole app )
			throws InvalidArgumentException, InvalidOptionNumberException {
		
		if( numberOfTheOption < 1 || numberOfTheOption > options.length )
			throw new InvalidOptionNumberException( "INVALID NUMBER OF OPTION!" );
		if( app == null )
			throw new InvalidArgumentException( "INVALID APP FOR CONSOLE!" );
		
		options[numberOfTheOption - 1].executeToConsole( app );
		
		if( numberOfTheOption == options.length )
			return true;
		return false;
		
	}
	
	
	/**
	 * Performs the action corresponding to the option {@code option} of this.
	 * 
	 * @param option
	 *            The option to execute.
	 * @return {@code true} if the option executed is the last of the menu;
	 *         {@code false} otherwise.
	 * @throws InvalidArgumentException
	 */
	public boolean executeOptionToConsole( Option option,
			AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID APP FOR CONSOLE!" );
		
		// execute if option is in the menu
		for( Option opt : options )
			if( option.equals( opt ) )
			{
				option.executeToConsole( app );
				
				// return true if option is the last in the menu
				if( option == options[options.length] )
					return true;
				return false;
			}
		
		// if not in the menu, invalid option
		throw new InvalidArgumentException( "INVALID OPTION!" );
		
	}
	
}
