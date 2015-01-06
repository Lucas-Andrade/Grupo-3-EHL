package main.java.cli.outputformatters.translators;

import main.java.cli.outputformatters.Translatable;

public interface Translator
{
	public String encode(Translatable translatable);
	
}
