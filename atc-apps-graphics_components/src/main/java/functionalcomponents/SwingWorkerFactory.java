package functionalcomponents;


import javax.swing.SwingWorker;


/**
 * Creates instances of {@link ExceptionHandlerSW} for a specific type of {@link FunctionalWindow}.
 *
 * @param <S>
 *            The type of the {@link ExceptionHandlerSW} returned in the method
 *            {@link #newInstance()}.
 * @param <R>
 *            The type of the results returned by the methods
 *            {@link ExceptionHandlerSW#doInBackground()} and {@link ExceptionHandlerSW#get()}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface SwingWorkerFactory< S extends SwingWorker< R, Void >, R > {
    
    /**
     * Produces a new instance of {@link ExceptionHandlerSW} of type {@code S}.
     * 
     * @return A new instance of {@link ExceptionHandlerSW}.
     */
    public S newInstance();
    
}
