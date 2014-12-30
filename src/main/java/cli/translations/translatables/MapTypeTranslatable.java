package main.java.cli.translations.translatables;

import java.util.Map;

import main.java.cli.translations.translators.Translator;

/**
 * Instances of this class have a "Map" {@code PropertyBag}, i.e., a Map with
 * the desired info to be read by a {@link Translator}.
 * 
 * PropertyBag notes: The {@code Map key}s, with a tag {@code keyTag}, have
 * the properties names, associated to its descriptions, in the
 * {@code Map value}s, with a tag {@code valueTag}. * 
 * 
 * This {@code PropertyBag} have an associated {@code Tag}, and its properties
 * have a tag {@code entryTag}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class MapTypeTranslatable implements Translatable<Map<String, String>>
{
	private final String tag;
	private final String entryTag;
	private final String keyTag;
	private final String valueTag;
	private final Map<String, String> propertyBag;

	public MapTypeTranslatable( String tag, String entryTag, String keyTag,
			String valueTag, Map<String, String> propertyBag )
	{
		this.tag = tag;
		this.entryTag = entryTag;
		this.keyTag = keyTag;
		this.valueTag = valueTag;

		this.propertyBag = propertyBag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}

	@Override
	public Map<String, String> getPropertiesBag()
	{
		return propertyBag;
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
}
