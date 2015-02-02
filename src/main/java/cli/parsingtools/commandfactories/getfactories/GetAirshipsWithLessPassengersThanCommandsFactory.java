package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.GetAirshipsWithLessPassengersThanCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands of
 * type {@link GetAirshipsWithLessPassengersThanCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Optional} {@link Iterable
 * Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommandsFactory extends
        StringsToCommandsFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final Database<Airship> airshipsDatabase;
    
    /**
     * {@code maximumNumberOfPassengers} - The maximum number of passengers allowed.
     */
    private int maximumNumberOfPassengers;
    
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
    public GetAirshipsWithLessPassengersThanCommandsFactory( Database<Airship> airshipsDatabase )
        throws InvalidArgumentException {
        
        super( "Gets all airships that are transgressing their air corridors." );
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.requiredParametersNames =
                new String[]{ CLIStringsDictionary.NUMBEROFPASSENGERS_UPPERLIMIT };
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
     * @throws InvalidParameterValueException
     *             If the value received to be interpreted as the maximum number of passengers is
     *             not convertible to integer.
     * @throws InvalidArgumentException
     *             If the {@code database} is null or the {@code maximumNumberOfPassengers} is less
     *             than 0.
     * @throws MissingRequiredParameterException
     *             If {@link #parametersMap} does not contain a parameter with name {@code name}
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>> internalNewInstance()
        throws InvalidParameterValueException, InvalidArgumentException,
        MissingRequiredParameterException {
        
        setMaxOfPassengersValueOfTheParametersMap();
        
        return new GetAirshipsWithLessPassengersThanCommand( airshipsDatabase,
                                                             maximumNumberOfPassengers );
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command - in
     * this case the name of the parameter that contains the maximum number of passengers allowed.
     * 
     * @return {@code requiredParametersNames}.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return requiredParametersNames;
    }
    
    // PRIVATE AUXILIAR METHOD
    
    /**
     * Sets the value of the field {@link #maximumNumberOfPassengers} with the value received in the
     * parameters map.
     * <p>
     * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn, this
     * last one is called inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed
     * that the field {@link #maximumNumberOfPassengers} is non-{@code null} after this method
     * finishes its job.
     * </p>
     * 
     * @throws InvalidParameterValueException
     *             If the value received to be interpreted as the maximum number of passengers is
     *             not convertible to integer.
     * @throws MissingRequiredParameterException
     *             If {@link #parametersMap} does not contain a parameter with name {@code name}.
     * 
     * @see {@link StringsToCommandsFactory#getParameterAsInt() getParameterAsInt()}.
     */
    private void setMaxOfPassengersValueOfTheParametersMap()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        maximumNumberOfPassengers = getParameterAsInt( requiredParametersNames[0] );
    }
}