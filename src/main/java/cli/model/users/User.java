package main.java.cli.model.users;

import main.java.cli.model.Element;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Instances of this class represents users. Users have a unique username, a unique email, a
 * password and may or may not have a full name.
 */
public class User implements Element {

	// Instance Fields

	/**
	 * {@code username} - String representation of the {@code User}'s username.
	 */
	private final String username;

	/**
	 * {@code password} - String representation of the {@code User}'s password.
	 */
	private String password;

	/**
	 * {@code email} - String representation of the {@code User}'s email.
	 */
	private final String email;

	/**
	 * {@code fullName} - String representation of the {@code User}'s fullName. Will be an empty
	 * String if it's not defined upon the {@code User} creation.
	 */
	private final String fullName;

	// Constructors

	/**
	 * Create a new {@code User} giving all the information.
	 * 
	 * @param username
	 *            - the {@code User} username.
	 * @param password
	 *            - the password associated to this {@code User}
	 * @param email
	 *            - the {@code User} email
	 * @param fullName
	 *            - the {@code User} full name. (optional information)
	 * 
	 * @throws InvalidArgumentException
	 *             If either the username, the password, the email or the full name given are
	 *             {@code null} or an empty string (""); if the given email has no '@' or has more
	 *             than one '@'.
	 */
	public User(String username, String password, String email, String fullName)
			throws InvalidArgumentException {

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
	 *            - the {@code User} username.
	 * @param password
	 *            - the password associated to this {@code User}
	 * @param email
	 *            - the {@code User} email
	 * 
	 * @throws InvalidArgumentException
	 *             If either the username, the password or the email given are {@code null} or the
	 *             empty string (""); if the given email has no '@' or has more than one '@'.
	 */
	public User(String username, String password, String email) throws InvalidArgumentException {

		this(username, password, email, "");
	}

	// Private Methods

	/**
	 * Method responsible for checking if the email given as parameter in this class constructors
	 * has one and only one "@".
	 * 
	 * @param email
	 *            - the {@code User} email
	 * 
	 * @return {@code true} if the given {@code email} has one and only one "@" and {@code false}
	 *         otherwhise.
	 */
	private boolean hasOneAT(String email) {

		int hasAt = email.indexOf("@");
		return hasAt != -1 && hasAt == email.lastIndexOf("@");
	}

	// Public Methods

	/**
	 * Checks whether this user's password is equal to the {@code introducedPassword}.
	 * 
	 * @param introducedPassword
	 *            - The password to be checked.
	 * 
	 * @return {@code true} if this user's password is equal to the {@code introducedPassword} and
	 *         {@code false} otherwise.
	 */
	public boolean authenticatePassword(String introducedPassword) {

		return this.password.equals(introducedPassword);
	}

	/**
	 * Public method used to change the User {@code password}
	 * 
	 * @param newPassword
	 *            - The {@code User}'s new password.
	 * @param oldPassword
	 *            - The {@code User}'s old password.
	 * 
	 * @throws InvalidArgumentException
	 *             If the new password is {@code null} or and empty String.
	 */
	public boolean changePassword(String newPassword, String oldPassword)
			throws InvalidArgumentException {

		if (newPassword == null || newPassword.equals(""))
			throw new InvalidArgumentException("Invalid new password");

		if (this.authenticatePassword(oldPassword)) {

			this.password = newPassword;

			return true;
		}

		return false;
	}

	/**
	 * @return a String with the {@code User}'s username, password, email and fullname.
	 */
	public String toStringWithoutPassword() {

		StringBuilder result = new StringBuilder("username: ");

		result.append(username).append(", email: ").append(email);

		return fullName.equals("") ? result.append("\n").toString() : result.append(", fullName: ")
				.append(fullName).append("\n").toString();
	}

	// Overrides

	/**
	 * Override of the {@link Object#toString() toString()} method from {@link Object}.
	 * 
	 * Gets all the {@code User} Information
	 * 
	 * @return a String with the {@code User}'s username, password, email and fullname.
	 */
	@Override
	public String toString() {

		StringBuilder result = new StringBuilder("username: ");

		result.append(username).append(", password: ").append(password).append(", email: ")
				.append(email);

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
	 * Override of the method {@link Element#getIdentification() getIdentification()} from the
	 * {@link Element} Interface.
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
	public String getFullName() {

		return fullName;
	}
}