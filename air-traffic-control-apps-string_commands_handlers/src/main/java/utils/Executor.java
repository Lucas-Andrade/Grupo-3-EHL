package utils;


import java.io.OutputStream;


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
     */
    public String getOuput();
    
    /**
     * Returns the stream where to write the output to.
     * 
     * @return The stream where to write the output to.
     */
    public OutputStream getStream();
    
}
