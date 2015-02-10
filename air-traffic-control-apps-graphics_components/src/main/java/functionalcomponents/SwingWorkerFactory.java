package functionalcomponents;


import javax.swing.SwingWorker;


/**
 * Creates instances of {@link ExceptionHandler_SW} for a specific type of {@link FunctionalWindow}.
 *
 * @param <S>
 *            The type of the {@link ExceptionHandler_SW} returned in the method
 *            {@link #newInstance()}.
 * @param <R>
 *            The type of the results returned by the methods
 *            {@link ExceptionHandler_SW#doInBackground()} and {@link ExceptionHandler_SW#get()}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface SwingWorkerFactory< S extends SwingWorker< R, Void >, R > {
    
    /**
     * Produces a new instance of {@link ExceptionHandler_SW} of type {@code S}.
     * 
     * @return A new instance of {@link ExceptionHandler_SW}.
     */
    public S newInstance();
    
}
