package functionalcomponents.infobuttons;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import swingworkers.SwingWorkerForButtonFactory;
import entities.SimpleUser;
import exceptions.SwingWorkerFactoryMissingException;


/**
 * Class whose instances are {@link JButton}s with {@link ActionListener}s that print the
 * information about a {@link SimpleUser} by its identification.
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
    
    
    // STATIC MEMBERS
    
    /**
     * The {@link SwingWorkerFactory} that produces swingworkers that returns the user with a given
     * username.
     */
    private static SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > swFactory;
    
    /**
     * A lock for {@link #swFactory}.
     */
    private static final Object factoryLock = new Object();
    
    /**
     * Sets the {@link SwingWorkerForButtonFactory} that produces swingworkers that returns the user
     * with a given username.
     * 
     * @param factory
     *            The {@link SwingWorkerForButtonFactory} that produces swingworkers that returns
     *            the user with a given username.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers
     *         that returns the user with a given username; <br/>
     *         {@code false} if there was a factory already set or {@code factory} is {@code null}.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerForButtonFactory< SwingWorker< SimpleUser, Void >, SimpleUser > factory ) {
    
        if( factory != null )
            synchronized (factoryLock) {
                if( swFactory == null ) {
                    swFactory = factory;
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
     * Creates and runs a {@link SwingWorker} created by the {@link #swFactory}.
     * 
     * @throws SwingWorkerFactoryMissingException
     *             If {@code swingWorkerFactory} is {@code null}. This exception's message is
     *             <i>«Missing SwingWorkerFactory in class SimpleUserInfoButton.»</i>
     * 
     * @see EntitiesInfoButton#runNewSwingWorker(String, JTextArea)
     */
    @Override
    protected void runNewSwingWorker( String identification, JTextArea textArea )
        throws SwingWorkerFactoryMissingException {
    
        if( swFactory == null )
            throw new SwingWorkerFactoryMissingException( this.getClass().getSimpleName() );
        
        swFactory.newInstance( identification, textArea ).run();
    }
    
}
