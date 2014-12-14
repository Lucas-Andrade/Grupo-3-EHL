package appForConsole.model.users;

import appForConsole.model.Element;

/**
 * Class whose instances represent users. Users have an unique username, email and password and may
 * or may not have a full name.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class User implements Element {

	// Instance Fields

	private final String username;
	private final String password;
	private final String email;
	private final String fullName;

	// Constructors

	/**
	 * Create a new {@code User} giving all the info.
	 * 
	 * @param username
	 *            - the username.
	 * @param password
	 *            - the password associated to this User
	 * @param email
	 *            - the user email
	 * @param fullName
	 *            - the user full name. (optional info)
	 */
	public User(String username, String password, String email, String fullName) {

		if (username == null || username.equals(""))
			throw new IllegalArgumentException("Invalid username");

		if (password == null || password.equals(""))
			throw new IllegalArgumentException("Invalid password");

		if (email == null || email.equals("") || !hasOneAT(email))
			throw new IllegalArgumentException("Invalid email");

		if (fullName == null)
			throw new IllegalArgumentException("Invalid fullname");

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
	 */
	public User(String username, String password, String email) {

		this(username, password, email, "");
	}

	// Private Methods

	/**
	 * Method responsible for check if email has one and only one "@".
	 * 
	 * @param String
	 *            - email
	 * @return boolean with
	 */
	private boolean hasOneAT(String email) {

		int hasAt = email.indexOf("@");
		return hasAt != -1 && hasAt == email.lastIndexOf("@") ? true : false;
	}

	// Public Methods

	/**
	 * Checks whether this user's password equals {@code introducedPassword}.
	 * 
	 * @param introducedPassword
	 *            The password to be checked.
	 * @return {@code true} if this user's password equals {@code introducedPassword};</br>
	 *         {@code false} otherwise.
	 */
	public boolean authenticatePassword(String introducedPassword) {

		return this.password.equals(introducedPassword);
	}

	// Overrides

	/**
	 * Gets all the {@code User} Information
	 * 
	 * @return a String with username, password, email and fullname.
	 */
	@Override
	public String toString() {

		StringBuilder result = new StringBuilder("username: ");

		result.append(username).append(", password: ").append(password).append(", email: ")
				.append(email);

		return fullName.equals("") ? result.toString() : result.append(", fullName: ")
				.append(fullName).toString();
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

		if (getClass() != obj.getClass())
			return false;

		if (email == null) {
			if (((User) obj).email != null)
				return false;

		} else if (!email.equals(((User) obj).email))
			return false;

		if (username == null) {
			if (((User) obj).username != null)
				return false;

		} else if (!username.equals(((User) obj).username))
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
}