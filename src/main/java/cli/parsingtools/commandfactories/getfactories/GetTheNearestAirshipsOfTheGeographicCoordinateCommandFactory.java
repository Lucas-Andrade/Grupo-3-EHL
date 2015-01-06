package main.java.cli.parsingtools.commandfactories.getfactories;

import java.util.concurrent.Callable;
import main.java.cli.commands.getcommands.GetTheNearestAirshipsOfTheGeographicCoordinateCommand;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.utils.CommandLineStringsDictionary;
import main.java.cli.utils.Optional;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.utils.exceptions.factoryexceptions.MissingRequiredParameterException;

/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce
 * commands of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}.
 *  Commands are {@link Callable} instances.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

	public class GetTheNearestAirshipsOfTheGeographicCoordinateCommandFactory extends StringsToCommandsFactory<Optional<Iterable<Airship>>>{

		
		// INSTANCE FIELDS
		
		/**
		 * The database of airships.
		 */
		
		private final InMemoryAirshipsDatabase airshipsDatabase;
		
		/**
		 * An array of Strings with all required parameters needed to construct the command. 
		 */
		private final String[] requiredParameters;
		
		/**
		 *  The number of airship to get
		 */
		private int numberOfAirshipsToGet;
		

		/**
		 *  The longitude parameter of the Geographic Coordinate.   
		 */
		private double longitude;
		

		/**
		 * The latitude parameter of the Geographic Coordinate.  
		 */
		private double latitude;
		
		// CONSTRUCTOR

		/**
		 * Constructor of {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommandFactory} 
		 * that produces {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}. 
		 * 
		 * @param airshipsDatabase
		 * 				The airships Database who stores all the elements.
		 * @throws InvalidArgumentException
		 * 				If {@code database==null}.
		 */
		public GetTheNearestAirshipsOfTheGeographicCoordinateCommandFactory(
			InMemoryAirshipsDatabase airshipsDatabase) throws InvalidArgumentException {
		
		super("Get the nearest aircrafts of Geographic coordinates");
		 
		if (airshipsDatabase == null)

			throw new InvalidArgumentException(
					"It's not allow instantiate a factory with null airships database" );

		this.airshipsDatabase = airshipsDatabase;
		this.requiredParameters =
				new String[]{ CommandLineStringsDictionary.LATITUDE, CommandLineStringsDictionary.LONGITUDE,
				CommandLineStringsDictionary.NUMBEROFAIRSHIPSTOFIND };

	}

		// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory

		/**
		 * 
		 * Returns a command of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}. 
		 * 
		 * @return  a command of type {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand}. 
		 * @throws InvalidArgumentException 
		 * @throws MissingRequiredParameterException 
		 */
		@Override
		
		protected Callable<Optional< Iterable<Airship > > >  internalNewInstance() throws InvalidParameterValueException, InvalidArgumentException, MissingRequiredParameterException{

			getNumberOfAirshipsTofind();
			getLatitude();
			getLongitude();
			
		return  new GetTheNearestAirshipsOfTheGeographicCoordinateCommand(airshipsDatabase,numberOfAirshipsToGet,
																									latitude,longitude );
	

	}

		/**
		 * Method responsible to return a array who contains all the required
		 * parameters Needed to create a
		 * {@link GetTheNearestAirshipsOfTheGeograficCoordinateCommand} command.
		 * 
		 * @return a array with required parameters.
		 * 
		 */
		@Override
		protected String[] getRequiredParameters()
		{

			return requiredParameters;

		}

		// AUXILIARY PRIVATE METHODS
	
		/**
		 * 
		 * 
		 * Method responsible to set the number of airships to get field needed
		 *  to {@code PatchUserPasswordCommands} command.
		 *  
		 * This method calls the 
		 * 		{@link StringsToCommandsFactory#getParameterAsString(String)} 
		 * where searches on the Map, with all the parameters, 
		 * the value of the desired  number of airships.
		 * @throws MissingRequiredParameterException 
		 * 
		 */		
		private void  getNumberOfAirshipsTofind() throws InvalidParameterValueException, MissingRequiredParameterException {
		
		numberOfAirshipsToGet = getParameterAsInt(requiredParameters[2]);
		}
		
		/**
		 * 
		 * Method responsible to set the airships longitude field needed
		 *  to {@code PatchUserPasswordCommands} command.
		 *  
		 * This method calls the 
		 * 		{@link StringsToCommandsFactory#getParameterAsString(String)} 
		 * where searches on the Map, with all the parameters, 
		 * the value of the airships longitude.
		 * @throws MissingRequiredParameterException 
		 * 
		 */		
		private void getLongitude() throws InvalidParameterValueException, MissingRequiredParameterException {
		
		longitude = getParameterAsDouble(requiredParameters[1]);
		}
		
		/**
		 * 
		 * Method responsible to set the airships latitude field needed
		 *  to {@code PatchUserPasswordCommands} command.
		 *  
		 * This method calls the 
		 * 		{@link StringsToCommandsFactory#getParameterAsString(String)} 
		 * where searches on the Map, with all the parameters, 
		 * the value of the airships latitude.
		 * @throws MissingRequiredParameterException 
		 * 
		 */		
		 private void getLatitude() throws InvalidParameterValueException, MissingRequiredParameterException {

		 latitude = getParameterAsDouble(requiredParameters[0]);
	
		}


}
