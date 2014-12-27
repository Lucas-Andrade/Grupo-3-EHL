package main.java.cli.translations.translatables;

public class ComposedTypeTranslatable implements Translatable
{
	private final String tag;
	private final Translatable[] properties;
	
	public ComposedTypeTranslatable(String tag, Translatable[] properties)
	{
		this.tag = tag;
		this.properties = properties;
	}
	
	@Override
	public String getTag()
	{
		return tag;
	}

	public Translatable[] getPropertiesBag()
	{
		return properties;
	}
	

}
