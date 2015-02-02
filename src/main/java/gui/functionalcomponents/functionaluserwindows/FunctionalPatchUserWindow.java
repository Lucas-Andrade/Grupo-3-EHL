package main.java.gui.functionalcomponents.functionaluserwindows;


import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.design.windows.popupwindows.SuccessWindow;
import main.java.gui.design.windows.userwindows.PatchUserWindow;
import main.java.gui.functionalcomponents.FunctionalWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;
import main.java.utils.exceptions.InvalidArgumentException;


public class FunctionalPatchUserWindow extends FunctionalWindow< String > {
    
    private Database< User > usersDatabase;
    private PatchUserWindow functionalWindow;
    
    public FunctionalPatchUserWindow( PatchUserWindow nonFunctionalWindow,
                                      Database< User > usersDatabase ) {
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        this.usersDatabase = usersDatabase;
    }
    
    @Override
    protected FunctionalWindowSwingWorker< String > getSwingWorker() {
        return new FunctionalWindowSwingWorker< String >( functionalWindow.getErrorLabel() ) {
            
            String username = functionalWindow.getUserPanel().getJTextField().getText();
            String oldPassword = functionalWindow.getOldPasswordPanel().getJTextField().getText();
            String newPassword = functionalWindow.getNewPasswordPanel().getJTextField().getText();
            String newPasswordToConfirm = functionalWindow.getNewPasswordConfirmationPanel()
                                                          .getJTextField().getText();
            
            @Override
            protected String doInBackground() throws Exception {
                if( newPassword.equals( "" ) )
                    throw new InvalidArgumentException( "Please Insert a New Password" );
                
                if( !newPasswordToConfirm.equals( newPassword ) ) {
                    throw new InvalidArgumentException( "The Passwords Don't Match" );
                }
                
                return new PatchUserPasswordCommand( usersDatabase, username, oldPassword,
                                                     newPassword ).call();
            }
            
            @Override
            public void functionalDone( String resultOfDoInBackGround ) throws Exception {
                if( resultOfDoInBackGround.equals( "User password successfully changed" ) ) {
                    new SuccessWindow( resultOfDoInBackGround );
                    functionalWindow.dispose();
                }
                else {
                    throw new InvalidArgumentException( resultOfDoInBackGround );
                }
            }
        };
    }
}

// // Implementation of the methods inherited from the FunctionalWindow
// class
//
//
//
// @Override
// protected String doInBackground() throws Exception {
//
//
// }
// };
// }
//
// @Override
// protected void functionalWindowDone(String resultOfDoInBackGround)
// throws InvalidArgumentException, Exception {



