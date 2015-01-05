package main.java.cli.translations.translatables;

import main.java.cli.translations.translators.Translator;


/**
 * Instances whose class implements this interface have a {@code PropertyBag},
 * i.e., the desired info to be read by a {@link Translator}. All
 * {@code PropertyBag}s have an associated {@code Tag}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Translatable<T>
{
	/**
	 * @return the Tag for the PropertiesBag
	 */
	public String getTag();

	/**
	 * @return the PropertiesBag
	 */
	public T getPropertiesBag();
}
