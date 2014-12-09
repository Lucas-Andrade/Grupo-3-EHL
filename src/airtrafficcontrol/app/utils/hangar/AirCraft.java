package airtrafficcontrol.app.utils.hangar;

import java.util.LinkedList;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;



/**
 * Abstract class that represents an AirCraft with a position and a ID
 *
 * @author Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public abstract class AirCraft
{
	/**
	 * This aircraft's flightID.
	 */
	private String flightID;
	
	/**
	 * This aircraft's last know geographical positions.
	 */
	private LinkedList< GeographicalPosition > lastKnownGeograficalPositions;
	
	/**
	 * nao sabemos para que serve!!!
	 */
	private boolean positionWasUpdated = false;
	
	/**
	 * Constructs an airCraft with the ID {@code flightID}, geographical coordinates {@code statingPostition}.
	 * Also creates a structure to save the airCraft's last known geographical positions.
	 * 
	 * @param flightID
	 *            The flight's ID.
	 * @param statingPosition
	 *            The coordinates where the aircraft will take-off.
	 * @throws InvalidArgumentException
	 *             If {@code flightID}, {@code statingPosition} are null.
	 */
	public AirCraft( String flightID, GeographicalPosition statingPosition ) throws InvalidArgumentException
	{
		if( flightID == null || statingPosition == null)
			throw new InvalidArgumentException();
		
		this.flightID = flightID;
		this.lastKnownGeograficalPositions = new LinkedList< GeographicalPosition >();
		lastKnownGeograficalPositions.add( statingPosition );
	}
	
	/**
	 * @return the ID of the plane
	 */
	public String getFlightID()
	{
		return flightID;
	}
	
	/**
	 * @return the current geographical position of the airCraft
	 */
	public GeographicalPosition getGeographicPosition()
	{
		return lastKnownGeograficalPositions.getFirst();
	}
	
	/**
	 * @return an object array, where all the known geographic positions of the aircraft are saved as
	 * GeographicalPosition objects
	 */
	public Object[] getLastKnownGeographicPosition()
	{
		return lastKnownGeograficalPositions.toArray();
	}
	
	/**
	 * @param newGeographicalPosition
	 *            - updates the geographical position to a new one
	 * @throws InvalidArgumentException
	 */
	public void updateGeographicPosition( GeographicalPosition newGeographicalPosition )
			throws InvalidArgumentException
	{
		if( newGeographicalPosition == null )
			throw new InvalidArgumentException();
		
		positionWasUpdated = true;
		lastKnownGeograficalPositions.addFirst( newGeographicalPosition );
	}
	
	/**
	 * @return true if the position was updated since the last time
	 *         positionToString() was called
	 * @return false if it was not
	 */
	public boolean wasPositionUpdated()
	{
		return positionWasUpdated;
	}
	
	/**
	 * sets positionWasUpdated to false
	 */
	public void setToNotUpdated()
	{
		positionWasUpdated = false;
	}
	
	/**
	 * @param aircraft
	 * @return true if aircraft is flying, false otherwise
	 */
	public boolean isFlying( AirCraft aircraft )
	{
		double currentAltitude = aircraft.getGeographicPosition().getAltitude();
		if( currentAltitude != 0 )
			return true;
		else return false;
	}

	/**
	 * @return returns a string with the id, position, and observations about
	 *         the airplane
	 * @throws InvalidArgumentException
	 */
	public String positionToString() throws InvalidArgumentException
	{
		StringBuilder builder = new StringBuilder();
		GeographicalPosition pos = getGeographicPosition();
		builder.append( flightID ).append( " " ).append( pos.getLatitude() )
				.append( " " ).append( pos.getLongitude() ).append( " " )
				.append( pos.getAltitude() ).append( " " );
		return builder.toString();
	}
	
	public abstract String[] toStringArray();
}
