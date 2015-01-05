package main.java.cli.translations.translatables;


/**
 * Instances of this class have a "composed" {@code PropertyBag}, i.e., a list
 * of {@link Translatable}s.
 * 
 * This {@code PropertyBag} have an associated {@code Tag}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ComposedTypeTranslatable implements
		Translatable< Translatable< ? >[] >
{
	
	private final String tag;
	private final Translatable< ? >[] propertyBag;
	
	public ComposedTypeTranslatable( String tag, Translatable< ? >[] propertyBag ) {
		this.tag = tag;
		this.propertyBag = propertyBag;
	}
	
	@Override
	public String getTag() {
		return tag;
	}
	
	@Override
	public Translatable< ? >[] getPropertiesBag() {
		return propertyBag;
	}
}
