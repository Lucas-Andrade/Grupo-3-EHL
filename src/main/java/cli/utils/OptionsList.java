package main.java.cli.utils;


import java.util.Map;


/**
 * Class whose instances represent lists of options. Keys are the options and
 * values are their descriptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class OptionsList
{
	
	public final Map< String, String > options;
	
	public OptionsList( Map< String, String > options ) {
		this.options = options;
	}
	
	public String toString(){
		return options.toString();
	}
}
