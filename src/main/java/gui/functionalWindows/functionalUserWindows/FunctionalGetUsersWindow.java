package main.java.gui.functionalWindows.functionalUserWindows;

import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.WindowBase;
import main.java.gui.functionalWindows.FunctionalWindow;

public class FunctionalGetUsersWindow extends FunctionalWindow< Iterable< User > >
{

	public FunctionalGetUsersWindow( WindowBase nonFunctionalWindow )
	{
		super( nonFunctionalWindow );
		// TODO Auto-generated constructor stub
	}

	@Override
	protected FunctionalWindowSwingWorker getSwingWorker()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void functionalWindowDone( Iterable< User > resultOfDoInBackGround ) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

}
