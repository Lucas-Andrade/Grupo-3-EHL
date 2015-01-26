package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.domain.commands.postcommands.PostCivilAirshipCommand;
import main.java.domain.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands that
 * post airships. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link String Strings}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommandsFactory extends UserAuthenticatingFactory< Airship, String > {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code type} - The string representation of the airship's concrete type.
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
     * Creates a new {@link PostAirshipCommandsFactory} that produces commands of type
     * {@link PostCivilAirshipCommand} or {@link PostMilitaryAirshipCommand}.
     * 
     * @param postingUsersDatabase
     *            - The users database that stores the user who is posting.
     * @param airshipsDatabase
     *            - The database where to post the new airship.
     * 
     * @throws InvalidArgumentException
     *             If either {@code usersDatabase} or {@code airshipDatabase} are {@code null}.
     */
    public PostAirshipCommandsFactory( Database< User > postingUsersDatabase,
                                       Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
        
        super( "Adds a new airship.", postingUsersDatabase, airshipsDatabase );
        
        this.requiredParametersNames =
                new String[]{ CLIStringsDictionary.AIRSHIP_TYPE, CLIStringsDictionary.LATITUDE,
                             CLIStringsDictionary.LONGITUDE, CLIStringsDictionary.ALTITUDE,
                             CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE,
                             CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE };
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM UserAuthenticatingFactory
    
    /**
     * Returns a command of type {@link PostCivilAirshipCommand} or
     * {@link PostMilitaryAirshipCommand} after getting the necessary {@code required parameters}
     * using the private auxiliar method {@link #setValuesOfTheParametersMap()}.
     * 
     * The created command depends on the value of {@link #type} and its created using reflection.
     * 
     * @param userWhoIsPosting
     *            - The user who is posting the airship.
     * 
     * @return A command that post airships.
     * 
     * @throws MissingRequiredParameterException
     *             If any of the required parameters is missing.
     * @throws InvalidParameterValueException
     *             If any of the parameters have an invalid value.
     * @throws InternalErrorException
     *             Should never happen!
     */
    @SuppressWarnings( "unchecked" )
    @Override
    protected Callable< String > internalInternalNewInstance( User userWhoIsPosting )
        throws InternalErrorException, MissingRequiredParameterException,
        InvalidParameterValueException {
        
        setValuesOfTheParametersMap();
        
        String methodName = "post" + type + "Airship";
        Class< ? extends PostAirshipCommandsFactory > c = this.getClass();
        Class< ? extends User > u = userWhoIsPosting.getClass();
        
        try {
            Method creatorMethod = c.getDeclaredMethod( methodName, u );
            return (Callable< String >)creatorMethod.invoke( this, userWhoIsPosting );
            
        }
        catch( InvocationTargetException e ) {
            
            try {
                throw e.getTargetException();
                
            }
            catch( MissingRequiredParameterException e1 ) {
                throw (MissingRequiredParameterException)e1;
                
            }
            catch( InvalidParameterValueException e1 ) {
                throw (InvalidParameterValueException)e1;
                
            }
            catch( Throwable e1 ) {// If this happen make a call to the God of Java
                throw new InternalErrorException();
            }
            
        }
        catch( NoSuchMethodException | SecurityException | IllegalAccessException
               | IllegalArgumentException e ) {
            throw new InvalidParameterValueException( CLIStringsDictionary.AIRSHIP_TYPE, type );
        }
        
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command - in
     * this case it will return the name of the parameters that contain the airship's type and
     * properties.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getSpecificRequiredParameters() {
        
        return requiredParametersNames;
    }
    
    // PRIVATE AUXILIAR METHOD - used in the method postsInternalNewInstance()
    
    /**
     * Sets the value of the airship's type and properties fields with the values received in the
     * parameters map.
     * <p>
     * If this method is called inside {@link #internalNewInstance(Map)} and this one is called
     * inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that the fields
     * {@link #type}, {@link #latitude}, {@link #longitude}, {@link #altitude}, {@link #minAltitude}
     * and {@link #maxAltitude} are non- {@code null} after this method finishes its job.
     * </p>
     * 
     * @throws InvalidParameterValueException
     *             If the value received for a certain parameter is not convertible to correct type.
     * @throws MissingRequiredParameterException
     *             If any of the required parameters is missing.
     */
    private void setValuesOfTheParametersMap()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        type = getParameterAsString( CLIStringsDictionary.AIRSHIP_TYPE );
        latitude = getParameterAsDouble( CLIStringsDictionary.LATITUDE );
        longitude = getParameterAsDouble( CLIStringsDictionary.LONGITUDE );
        altitude = getParameterAsDouble( CLIStringsDictionary.ALTITUDE );
        minAltitude = getParameterAsDouble( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE );
        maxAltitude = getParameterAsDouble( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE );
    }
    
    // Methods Used With Reflection
    
    /**
     * Private method that is invoked using reflection inside the
     * {@link #postsInternalNewInstance()}.
     * <p>
     * <b> This method's name must be <i>postCivilAirship</i></b>.
     * 
     * @return A {@link PostCivilAirshipCommand}.
     * 
     * @throws MissingRequiredParameterException
     *             If the parameter on the number of passengers carried by the airship was not
     *             received.
     * @throws InvalidParameterValueException
     *             If the parameter on the number of passengers carried by the airship has an
     *             invalid value.
     * @throws InternalErrorException
     *             Should not happen.
     */
    @SuppressWarnings( "unused" )
    // used with reflection
            private
            Callable< String > postCivilAirship( User userWhoIsPosting )
                throws MissingRequiredParameterException, InvalidParameterValueException,
                InternalErrorException {
        
        if( !parametersMap.containsKey( CLIStringsDictionary.NUMBEROFPASSENGERS ) )
            throw new MissingRequiredParameterException( CLIStringsDictionary.NUMBEROFPASSENGERS );
        
        numberOfPassengers = getParameterAsInt( CLIStringsDictionary.NUMBEROFPASSENGERS );
        
        try {
            return new PostCivilAirshipCommand( latitude, longitude, altitude, maxAltitude,
                                                minAltitude, numberOfPassengers, databaseToChange,
                                                userWhoIsPosting );
        }
        catch( InvalidArgumentException e ) {// never happens for databaseWhereToPost is not null
            throw new InternalErrorException();
        }
    }
    
    /**
     * Private method that is invoked using reflection inside the
     * {@link #postsInternalNewInstance()}.
     * <p>
     * <b>This method's name must be <i>postMilitaryAirship</i></b>.
     * 
     * @return A {@link PostMilitaryAirshipCommand}.
     * 
     * @throws MissingRequiredParameterException
     *             If the parameter on whether the airship carries or does not carries armours was
     *             not received.
     * @throws InvalidParameterValueException
     *             If the parameter on whether the airship carries or does not carries armours has
     *             an invalid value.
     * @throws InternalErrorException
     *             Should not happen.
     */
    @SuppressWarnings( "unused" )
    private Callable< String > postMilitaryAirship( User userWhoIsPosting )
        throws MissingRequiredParameterException, InvalidParameterValueException,
        InternalErrorException {
        
        if( !parametersMap.containsKey( CLIStringsDictionary.HASARMOUR ) )
            throw new MissingRequiredParameterException( CLIStringsDictionary.HASARMOUR );
        
        hasArmour = getParameterAsBoolean( CLIStringsDictionary.HASARMOUR );
        
        try {
            return new PostMilitaryAirshipCommand( latitude, longitude, altitude, maxAltitude,
                                                   minAltitude, hasArmour, databaseToChange,
                                                   userWhoIsPosting );
        }
        catch( InvalidArgumentException e ) {// never happens for databaseWhereToPost is not null
            throw new InternalErrorException();
        }
    }
}