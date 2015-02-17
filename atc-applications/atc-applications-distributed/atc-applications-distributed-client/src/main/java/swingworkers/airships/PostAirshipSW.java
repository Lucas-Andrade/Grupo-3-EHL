package swingworkers.airships;


import javax.swing.JTabbedPane;
import swingworkers.SwingWorkerFactory;
import utils.ClientRequest;
import utils.CompletionStatus;
import utils.ClientNonGETRequest;
import utils.StringCommandsDictionary;
import utils.StringUtils;
import utils.ClientNonGETRequest.NonGetMethods;
import com.google.gson.Gson;
import design.windows.airshipwindows.PostAirshipsWindow;
import exceptions.MissingRequiredParameterException;
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
public class PostAirshipSW extends FunctionalPostAirshipWindow.SwingWorker {
    
    // CONSTRUCTOR
    public PostAirshipSW( PostAirshipsWindow window ) {
    
        super( window );
        
    }
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalPostAirshipWindow.SwingWorker
    
    /**
     * Implementation of the {@link SwingWorker#doInBackground()} method with the purpose of
     * executing a {@link PostCivilAirshipCommand} or {@link PostMilitaryAirshipCommand}, depending
     * of {@link JTabbedPane} activated. After this create a request with the info needed to be
     * executed.
     * 
     * @return Returns a {@link CompletionStatus} with all the operation information.
     */
    @Override
    protected CompletionStatus doInBackground() throws Exception {
    
        boolean isCivil = typeAirshipTabbedPane == 0;
        String AirshipType =
                (isCivil) ? StringCommandsDictionary.CIVIL
                                            : StringCommandsDictionary.MILITARY;
        
        ClientRequest request = new ClientNonGETRequest( NonGetMethods.POST, "airships/" + AirshipType ) {
            
            @Override
            public void createParameters() throws MissingRequiredParameterException {
            
                addNewParameter( StringCommandsDictionary.LATITUDE,
                                 StringUtils.parameterToString( latitudeLabel, latitude ) );
                addNewParameter( StringCommandsDictionary.LONGITUDE,
                                 StringUtils.parameterToString( longitudeLabel, longitude ) );
                addNewParameter( StringCommandsDictionary.ALTITUDE,
                                 StringUtils.parameterToString( altitudeLabel, altitude ) );
                addNewParameter( StringCommandsDictionary.AIRCORRIDOR_MAXALTITUDE,
                                 StringUtils.parameterToString( maxAltitudeLabel, maxAltitude ) );
                addNewParameter( StringCommandsDictionary.AIRCORRIDOR_MINALTITUDE,
                                 StringUtils.parameterToString( minAltitudeLabel, minAltitude ) );
                
                if(isCivil){
                addNewParameter( StringCommandsDictionary.NUMBEROFPASSENGERS,
                                 StringUtils.parameterToString( specificComponentLabel,
                                                                specificComponent ) );
                }else{
                    addNewParameter( StringCommandsDictionary.HASARMOUR,
                                     StringUtils.parameterToString( specificComponentLabel,
                                                                    specificComponent ) );
                }
                
                addAuthenticateParameters( simpleLoggedUser.getIdentification(),
                                           simpleLoggedUser.getPassword() );
            }
            
        };
        
        
        
        return new Gson().fromJson( request.getResponse(), CompletionStatus.class );
    }
    
    // INNER CLASS
    /**
     * Inner class responsible for produce a new instance of {@link PostAirshipSW}
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
         * create a {@link PostAirshipSW}
         * 
         * @return Returns a {@link PostAirshipSW}
         */
        
        @Override
        public SwingWorker newInstance() {
        
            return new PostAirshipSW( window );
        }
        
    }
    
    
}
