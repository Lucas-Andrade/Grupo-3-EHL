package parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;

import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.ParsingCommand;
import utils.CLIStringsDictionary;
import utils.Optional;

import commands.getcommands.GetAirshipsWithLessPassengersThanCommand;

import databases.Database;
import elements.Airship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link CommandFactory factories} that produce commands of type
 * {@link GetAirshipsWithLessPassengersThanCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link CommandFactory} of {@link Optional} {@link Iterable Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommandsFactory extends
        CommandFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final Database< Airship > airshipsDatabase;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAllElementsInADatabaseCommandFactory factory} that produces commands
     * of type {@link GetAirshipsWithLessPassengersThanCommand}.
     * 
     * @param airshipsDatabase
     *            - The database where to get the airships from.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public GetAirshipsWithLessPassengersThanCommandsFactory( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
    
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.airshipsDatabase = airshipsDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * Returns a command of type {@link GetAirshipsWithLessPassengersThanCommand} after getting the
     * necessary {@code required parameters} using the private auxiliar method
     * {@link #setMaxOfPassengersValueOfTheParametersMap()}.
     * 
     * @return A command of type {@link GetAirshipsWithLessPassengersThanCommand}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * 
     * @throws InvalidParameterValueException
     *             If the value received to be interpreted as the maximum number of passengers is
     *             not convertible to integer.
     * @throws MissingRequiredParameterException
     *             If {@link #parametersMap} does not contain a parameter with name {@code name}
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>>
            internalNewCommand( Map< String, String > parametersMap )
                throws InvalidParameterValueException, MissingRequiredParameterException {
    
        return new GetAWLPT_ParsingCommand( parametersMap ).newCommand();
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command - in
     * this case the name of the parameter that contains the maximum number of passengers allowed.
     * 
     * @return {@code requiredParametersNames}.
     */
    @Override
    protected String[] getRequiredParametersNames() {
    
        return new String[]{ CLIStringsDictionary.NUMBEROFPASSENGERS_UPPERLIMIT };
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
    
        return "Gets all airships that are transgressing their air corridors.";
    }
    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a
     * {@link GetAirshipsWithLessPassengersThanCommand}
     */
    private class GetAWLPT_ParsingCommand extends ParsingCommand< Optional< Iterable< Airship >> > {
        
        /**
         * {@code maximumNumberOfPassengers} - The maximum number of passengers allowed.
         */
        private int maximumNumberOfPassengers;
        
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
        public GetAWLPT_ParsingCommand( Map< String, String > parametersMap )
            throws InvalidParameterValueException, MissingRequiredParameterException {
        
            super( parametersMap );
            maximumNumberOfPassengers =
                    getParameterAsInt( CLIStringsDictionary.NUMBEROFPASSENGERS_UPPERLIMIT );
        }
        
        /**
         * @return A command of type {@link GetAirshipsWithLessPassengersThanCommand}.
         */
        @Override
        public Callable< Optional< Iterable< Airship >>> newCommand() {
        
            try {
                return new GetAirshipsWithLessPassengersThanCommand( airshipsDatabase,
                                                                     maximumNumberOfPassengers );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN GetAirshipsOfOwnerCommandsFactory!",
                                                  e );
                // never happens because database is not null
            }
        }
    }
}
