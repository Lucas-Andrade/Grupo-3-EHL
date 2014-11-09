package app;


/**
 * Class whose instances represent a options menu.
 * 
 * <p>
 * The last option of an options menu represented by an instance of
 * {@link OptionsMenu} is somewhat special in the following manner: when the
 * argument of the method {@link #executeOptionToConsole(int) executeOption} is
 * the number of the last option of the menu, it returns {@code true} (all other
 * executions return {@code false}). Yet, in no way the action performed by this
 * option is altered.</br> An example of utility of this feature is to consider
 * an option menu with an exiting option: by making this option the last of the
 * menu, classes that use this options menu receive the information that the
 * exiting option was activated.
 * </p>
 * <p>
 * The representation in a string of this instances is a numbered list; classes
 * who use an options menu
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
	 */
	public OptionsMenu( String menuTitle, Option... options ) {
		
		this.menuTitle = menuTitle;
		this.numberOfOptions = options.length;
		this.options = options;
		
	}
	
	
	
	// MÉTODOS
	
	
	
	/**
	 * Returns a string representation of this menu. The string is a numbered
	 * list of the options.
	 * 
	 * @return A string representation of this menu.
	 */
	public String inAString() {
		
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
	 */
	public String getOptionTitle( Option option ) {
		
		// TODO the throw of InvalidOptionException
		
		return option.title;
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
	public String getOptionDescription( Option option ) {
		
		// TODO the throw of InvalidOptionException
		
		return option.description;
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
	public boolean executeOptionToConsole( int numberOfTheOption )
			throws InvalidOptionNumberException {
		
		if( numberOfTheOption < 1 || numberOfTheOption > options.length )
			throw new InvalidOptionNumberException( "INVALID NUMBER OF OPTION!" );
		
		options[numberOfTheOption - 1].executeToConsole();
		
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
	 */
	public boolean executeOptionToConsole( Option option ) {
		
		// TODO the throw of InvalidOptionException
		
		option.executeToConsole();
		
		if( option == options[options.length] )
			return true;
		return false;
		
	}
	
}
