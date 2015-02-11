package outputformatters.translators;


import outputformatters.Translatable;
import utils.exceptions.formattersexceptions.UnknownTranslatableException;


/**
 * Interface whose implementors have the task of translating {@link Translatable} instances into
 * formatted strings.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@FunctionalInterface
public interface Translator {
    
    /**
     * Returns a string representation of {@code translatable}.
     * 
     * @param translatable
     *            The {@link Translatable} to be translated.
     * @return A string representation of {@code translatable}.
     * @throws UnknownTranslatableException
     *             If {@code translatable} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    public String encode( Translatable translatable ) throws UnknownTranslatableException;
    
}