package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.commands.getcommands.GetTheNearestAirshipsToGeographicPositionCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.GeographicPosition;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link CommandFactory factories} that produce commands of type
 * {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}. Commands are {@link Callable}
 * instances.
 * 
 * Extends {@link CommandFactory} of {@link Optional} {@link Iterable Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetTheNearestAirshipsToGeographicPositionCommandsFactory extends
        CommandFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final Database< Airship > airshipsDatabase;
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    
    
    // CONSTRUCTOR
    
    /**
     * Constructor of {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommandFactory} that
     * produces {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}.
     * 
     * @param airshipsDatabase
     *            The airships Database who stores all the airships.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public GetTheNearestAirshipsToGeographicPositionCommandsFactory( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException(
                                                "It's not allow instantiate a factory with null airships database" );
        
        this.airshipsDatabase = airshipsDatabase;
        this.requiredParametersNames =
                new String[]{ CLIStringsDictionary.LATITUDE, CLIStringsDictionary.LONGITUDE,
                             CLIStringsDictionary.NUMBEROFAIRSHIPSTOFIND };
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * 
     * Returns a command of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand} after
     * getting the necessary {@code required parameters} using the private auxiliar methods
     * {@link #setNumberOfAirshipsTofind()}, {@link #setLatitude()}, {@link #setLongitude()}.
     * 
     * @return a command of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}.
     * 
     * @throws InvalidParameterValueException
     *             If a received value (altitude, longitude or maximum number of passengers) is not
     *             convertible to double/integer.
     * @throws MissingRequiredParameterException
     *             If one of the {@link #parametersMap} does not contain a parameter.
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>>
            internalNewCommand( Map< String, String > parametersMap )
                throws InvalidParameterValueException, MissingRequiredParameterException {
        
        return new GetTNATGP_ParsingCommand( parametersMap ).newCommand();
    }
    
    /**
     * Method responsible to return a array who contains all the required parameters needed to
     * create a {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand} command.
     * 
     * @return a array with required parameters.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return requiredParametersNames;
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Get the nearest aircrafts of Geographic coordinates";
    }

    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a
     * {@link GetTheNearestAirshipsToGeographicPositionCommand}
     */
    private class GetTNATGP_ParsingCommand extends ParsingCommand< Optional< Iterable< Airship >> > {
        
        /**
         * {@code numberOfAirshipsToGet} - The numbers of airships closer to a specific
         * {@link GeographicPosition} that we will get.
         */
        private int numberOfAirshipsToGet;
        
        /**
         * {@code longitude} - The longitude parameter of the Geographic Position.
         */
        private double longitude;
        
        /**
         * {@code latitude} - The latitude parameter of the Geographic Position.
         */
        private double latitude;
        
        /**TODO
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         * 
         * @throws InvalidParameterValueException
         *             If the value received to be interpreted as the maximum number of passengers
         *             is not convertible to integer.
         * @throws MissingRequiredParameterException
         *             If {@link #parametersMap} does not contain a parameter with name {@code name}
         */
        public GetTNATGP_ParsingCommand( Map< String, String > parametersMap )
            throws InvalidParameterValueException, MissingRequiredParameterException {
            
            super( parametersMap );
            setLatitude();
            setLongitude();
            setNumberOfAirshipsTofind();
        }
        
        /**
         * @return A command of type {@link GetTheNearestAirshipsToGeographicPositionCommand}.
         */
        @Override
        public Callable< Optional< Iterable< Airship >>> newCommand() {
            
            try {
                return new GetTheNearestAirshipsToGeographicPositionCommand( airshipsDatabase,
                                                                             numberOfAirshipsToGet,
                                                                             latitude, longitude );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN GetAirshipsOfOwnerCommandsFactory!",
                                                  e );
                // never happens because database is not null
            }
        }
        
        // PRIVATE AUXILIAR METHODS
        
        /**
         * Sets the value of the field {@link #latitude} with the value received in the parameters
         * map needed to {@link GetTheNearestAirshipsToGeographicPositionCommand}.
         * 
         * This method calls the {@link ParsingCommand#getParameterAsDouble(String)} where it
         * searches on the Map the value of the airships latitude.
         * 
         * <p>
         * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn,
         * this last one is called inside {@link ParsingCommand#newCommand(Map)}, it is guaranteed
         * that the field {@link #latitude} is non-{@code null} after this method finishes its job.
         * </p>
         * 
         * @throws InvalidParameterValueException
         *             If the value received to be interpreted as the latitude is not convertible to
         *             a double.
         * @throws MissingRequiredParameterException
         *             If the {@link #parametersMap} does not contain a parameter with name
         *             {@code latitude}.
         */
        private void setLatitude()
            throws InvalidParameterValueException, MissingRequiredParameterException {
            
            latitude = getParameterAsDouble( requiredParametersNames[0] );
        }
        
        /**
         * Sets the value of the field {@link #longitude} with the value received in the parameters
         * map needed to {@link GetTheNearestAirshipsToGeographicPositionCommand}.
         * 
         * This method calls the {@link ParsingCommand#getParameterAsDouble(String)} where it
         * searches on the Map the value for the airships longitude.
         * 
         * <p>
         * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn,
         * this last one is called inside {@link ParsingCommand#newCommand(Map)}, it is guaranteed
         * that the field {@link #longitude} is non-{@code null} after this method finishes its job.
         * </p>
         * 
         * @throws InvalidParameterValueException
         *             If the value received to be interpreted as the longitude is not convertible
         *             to a double.
         * @throws MissingRequiredParameterException
         *             If the {@link #parametersMap} does not contain a parameter with name
         *             {@code longitude}
         */
        private void setLongitude()
            throws InvalidParameterValueException, MissingRequiredParameterException {
            
            longitude = getParameterAsDouble( requiredParametersNames[1] );
        }
        
        /**
         * 
         * Method responsible to set the number of airships to get field needed to
         * {@link GetTheNearestAirshipsToGeographicPositionCommand}.
         * 
         * * This method calls the {@link ParsingCommand#getParameterAsInt(String)} where it
         * searches on the Map the value for the airships longitude.
         * 
         * <p>
         * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn,
         * this last one is called inside {@link ParsingCommand#newCommand(Map)}, it is guaranteed
         * that the field {@link #numberOfAirshipsToGet} is non-{@code null} after this method
         * finishes its job.
         * </p>
         * 
         * @throws InvalidParameterValueException
         *             If the value received to be interpreted as the maximum number of passengers
         *             is not convertible to a int.
         * @throws MissingRequiredParameterException
         *             If the {@link #parametersMap} does not contain a parameter with name
         *             {@code nbPassengers}
         */
        private void setNumberOfAirshipsTofind()
            throws InvalidParameterValueException, MissingRequiredParameterException {
            
            numberOfAirshipsToGet = getParameterAsInt( requiredParametersNames[2] );
        }
    }
}
