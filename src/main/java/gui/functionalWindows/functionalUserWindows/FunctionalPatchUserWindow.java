package main.java.gui.functionalWindows.functionalUserWindows;

import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.popupWindows.SuccessWindow;
import main.java.gui.designWindows.windows.userWindows.PatchUserWindow;
import main.java.gui.functionalWindows.FunctionalWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class FunctionalPatchUserWindow extends FunctionalWindow<String> {
	
	// Fields
	
	private Database<User> usersDatabase;
	
	private PatchUserWindow functionalWindow;
	
	// Constructor
	
	public FunctionalPatchUserWindow(PatchUserWindow nonFunctionalWindow,
		Database<User> usersDatabase) {
	
		super(nonFunctionalWindow);
		
		this.functionalWindow = nonFunctionalWindow;
		
		this.usersDatabase = usersDatabase;
		
	}

	// Implementation of the methods inherited from the FunctionalWindow class
	
	@Override
	protected FunctionalWindowSwingWorker getSwingWorker() {
	
		return new FunctionalWindowSwingWorker() {
			
			String username = functionalWindow.getUserPanel().getJTextField().getText();
			String oldPassword = functionalWindow.getOldPasswordPanel().getJTextField().getText();
			String newPassword = functionalWindow.getNewPasswordPanel().getJTextField().getText();
			String newPasswordToConfirm = functionalWindow.getNewPasswordConfirmationPanel()
				.getJTextField().getText();
			
			@Override
			protected String doInBackground() throws Exception {
			
				if (newPassword.equals(""))
					throw new InvalidArgumentException("Please Insert a New Password");
				
				if (!newPasswordToConfirm.equals(newPassword)) {
					throw new InvalidArgumentException("The Passwords Don't Match");
				}
				
				return new PatchUserPasswordCommand(usersDatabase, username, oldPassword,
					newPassword).call();
			}
		};
	}
	
	@Override
	protected void functionalWindowDone(String resultOfDoInBackGround)
		throws InvalidArgumentException, Exception {
	
		if (resultOfDoInBackGround.equals("User password successfully changed")) {
			
			new SuccessWindow(resultOfDoInBackGround);
			
			functionalWindow.dispose();
			
		} else {
			throw new InvalidArgumentException(resultOfDoInBackGround);
		}
	}
}
