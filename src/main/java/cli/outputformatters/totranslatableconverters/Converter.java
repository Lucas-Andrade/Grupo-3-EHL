package main.java.cli.outputformatters.totranslatableconverters;


import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class whose subclasses' instances are converters that turn instances of a certain concrete type
 * into {@link Translatable}s.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
abstract class Converter {
    
    /* we wanted to make this type generic so that the convert method could take specific
     * arguments. yet this idea presented problems when ToTranslatableConversor tried to
     * delegate instances to the correct converters because the conversor argument's type
     * was known only at runtime */
    
    
    /**
     * Converts {@link object} into a {@link Translatable}.
     * 
     * @param instanceToBeConverted
     *            The instance to be converted into a {@link Translatable}.
     * @return The {@link Translatable}.
     * @throws UnknownTypeException
     *             If {@code object} has not the expected concrete type.
     */
    abstract Translatable convert( Object instanceToBeConverted ) throws UnknownTypeException;
    
}
