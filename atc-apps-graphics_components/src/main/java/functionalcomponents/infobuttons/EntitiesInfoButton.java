package functionalcomponents.infobuttons;


import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import utils.StringUtils;
import design.windows.popupwindows.UnderConstrutionWindow;
import entities.Entity;
import exceptions.SwingWorkerFactoryMissingException;


/**
 * Abstract class for {@code Button}s with a {@link ActionListener} to GET the info of a
 * {@link Entity} by its identification.
 * 
 * Each {@code Button} will be associated to a {@link Entity} {@code identification}, and write its
 * info on a given {@link JTextArea}.
 *
 * @param <E>
 *            - The type of the {@link Entity} to GET
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public abstract class EntitiesInfoButton< E extends Entity > extends JButton {
    
    
    //Constructor
    /**
     * Create a {@link JButton} with an {@link ActionListener} that have the propose to create a new
     * {@link SwingWorker} and {@code run} it. {@code SwingWorker phases:}<br>
     * -> {@link SwingWorker#doInBackground doInBackground}: GET the {@code Entity} by its
     * {@code identification}<br>
     * -> {@link SwingWorker#done done}: Is Written the {@code Entity} info on the {@code textArea}.
     * If some {@code Exception} is thrown during this phases then is written it {@code message} on
     * the {@code textArea}.
     * 
     * 
     * @param identification
     *            - The {@link Entity} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     */
    public EntitiesInfoButton( String identification, JTextArea textArea ) {
    
        
        addActionListener( action -> {
            try {
                runNewSwingWorker( identification, textArea );
            }
            catch( SwingWorkerFactoryMissingException e ) {
                new UnderConstrutionWindow();
            }
            
        } );
    }
    
    
    
    // Abstract method
    /**
     * Create and run a new {@link SwingWorker}.
     * 
     * @param identification
     *            - The {@link Entity} identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     */
    protected abstract void runNewSwingWorker( String identification, JTextArea textArea )
        throws SwingWorkerFactoryMissingException;

    
    
    // Inner class
    /**
     * Abstract class for {@link SwingWorker}s associated to {@link EntitiesInfoButton}, that will
     * write the result of the {@link SwingWorker#get() get()} on the given {@link JTextArea}.
     *
     * @param <E>
     *            - The result of the {@code doInBackgorung} method.
     */
    public static abstract class EntitiesInfoSwingWorker< E extends Entity > extends
            SwingWorker< E, Void > {
        
        /*
         * Where the text will be written
         */
        private JTextArea textArea;
        
        
        
        //Constructor
        /**
         * Create a new {@code SwingWorker}, given to it a {@code textArea}, to where the result of
         * the {@link SwingWorker#get() get()} method will be written.
         * 
         * @param textArea
         *            - The {@link JTextArea} where to display the result.
         */
        public EntitiesInfoSwingWorker( JTextArea textArea ) {
        
            this.textArea = textArea;
        }
        
        
        //Protected method
        /**
         * Implementation of the {@link SwingWorker#done() done()} method.
         * 
         * After the {@link SwingWorker#doInBackground} method, this method will write the
         * {@link Entity} info on the {@code textArea}.
         * 
         * The method {@link SwingWorker#get()} should not throw an {@code Exception}.
         */
        protected final void done() {
        
            try {
                textArea.setText( StringUtils.errorStringParser(get().toString(),23) );
            //    textArea.setText(get().toString());
                textArea.setForeground( Color.WHITE );
            }
            catch( Exception e ) {
                textArea.setText( e.getMessage() );
                textArea.setForeground( Color.RED );
            }
        };
    }
}
