package functionalcomponents.infobuttons;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import entities.Entity;
import entities.SimpleUser;
import functionalcomponents.SwingWorkerForButtonFactory;


/**
 * Instance of this class are {@link JButton}s with a {@link ActionListener} to GET the info of a
 * {@link SimpleUser} by its identification.
 * 
 * Each {@code Button} is associated to a {@link SimpleUser} {@code identification}, and write its
 * info on a given {@link JTextArea}.
 * 
 * The {@code SimpleUserInfoButton} have a {@link SwingWorkerForButtonFactory} that have to be HAVE
 * be initialized one and only one time with the {@link SimpleUserInfoButton#setSwingWorkerFactory
 * setSwingWorkerFactory} static method.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class SimpleUserInfoButton extends EntitiesInfoButton< SimpleUser > {
    
    /**
     * The {@link SwingWorker} factory, it HAVE be initialized one and only one time with the
     * {@link SimpleUserInfoButton#setSwingWorkerFactory setSwingWorkerFactory}.
     */
    private static SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > swingWorkerFactory;
    private static final Object lock = new Object();
    
    
    /**
     * Create a {@code SimpleUserInfoButton} associated to a {@link SimpleUser}
     * {@code identification}, that will GET and write its info on a given {@code JTextArea}.
     * 
     * @see EntitiesInfoButton#EntitiesInfoButton(String, JTextArea)
     * 
     * 
     * @param identification
     *            - The {@link Entity} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     */
    public SimpleUserInfoButton( String identification, JTextArea textArea ) {
    
        super( identification, textArea );
    }
    
    
    // Public static method
    /**
     * Initialize the {@code SwingWorkerFactory}. This method HAVE be called one and only one time.
     * 
     * @param swFactory
     *            - the SwingWorkerFactory
     * @return TODO
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > swFactory ) {
    
        synchronized (lock) {
            if( swingWorkerFactory == null && swFactory != null ) {
                
                swingWorkerFactory = swFactory;
                return true;
            }
            return false;
        }
    }
    
    // Protected method
    /**
     * Create a new {@link SwingWorker}.
     * 
     * @see EntitiesInfoButton#newSwingWorker(String, JTextArea)
     */
    @Override
    protected SwingWorker< SimpleUser, Void > newSwingWorker( String identification,
                                                              JTextArea textArea ) {
    
//      if( swFactory == null )
//      throw new SwingWorkerFactoryMissingException( this.getClass().getSimpleName() );
        return swingWorkerFactory.newInstance( identification, textArea );
    }
}
