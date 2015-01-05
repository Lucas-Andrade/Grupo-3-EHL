package main.java.cli.commandfactories.postfactories;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.StringsDictionary;
import main.java.cli.commandfactories.CallablesFactory;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.postcommands.PostCivilAirshipCommand;
import main.java.cli.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.users.User;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce
 * commands that post airships. Commands are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommandsFactory extends
		PostCommandsFactory< Airship, String >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The array of strings with the names of the parameters needed to produce
	 * the command.
	 */
	private final String[] requiredParametersNames;
	
	/**
	 * The string representation of the airship's concrete type.
	 */
	private String type;
	
	/**
	 * The properties of the airship to be added.
	 */
	private double latitude;
	private double longitude;
	private double altitude;
	private double minAltitude;
	private double maxAltitude;
	private int numberOfPassengers; // not null only if Civil
	private boolean hasArmour; // not null only if Military
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link PostAirshipCommandsFactory} that produces commands
	 * that post airships.
	 * 
	 * @param postingUsersDatabase
	 *            The users database that stores the user who is posting.
	 * @param airshipsDatabase
	 *            The database where to post the new airship.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public PostAirshipCommandsFactory( Database< User > postingUsersDatabase,
			Database< Airship > airshipsDatabase )
			throws InvalidArgumentException {
		
		super( "Adds a new airship.", postingUsersDatabase, airshipsDatabase );
		
		this.requiredParametersNames = new String[]{
				StringsDictionary.AIRSHIP_TYPE,
				StringsDictionary.LATITUDE,
				StringsDictionary.LONGITUDE,
				StringsDictionary.ALTITUDE,
				StringsDictionary.AIRCORRIDOR_MINALTITUDE,
				StringsDictionary.AIRCORRIDOR_MAXALTITUDE };
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM PostCommandsFactory
	
	/**
	 * Returns a command that post airships.
	 * 
	 * @return A command that post airships.
	 * @throws MissingRequiredParameterException
	 * @throws InvalidParameterValueException
	 * @throws InternalErrorException
	 */
	@Override
	protected Callable< String > postsInternalNewInstance( User userWhoIsPosting )
			throws InternalErrorException, MissingRequiredParameterException,
			InvalidParameterValueException {
		
		getValuesOfTheParametersMap();
		
		
		String methodName = "post" + type + "Airship";
		Class< ? extends PostAirshipCommandsFactory > c = this.getClass();
		Class< ? extends User > u = userWhoIsPosting.getClass();
		
		try
		{
			Method creatorMethod = c.getDeclaredMethod( methodName, u );
			return (Callable< String >)creatorMethod.invoke( this,
					userWhoIsPosting );
		}
		catch( InvocationTargetException e )
		{
			try
			{
				throw e.getTargetException();
			}
			catch( MissingRequiredParameterException e1 )
			{
				throw (MissingRequiredParameterException)e1;
			}
			catch( InvalidParameterValueException e1 )
			{
				throw (InvalidParameterValueException)e1;
			}
			catch( Throwable e1 )
			{// If this happen make a call to the God of Java
				throw new InternalErrorException();
			}
		}
		catch( NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException e )
		{
			throw new InvalidParameterValueException(
					StringsDictionary.AIRSHIP_TYPE, type );
		}
		
	}
	
	/**
	 * Returns an array of strings with name of the parameters needed to produce
	 * the command: the name of the parameters that contain the airship's type
	 * and properties.
	 * 
	 * @return An array of strings with the name of the parameters that contain
	 *         the airship's type and properties.
	 */
	@Override
	protected String[] getSpecificRequiredParameters() {
		
		return requiredParametersNames;
	}
	
	
	
	// AUXILIARY PRIVATE METHODS - used in the method postsInternalNewInstance()
	
	/**
	 * Sets the value of the airship's type and properties fields with the
	 * values received in the parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and
	 * this one is called inside
	 * {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that
	 * the fields {@link #type}, {@link #latitude}, {@link #longitude},
	 * {@link #altitude}, {@link #minAltitude} and {@link #maxAltitude} are non-
	 * {@code null} after this method finishes its job.
	 * </p>
	 * 
	 * @throws InvalidParameterValueException
	 *             If the value received for a certain parameter is not
	 *             convertible to correct type.
	 */
	private void getValuesOfTheParametersMap()
			throws InvalidParameterValueException {
		
		type = getParameterAsString( StringsDictionary.AIRSHIP_TYPE );
		latitude = getParameterAsDouble( StringsDictionary.LATITUDE );
		longitude = getParameterAsDouble( StringsDictionary.LONGITUDE );
		altitude = getParameterAsDouble( StringsDictionary.ALTITUDE );
		minAltitude = getParameterAsDouble( StringsDictionary.AIRCORRIDOR_MINALTITUDE );
		maxAltitude = getParameterAsDouble( StringsDictionary.AIRCORRIDOR_MAXALTITUDE );
	}
	
	/**
	 * Private method is invoked using reflection inside the
	 * {@link #postsInternalNewInstance()}. <b>This method's name must be
	 * <i>post{@link StringsDictionary#CIVIL} Airship</i></b>.
	 * 
	 * @return A command that posts civil airships.
	 * @throws MissingRequiredParameterException
	 *             If the parameter on the number of passengers carried by the
	 *             airship was not received.
	 * @throws InvalidParameterValueException
	 *             If the parameter on the number of passengers carried by the
	 *             airship has an invalid value.
	 * @throws InternalErrorException
	 *             Should not happen.
	 */
	@SuppressWarnings( "unused" )
	// used with reflection
	private Callable< String > postCivilAirship( User userWhoIsPosting )
			throws MissingRequiredParameterException,
			InvalidParameterValueException, InternalErrorException {
		
		if( !parametersMap
				.containsKey( StringsDictionary.NUMBEROFPASSENGERS ) )
			throw new MissingRequiredParameterException(
					StringsDictionary.NUMBEROFPASSENGERS );
		
		numberOfPassengers = getParameterAsInt( StringsDictionary.NUMBEROFPASSENGERS );
		
		try
		{
			return new PostCivilAirshipCommand( latitude, longitude, altitude,
					maxAltitude, minAltitude, numberOfPassengers,
					databaseWhereToPost, userWhoIsPosting );
		}
		catch( InvalidArgumentException e )
		{// never happens for databaseWhereToPost is not null
			throw new InternalErrorException();
		}
	}
	
	/**
	 * Private method is invoked using reflection inside the
	 * {@link #postsInternalNewInstance()}. <b>This method's name must be
	 * <i>post{@link StringsDictionary#MILITARY}
	 * Airship</i></b>.
	 * 
	 * @return A command that posts military airships.
	 * @throws MissingRequiredParameterException
	 *             If the parameter on whether the airship carries or does not
	 *             carries armours was not received.
	 * @throws InvalidParameterValueException
	 *             If the parameter on whether the airship carries or does not
	 *             carries armours has an invalid value.
	 * @throws InternalErrorException
	 *             Should not happen.
	 */
	@SuppressWarnings( "unused" )
	// used with reflection
	private Callable< String > postMilitaryAirship( User userWhoIsPosting )
			throws MissingRequiredParameterException,
			InvalidParameterValueException, InternalErrorException {
		
		if( !parametersMap
				.containsKey( StringsDictionary.HASARMOUR ) )
			throw new MissingRequiredParameterException(
					StringsDictionary.HASARMOUR );
		
		hasArmour = getParameterAsBoolean( StringsDictionary.HASARMOUR );
		
		try
		{
			return new PostMilitaryAirshipCommand( latitude, longitude,
					altitude, maxAltitude, minAltitude, hasArmour,
					databaseWhereToPost, userWhoIsPosting );
		}
		catch( InvalidArgumentException e )
		{// never happens for databaseWhereToPost is not null
			throw new InternalErrorException();
		}
	}
	
	
	
}
