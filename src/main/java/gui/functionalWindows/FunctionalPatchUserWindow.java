package main.java.gui.functionalWindows;

import java.awt.Color;

import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.gui.To_be_eliminated.windows.PatchUserWindow;
import main.java.gui.To_be_eliminated.windows.popup.SuccessfulActionWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class FunctionalPatchUserWindow extends FunctionalWindow<String> {
	
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
	

	// Private Method
	
	private boolean newPasswordConfirmation(String newPassword) {
	
		String newPasswordToConfirm = functionalWindow.getNewPasswordConfirmationPanel()
			.getTextField().getText();
		
		if (newPassword.equals(newPasswordToConfirm))
			return true;
		
		return false;
	}
	
	@Override
	protected FunctionalWindowSwingWorker getSwingWorker() {

		return new FunctionalWindowSwingWorker()
		{
			String username = functionalWindow.getUserPanel().getTextField().getText();
			String oldPassword = functionalWindow.getOldPasswordPanel().getTextField().getText();
			String newPassword = functionalWindow.getNewPasswordPanel().getTextField().getText();

			@Override
			protected String doInBackground() throws Exception
			{
				if (newPassword.equals(""))
					throw new InvalidArgumentException("Please Insert a New Password");
				
				if (!newPasswordConfirmation(newPassword)) {
					throw new InvalidArgumentException("The Passwords Don't Match");
				}
			
				return new PatchUserPasswordCommand(usersDatabase, username, oldPassword,
					newPassword).call();
			}
		};
	}
		

	
	@Override
	protected void functionalWindowDone(String resultOfDoInBackGround) throws InvalidArgumentException, Exception {
	
		if (resultOfDoInBackGround.equals("User password successfully changed")) {
			
			new SuccessfulActionWindow(resultOfDoInBackGround, Color.GREEN);
						
			functionalWindow.dispose();
			
		} else {
			throw new InvalidArgumentException(resultOfDoInBackGround);
		}
	}
}
