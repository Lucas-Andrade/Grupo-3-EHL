package main.java.gui.functionalWindows;

import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.MainWindow;
import main.java.gui.fromDG_to_P.LogInWindow;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalLoginWindow
	extends FunctionalWindow< User >
{
	private InMemoryUsersDatabase usersDatabase;
	private LogInWindow functionalWindow;

	public FunctionalLoginWindow( LogInWindow nonFunctionalWindow, InMemoryUsersDatabase usersDatabase )
	{
		super( nonFunctionalWindow );

		this.functionalWindow = nonFunctionalWindow;

		this.usersDatabase = usersDatabase;
	}

	@Override
	protected FunctionalWindowSwingWorker getSwingWorker()
	{
		return new FunctionalWindowSwingWorker()
		{
			String username = functionalWindow.getUserPanel().getTextField().getText();
			String password = functionalWindow.getPasswordPanel().getTextField().getText();

			@Override
			protected User doInBackground() throws Exception
			{
				User user = usersDatabase.getElementByIdentification( username ).get();
				if( user.authenticatePassword( password ) )
				{
					return user;
				}
				else
				{
					throw new InvalidArgumentException( "Wrong Password!" );
				}
			}
		};
	}

	@Override
	protected void functionalWindowDone( User resultOfDoInBackGround )
	{
		new MainWindow(resultOfDoInBackGround);
		functionalWindow.dispose();
	}
}
