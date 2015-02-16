package swingworkers;

import javax.swing.JTextArea;
import utils.CompletionStatus;
import design.windows.WindowBase;
import design.windows.popupwindows.SuccessWindow;
import design.windows.userwindows.PostUserWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow.SwingWorker;
import functionalcomponents.functionalmainwindow.BodyPanelFunctionalizer;

/**TODO - documentation
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class AirshipsOperatorSW extends ExceptionHandlerSW< CompletionStatus > {

    private WindowBase window;

    public AirshipsOperatorSW( WindowBase window , JTextArea errorJTextArea ) {
    
        super( errorJTextArea );

        this.window = window;
    }

 // IMPLEMENTATION OF THE METHOD finalizeDone
    /**
     * Implementation of the {@link ExceptionHandlerSW#finalizeDone(Object) finalizeDone(Object)}
     * method. This method will receive the result of the {@link SwingWorker#doInBackground()} 
     * method and use it to create a {@link SuccessWindow} if {@code resultOfDoInBackGround} confirm 
     * the expected result and set the {@link JTextArea} text on {@link PostUserWindow} if fails the operation.
     * 
     * 
     * @param resultOfDoInBackGround
     *            - The result of the {@link SwingWorker#doInBackground()} method.
     * 
     * @throws Exception
     *             Depending on the function the window its supposed to do.
     */
        @Override
        protected final void finalizeDone( CompletionStatus resultOfDoInBackGround ) throws Exception {
        
            if(resultOfDoInBackGround.completedSuccessfully()){       
            
                new SuccessWindow( resultOfDoInBackGround.getMessage() );
                BodyPanelFunctionalizer.runNewSwingWorker();
            } else {
                window.getErrorJTextArea().setText( resultOfDoInBackGround.getMessage() );         
            }
        }

}
