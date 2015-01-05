package main.java.cli.translations.translatables;

import java.util.Map;

import main.java.cli.translations.translators.Translator;

/**TODO
 * Instances of this class have a "Map" {@code PropertyBag}, i.e., a Map with
 * the desired info to be read by a {@link Translator}.
 * 
 * PropertyBag notes: The {@code Map key}s, with a tag {@code keyTag}, have
 * the properties names, associated to its descriptions, in the
 * {@code Map value}s, with a tag {@code valueTag}.
 * 
 * This {@code PropertyBag} have an associated {@code Tag}, and its properties
 * have a tag {@code entryTag}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Translatable
{
	private final String tag;
	private final String entryTag;
	private final String keyTag;
	private final String valueTag;
	private final String toString;
	
	private final Map<String, Object> propertiesBag;
	
	public Translatable( String tag, String entryTag, String keyTag,
			String valueTag, Map<String, Object> propertiesBag, String toString )
	{
		this.tag = tag;
		this.entryTag = entryTag;
		this.keyTag = keyTag;
		this.valueTag = valueTag;

		this.propertiesBag = propertiesBag;
		this.toString = toString;
	}

	/**
	 * @return the tag
	 */
	public String getTag()
	{
		return tag;
	}

	/**
	 * @return the propertiesBag
	 */
	public Map<String, Object> getPropertiesBag()
	{
		return propertiesBag;
	}

	/**
	 * @return the entryTag
	 */
	public String getEntryTag()
	{
		return entryTag;
	}

	/**
	 * @return the keyTag
	 */
	public String getKeyTag()
	{
		return keyTag;
	}

	/**
	 * @return the valueTag
	 */
	public String getValueTag()
	{
		return valueTag;
	}
	
	/**
	 * @return the toString
	 */
	public String getToString()
	{
		return toString;
	}
}
