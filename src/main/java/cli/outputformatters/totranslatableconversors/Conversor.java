package main.java.cli.outputformatters.totranslatableconversors;


import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of a certain concrete type into
 * {@link Translatable}s.
 * 
 * Due to the generic limits in Java this class can not be a generic Class,
 * as we would like.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
abstract class Conversor
{
	
	/**
	 * Converts {@link object} into a {@link Translatable}. *
	 * 
	 * @param object
	 *            The instance to be converted into a {@link Translatable}.
	 * @return The {@link Translatable}.
	 * @throws UnknownTypeException
	 *             If {@code object} has not the expected concrete type.
	 */
	abstract Translatable convert( Object object ) throws UnknownTypeException;
	
	// ISTO NAO PÔDE SER GENERICO SENAO NAO CONSEGUIMOS FAZER O convert() DA
	// CLASSE ToTranslatableConversor. que genéricos fraquinhos!
}
