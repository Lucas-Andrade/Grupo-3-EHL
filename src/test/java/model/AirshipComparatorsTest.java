package test.java.model;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.GeographicPosition;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;

import org.junit.Test;


public class AirshipComparatorsTest
{
	private InMemoryAirshipsDatabase database;

	@Test
	public void test()
	{
		// Arrange
		GeographicPosition gp;
		List<Airship> expectedAirshipList = new ArrayList<Airship>();
		try
		{
			User user = new User( "username", "password", "email@" );
			database = new InMemoryAirshipsDatabase( "airships database" );

			Airship a0 = new CivilAirship( 1, 2, 5, 10, 0, 10 );
			Airship a1 = new CivilAirship( 5, 3, 6, 10, 0, 10 );
			Airship a2 = new CivilAirship( 7, 5, 7, 10, 0, 10 );
			Airship a3 = new CivilAirship( 9, 2, 8, 10, 0, 10 );
			Airship a4 = new CivilAirship( - 11, 1, 5, 10, 0, 10 );
			Airship a5 = new MilitaryAirship( - 12, 2, 4, 10, 0, true );
			Airship a6 = new MilitaryAirship( 45, 67, 4, 10, 0, true );
			Airship a7 = new MilitaryAirship( 12, 12, 4, 10, 0, true );
			Airship a8 = new MilitaryAirship( 34, 2, 4, 10, 0, true );
			Airship a9 = new MilitaryAirship( 0, 0, 4, 10, 0, true );

			gp = new GeographicPosition( 0, 0, 0 );
			
			// Act
			database.add( a0, user );
			database.add( a1, user );
			database.add( a2, user );
			database.add( a3, user );
			database.add( a4, user );
			database.add( a5, user );
			database.add( a6, user );
			database.add( a7, user );
			database.add( a8, user );
			database.add( a9, user );


			expectedAirshipList.add( a9 );
			expectedAirshipList.add( a0 );
			expectedAirshipList.add( a1 );
			expectedAirshipList.add( a2 );
			expectedAirshipList.add( a3 );
			expectedAirshipList.add( a4 );
			expectedAirshipList.add( a5 );
			expectedAirshipList.add( a7 );
			expectedAirshipList.add( a8 );
			expectedAirshipList.add( a6 );
			

		}
		catch( InvalidArgumentException e )
		{
			// never happen
			gp = null;
			e.printStackTrace();
		}

		// Assert
		assertEquals( expectedAirshipList,
				database.getAirshipsByDistance( gp, 10 ) );
	}
}
