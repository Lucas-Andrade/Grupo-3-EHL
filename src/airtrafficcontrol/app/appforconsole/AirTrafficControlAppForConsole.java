package airtrafficcontrol.app.appforconsole;


import java.util.Scanner;
import airtrafficcontrol.app.AirTrafficControlApp;
import airtrafficcontrol.app.appforconsole.menuoptions.Option;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.utils.towerControl.Database;
import airtrafficcontrol.app.utils.towerControl.ReportGenerator;


/**
 * Class whose instances represent apps runnable in a console that perform
 * actions of air traffic control.
 * <p>
 * {@link AirTrafficControlAppForConsole Air Traffic Control apps for console}
 * have a {@link AirTrafficControlApp#appTitle name} and a
 * {@link AirTrafficControlApp#appDeveloper developer} and they are associated
 * with a {@link Database flights database} and a {@link ReportGenerator reports
 * generator}. </br> They also have a {@link OptionsMenu options menu}, a
 * {@link ConsoleOutputFormatter console output formatter} and a
 * {@link ConsoleInputHandler console input handler}.
 * </p>
 * <p style="font-size:16">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>The method {@link #run()} may be called once for each instance of
 * {@link AirTrafficControlAppForConsole}.
 * <li>{@link AirTrafficControlAppForConsole} instances are mutable; when the
 * method {@link #exitApp()} is called, method {@link #run()}'s behaviour
 * changes.</li>
 * <li>Fields {@link #outputStylizer}, {@link #inputHandler} and
 * {@link #mainMenu} are final and public.</li>
 * </ul>
 *
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public class AirTrafficControlAppForConsole extends AirTrafficControlApp
{
	
	// INSTANCE FIELDS
	
	/**
	 * This app's {@link ConsoleOutputFormatter console output formatter}.
	 */
	public final ConsoleOutputFormatter outputStylizer;
	
	/**
	 * This app's {@link ConsoleInputHandler console input handler}.
	 */
	public final ConsoleInputHandler inputHandler;
	
	/**
	 * This app's {@link OptionsMenu options menu}.
	 */
	public final OptionsMenu mainMenu;
	
	/**
	 * This app's running status. Dictates whether the method {@link #run() run}
	 * continues or stops the execution of the do-while cycle.
	 */
	private boolean keepRunning;
	
	
	// CONSTRUCTOR
	
	/**
	 * Creates an instance of {@link AirTrafficControlAppForConsole} that
	 * features:
	 * <p>
	 * <ul>
	 * <li>the {@link AirTrafficControlApp#appTitle name} {@code appTitle},</li>
	 * <li>the {@link AirTrafficControlApp#appDeveloper developer}
	 * {@code appDeveloper},</li>
	 * <li>a {@link Database flights database},</li>
	 * <li>a {@link ReportGenerator reports generator},</li>
	 * <li>a {@link ConsoleOutputFormatter console output formatter} whose
	 * {@link ConsoleOutputFormatter#sectionDelimiter section delimiter} is a
	 * line with {@code lengthOfSectionDelimiter} times the symbol
	 * {@code symbolOfSectionDelimiter} and whose
	 * {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections} is {@code numberOfBlankLinesBetweenSections} blank lines.,</li>
	 * <li>a {@link ConsoleInputHandler console input handler} and</li>
	 * <li>a {@link OptionsMenu options menu} with the
	 * {@link OptionsMenu#menuTitle title} {@code menuTitle} and the
	 * {@link Option options} {@code options},</li>
	 * </ul>
	 * </p>
	 * 
	 * @param appTitle
	 *            This app's name.
	 * @param appDeveloper
	 *            This app's developer.
	 * @param symbolOfSectionDelimiter
	 *            The symbol to be repeated in the output's section delimiter.
	 * @param lengthOfSectionDelimiter
	 *            The length of the output's section delimiter.
	 * @param numberOfBlankLinesBetweenSections
	 *            The number of lines to be left between the app's output
	 *            sections.
	 * @param menuTitle
	 *            This app's menu's title.
	 * @param options
	 *            This app's menu's options.
	 * @throws InvalidArgumentException
	 *             If {@code null} arguments are inserted; or if appTitle,
	 *             appDeveloper or menuTitle are given the empty string; or if
	 *             {@code lengthOfSectionDelimiter} or
	 *             {@code numberOfBlankLinesBetweenSections} are {@code <1}.
	 */
	public AirTrafficControlAppForConsole( String appTitle,
			String appDeveloper, char symbolOfSectionDelimiter,
			int lengthOfSectionDelimiter,
			int numberOfBlankLinesBetweenSections, String menuTitle,
			Option... options ) throws InvalidArgumentException {
		
		super( appTitle, appDeveloper );
		
		for( Option option : options )
			if( option == null )
				throw new InvalidArgumentException( "INVALID NULL OPTION!" );
		if( menuTitle == null || menuTitle.equals( "" ) )
			throw new InvalidArgumentException( "INVALID MENU TITLE!" );
		
		mainMenu = new OptionsMenu( menuTitle, options );
		outputStylizer = new ConsoleOutputFormatter( symbolOfSectionDelimiter,
				lengthOfSectionDelimiter, numberOfBlankLinesBetweenSections );
		inputHandler = new ConsoleInputHandler();
		keepRunning = true;
	}
	
	
	
	// STATUS CHANGING METHODS
	
	/**
	 * Stops this app's execution. Informs the method {@link #run()} that no
	 * other option from the menu is to be executed next.
	 */
	public void exitApp() {
		keepRunning = false;
	}
	
	
	
	// METHOD INHERITED FROM AirTrafficControlApp
	
	/**
	 * Runs the app.
	 * <p>
	 * Presents the welcoming message followed by the options menu, allows the
	 * user to select the option from the menu to be executed, executes the
	 * option, returns to the menu, allows the user to select another option,
	 * ... . After the {@link #exitApp()} method is called, it presents a
	 * goodbye message and the final credits, and exits.
	 * </p>
	 */
	public void run() {
		
		presentWelcomingMessageInConsole();
		Scanner in = new Scanner( System.in );
		
		do
		{
			int selectedOption = presentMenuAndAskUserToChooseAnOption();
			executeTheChosenOption( selectedOption );
			
			if( keepRunning )
			{// continue to the menu
				System.out.println( "(press Enter to continue)" );
				in.nextLine();
			}
		}
		while( keepRunning );
		
		in.close();
		presentCreditsAndEndMessageInConsole();
	}
	
	
	
	// PRIVATE AUXILIAR METHODS called in method run()
	
	private void presentWelcomingMessageInConsole() {
		outputStylizer.printSectionDelimiter();
		System.out.print( "Welcome to the \n" + appTitle + "!" );
		outputStylizer.printSectionDelimiter();
	}
	
	private int presentMenuAndAskUserToChooseAnOption() {
		
		printBeginningOfSection( mainMenu.menuTitle );
		System.out.print( mainMenu.toString() );
		outputStylizer.printSectionDelimiter();
		int selectedOption = askUserToChooseAnOptionFromTheMenu();
		outputStylizer.printSectionDelimiter();
		
		return selectedOption;
	}
	
	// called in method presentMenuAndAskUserToChooseAnOption()
	private int askUserToChooseAnOptionFromTheMenu() {
		
		String instructionToGetOptionNumber = new StringBuilder(
				" Type the number of the option you want to" )
				.append( "\n perform and press Enter." )
				.append( "\n\nExecute option number: " ).toString();
		
		try
		{
			return ConsoleInputHandler.getAValidIntFromUser( 1,
					mainMenu.numberOfOptions, instructionToGetOptionNumber );
		}
		catch( InvalidArgumentException e )
		{
			return 0;
			// this catch never happens cause all OptionsMenu have at least
			// 1 option; so mainMenu.numberOfOption is never <1, which is
			// the only condition which could throw this exception
		}
	}
	
	private void executeTheChosenOption( int selectedOption ) {
		
		try
		{
			printBeginningOfSection( mainMenu.getOptionTitle( selectedOption ) );
			mainMenu.executeOptionToConsole( selectedOption, this );
		}
		catch( InvalidOptionNumberException | InvalidArgumentException e )
		{
			System.out.println( e.getMessage() );
		}
		outputStylizer.printSectionDelimiter();
	}
	
	// called in methods presentMenuAndAskUserToChooseAnOption and
	// executeTheChosenOption
	/**
	 * Prints the beginning of a section in the console.
	 * <p>
	 * Prints:
	 * <ul>
	 * <li>the {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections} ,</li>
	 * <li>the {@link ConsoleOutputFormatter#sectionDelimiter section delimiter}
	 * ,</li>
	 * <li>the formatted section title {@code sectionTitle} and</li>
	 * <li>another {@link ConsoleOutputFormatter#sectionDelimiter section
	 * delimiter}</li>
	 * </ul>
	 * as established in this app's {@link #outputStylizer console output
	 * formatter}.
	 * </p>
	 * 
	 * @param sectionTitle
	 *            The title of the section.
	 */
	private void printBeginningOfSection( String sectionTitle ) {
		
		if( sectionTitle == null )
		{
			outputStylizer.printSpaceBetweenSections();
			outputStylizer.printSectionDelimiter();
			outputStylizer.printSectionDelimiter();
		}
		else
		{
			outputStylizer.printSpaceBetweenSections();
			outputStylizer.printSectionDelimiter();
			outputStylizer.printSectionTitle( sectionTitle );
			outputStylizer.printSectionDelimiter();
		}
	}
	
	private void presentCreditsAndEndMessageInConsole() {
		System.out.print( "\nCopyright (c) 2014, " + appDeveloper
				+ ". All rights reserved.\n" );
		outputStylizer.printSectionTitle( "end" );
	}
	
	
}