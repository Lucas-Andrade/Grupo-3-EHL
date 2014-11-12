package airtrafficcontrol.app.menuoptions;

import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;


/**
 * This class represents the option with the title
 * {@code Report altitude transgressions.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of printing a list of the
 * flights whose airships are currently transgressing the established air
 * corridors. For more information, read the documentation of method
 * {@link #execute() execute}.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Instances of this class are immutable.</li>
 * <li>All instances of this class share the same {@code title} and
 * {@code description}.
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ReportTransgressionsOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type PrintTransgressionsReportOption.
	 */
	private static ReportTransgressionsOption instance = new ReportTransgressionsOption();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type PrintTransgressionsReportOption and sets
	 * up the final values of the fields {@code title} and {@code description}.
	 */
	public ReportTransgressionsOption() {
		super( "Report altitude transgressions.", "d" );
	};
	
	/**
	 * Returns an instance of type PrintTransgressionsReportOption, without
	 * creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #PrintTransgressionsReportOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type PrintTransgressionsReportOption.
	 */
	public static ReportTransgressionsOption getInstance() {
		return instance;
	}
	
	
	
	// execute()
	
	/**
	 * Prints a list of the flights whose airships are currently transgressing
	 * the established air corridors.
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	public void executeToConsole(ConsoleDataToolbox app) {
		System.out.println( title );
	};
	
}