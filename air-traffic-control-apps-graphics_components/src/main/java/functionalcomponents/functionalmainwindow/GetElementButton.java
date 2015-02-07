package functionalcomponents.functionalmainwindow;


import Element;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import functionalcomponents.FunctionalWindowSwingWorker;
import main.java.Database;
import main.java.domain.commands.getcommands.GetElementFromADatabaseByIdCommand;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Instances of this class are {@link JButton buttons} to which an {@link ActionListener} was added
 * that calls the {@link GetElementFromADatabaseByIdCommand} and displays the result in a given
 * {@code textArea}
 *
 * @param <E>
 *            - Any class that extends from Element.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class GetElementButton< E extends Element > extends JButton {
    
    /**
     * Public constructor that creates an instance of {@code GetElementButton}.
     * 
     * @param identification
     *            - The element's identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            - The element's database.
     */
    public GetElementButton( String identification, JTextArea textArea, Database< E > database ) {
    
        this.addActionListener( action -> getSwingWorker( identification, textArea, database ).run() );
    }
    
    /**
     * Creates an anonymous {@link SwingWorker} class, with an override of the
     * {@link SwingWorker#doInBackground() doInBackground()} method, that will call the
     * {@link GetElementFromADatabaseByIdCommand} and write the information regarding the obtained
     * element in given text area, using an implementation of the {@link SwingWorker#done() done()}
     * method.
     * 
     * @param identification
     *            - The element's identification.
     * @param textArea
     *            - The {@link JTextArea} where to display the result.
     * @param database
     *            - The element's database.
     * 
     * @return the swingWorker.
     */
    private SwingWorker< E, Void > getSwingWorker( String identification, JTextArea textArea,
                                                   Database< E > database ) {
    
        return new SwingWorker< E, Void >() {
            
            /**
             * Implementation of the {@link SwingWorker#doInBackground() doInBackground()} method
             * with the purpose the purpose of executing a
             * {@link GetElementFromADatabaseByIdCommand} and obtaining its result.
             * 
             * @return Returns an element from the given database that matches the given
             *         identification.
             * 
             * @throws InvalidArgumentException
             *             If any of the given parameters are invalid.
             */
            @Override
            protected E doInBackground() throws Exception {
            
                return new GetElementFromADatabaseByIdCommand< E >( database, identification ).call()
                                                                                              .get();
            }
            
            /**
             * Implementation of the {@link FunctionalWindowSwingWorker#done() done()} method.
             * 
             * After the {@link SwingWorker#doInBackground} method, this method will write the
             * obtained element's info on the {@code textArea}.
             * 
             * The method {@link SwingWorker#get()} should not throw an {@code Exception}.
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
