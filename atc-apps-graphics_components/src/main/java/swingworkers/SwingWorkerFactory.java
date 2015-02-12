package swingworkers;


import javax.swing.SwingWorker;


/**
 * Creates instances of a certain subtype of {@link SwingWorker}.
 *
 * @param <S>
 *            The type of the {@link SwingWorker} returned in the method {@link #newInstance()}.
 * @param <R>
 *            The type of the results returned by the methods {@link SwingWorker#doInBackground()}
 *            and {@link SwingWorker#get()}.
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
