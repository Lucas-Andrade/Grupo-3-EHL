package app;


import java.lang.reflect.Field;
import javax.swing.SwingWorker;
import swingworkers.SwingWorkerFactory;
import exceptions.InternalErrorException;
import exceptions.SwingWorkerFactoryMissingException;


/**
 * Class that provides utility methods. Several functional components implement similar methods, for
 * instance,
 * <ul>
 * <li>static methods that set {@link SwingWorkerFactory}s in static fields. Their implementation
 * cannot be inherited since the methods are static, so this class provides an implementation (that
 * uses reflection) to be invoked by those static methods;</li>
 * <li>similar methods to create and run {@link SwingWorker}s using different
 * {@link SwingWorkerFactory}s. Their implementation cannot be inherited since the methods make use
 * of static fields, so this class provides an implementation to be invoked by those methods.</li>
 * </ul>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Utils {
    
    
    /**
     * Sets a {@link SwingWorkerFactory} in a static field of a class.
     * 
     * @param theClass
     *            The class where to set the {@code factoryToSetInTheField} in the field with name
     *            {@code publicStaticFieldName}.
     * @param staticFieldName
     *            The name of the static field where to set {@code factoryToSetInTheField}.
     * @param factoryToSetInTheField
     *            The {@link SwingWorkerFactory} to be set in {@code staticField}.
     * @param factoryLock
     *            The object to lock this setting operation.
     * @return {@code true} if {@code factoryToSetInTheField} was set; <br/>
     *         {@code false} if there was a factory already set in {@code staticField} or
     *         {@code factoryToSetInTheField} is {@code null}.
     */
    public static final
            boolean
            setSWFactory( Class< ? > theClass,
                          String staticFieldName,
                          SwingWorkerFactory< ? extends SwingWorker< ?, ? >, ? > factoryToSetInTheField,
                          Object factoryLock ) {
    
        try {
            
            
            if( factoryToSetInTheField != null )
                synchronized (factoryLock) {
                    
                    Field publicStaticField = theClass.getDeclaredField( staticFieldName );
                    publicStaticField.setAccessible( true );
                    
                    if( publicStaticField.get( null ) == null ) {
                        publicStaticField.set( null, factoryToSetInTheField );
                        publicStaticField.setAccessible( false );
                        return true;
                    }
                    
                    publicStaticField.setAccessible( false );
                }
            return false;
            
            
//          "EQUALS" THE FOLLOWING        
//
//          if( factoryToSetInTheField != null )
//              synchronized (factoryLock) {
//                  if( publicStaticField == null ) {
//                      publicStaticField = factoryToSetInTheField;
//                      return true;
//                  }
//              }
//          return false;
            
            
        }
        catch( NoSuchFieldException | SecurityException | IllegalArgumentException
               | IllegalAccessException e ) {
            
            throw new InternalErrorException( theClass.getSimpleName()
                                              + " IS INCORRECTLY USING METHOD setSWFactory!", e );
        }
    }
    
    /**
     * Creates and runs a {@link SwingWorker} created by {@code swFactory} (via the method
     * {@link SwingWorkerFactory#newInstance()}).
     * 
     * @param swFactory
     *            The {@link SwingWorkerFactory} that will create the {@link SwingWorker}.
     * @param nameOfTheClass
     *            The name of the class where {@code swFactory} is supposed to be set.
     * @throws SwingWorkerFactoryMissingException
     *             If {@code swFactory} is {@code null}. This exception's message is <i>«Missing
     *             SwingWorkerFactory in class {@code nameOfTheClass}.»</i>
     */
    public static void runNewSwingWorker( SwingWorkerFactory< ?, ? > swFactory,
                                          String nameOfTheClass )
        throws SwingWorkerFactoryMissingException {
    
        if( swFactory == null )
            throw new SwingWorkerFactoryMissingException( nameOfTheClass );
        swFactory.newInstance().run();
    }
    
    
    
    /**
     * Unused private constructor.
     */
    private Utils() {
    
    }
    
}
