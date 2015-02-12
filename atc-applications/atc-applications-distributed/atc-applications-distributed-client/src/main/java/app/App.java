package app;


import java.awt.EventQueue;
import swingworkers.LoginWindowSwingWorker;
import design.windows.popupwindows.FailWindow;
import design.windows.popupwindows.UnderConstrutionWindow;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.SwingWorkerFactoryMissingException;
import functionalcomponents.FunctionalWindow;
import functionalcomponents.SwingWorkerFactory;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;


/**
 * TODO: review User Interface for AirTrafficControl - Swing
 * 
 * How to use, first login user:
 * <ul>
 * <li>Username: MASTER
 * <li>Password: master
 * </ul>
 * 
 * It will open the main window. At the top right corner, you see all the {@code 

User}
 * functionalities. At the bottom you see all the {@code Airships}
 * 
 * functionalities.
 * 
 * User Buttons:
 * <ul>
 * <li>POST a new user
 * <li>GET all users: it will open a new window, with a list of all posted user.
 * 
 * Click in one to see it info.
 * <li>PATCH a user: to change your password. You can not change the MASTER
 * 
 * password.
 * <li>Logout: close the main window and show the login window
 * <li>Turn off: close the application
 * <li>DELETE a user: still under construction
 * </ul>
 * 
 * Airship Buttons:
 * <ul>
 * <li>POST a new airship
 * <li>GET all airships: not working, we don't know why :(, to see all posted
 * 
 * airships logout and login again. It will appear the posted airships in the world image, and a
 * list
 * 
 * at the right. To see an Airship info click on one.
 * <li>Under Construction:
 * <ul>
 * <li>Airships Closest to Coordinates
 * <li>Get Transgressing Airships
 * <li>Get Airships with less Passengers
 * <li>Change Airship
 * <li>Delete Airship
 * </ul>
 * 
 * In the next version you can choose your favorite Chuck Norris picture.
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class App {
    
    
    
    public static void main( String[] args ) throws InvalidArgumentException {
    
        EventQueue.invokeLater( new Runnable() {
            
            /**
             * Runs the app.
             */
            @Override
            public void run() {
            
                try {
                    
                    
                    setSwingWorkerFactoriesInTheFunctionalWindows();
                    new FunctionalLoginWindow();
                    
                    
                }
                catch( InternalErrorException e ) {
                    if( e.getCause() instanceof SwingWorkerFactoryMissingException )
                        new UnderConstrutionWindow();
                    else new FailWindow( "INTERNAL ERROR!" );
                    System.out.println( e.getMessage() );
                }
                
            }
            
            /**
             * Sets the {@link SwingWorkerFactory}s that produce
             * {@link FunctionalLoginWindow.SwingWorker}s for each subtype of
             * {@link FunctionalWindow}.
             */
            private void setSwingWorkerFactoriesInTheFunctionalWindows() {
            
                LoginWindowSwingWorker.Factory loginWindowFactory =
                        new LoginWindowSwingWorker.Factory( FunctionalLoginWindow.baseWindow );
                FunctionalLoginWindow.setSwingWorkerFactory( loginWindowFactory );
            }
            
        } );
        
        
    }
    
    /**
     * Unused private constructor
     */
    private App() {
    
    }
    
}
