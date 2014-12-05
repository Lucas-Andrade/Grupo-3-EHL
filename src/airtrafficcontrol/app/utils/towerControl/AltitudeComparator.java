package airtrafficcontrol.app.utils.towerControl;
import java.util.Comparator;
import airtrafficcontrol.app.utils.hangar.AirCraft;
import airtrafficcontrol.app.utils.hangar.Airship;

/**
 * compares Airships and sorts them out by altitude
 * 
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 *@author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */

public class AltitudeComparator implements Comparator<AirCraft> {

	@Override
	public int compare(AirCraft a1, AirCraft a2) {
		
//		if(a1==null || a2 == null)
//			try {
//				throw new InvalidArgumentException();
//			} catch (InvalidArgumentException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		
//		if(a1==null || a2 == null)
//			try {
//				throw new InvalidArgumentException();
//			} catch (InvalidArgumentException e) {
//				e.printStackTrace();
//			}
			
		
		double altitude1 = a1.getGeographicPosition().getAltitude();
		double altitude2 = a2.getGeographicPosition().getAltitude();
		
		
		return (altitude1 > altitude2) ? 1 : (altitude1 == altitude2) ? 0 : -1;
		
	
	}

}
