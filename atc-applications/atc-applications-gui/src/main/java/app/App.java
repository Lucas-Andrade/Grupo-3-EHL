package app;


import swingworkers.LoginWindowSW;
import swingworkers.airships.GetAirshipByIdSW;
import swingworkers.airships.GetAirshipsWithLessPassengersThanSW;
import swingworkers.airships.GetAllAirshipsSW;
import swingworkers.airships.GetAirshipsCloserToSW;
import swingworkers.airships.GetTransgressingAirshipsSW;
import swingworkers.airships.PostAirshipSW;
import swingworkers.users.GetAllUsersSW;
import swingworkers.users.GetUserByIdSW;
import swingworkers.users.PatchUserSW;
import swingworkers.users.PostUserSW;
import databases.Database;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;
import functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;


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
    
    
    static Database< User > usersDatabase;
    static Database< Airship > airshipsDatabase;
    static {
        
        try {
            usersDatabase = new InMemoryUsersDatabase( "the system" );
            airshipsDatabase = new InMemoryAirshipsDatabase( "the system" );
        }
        catch( Exception e ) {
            throw new InternalErrorException( "ERROR IN gui APP", e );
        }
    }
    
    
    public static void main( String[] args ) throws InvalidArgumentException {
    
        
        // Creating the factories
        
        LoginWindowSW.Factory loginWindowSWFactory =
                new LoginWindowSW.Factory( FunctionalLoginWindow.baseWindow, usersDatabase );
        PostUserSW.Factory postUserSWFactory =
                new PostUserSW.Factory( FunctionalPostUserWindow.baseWindow, usersDatabase );
        PatchUserSW.Factory patchUserSWFactory =
                new PatchUserSW.Factory( FunctionalPatchUserWindow.baseWindow, usersDatabase );
        GetAllUsersSW.Factory getUsersSWFactory = new GetAllUsersSW.Factory( usersDatabase );
        GetUserByIdSW.Factory getUserByIdSWFactory = new GetUserByIdSW.Factory( usersDatabase );
        
        GetAllAirshipsSW.Factory getAllAirshipsFactory =
                new GetAllAirshipsSW.Factory( airshipsDatabase );
        GetAirshipByIdSW.Factory getAirshipByIdFactory =
                new GetAirshipByIdSW.Factory( airshipsDatabase );
        GetAirshipsCloserToSW.Factory getAirshipsCloserToFactory =
                new GetAirshipsCloserToSW.Factory(
                                                                    FunctionalGetGeographicalCoordinatesParametersWindow.baseWindow,
                                                                    airshipsDatabase );
        GetTransgressingAirshipsSW.Factory getTransgressingAirshipsFactory =
                new GetTransgressingAirshipsSW.Factory( airshipsDatabase );
        GetAirshipsWithLessPassengersThanSW.Factory getAirshipsWithLessPassengersFact =
                new GetAirshipsWithLessPassengersThanSW.Factory(
                                                                       FunctionalGetAirshipsWithLessPassengerThanWindow.baseWindow,
                                                                       airshipsDatabase );
        PostAirshipSW.Factory postAirshipFactory =
                new PostAirshipSW.Factory( FunctionalPostAirshipWindow.baseWindow,
                                           airshipsDatabase, usersDatabase );
        
        
        // Creating and running the app
        
        new GUIapp( loginWindowSWFactory, postUserSWFactory, patchUserSWFactory, getUsersSWFactory,
                    getUserByIdSWFactory, getAllAirshipsFactory, getAirshipByIdFactory,
                    getAirshipsCloserToFactory, getTransgressingAirshipsFactory,
                    getAirshipsWithLessPassengersFact, postAirshipFactory ).run();
    }
    
    
    /**
     * Unused private constructor
     */
    private App() {
    
    }
    
}
