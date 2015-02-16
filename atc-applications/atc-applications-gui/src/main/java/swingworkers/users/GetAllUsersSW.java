package swingworkers.users;


import java.util.ArrayList;
import java.util.List;
import swingworkers.SwingWorkerFactory;
import utils.EntitiesConversor;
import commands.getcommands.GetAllElementsInADatabaseCommand;
import databases.Database;
import elements.User;
import entities.SimpleUser;
import functionalcomponents.functionalmainwindow.FunctionalHeaderPanel;


/**
 * Concrete implementation of {@link FunctionalHeaderPanel.GetAllUsersSW}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllUsersSW extends FunctionalHeaderPanel.GetAllUsersSW {
    
    
    private Database< User > usersDatabase;
    
    public GetAllUsersSW( Database< User > usersDatabase ) {
    
        this.usersDatabase = usersDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM FunctionalLoginWindow.SwingWorker
    /**
     * Return an {@link Iterable} of {@link SimpleUser}s representing all the users registered in
     * the app.
     */
    @Override
    protected Iterable< SimpleUser > doInBackground() throws Exception {
    
        Iterable< User > allUsers =
                new GetAllElementsInADatabaseCommand<>( usersDatabase ).call().get();
        
        List< SimpleUser > allSimpleUsers = new ArrayList<>();
        EntitiesConversor conversor = new EntitiesConversor();
        for( User user : allUsers )
            allSimpleUsers.add( conversor.toSimpleUser( user ) );
        
        return allSimpleUsers;
    }
    
    
    
    // INNER CLASS
    public static class Factory implements
            SwingWorkerFactory< FunctionalHeaderPanel.GetAllUsersSW, Iterable< SimpleUser > > {
        
        
        private Database< User > usersDatabase;
        
        
        public Factory( Database< User > usersDatabase ) {
        
            this.usersDatabase = usersDatabase;
        }
        
        
        @Override
        public GetAllUsersSW newInstance() {
        
            return new GetAllUsersSW( usersDatabase );
        }
        
    }
    
}
