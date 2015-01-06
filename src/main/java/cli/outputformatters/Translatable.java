package main.java.cli.outputformatters;


import java.util.Map;
import main.java.cli.outputformatters.translators.Translator;


/**
 * Class whose instances are intermediate representations of instances that will
 * be translated into a certain formatted string. That translation is performed
 * by {@link Translator}s.
 * 
 * <p>
 * Each instance of this class has several tags and a properties bag (a
 * {@link Map} whose entries are name-value pairs). <br/>
 * {@link Tranlatable}s are intended to simultaneously represent accurately
 * simple instances (whose instance fields are representable by simple
 * name-value pairs), or {@link Iterable}s (who consist in a list of other
 * simple instances) or {@link Map}s (
 * </p>
 * <p>
 * PropertyBag notes: The {@code Map key}s, with a tag {@code keyTag}, have the
 * properties names, associated to its descriptions, in the {@code Map value}s,
 * with a tag {@code valueTag}.
 * 
 * This {@code PropertyBag} have an associated {@code Tag}, and its properties
 * have a tag {@code entryTag}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Translatable
{
	
	/**
	 * 
	 */
	private final String tag;
	private final String entryTag;
	private final String keyTag;
	private final String valueTag;
	private final String toString;
	
	private final Map< String, Object > propertiesBag;
	
	public Translatable( String tag, String entryTag, String keyTag,
			String valueTag, Map< String, Object > propertiesBag,
			String toString ) {
		this.tag = tag;
		this.entryTag = entryTag;
		this.keyTag = keyTag;
		this.valueTag = valueTag;
		
		this.propertiesBag = propertiesBag;
		this.toString = toString;
	}
	
	/**
	 * @return the propertiesBag
	 */
	public Map< String, Object > getPropertiesBag() {
		return propertiesBag;
	}
	
	
	// GETTERs of tags
	
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * @return the entryTag
	 */
	public String getEntryTag() {
		return entryTag;
	}
	
	/**
	 * @return the keyTag
	 */
	public String getKeyTag() {
		return keyTag;
	}
	
	/**
	 * @return the valueTag
	 */
	public String getValueTag() {
		return valueTag;
	}
	
	
	// OVERRIDE OF Object'S toString() METHOD
	
	/**
	 * @return the toString
	 */
	public String toString() {
		return toString;
	}
	
}
