package main.java.cli.translations.translatables;


import java.util.Map;
import main.java.cli.translations.translators.Translator;


/**
 * Instances of this class have a "simple" {@code PropertyBag}, i.e., an
 * {@code Map} with the desired info to be read by a {@link Translator}. The
 * {@code key}s are the property names and the {@code values}s its descriptions.
 * 
 * This {@code PropertyBag} have an associated {@code Tag}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class SimpleTypeTranslatable implements
		Translatable< Map< String, String >>
{
	
	private final String tag;
	private final Map< String, String > propertyBag;
	private final String toString;
	
	public SimpleTypeTranslatable( String tag,
			Map< String, String > propertyBag, String toString ) {
		this.tag = tag;
		this.propertyBag = propertyBag;
		this.toString = toString;
	}
	
	@Override
	public String getTag() {
		return tag;
	}
	
	@Override
	public Map< String, String > getPropertiesBag() {
		return propertyBag;
	}
	
	public void addProperty( String key, String value ) {
		propertyBag.put( key, value );
	}
	
	@Override
	public String toString() {
		return toString;
	}
	
}
