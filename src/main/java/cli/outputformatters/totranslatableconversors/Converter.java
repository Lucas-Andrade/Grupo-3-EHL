package main.java.cli.outputformatters.totranslatableconversors;


import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Utility classes that convert instances of a certain concrete type into a
 * {@link Translatable}.
 * 
 * Due to the generic limits in Java this class can not be a generic Class,
 * as we would like.
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
	abstract Translatable convert(Object object) throws UnknownTypeException;
}
