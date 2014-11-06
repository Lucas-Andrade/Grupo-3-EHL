import java.util.Comparator;


public class AltitudeComparator implements Comparator<Airship> {

	@Override
	public int compare(Airship a1, Airship a2) {
		double altitude1 = a1.getGeographicPosition().getAltitude();
		double altitude2 = a2.getGeographicPosition().getAltitude();
		return (altitude1 > altitude2) ? 1 : (altitude1 == altitude2) ? 0 : -1;
	}

}
