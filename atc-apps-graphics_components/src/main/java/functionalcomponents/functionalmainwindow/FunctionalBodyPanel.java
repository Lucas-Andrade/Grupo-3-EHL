package functionalcomponents.functionalmainwindow;


import java.lang.reflect.Method;
import javax.swing.JPanel;
import swingworkers.ExceptionHandlerSW;
import swingworkers.SwingWorkerFactory;
import design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import design.panels.mainwindowpanels.JWorldMapWithAirships;
import design.windows.WindowBase;
import entities.SimpleAirship;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;


/**
 * Class whose instances have the purpose of add functionality to a {@link JBodyPanelForMainWindow}.
 * Giving functionality to a window means: <br>
 * -> GET all {@link SimpleAirship}s in the "system" and update the {@code BodyPanel} with that
 * received list using {@link FunctionalBodyPanel#updateBodyPanel() updateBodyPanel()} method.<br>
 * 
 * -> Update the {@code BodyPanel} with a new list of {@link SimpleAirship}s, using
 * {@link FunctionalBodyPanel#updateBodyPanel(Iterable) updateBodyPanel(iterable)} method.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalBodyPanel {
    
    // Static fields
    /**
     * The {@link JBodyPanelForMainWindow} we want to add functionality to.
     */
    public final JBodyPanelForMainWindow basePanel;
    
    /**
     * The {@link SwingWorkerFactory} that produces {@link FunctionalLoginWindow.SwingWorker}s for
     * the {@link FunctionalLoginWindow}s.
     */
    private static SwingWorkerFactory< FunctionalBodyPanel.SwingWorker, Iterable< SimpleAirship > > swFactory;
    
    /**
     * A lock for the {@link #swFactory}.
     */
    private static Object factoryLock = new Object();
    
    // Instance Fields
    /**
     * {@code airshipsScrollPane} {@link JPanel} variable that represents a
     * {@link JScrollPanelForElements#produceAJScrollPaneWithAllElements} panel.
     */
    private JPanel airshipsScrollPane;
    /**
     * {@code airshipsScrollPane} {@link JPanel} variable that represents a
     * {@link JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships} panel.
     */
    private JPanel worldMapWithAirships;
    
    // Constructor
    /**
     * Create a new {@code BodyPanel} with a list of {@link SimpleAirship}s in the {@code "System"}.
     * 
     * @see {@link FunctionalBodyPanel#createWorldMapAndScrollPanel()}
     */
    public FunctionalBodyPanel(JBodyPanelForMainWindow bodyPanel ) {
    
        basePanel = bodyPanel;
        createWorldMapAndScrollPanel();
        
        Method update;
        Method update2;

        try {
            
            update = this.getClass().getMethod( "updateBodyPanel" );
            basePanel.setUpdate( update );
            
            update2 = this.getClass().getMethod( "updateBodyPanel", Iterable.class );
            basePanel.setUpdate2( update2 );
        }
        catch( Exception e ) {
            //TODO
            e.printStackTrace();
        }
        
    }
    
    
    // Public methods
    /**
     * Update the {@link JBodyPanelForMainWindow} panel, after GETting a new list of
     * {@link SimpleAirship}s.
     */
    @SuppressWarnings( "unused" )
    private void updateBodyPanel() {
    
        remove();
        createWorldMapAndScrollPanel();
        paint();
    }
    
    /**
     * Update the {@link JBodyPanelForMainWindow} panel with the {@code airshipsFound}.
     * 
     * @param airshipsFound
     *            - List of {@link SimpleAirship}s which the {@code WorldMap} and the
     *            {@code ScrollPane} will be created.
     * 
     */
    private void updateBodyPanel( Iterable< SimpleAirship > airshipsFound ) {
    
        remove();
        createWorldMapAndScrollPanel( airshipsFound );
        paint();
    }
    
    
    
    // STATIC METHOD
    /**
     * TODO Sets the {@link SwingWorkerFactory} that produces
     * {@link FunctionalLoginWindow.SwingWorker}s for the {@link FunctionalLoginWindow}s.
     * 
     * @param factory
     *            The {@link SwingWorkerFactory} that produces
     *            {@link FunctionalLoginWindow.SwingWorker}s for the {@link FunctionalLoginWindow}s.
     * @return {@code true} if {@code factory} was set as the factory that produces swingworkers for
     *         the {@link FunctionalLoginWindow}s; <br/>
     *         {@code false} if there was a factory already set.
     */
    public static
            boolean
            setSwingWorkerFactory( SwingWorkerFactory< FunctionalBodyPanel.SwingWorker, Iterable< SimpleAirship > > factory ) {
    
        synchronized (factoryLock) {
            if( swFactory == null && factory != null ) {
                swFactory = factory;
                return true;
            }
            return false;
        }
    }
    
    
    // Protected method
    /**
     * @see functionalcomponents.FunctionalWindow#getNewSwingWorker()
     */
    protected SwingWorker getNewSwingWorker() throws SwingWorkerFactoryMissingException {
    
        if( swFactory == null )
            throw new SwingWorkerFactoryMissingException( this.getClass().getSimpleName() );
        return swFactory.newInstance();
    }
    
    // Private methods
    /**
     * Create a new {@link SwingWorker} and run it. The {@link SwingWorker#doInBackground()} method
     * should GET a list of {@link SimpleAirship}s.
     * 
     * @see {@link FunctionalBodyPanel.SwingWorker}
     * 
     * @throws SwingWorkerFactoryMissingException
     */
    private void createWorldMapAndScrollPanel() {
    
        try {
            getNewSwingWorker().run();
        }
        catch( SwingWorkerFactoryMissingException e ) {
            
            FunctionalMainWindow.windowBase.getErrorJTextArea().setText("Can not create World Map and Airship List!!");
        }
    }
    
    /**
     * Create the {@code worldMapWithAirships} and the {@code airshipsScrollPane}, with a new list
     * of {@link SimpleAirship}.
     * 
     * @see JWorldMapWithAirships#createAJPanelWithWorldMapAndAirships(Iterable)
     * @see JWorldMapWithAirships#produceAJScrollPaneWithAllEntities(Iterable)
     * 
     * @param airshipsFound
     *            - List of {@link SimpleAirship}s which the {@code WorldMap} and the
     *            {@code ScrollPane} will be created.
     */
    private void createWorldMapAndScrollPanel( Iterable< SimpleAirship > airshipsFound ) {
    
        worldMapWithAirships =
                new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships( airshipsFound );
        airshipsScrollPane =
                new JWorldMapWithAirships().produceAJScrollPaneWithAllEntities( airshipsFound );
        
        basePanel.add( worldMapWithAirships );
        basePanel.add( airshipsScrollPane );
    }
    
    /**
     * Remove the {@code worldMapWithAirships} and the {@code airshipsScrollPane}.
     */
    private void remove() {
    
        basePanel.remove( worldMapWithAirships );
        basePanel.remove( airshipsScrollPane );
    }
    
    /**
     * Paint the {@code worldMapWithAirships} and the {@code airshipsScrollPane}.
     */
    private void paint() {
    
        basePanel.revalidate();
        basePanel.repaint();
    }
    
    
    // Inner SwingWorker Class
    

    /**
     * Abstract class that extends {@link ExceptionHandlerSW}s and implements the
     * {@link SwingWorker#finalizeDone(Iterable) finalizeDone(Iterable)}, the {@code BodyPanel} is
     * updated with the result of the {@link SwingWorker#doInBackground() doInBackground()} method.
     * 
     */
    public abstract class SwingWorker extends ExceptionHandlerSW< Iterable< SimpleAirship > > {
        /**
         * Public constructor that will receive a window's error label as parameter where the error
         * messages will be written.
         * 
         * @param errorLabel
         *            - The window error label.
         */
        
        /**
         * Create a new {@link SwingWorker}, where the {@code error label} is the {@link WindowBase} {@code error area}.
         * 
         * @see ExceptionHandlerSW
         */
        public SwingWorker() {
        
            super( FunctionalMainWindow.windowBase.getErrorJTextArea() );
        }
        
        /**
         * Update the {@code BodyPanel}.
         * 
         * @see swingworkers.ExceptionHandlerSW#finalizeDone(Object)
         */
        @Override
        protected void finalizeDone( Iterable< SimpleAirship > resultOfDoInBackGround )
            throws Exception {
        
            updateBodyPanel( resultOfDoInBackGround );
        }
    }
}
