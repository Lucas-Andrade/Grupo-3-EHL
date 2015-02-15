package swingworkers.airships;

import javax.swing.JTabbedPane;
import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.CompletionStatus;
import com.google.gson.Gson;
import design.windows.airshipwindows.PostAirshipsWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow.SwingWorker;



/**
 * Class whose instances have the purpose of add functionality to a {@link PostAirshipsWindow}.
 *
 * Extends {@link FunctionalPostAirshipWindow.SwingWorker}.
 *
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipSwingWorker extends FunctionalPostAirshipWindow.SwingWorker{
   
    // CONSTRUCTOR
    public PostAirshipSwingWorker( PostAirshipsWindow window ) {
    
        super( window );

    }
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostAirshipWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of
     * executing a {@link PostCivilAirshipCommand} or {@link PostMilitaryAirshipCommand}, depending
     * of {@link JTabbedPane} activated. After this create a request with the info needed to be executed.  
     * 
     * @return Returns a {@link CompletionStatus} with all the operation information.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        
         String AirshipType = (typeTypeAirshipTabbedPane==0) ? "Civil":"Military";
         
        ClientRequest request = new ClientRequest( "POST", "/airships/"+AirshipType ) {
            
            @Override
            public void createParameters() {            
                
                newParameter( latitudeLabel, latitude );
                newParameter( longitudeLabel, longitude );
                newParameter( altitudeLabel,altitude);
                newParameter( maxAltitudeLabel, maxAltitude );
                newParameter( minAltitudeLabel,minAltitude);
                newParameter( specificComponentLabel, specificComponent );
                newParameter("loginName",loginName);
                newParameter("loginPassword",loginPassword);
            }
            
        };
        
        if( request.createConnection() )
            
            new Gson().fromJson( request.getResponse(), CompletionStatus.class );
        
        throw new Exception( request.getResponse() );
       
       
    }   
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of {@link PostAirshipSwingWorker}
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    
    public static class Factory implements
            SwingWorkerFactory< FunctionalPostAirshipWindow.SwingWorker, CompletionStatus > {
        
        
        // INSTANCE FIELD     
        private PostAirshipsWindow window;
     
        
        // CONSTRUCTOR
        public Factory( PostAirshipsWindow window ) {                  
            this.window = window;
        }
        
        /**
         * Implementation of the {@link SwingWorkerFactory#newInstance()} method with the purpose of
         * create a {@link PostAirshipSwingWorker}
         * 
         * @return Returns a {@link PostAirshipSwingWorker}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new PostAirshipSwingWorker( window );
        }
        
    }
    

}
