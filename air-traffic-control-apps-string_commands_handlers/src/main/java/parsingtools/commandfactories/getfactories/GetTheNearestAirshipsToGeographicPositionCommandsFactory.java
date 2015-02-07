package parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;

import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.ParsingCommand;
import utils.CLIStringsDictionary;
import utils.Optional;

import commands.getcommands.GetTheNearestAirshipsToGeographicPositionCommand;

import databases.Database;
import elements.Airship;
import elements.airships.GeographicPosition;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;


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
     * The database where to search the elements from.
     */
    private final Database< Airship > airshipsDatabase;
    
    
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
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * 
     * Returns a command of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand} after
     * getting the necessary {@code required parameters} using the private auxiliar methods
     * {@link #setNumberOfAirshipsTofind()}, {@link #setLatitude()}, {@link #setLongitude()}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
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
    
        return new String[]{ CLIStringsDictionary.LATITUDE, CLIStringsDictionary.LONGITUDE,
                            CLIStringsDictionary.NUMBEROFAIRSHIPSTOFIND };
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
         * The numbers of airships closer to a specific {@link GeographicPosition} that we will get.
         */
        private int numberOfAirshipsToGet;
        
        /**
         * The longitude parameter of the Geographic Position.
         */
        private double longitude;
        
        /**
         * The latitude parameter of the Geographic Position.
         */
        private double latitude;
        
        /**
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
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
            setParametersFields();
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
        
        /**
         * Set the required parameters to create an
         * {@link GetTheNearestAirshipsToGeographicPositionCommand}.
         * 
         * @throws MissingRequiredParameterException
         *             If one parameter is null or the empty string.
         * @throws InvalidParameterValueException
         *             If one parameter value is not convertible into a {@code Double}
         */
        private void setParametersFields()
            throws InvalidParameterValueException, MissingRequiredParameterException {
        
            latitude = getParameterAsDouble( CLIStringsDictionary.LATITUDE );
            longitude = getParameterAsDouble( CLIStringsDictionary.LONGITUDE );
            numberOfAirshipsToGet = getParameterAsInt( CLIStringsDictionary.NUMBEROFAIRSHIPSTOFIND );
        }
    }
}
