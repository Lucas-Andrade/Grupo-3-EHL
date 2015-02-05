package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.commands.patchcommands.PatchAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link ParsingCommand factories} that produce commands that patch
 * airships. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link String Strings}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommandsFactory extends UserAuthenticatingFactory< Airship, CompletionStatus > {
    
    
    
    // CONSTRUCTOR
    /**
     * Creates a new {@link PatchAirshipCommandsFactory} that produces commands of type
     * {@link PatchAirshipCommand}.
     * 
     * @param usersDatabase
     *            - The database that contains the user that is deleting the airship from
     *            {@link #airshipDatabase}.
     * @param airshipDatabase
     *            - The airship database whose elements can be deleted.
     * 
     * @throws InvalidArgumentException
     *             If either {@code usersDatabase} or {@code airshipDatabase} are {@code null}.
     */
    public PatchAirshipCommandsFactory( Database< User > usersDatabase,
                                        Database< Airship > airshipDatabase )
        throws InvalidArgumentException {
    
        super( usersDatabase, airshipDatabase );
    }
    
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM CommandFactory AND UserAuthenticatingFactory
    
    /**
     * @see main.java.cli.parsingtools.commandfactories.CommandFactory#getCommandsDescription()
     */
    @Override
    public String getCommandsDescription() {
    
        return "Change an Airship Coordinates and/or AirCorridor";
    }
    
    /**
     * @see UserAuthenticatingFactory#internalInternalNewCommand(java.util.Map,
     *      main.java.domain.model.users.User)
     */
    @Override
    protected Callable< CompletionStatus > internalInternalNewCommand( Map< String, String > parametersMap,
                                                             User authenticatedUser )
        throws InvalidParameterValueException {
    
        return new PatchAirship_ParsingCommand( parametersMap, authenticatedUser ).newCommand();
    }
    
    /**
     * @see UserAuthenticatingFactory#getSpecificRequiredParametersNames()
     */
    @Override
    protected String[] getSpecificRequiredParametersNames() {
    
        return new String[]{ CLIStringsDictionary.FLIGHTID };
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are responsible for creating commands of type
     * {@link PatchAirshipCommand} after interpreting the string parameters received in the
     * parameters map.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    private class PatchAirship_ParsingCommand extends ParsingCommand< CompletionStatus > {
        
        
        // INSTANCE FIELDS
        
        /**
         * {@code identification} - The identification of the airship we will
         */
        private String flightId;
        
        /**
         * The properties of the airship to be added.
         */
        private Double latitude;
        private Double longitude;
        private Double altitude;
        private Double minAltitude;
        private Double maxAltitude;
        
        /**
         * The user who is changing the airship.
         */
        private User authenticatedUser;
        
        
        // CONSTRUCTOR
        /**
         * Creates a new {@link PatchAirshipCommandsFactory} that produces commands of type
         * {@link PatchAirshipCommand} or {@link PatchMilitaryAirshipCommand}.
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
         * @param authenticatedUser
         *            The user who is changing the airship.
         */
        public PatchAirship_ParsingCommand( Map< String, String > parametersMap,
                                            User authenticatedUser ) {
        
            super( parametersMap );
            this.authenticatedUser = authenticatedUser;
        }
        
        
        
        // OTHER METHODS
        
        /**
         * Returns a command of type {@link PatchAirshipCommand} after extracting the value of the
         * parameters from the {@code parametersMap}.
         * 
         * @return A command of type {@link PatchAirshipCommand}.
         * @throws InvalidParameterValueException
         *             If the values received for latitude, longitude, altitude and air corridor's
         *             limits are invalid (cannot be converted into {@link Double}s).
         */
        @Override
        protected Callable< CompletionStatus > newCommand() throws InvalidParameterValueException {
        
            setFields();
            
            try {
                return new PatchAirshipCommand( databaseToChange, flightId, authenticatedUser,
                                                latitude, longitude, altitude, maxAltitude,
                                                minAltitude );
                
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED ERROR IN PatchAirshipCommandsFactory!",
                                                  e );
                // never happens for databaseWhereToPost is not null
            }
        }
        
        
        /**
         * Sets the fields with the values received in the parameters map or {@code null} in case
         * they are missing.
         * 
         * @throws InvalidParameterValueException
         *             If the values received for latitude, longitude, altitude and air corridor's
         *             limits are invalid (cannot be converted into {@link Double}s).
         */
        private void setFields() throws InvalidParameterValueException {
        
            try {
                flightId = getParameterAsString( CLIStringsDictionary.FLIGHTID );
            }
            catch( MissingRequiredParameterException e ) {
                flightId = null;
            }
            
            latitude = getParameterValueOrNull( CLIStringsDictionary.LATITUDE );
            longitude = getParameterValueOrNull( CLIStringsDictionary.LONGITUDE );
            altitude = getParameterValueOrNull( CLIStringsDictionary.ALTITUDE );
            maxAltitude = getParameterValueOrNull( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE );
            minAltitude = getParameterValueOrNull( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE );
            
        }
        
        /**
         * Returns the value of the parameter with name {@code parameterName} or {@code null} if the
         * parameter is missing.
         * 
         * @param parameterName
         *            The name of the parameter.
         * @return The value received or {@code null}.
         * @throws InvalidParameterValueException
         *             If the parameter's value is not convertible into a {@code Double} (e.g. if it
         *             contains letters). This exception's message is <i>«Required parameter with
         *             name {@code parameterName} has invalid value {@code theValue}.»</i>.
         */
        private Double getParameterValueOrNull( String parameterName )
            throws InvalidParameterValueException {
        
            try {
                return getParameterAsDouble( parameterName );
            }
            catch( MissingRequiredParameterException e ) {
                return null;
            }
        }
        
        
    }
    
}
