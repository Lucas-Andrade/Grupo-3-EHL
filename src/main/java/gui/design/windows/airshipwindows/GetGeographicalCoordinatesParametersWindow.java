package main.java.gui.design.windows.airshipwindows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;

@SuppressWarnings( "serial" )
public class GetGeographicalCoordinatesParametersWindow
	extends WindowBase
{


	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	private JLablePlusJTextField latitude;
	private JLablePlusJTextField longitude;
	private JLablePlusJTextField airshipsNumber;

	public GetGeographicalCoordinatesParametersWindow()
	{
		super( 550, 550, "src/main/resources/images/map-icon.png" );

		initial();
	}

	private void initial()
	{

		latitude = new JLablePlusJTextField( "Latitude", 10, Color.WHITE );
		this.getContentPane().add( latitude, GridBagUtils.updateGridBagConstraints( constraints, 1 ) );

		longitude = new JLablePlusJTextField( "Longitude", 10, Color.WHITE );
		this.getContentPane().add( longitude,
				GridBagUtils.updateGridBagConstraints( constraints, 0, 2, new Insets( 0, 0, 20, 0 ) ) );
		
		airshipsNumber = new JLablePlusJTextField( "Airships Number", 10, Color.WHITE );
		this.getContentPane().add( airshipsNumber,
				GridBagUtils.updateGridBagConstraints( constraints, 0, 3, new Insets( 0, 0, 20, 0 ) ) );
		
		
		this.setVisible( true );
	}

	public JLablePlusJTextField getLatitude()
	{
		return latitude;
	}

	public JLablePlusJTextField getLongitude()
	{
		return longitude;
	}

	public JLablePlusJTextField getAirshipsNumber()
	{
		return airshipsNumber;
	}
}
