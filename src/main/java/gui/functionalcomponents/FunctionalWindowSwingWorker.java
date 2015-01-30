package main.java.gui.functionalcomponents;

import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;


/**
 * Abstract SwingWorker Class that {@code Override} the {@code done()} method,
 * where the {@link Exception}s thrown by the
 * {@link SwingWorker#doInBackground()} method are caught and write on the
 * {@code errorLabel}.
 */
public abstract class FunctionalWindowSwingWorker< T >
	extends SwingWorker< T, Void >
{
	private final JLabel errorLabel;

	public FunctionalWindowSwingWorker( JLabel errorLabel )
	{
		this.errorLabel = errorLabel;
	}

	/**
	 * Caught and write the thrown {@link Exception}s by the
	 * {@link SwingWorker#doInBackground()} or
	 * {@link FunctionalWindowSwingWorker#functionalDone} methods to the
	 * {@code errorLabel}.
	 */
	@Override
	final protected void done()
	{
		try
		{
			functionalDone( get() );
			errorLabel.setText( " " );
		}
		catch( ExecutionException e )
		{
			errorLabel.setText( e.getCause().getMessage() );
		}
		catch( Exception e )
		{
			errorLabel.setText( e.getMessage() );
		}
	}

	/**
	 * Contract for subclasses where the result of the {@code doInBackground()}
	 * method will be treated. Implementations decisions: The treatment of
	 * {@link Exception}s are caught in the
	 * {@link FunctionalWindowSwingWorker#done()} method.
	 * 
	 * @param resultOfDoInBackGround
	 */
	public abstract void functionalDone( T resultOfDoInBackGround ) throws Exception;
}
