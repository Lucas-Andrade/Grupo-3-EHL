package airtrafficcontrol.app.deprecated;

import airtrafficcontrol.app.OptionsMenu;
import airtrafficcontrol.app.menuoptions.Option;


// ESTA CLASSE MORREU!

/**
 * Class that represents a {@code Options Menu} of this app and produces its
 * string representation.
 * 
 * <p style="font-size:16">
 * <b>Implementation notes</b>
 * </p>
 * <p>
 * As this menu is a unique and final piece not to be changed by other classes,
 * there is no need to allow creation of instances of this class nor provide
 * setter methods. The class itself represents the menu, all fields and methods
 * are static.
 * </p>
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class MainMenuMORREU extends OptionsMenu
{
	
	// SÓ PARA A CALAR
	public MainMenuMORREU( String menuTitle, Option[] options ) {
		super( menuTitle, options );
	}
	// TENTATIVA 3 passa a haver a classe OptionsMenu
	
	
	
	// TENTATIVA 2
	
	// public static final String menuTitle = "Option Menu";
	//
	// public static final int numberOfOptions = 10;
	//
	// public static final Option[] options;
	// static
	// {
	// options = new Option[numberOfOptions];
	//
	// options[0] = AddAListOfFlightsOption.instance;
	// options[1] = AddAFlightOption.instance;
	// options[2] = MonitorAirTrafficOption.instance;
	// options[3] = PrintTransgressionsReportOption.instance;
	// options[4] = ConsultFlightDetailsOption.instance;
	// options[5] = RemoveEmptyAirshipsOption.instance;
	// options[6] = RemoveAFlightOption.instance;
	// options[7] = ConfigurationsOption.instance;
	// options[8] = HelpOption.instance;
	// options[9] = ExitOption.instance;
	// }
	//
	// public static String inAString() {
	//
	// StringBuilder menu = new StringBuilder( "" );
	// for( int index = 0; index < options.length; ++index )
	// menu.append( "\n" ).append( index + 1 ).append( ". " )
	// .append( options[index].getOptionTitle() );
	// return menu.toString();
	//
	// }
	
	
	
	// TENTATIVA 1
	
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
