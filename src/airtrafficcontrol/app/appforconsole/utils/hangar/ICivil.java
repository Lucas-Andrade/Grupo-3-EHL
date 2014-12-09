package airtrafficcontrol.app.appforconsole.utils.hangar;

/**
 * All classes that implements this interface must have a integer field which indicates the number of passengers
 * This condition must be passed by the constructor.
 * 
 * @author Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public interface ICivil
{
	/**
	 * This method is about civil aircrafts
	 * @return the number of passengers
	 */
	public int getPassengersNumber();
	
	/**
	 * This method is about civil aircrafts
	 * @return true if the aircraft as any empty place 
	 */
	public boolean verifyEmptyPlaces();
}
