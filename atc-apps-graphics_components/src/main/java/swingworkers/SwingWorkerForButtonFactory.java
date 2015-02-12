package swingworkers;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import functionalcomponents.infobuttons.EntitiesInfoButton;


/**
 * Interface for {@link SwingWorker} {@code factories}, that creates instances of
 * {@link SwingWorker} for a specific type of {@link EntitiesInfoButton}.
 *
 * @param <S>
 *            The type of the {@link SwingWorker} returned in the method {@link #newInstance()}.
 * @param <R>
 *            The type of the results returned by the methods {@link SwingWorker#doInBackground()}
 *            and {@link SwingWorker#get()}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface SwingWorkerForButtonFactory< S extends SwingWorker< R, Void >, R > {
    
    /**
     * Produces a new instance of {@link SwingWorker} of type {@code S}.
     * 
     * @return A new instance of {@link SwingWorker}.
     */
    public S newInstance( String identification, JTextArea textArea );
    
}
