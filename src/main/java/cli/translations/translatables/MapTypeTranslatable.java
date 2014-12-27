package main.java.cli.translations.translatables;

import java.util.Map;

public class MapTypeTranslatable implements Translatable
{
	private final String tag;
	private final String entryTag;
	private final String keyTag;
	private final String valueTag;
	private final Map<String, String> properties;
	
	public MapTypeTranslatable( String tag, String entryTag, String keyTag, String valueTag, Map<String, String> properties )
	{
		this.tag = tag;
		this.entryTag = entryTag;
		this.keyTag = keyTag;
		this.valueTag = valueTag;
		this.properties = properties;
	}
	
	@Override
	public String getTag()
	{
		return tag;
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
	 * @return the properties
	 */
	public Map<String, String> getProperties()
	{
		return properties;
	}
}
