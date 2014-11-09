package app;


/**
 * This class's subclasses are supposed to have the entry point to EHL's AIR
 * TRAFFIC CONTROL apps. They are all required to have a field {@code public final}
 * {@link OptionsMenu}.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class App
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * This app's menu.
	 */
	public final OptionsMenu MAINMENU;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates a new App whose menu has the {@link OptionsMenu#menuTitle title}
	 * {@code menuTitle} and the options {@code options}.
	 * 
	 * @param menuTitle
	 * @param options
	 */
	public App( String menuTitle, Option... options ) {
		MAINMENU = new OptionsMenu( menuTitle, options );
	}
	
}
