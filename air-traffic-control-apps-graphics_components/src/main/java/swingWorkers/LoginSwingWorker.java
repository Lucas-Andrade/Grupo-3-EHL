package swingWorkers;

import javax.swing.JTextArea;
import functionalcomponents.FunctionalWindowSwingWorker;


public class LoginSwingWorker extends FunctionalWindowSwingWorker< T >{

    public LoginSwingWorker( JTextArea errorJTextArea ) {
    
        super( errorJTextArea );
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void functionalDone( T resultOfDoInBackGround ) throws Exception {
    
        // TODO Auto-generated method stub
        
    }

    @Override
    protected T doInBackground() throws Exception {
    
        // TODO Auto-generated method stub
        return null;
    }
    
}
