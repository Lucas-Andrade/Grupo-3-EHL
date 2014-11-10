package utils;
import java.util.Comparator;

import app.InvalidArgumentException;


public class AltitudeComparator implements Comparator<Airship> {

	@Override
	public int compare(Airship a1, Airship a2) throws InvalidArgumentException {
		
		if(a1==null || a2 == null)
			throw new InvalidArgumentException();
		
	/*	if(a1==null || a2 == null)
			try {
				throw new InvalidArgumentException();
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			*/
		
		double altitude1 = a1.getGeographicPosition().getAltitude();
		double altitude2 = a2.getGeographicPosition().getAltitude();
		
		
		return (altitude1 > altitude2) ? 1 : (altitude1 == altitude2) ? 0 : -1;
		
	
	}

}
