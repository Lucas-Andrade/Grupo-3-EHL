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
 * {@link Map} whose entries are name-value pairs; entry will be referred to as
 * properties). <br/>
 * {@link Tranlatable}s are intended to simultaneously and accurately represent
 * <ul>
 * <li> {@link String}s,</li>
 * <li>simple instances (of classes whose instance fields are representable by
 * simple name-value pairs),</li>
 * <li> {@link Iterable}s or</li>
 * <li> {@link Map}s with {@code String} keys and values.</li>
 * </ul>
 * and are inspired by the needs of translators that convert objects to HTML or
 * JSON.
 * </p>
 * <p>
 * Tags label the components of the translatable:
 * <ul>
 * <li> {@code tag} is the general label, labels the element represented by the
 * translatable,</li>
 * <li>{@code entryTag} is the label of each property (entry of the properties
 * bag),</li>
 * <li> {@code keyTag} is a common label for all the properties' names (the
 * common label for all keys of the properties bag) and</li>
 * <li> {@code keyTag} is a common label for all the properties' values (the
 * common label for all values of the properties bag).</li>
 * </ul>
 * </p>
 * <p>
 * <b>The following conventions are advised:</b> <br/>
 * Translatables that represent
 * </p>
 * <ul>
 * <li>{@link String}s have
 * {@code null tag, null entryTag, null keyTag, null valueTag} and a properties
 * bag containing only one entry.</li>
 * <li>simple instances have {@code null entryTag, null keyTag, null valueTag},
 * a non-{@code null tag} and several entries in the properties bag.</li>
 * <li>{@link Iterable}s have {@code null keyTag, null valueTag}, a non-
 * {@code null tag}, a non-{@code null entryTag} (which shall be the singular of
 * {@code tag}) and the entries in the properties bag have {@link Translatables}
 * as values (their keys are useless).</li>
 * <li>{@code Map<String,String>} have all tags with non-{@code null} values and
 * several entries in the properties bag.</li>
 * </ul>
 * Summarizing,
 * 
 * <pre>
 *             | String | Simple | Iterable |  Map 
 * ————————————+————————+————————+——————————+——————— 
 * tag         |   n    |    V   |     V    |   V 
 * entryTag    |   n    |    n   |     V    |   V 
 * keyTag      |   n    |    n   |     n    |   V 
 * valueTag    |   n    |    n   |     n    |   V
 * </pre>
 * 
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Translatable
{
	
	// INSTANCE FIELDS
	
	private final String tag;
	private final String entryTag;
	private final String keyTag;
	private final String valueTag;
	private final String toString;
	private final Map< String, Object > propertiesBag;
	
	
	// CONSTRUCTOR
	
	public Translatable( String tag, String entryTag, String keyTag,
			String valueTag, Map< String, Object > propertiesBag,
			String toString ) {
		
		this.tag = tag;
		this.entryTag = entryTag;
		this.keyTag = keyTag;
		this.valueTag = valueTag;
		
		this.propertiesBag = propertiesBag;
		
		this.toString = (toString == null) ? "" : toString;
	}
	
	
	// GET properties bag
	
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
