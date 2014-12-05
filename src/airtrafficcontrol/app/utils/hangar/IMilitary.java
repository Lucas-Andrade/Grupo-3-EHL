package airtrafficcontrol.app.utils.hangar;

/**
 * All classes that implements this interface must have a boolean field which indicates that if the instances
 * have armament.
 * This condition must be passed by the constructor.
 * 
 * @author Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public interface IMilitary
{
	/**
	 * This method differentiates if an  aircraift is military or civil
	 * @return true if have armament, false otherwise 
	 */
	public boolean hasArmament();
}
