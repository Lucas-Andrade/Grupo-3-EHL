package main.java.gui.functionalcomponents.functionalmainwindow;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import main.java.domain.commands.getcommands.GetElementFromADatabaseByIdCommand;
import main.java.domain.model.Database;
import main.java.domain.model.Element;


/**
 * Instances of this class are {@link JButton}s, where the {@link ActionListener} call the
 * {@link GetElementFromADatabaseByIdCommand} and write the result in the given {@code textArea}
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <E>
 */
@SuppressWarnings( "serial" )
public class GetElementButton< E extends Element > extends JButton {
    
    /**
     * Creates an instances of {@code GetElementButton}, that when activated it calls
     * {@link GetElementFromADatabaseByIdCommand} and write its info in {@code textArea}.
     * 
     * @param identification
     *            of the {@link Element}
     * @param textArea
     *            to write the result
     * @param database
     *            of {@link Element}s
     */
    public GetElementButton( String identification, JTextArea textArea, Database< E > database ) {
        addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent ae ) {
                getSwingWorker( identification, textArea, database ).run();
            }
        } );
    }
    
    /**
     * Creates a anonymous class of {@link SwingWorker} that will call in
     * {@link SwingWorker#doInBackground} the command {@link GetElementFromADatabaseByIdCommand},
     * and in {@link SwingWorker#done} write its info in {@code textArea}.
     * 
     * @param identification
     * @param textArea
     * @param database
     * 
     * @return the swingWorker
     */
    private SwingWorker< E, Void > getSwingWorker( String identification, JTextArea textArea,
                                                   Database< E > database ) {
        return new SwingWorker< E, Void >() {
            
            /**
             * GET the element
             */
            @Override
            protected E doInBackground() throws Exception {
                return new GetElementFromADatabaseByIdCommand< E >( database, identification ).call()
                                                                                              .get();
            }
            
            /**
             * After the {@link SwingWorker#doInBackground} method, it is write the element info on
             * the {@code textArea}. The method {@link SwingWorker#get()} should not throw an
             * {@code Exception}
             */
            protected void done() {
                try {
                    textArea.setText( get().toString() );
                    textArea.setForeground( Color.WHITE );
                }
                catch( Exception e ) {
                    textArea.setText( e.getMessage() );
                    textArea.setForeground( Color.RED );
                }
            };
        };
    }
}
