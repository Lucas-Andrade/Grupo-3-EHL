package app;


import utils.Database;


/**
 * Class whose subclasses's instances provide tools for apps of air traffic
 * control with user-app interaction established through a console. Instances of
 * {@link AirTrafficControlAppForConsoleTools} inherit a public final field of
 * type {@link OptionsMenu} and a public final field of type {@link Database}
 * from class {@link AirTrafficControlAppTools}, and they also must have a
 * public final field of type {@link ConsoleOutputFormatter}.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AirTrafficControlAppForConsoleTools extends
		AirTrafficControlAppTools
{
	
	// CAMPOS DA CLASSE
	
	
	/**
	 * The style formatting setter for an app's output.
	 */
	public final ConsoleOutputFormatter STYLIZER;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of {@link AirTrafficControlAppForConsoleTools}.
	 * <p>
	 * This instance provides:
	 * <ul>
	 * <li>a {@link AirTrafficControlAppTools#MAINMENU MAINMENU} with the
	 * {@link OptionsMenu#title title} {@code menuTitle} and the options
	 * {@code options};</li>
	 * <li>an empty {@link utils.Database flights' database} and</li>
	 * <li>a {@link AirTrafficControlAppForConsoleTools#STYLIZER STYLIZER} with
	 * a {@link ConsoleOutputFormatter#sectionDelimiter section delimiter} that
	 * is a line filled with a sequence of {@code lengthOfSectionDelimiter}
	 * repetitions of the symbol {@code symbolOfSectionDelimiter} and whose
	 * {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections} is {@code numberOfBlankLinesBetweenSections} blank lines</li>
	 * </ul>
	 * for the app.
	 * </p>
	 * 
	 * @param menuTitle
	 *            The title of the menu.
	 * @param symbolOfSectionDelimiter
	 *            The symbol to be used in the sections' delimiters.
	 * @param lengthOfSectionDelimiter
	 *            The length of the sections' delimiters.
	 * @param numberOfBlankLinesBetweenSections
	 *            The number of blank lines that must appear between sections.
	 * @param options
	 *            The options of the menu.
	 */
	public AirTrafficControlAppForConsoleTools( String menuTitle,
			char symbolOfSectionDelimiter, int lengthOfSectionDelimiter,
			int numberOfBlankLinesBetweenSections, Option... options ) {
		super( menuTitle, options );
		STYLIZER = new ConsoleOutputFormatter( symbolOfSectionDelimiter,
				lengthOfSectionDelimiter, numberOfBlankLinesBetweenSections );
	}
	
	
	
}