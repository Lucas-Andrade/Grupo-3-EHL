package airtrafficcontrol.app.appforconsole;


import java.util.Scanner;
import airtrafficcontrol.app.AirTrafficControlApp;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.menuoptions.Option;


/**
 * Class whose instances represent Air Traffic Control apps runnable in a
 * console.
 */
public class AirTrafficControlAppForConsole extends AirTrafficControlApp
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The app's tools (defines its options menu, its console output formatter,
	 * creates the app's airships database and its reports generator).
	 */
	public final ConsoleDataToolbox dataTools;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of {@link AirTrafficControlAppForConsole} that
	 * features:
	 * <p>
	 * <ul>
	 * <li>the {@link AirTrafficControlAppForConsole#title title}
	 * {@code appTitle};
	 * <li>a {@link airtrafficcontrol.app.OptionsMenu menu} with the
	 * {@link airtrafficcontrol.app.OptionsMenu#title title} {@code menuTitle}
	 * and the {@link Option options} {@code options}
	 * <li>an initially empty {@link airtrafficcontrol.app.utils.Database
	 * flights' database};</li>
	 * <li>a {@link airtrafficcontrol.app.utils.ReportGenerator reports
	 * generator};</li>
	 * <li>a {@link ConsoleOutputFormatter console output formatter} that draws
	 * a line filled with a sequence of {@code lengthOfSectionDelimiter}
	 * repetitions of the symbol {@code symbolOfTheSectionDelimiter} as a
	 * {@link ConsoleOutputFormatter#sectionDelimiter section delimiter} and
	 * prints {@code numberOfBlankLinesBetweenSections} blank lines of
	 * {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections}</li>
	 * <li>a {@link ConsoleInputHandler console input handler}.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param appTitle
	 *            This app's title.
	 * @param menuTitle
	 *            This app's menu's title.
	 * @param symbolOfSectionDelimiter
	 *            The symbol to be repeated in the output's section delimiter.
	 * @param lengthOfSectionDelimiter
	 *            The length of the output's section delimiter.
	 * @param numberOfBlankLinesBetweenSections
	 *            The number of lines to be left between the app's output
	 *            sections.
	 * @param options
	 *            This app's menu's options.
	 * @throws InvalidArgumentException
	 *             If {@code null} arguments are inserted; or if appTitle,
	 *             appDeveloper or menuTitle are given the empty string; or if
	 *             {@code lengthOfSectionDelimiter} or
	 *             {@code numberOfBlankLinesBetweenSections} are {@code <1}.
	 */
	public AirTrafficControlAppForConsole( String appTitle,
			String appDeveloper, String menuTitle,
			char symbolOfSectionDelimiter, int lengthOfSectionDelimiter,
			int numberOfBlankLinesBetweenSections, Option... options )
			throws InvalidArgumentException {
		
		super( appTitle, appDeveloper, menuTitle, options );
		
		this.dataTools = new ConsoleDataToolbox( symbolOfSectionDelimiter,
				lengthOfSectionDelimiter, numberOfBlankLinesBetweenSections );
	}
	
	
	
	/**
	 * Runs the app.
	 * <p>
	 * Presents the welcoming message followed by the options menu, allows the
	 * user to select the option from the menu to be executed, executes the
	 * option, returns to the menu, allows the user to select another option,
	 * ... . After the exiting option is selected, presents a goodbye message
	 * and the final credits, and exits.
	 * </p>
	 */
	public void run() {
		
		
		// WELCOME
		dataTools.outputStylizer.printSectionDelimiter();
		System.out.print( "Welcome to the \n" + appTitle + "!" );
		dataTools.outputStylizer.printSectionDelimiter();
		
		
		boolean runApp = true; // dictates whether the app must return to main
								// menu after an option has been executed or end
								// the do-while cycle
		
		
		@SuppressWarnings( "resource" )
		Scanner in = new Scanner(System.in);
		do
		{
			
			// menu presentation
			
			printBeginningOfSection( mainMenu.menuTitle );
			System.out.print( mainMenu.toString() );
			dataTools.outputStylizer.printSectionDelimiter();
			
			
			
			// ask the user to choose an option
			
			String instructionToGetOptionNumber = new StringBuilder(
					" Type the number of the option you want to" )
					.append( "\n perform and press Enter." )
					.append( "\n\nExecute option number: " ).toString();
			
			int selectedOption;
			try
			{
				selectedOption = ConsoleInputHandler.getAValidIntFromUser( 1,
						mainMenu.numberOfOptions, instructionToGetOptionNumber );
			}
			catch( InvalidArgumentException e )
			{
				selectedOption = 0;
				// this catch never happens cause all OptionsMenu have at least
				// 1 option; so mainMenu.numberOfOption is never <1, which is
				// the only condition which could throw this exception
			}
			
			printEndOfSection();
			
			
			
			// option execution
			
			try
			{
				printBeginningOfSection( mainMenu
						.getOptionTitle( selectedOption ) );
				runApp = !mainMenu
						.executeOptionToConsole( selectedOption, this );
			}
			catch( InvalidOptionNumberException | InvalidArgumentException e )
			{
				System.out.println( e.getMessage() );
			}
			printEndOfSection();
			
			// continue to the menu
			if( runApp )
			{
				System.out.println( "(press Enter to continue)" );
				in.nextLine();
			}
			
			
		}
		while( runApp );
		
		
		// CREDITS & END
		
		System.out.print( "\nCopyright (c) 2014, " + appDeveloper
				+ ". All rights reserved.\n" );
		dataTools.outputStylizer.printSectionTitle( "end" );
	}
	
	
	// METODOS RELACIONADOS COM IMPRESSAO DE SECCOES
	
	
	
	/**
	 * Prints the space between sections, the section delimiter, the formatted
	 * section title and another section delimiter, according to the
	 * {@link #outputStylizer STYLIZER}.
	 * 
	 * @param sectionTitle
	 *            The title of the section.
	 */
	private void printBeginningOfSection( String sectionTitle ) {
		
		if( sectionTitle == null )
		{
			dataTools.outputStylizer.printSpaceBetweenSections();
			dataTools.outputStylizer.printSectionDelimiter();
			dataTools.outputStylizer.printSectionDelimiter();
		}
		else
		{
			dataTools.outputStylizer.printSpaceBetweenSections();
			dataTools.outputStylizer.printSectionDelimiter();
			dataTools.outputStylizer.printSectionTitle( sectionTitle );
			dataTools.outputStylizer.printSectionDelimiter();
		}
	}
	
	
	/**
	 * Prints a section delimiter.
	 */
	private void printEndOfSection() {
		dataTools.outputStylizer.printSectionDelimiter();
	}
	
}