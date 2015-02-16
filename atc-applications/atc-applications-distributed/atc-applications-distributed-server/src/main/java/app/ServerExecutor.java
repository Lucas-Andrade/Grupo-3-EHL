package app;
import parsingtools.CommandParser;
import utils.StringCommandsExecutor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.UnsupportedAcceptValueException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import exceptions.InvalidArgumentException;


/**
 * Class whose instances are responsible for interpreting string-commands received by an HTTP server
 * request.
 * 
 * This class extends {@link StringCommandsExecutor} which has methods for returning the apropriate
 * response String to sent to the resquesting client.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ServerExecutor extends StringCommandsExecutor {
    
    // Constructor
    
    /**
     * Creates a new instance of {@link ServerExecutor} bound to a concrete string-command and to a
     * {@link CommandParser} instance. The method and path received in {@code args} (its first and
     * second entries) should match the method and path of one of the commands registered in
     * {@code cmdParser}.
     * <p>
     * To know more about string-commands correct syntax, see {@link CommandParser}'s documentation.
     * </p>
     * 
     * @param cmdParser
     *            The command parser whose register should contain the given string-command.
     * @param args
     *            The string-command. This array with 2 or 3 entries contains the string command's
     *            <ul>
     *            <li>method (in the entry of index 0),</li>
     *            <li>path (in the entry of index 1),</li>
     *            <li>parameters-list (optionally, in the entry of index 2)</li>
     *            </ul>
     * @throws InvalidArgumentException
     *             If {@code cmdParser==null}.
     * @throws InvalidCommandSyntaxException
     *             If {@code args}' length is smaller than 2 or bigger than 3.
     * @throws InvalidCommandParametersSyntaxException
     *             If the parameters in {@code args[2]} (if existent) are not correctly separated by
     *             '{@code &}' or a certain parameter has not the format {@code name=value}.
     * @throws DuplicateParametersException
     *             If several values for the same parameter have been received in the
     *             parameters-list.
     * @throws UnsupportedAcceptValueException
     *             If the {@code Accept} parameter value received is not supported by this
     *             application.
     * 
     * @see CommandParser
     */
    public ServerExecutor( CommandParser cmdParser, String[] args )
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        super( cmdParser, args );
    }
}
