package main.java.gui.functionalWindows;

import java.awt.Color;

import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.gui.MainWindow;
import main.java.gui.fromDG_to_P.windows.PatchUserWindow;
import main.java.gui.fromDG_to_P.windows.popup.SuccessfulActionWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class FunctionalPatchUserWindow extends FunctionalWindow {
	
	// Fields
	
	private InMemoryUsersDatabase usersDatabase;
	
	private PatchUserWindow functionalWindow;
	
	// Constructor
	
	public FunctionalPatchUserWindow(PatchUserWindow nonFunctionalWindow,
		InMemoryUsersDatabase usersDatabase) {
	
		super(nonFunctionalWindow);
		
		this.functionalWindow = nonFunctionalWindow;
		
		this.usersDatabase = usersDatabase;
	}
	
	// Implementation of the method inherited from the FunctionalWindow class
	
	@Override
	protected void leftButtonAction() throws Exception {
	
		String newPassword = functionalWindow.getNewPasswordPanel().getTextField().getText();
		
		if (newPassword.equals(""))
			throw new InvalidArgumentException("Please Insert a New Password");
		
		if (newPasswordConfirmation(newPassword)) {
			
			String username = functionalWindow.getUserPanel().getTextField().getText();
			String oldPassword = functionalWindow.getOldPasswordPanel().getTextField().getText();
			
			String result = new PatchUserPasswordCommand(usersDatabase, username, oldPassword,
				newPassword).call();
			
			if (result.equals("User password successfully changed")) {
				
				new SuccessfulActionWindow(result, Color.GREEN);
				
				new MainWindow(null, usersDatabase, usersDatabase.getElementByIdentification(
					username).get());
				
				functionalWindow.dispose();
				
			} else {
				throw new InvalidArgumentException(result);
			}
			
		} else {
			throw new InvalidArgumentException("The Passwords Don't Match");
		}
	}
	
	// Private Auxiliar Method
	
	private boolean newPasswordConfirmation(String newPassword) {
	
		String newPasswordToConfirm = functionalWindow.getNewPasswordConfirmationPanel()
			.getTextField().getText();
		
		if (newPassword.equals(newPasswordToConfirm))
			return true;
		
		return false;
	}
	
	@Override
	protected FunctionalWindowSwingWorker getSwingWorker() {
	
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void functionalWindowDone(Object resultOfDoInBackGround) {
	
		// TODO Auto-generated method stub
		
	}
}