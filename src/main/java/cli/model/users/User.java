package main.java.cli.model.users;


import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Element;


/**
 * Instances of this class represents users. Users have a unique username, a
 * unique email, a password and may or may not have a full name.
 */

public class User implements Element
{
	
	// Instance Fields
	
	private final String username;
	private String password;
	private final String email;
	private final String fullName;
	 
	// Constructors
	
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
	 * @throws InvalidArgumentException
	 *             If either the username, the password, the email or the full
	 *             name given are {@code null} or the empty string (""); if the
	 *             given email has no '@' or has more than one '@'.
	 */
	public User(String username, String password, String email, String fullName) throws InvalidArgumentException {
		  
		if (username == null || username.equals(""))
			throw new InvalidArgumentException("Invalid username");

		if (password == null || password.equals(""))
			throw new InvalidArgumentException("Invalid password");

		if (email == null || email.equals("") || !hasOneAT(email))
			throw new InvalidArgumentException("Invalid email");

		if (fullName == null)
			throw new InvalidArgumentException("Invalid fullname");

		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}
	
	/**
	 * Create a new {@code User} giving the minimum info needed.
	 * 
	 * @param username
	 *            - the user Name.
	 * @param password
	 *            - the password associated to this User
	 * @param email
	 *            - the user email
	 * @throws InvalidArgumentException
	 *             If either the username, the password or the email given are
	 *             {@code null} or the empty string (""); if the given email has
	 *             no '@' or has more than one '@'.
	 */
	public User( String username, String password, String email )
			throws InvalidArgumentException {
		
		this( username, password, email, "" );
	}
	
	// Private Methods
	
	/**
	 * Method responsible for check if email has one and only one "@".
	 * 
	 * @param String
	 *            - email
	 * @return boolean with
	 */
	private boolean hasOneAT( String email ) {
		
		int hasAt = email.indexOf( "@" );
		return hasAt != -1 && hasAt == email.lastIndexOf( "@" ) ? true : false;
	}
	
	// Public Methods
	
	/**
	 * Checks whether this user's password equals {@code introducedPassword}.
	 * 
	 * @param introducedPassword
	 *            The password to be checked.
	 * @return {@code true} if this user's password equals
	 *         {@code introducedPassword};</br> {@code false} otherwise.
	 */
	public boolean authenticatePassword( String introducedPassword ) {
		
		return this.password.equals( introducedPassword );
	}
	
	// Overrides
	
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
		
		return fullName.equals( "" ) ? result.append("\n").toString() : result
				.append( ", fullName: " ).append( fullName ).append("\n").toString();
	}
	
	public String toStringWithoutPassword() {

		StringBuilder result = new StringBuilder("username: ");

		result.append(username).append(", email: ").append(email);
 
		return fullName.equals("") ? result.append("\n").toString() : result.append(", fullName: ")
				.append(fullName).append("\n").toString(); 
	}
		
	/**
	 * Override of the {@code equals} method from the {@link Object} class.
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof User))
			return false;

		if (!email.equals(((User) obj).email))
			return false;

		if (!username.equals(((User) obj).username))
			return false;

		return true;
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
	
	// Get Methods
	
	/**
	 * Gets the {@code email} identification
	 * 
	 * @return a string with an email.
	 */
	public String getEmail() {
		
		return email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName()
	{
		return fullName;
	}
 
	/**
	 * 
	 * Change the User {@code password} 
	 * 
	 * @param newPassword - The new User password. 
	 * @throws InvalidArgumentException 
	 */
	public boolean changePassword(String newPassword , String oldPassword) throws InvalidArgumentException{
		
		if (newPassword == null || newPassword.equals(""))
			throw new InvalidArgumentException("Invalid new password");
		
			
		if( this.authenticatePassword(oldPassword))  { 
					
			this.password = newPassword;
			
				return true;
	
			}
		
		return false;
	}




}