package main.java.gui.functionalWindows;

import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.To_be_eliminated.windows.LogInWindow;
import main.java.gui.designWindows.windows.MainWindow;
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
	private LogInWindow functionalWindow;

	private InMemoryUsersDatabase usersDatabase;
	private InMemoryAirshipsDatabase airshipdatabase;

	public FunctionalLoginWindow( LogInWindow nonFunctionalWindow, InMemoryUsersDatabase usersDatabase,
			InMemoryAirshipsDatabase airshipdatabase )
	{
		super( nonFunctionalWindow );

		this.functionalWindow = nonFunctionalWindow;

		this.usersDatabase = usersDatabase;
		this.airshipdatabase = airshipdatabase;
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
		try
		{
			new MainWindow( airshipdatabase, usersDatabase, resultOfDoInBackGround );
		}
		catch( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		functionalWindow.dispose();
	}
}
