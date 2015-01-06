package main.java.cli.outputformatters.totranslatableconversors;


import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Utility classes that convert instances of a certain concrete type into a
 * {@link Translatable}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
abstract class Converter
{
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws UnknownTypeException 
	 */
	abstract Translatable convert(Object object) throws UnknownTypeException;
	
	// ISTO NAO PÔDE SER GENERICO SENAO NAO CONSEGUIMOS FAZER O convert() DA
	// CLASSE ToTranslatableConversor
	// é culpa do Java.
}
