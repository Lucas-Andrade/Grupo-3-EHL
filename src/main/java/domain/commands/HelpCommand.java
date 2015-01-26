package main.java.domain.commands;


import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.InvalidArgumentException;


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
     * The command parser that contains a registry of the commands whose descriptions are to be
     * returned by the command created by this factory.
     */
    private CommandParser cmdParser;
    
    /**
     * Creates a new instance of this command that gets an {@link OptionsList} whose options are the
     * string-commands registered in {@code cmdParser}.
     * 
     * @param cmdParser
     *            The command parser that contains a registry of the commands whose descriptions are
     *            to be returned by the command created by this factory.
     * @throws InvalidArgumentException
     *             If {@code cmdParser==null}.
     */
    public HelpCommand( CommandParser cmdParser ) throws InvalidArgumentException {
        
        if( cmdParser == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null parser." );
        this.cmdParser = cmdParser;
    }
    
    /**
     * Returns an {@link OptionsList} whose options are the string-commands registered in
     * {@code cmdParser} (given in the constructor).
     * 
     * @return A {@link OptionsList} with the commands registered in {@code cmdParser} and their
     *         descriptions.
     */
    public OptionsList call() {
        
        return new OptionsList( cmdParser.getRegisteredCommands() );
    }
    
}
