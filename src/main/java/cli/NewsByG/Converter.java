package main.java.cli.NewsByG;
import main.java.cli.translations.translatables.Translatable;


/**TODO
 * 
 * {@code Conversor}s convert a given {@code Object} to a {@link Translatable} 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 */
public interface Converter<T>
{
	/**
	 * 
	 * @param object
	 * @return
	 */
	Translatable convert(T object);
}
