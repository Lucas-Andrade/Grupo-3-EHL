package main.java.cli.outputformatters.totranslatableconversors;


import main.java.cli.outputformatters.Translatable;


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
	 */
	abstract Translatable convert(Object object);
	
	// ISTO NAO PÔDE SER GENERICO SENAO NAO CONSEGUIMOS FAZER O convert() DA
	// CLASSE ToTranslatableConversor
	// é culpa do Java.
}
