package functionalcomponents.infobuttons;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerForButtonFactory;
import entities.SimpleUser;
import exceptions.SwingWorkerFactoryMissingException;


/**
 * Instance of this class are {@link JButton}s with a {@link ActionListener} to GET the info of a
 * {@link SimpleUser} by its identification.
 * 
 * Each {@code Button} is associated to a {@link SimpleUser} {@code identification}, and write its
 * info on a given {@link JTextArea}.
 * 
 * The {@code SimpleAirshipInfoButton} have a {@link SwingWorkerForButtonFactory} that has to be
 * initialized in a static way with the
 * {@link SimpleUserInfoButton#setSwingWorkerFactory(SwingWorkerForButtonFactory)
 * setSwingWorkerFactory} method.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class SimpleUserInfoButton extends EntitiesInfoButton< SimpleUser > {
    
    
    // Static fields
    /**
     * The {@link SwingWorker} factory, it HAVE be initialized one and only one time with the
     * {@link SimpleUserInfoButton#setSwingWorkerFactory setSwingWorkerFactory}.
     */
    private static SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > swingWorkerFactory;
    private static final Object lock = new Object();
    
    
    
    // Public static method
    /**
     * Sets the {@code SwingWorkerFactory} in a field of a class.
     * 
     * @param swFactory
     *            - The {@link SwingWorkerForButtonFactory} to be set in {@code swingWorkerFactory}.
     * @return {@code true} if {@code factoryToSetInTheField} was set; <br>
     *         {@code false} if there was a factory already set in {@code staticField} or
     *         {@code factoryToSetInTheField} is {@code null}.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > swFactory ) {
    
        if( swFactory != null )
            synchronized (lock) {
                if( swingWorkerFactory == null ) {
                    swingWorkerFactory = swFactory;
                    return true;
                }
            }
        return false;
    }
    
    
    
    // Constructor
    /**
     * Create a {@code SimpleUserInfoButton} associated to a {@link SimpleUser}
     * {@code identification}, that will GET and write its info on a given {@code JTextArea}.
     * 
     * @see EntitiesInfoButton#EntitiesInfoButton(String, JTextArea)
     * 
     * 
     * @param identification
     *            - The {@link SimpleUser} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     */
    public SimpleUserInfoButton( String identification, JTextArea textArea ) {
    
        super( identification, textArea );
    }
    
    
    
    // Protected method
    /**
     * Creates and runs a {@link SwingWorker} created by the {@code swingWorkerFactory}.
     * 
     * @throws SwingWorkerFactoryMissingException
     *             If {@code swingWorkerFactory} is {@code null}. This exception's message is
     *             <i>«Missing SwingWorkerFactory in class {@code nameOfTheClass}.»</i>
     * 
     * @see EntitiesInfoButton#runNewSwingWorker(String, JTextArea)
     */
    @Override
    protected void runNewSwingWorker( String identification, JTextArea textArea )
        throws SwingWorkerFactoryMissingException {
    
        if( swingWorkerFactory == null )
            throw new SwingWorkerFactoryMissingException( this.getClass().getSimpleName() );
        
        swingWorkerFactory.newInstance( identification, textArea ).run();
    }
}
