package utils;


import java.io.OutputStream;
import exceptions.InvalidParameterValueException;


/**
 * Interface whose implementors allow to execute a string-command and obtain its string-output and
 * also obtain the strongly-typed stream where to write the output to.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Executor {
    
    /**
     * Returns the string-output of a command.
     * 
     * @return The string-output of a command.
     * @throws Exception 
     */
    public String getOutput() throws Exception;
    
    /**
     * Returns the stream where to write the output to.
     * 
     * @return The stream where to write the output to.
     * @throws InvalidParameterValueException 
     */
    public OutputStream getStream() throws InvalidParameterValueException;
    
}
