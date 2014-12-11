package airtrafficcontrol.app.appforcommandline.model;
//package airtrafficcontrol.app.appforcommandline;

/**
 * Contract to be supported by {@code User } and {@code Airship}.
 * 
 * NOTE: (DIFFERENT ELEMENTS MUST HAVE DIFFERENT IDENTIFICATION)
 */

public interface Element {
	
	/**
	 * Gets the {@code Element} identification
	 */
	public String getIdentification(); 
	
}
