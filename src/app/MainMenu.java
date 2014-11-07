package app;


/**
 * Class that creates the {@code Option Menu} and its string representation.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class MainMenu
{
	
	public static final String menuTitle = "Option Menu";
	
	public static final int numberOfOptions = 10;
	
	public static final Option[] options;
	static
	{
		options = new Option[numberOfOptions];
		
		options[0] = AddAListOfFlightsOption.instance;
		options[1] = AddAFlightOption.instance;
		options[2] = MonitorAirTrafficOption.instance;
		options[3] = PrintTransgressionsReportOption.instance;
		options[4] = ConsultFlightDetailsOption.instance;
		options[5] = RemoveEmptyAirshipsOption.instance;
		options[6] = RemoveAFlightOption.instance;
		options[7] = ConfigurationsOption.instance;
		options[8] = HelpOption.instance;
		options[9] = ExitOption.instance;
	}
	
	public static String inAString() {
		
		StringBuilder menu = new StringBuilder( "" );
		for( int index = 0; index < options.length; ++index )
			menu.append( "\n" ).append( index + 1 ).append( ". " )
					.append( options[index].getOptionTitle() );
		return menu.toString();
		
	}
	
	
	
	// public static final String menuTitle = "Option Menu";
	//
	// public static final int numberOfOptions = 9;
	// private static String[] options;
	// static
	// {
	// options = new String[numberOfOptions];
	//
	// for( int i = 0; i < options.length; ++i )
	// options[i] = (i + 1) + ". ";
	// options[0] += "Add a list of flights from a text file.";
	// options[1] += "Add a flight manually.";
	// options[2] += "Monitor Air Traffic.";
	// options[3] += "Print a report of transgressions to a text file.";
	// options[4] += "Consult a flight's details.";
	// options[5] += "Remove all passenger-flights with zero passengers.";
	// options[6] += "Remove a flight manually.";
	// options[7] += "Configurations.";
	// options[8] += "Exit app.";
	// }
	//
	// public static String inAString() {
	//
	// StringBuilder menu = new StringBuilder("");
	// for( String option : options )
	// menu.append( "\n" ).append( option );
	// return menu.toString();
	//
	// }
	
	
}
