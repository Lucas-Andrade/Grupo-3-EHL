package main.java.cli.translations.translators;

import main.java.cli.translations.translatables.Translatable;

public interface Translator
{
	public String encode(Translatable translatable);
	
}
