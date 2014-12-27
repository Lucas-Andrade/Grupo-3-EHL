package main.java.cli.translations.translatables;

public class SimpleTypeTranslatable implements Translatable
{

	private final String tag;
	private final String[][] properties;
	
	public SimpleTypeTranslatable(String tag, String[][] properties)
	{
		this.tag = tag;
		this.properties = properties;
	}
	
	@Override
	public String getTag()
	{
		return tag;
	}

	public String[][] getPropertiesBag()
	{
		return properties;
	}

}
