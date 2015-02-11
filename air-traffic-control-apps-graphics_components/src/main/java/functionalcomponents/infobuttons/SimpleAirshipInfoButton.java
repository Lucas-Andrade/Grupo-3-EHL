package functionalcomponents.infobuttons;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import entities.Entity;
import entities.SimpleAirship;
import functionalcomponents.SwingWorkerForButtonFactory;


/**
 * Instance of this class are {@link JButton}s with a {@link ActionListener} to GET the info of a
 * {@link SimpleAirship} by its identification.
 * 
 * Each {@code Button} is associated to a {@link SimpleAirship} {@code identification}, and write its
 * info on a given {@link JTextArea}.
 * 
 * The {@code SimpleAirshipInfoButton} have a {@link SwingWorkerForButtonFactory} that have to be HAVE
 * be initialized one and only one time with the {@link SimpleAirshipInfoButton#setSwingWorkerFactory
 * setSwingWorkerFactory} static method.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class SimpleAirshipInfoButton extends EntitiesInfoButton< SimpleAirship > {
    
    /**
     * The {@link SwingWorker} factory, it HAVE be initialized one and only one time with the
     * {@link SimpleAirshipInfoButton#setSwingWorkerFactory setSwingWorkerFactory}.
     */
    private static SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > factory;
    private static final Object lock = new Object();


    /**
     * Create a {@code SimpleUserInfoButton} associated to a {@link SimpleAirship}
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
    public SimpleAirshipInfoButton( String identification, JTextArea textArea ) {
    
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
            setSwingWorkerFactory( SwingWorkerForButtonFactory< SwingWorker< SimpleAirship, Void >, SimpleAirship > swFactory ) {
    
        synchronized (lock) {
            if( swFactory == null )
                return false;
            
            SimpleAirshipInfoButton.factory = swFactory;
            return true;
        }
    }
    
    // Protected method
    /**
     * Create a new {@link SwingWorker}.
     * 
     * @see EntitiesInfoButton#newSwingWorker(String, JTextArea)
     */
    @Override
    protected SwingWorker< SimpleAirship, Void > newSwingWorker( String identification,
                                                                 JTextArea textArea ) {
    
        return factory.newInstance( identification, textArea );
    }
}
