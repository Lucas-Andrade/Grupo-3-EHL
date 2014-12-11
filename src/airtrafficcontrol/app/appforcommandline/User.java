package airtrafficcontrol.app.appforcommandline;


// package airtrafficcontrol.app.appforcommandline;

/**
 * Instances of this class represents the Air traffic User.
 */

public class User implements Element
{
	
	private final String username;
	private final String password;
	private final String email;
	private final String fullName;
	
	/**
	 * Create a new {@code User} giving all the info.
	 * 
	 * @param username
	 *            - the user Name.
	 * @param password
	 *            - the password associated to this User
	 * @param email
	 *            - the user email
	 * @param fullName
	 *            - the user full name. (optional info)
	 */
	
	public User( String username, String password, String email, String fullName ) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}
	
	/**
	 * * Create a new {@code User} giving the minimum info needed.
	 * 
	 * @param username
	 *            - the user Name.
	 * @param password
	 *            - the password associated to this User
	 * @param email
	 *            - the user email
	 */
	
	public User( String username, String password, String email ) {
		
		this( username, password, email, "" );
	}
	
	/**
	 * Gets all the {@code User} Information
	 * 
	 * @return a String with username, password, email and fullname.
	 */
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder( "username: " );
		result.append( username ).append( ", password: " ).append( password )
				.append( ", email: " ).append( email );
		
		return fullName.equals( "" ) ? result.toString() : result
				.append( ", fullName: " ).append( fullName ).toString();
	}
	
	/**
	 * Gets the {@code User} identification
	 * 
	 * @return a string with an username.
	 */
	
	@Override
	public String getIdentification() {
		
		return username;
		
	}
	
	
	@Override
	public boolean equals( Object obj ) {
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		User other = (User)obj;
		if( email == null )
		{
			if( other.email != null )
				return false;
		}
		else
			if( !email.equals( other.email ) )
				return false;
		if( username == null )
		{
			if( other.username != null )
				return false;
		}
		else
			if( !username.equals( other.username ) )
				return false;
		return true;
	}
	
	/**
	 * Returns the user's username.
	 * 
	 * @return The user's username.
	 */
	public String getUsername() {
		return username;
	}
}
