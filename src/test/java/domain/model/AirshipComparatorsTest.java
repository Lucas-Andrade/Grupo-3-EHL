package test.java.domain.model;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.GeographicPosition;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Test;


public class AirshipComparatorsTest
{
	private InMemoryAirshipsDatabase database;

	@Test
	public void test() throws InvalidArgumentException, Exception
	{
		// Arrange
		GeographicPosition gp;
		List<Airship> expectedAirshipList = new ArrayList<Airship>();
		try
		{
			User user = new User( "username", "password", "email@" );
			database = new InMemoryAirshipsDatabase( "airships database" );

			
			Airship air1 = new CivilAirship(30, 225, 10000, 20000, 0, 100);
			Airship air2 = new MilitaryAirship(0, 315, 15000, 20000, 0, true);
			Airship air3 = new CivilAirship(45, 180, 12000, 20000, 0, 50);
			Airship air4 = new MilitaryAirship(-60, 90, 15000, 20000, 0, false);
			Airship air5 = new CivilAirship(-60, 225, 12000, 20000, 0, 50);
			Airship air6 = new CivilAirship(-90, 360, 12000, 20000, 0, 50);
			Airship air7 = new CivilAirship(30, 45, 12000, 20000, 0, 50);
			Airship air8 = new MilitaryAirship(-30, 45, 15000, 20000, 0, false);
			
			
			gp = new GeographicPosition( 0, 0, 0 );
			
			// Act
			database.add( air1, user );
			database.add( air2, user );
			database.add( air3, user );
			database.add( air4, user );
			database.add( air5, user );
			database.add( air6, user );
			database.add( air7, user );
			database.add( air8, user );


			expectedAirshipList.add( air8 );
			expectedAirshipList.add( air7 );
			expectedAirshipList.add( air4 );
			expectedAirshipList.add( air3 );
			expectedAirshipList.add( air1 );
			expectedAirshipList.add( air5 );
			expectedAirshipList.add( air2 );
			expectedAirshipList.add( air6 );
		
			

		}
		catch( InvalidArgumentException e )
		{
			// never happen
			gp = null;
			e.printStackTrace();
		}
		
		// Assert
		assertEquals( expectedAirshipList,
				database.getAirshipsCloserTo( gp, 10 ).get() );
	}
}
