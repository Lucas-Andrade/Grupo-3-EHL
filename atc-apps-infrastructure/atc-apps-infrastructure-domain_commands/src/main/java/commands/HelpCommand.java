package commands;


import java.util.Map;
import java.util.concurrent.Callable;
import utils.OptionsList;
import exceptions.InvalidArgumentException;


/**
 * Class whose instances represent commands to get an {@link OptionsList} whose options are the
 * string-commands registered in a given {@link CommandParser}. Implements
 * {@code Callable<OptionsList>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class HelpCommand implements Callable< OptionsList > {
    
    // INSTANCE FIELD
    /**
     * The Map that contains a registry of the commands whose descriptions are to be returned by
     * this.
     */
    private Map< String, String > commandsDescription;
    
    /**
     * Creates a new instance of this command that gets an {@link OptionsList} whose options are the
     * string-commands registered in {@code commandsDescription}.
     * 
     * @param commandsDescription
     *            The Map that contains a registry of the commands whose descriptions are to be
     *            returned by this command.
     * @throws InvalidArgumentException
     *             If {@code commandsDescription==null}.
     */
    public HelpCommand( Map< String, String > commandsDescription ) throws InvalidArgumentException {
    
        if( commandsDescription == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate command with null commandsDescription." );
        this.commandsDescription = commandsDescription;
    }
    
    /**
     * Returns an {@link OptionsList} whose options are the string-commands registered in
     * {@code commandsDescription} (given in the constructor).
     * 
     * @return A {@link OptionsList} with the commands registered in {@code commandsDescription} and
     *         their descriptions.
     */
    public OptionsList call() {
    
        return new OptionsList( commandsDescription );
    }
}
