package main.java.cli.parsingtools.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.domain.commands.HelpCommand;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce commands of type
 * {@link HelpCommand}. Commands are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class HelpCommandsFactory extends CommandFactory< OptionsList > {
    
    /**
     * The Map that contains a registry of the commands whose descriptions are to be returned by the
     * command created by this factory.
     */
    private Map< String, String > commandsDescription;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link HelpCommandsFactory} that produces commands of type {@link HelpCommand}.
     * 
     * @param commandsDescription
     *            - The Map with the registry of the commands whose descriptions are to be returned
     *            by the {@link HelpCommand} produced by this factory.
     * 
     * @throws InvalidArgumentException
     *             If the {@code commandsDescription} is null.
     */
    public HelpCommandsFactory( Map< String, String > commandsDescription )
        throws InvalidArgumentException {
        
        if( commandsDescription == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null parser." );
        
        this.commandsDescription = commandsDescription;
    }
    
    // IMPLEMENTED METHOD OF StringsToCommandsFactory ABSTRACT CLASS
    
    /**
     * Returns a command of type {@link HelpCommand}.
     * 
     * @return A command of type {@link HelpCommand}.
     */
    @Override
    public final Callable< OptionsList > internalNewCommand( Map< String, String > parametersMap ) {
        
        try {
            return new HelpCommand( commandsDescription );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException( "UNEXPECTED EXCEPTION IN HelpCommandsFactory!" );
            // never happens cause commandsDescription is not null
        }
    }
    
    /**
     * Returns an array of strings with the name of the parameters needed to produce
     * {@link HelpCommand}s - in this case it will return {@code null} because factories of this
     * type don't require any parameters to create their commands.
     * 
     * @return {@code null}.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return null;
    }
    
    @Override
    public String getCommandsDescription() {
        
        return "Returns the descriptions of known commands.";
    }
}