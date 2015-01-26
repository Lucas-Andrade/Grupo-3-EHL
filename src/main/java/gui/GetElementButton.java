package main.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import main.java.domain.commands.getcommands.GetElementFromADatabaseByIdCommand;
import main.java.domain.model.Database;
import main.java.domain.model.Element;

@SuppressWarnings( "serial" )
public class GetElementButton <E extends Element>
	extends JButton
{
	public GetElementButton( String identification, JTextArea textArea, Database< E > database )
	{
		addActionListener( new ActionListener()
		{

			@Override
			public void actionPerformed( ActionEvent ae )
			{
				try
				{
					textArea.setText( new GetElementFromADatabaseByIdCommand< E >( database,
							identification ).call().get().toString() );
					
				}
				catch( Exception e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} );
	}
}
