package main.java.cli.translations.translators;

import main.java.cli.translations.translatables.ComposedTypeTranslatable;
import main.java.cli.translations.translatables.MapTypeTranslatable;
import main.java.cli.translations.translatables.SimpleTypeTranslatable;

public interface Translator
{
	public String encode(SimpleTypeTranslatable simpleType);
	public String encode(ComposedTypeTranslatable composedType);
	public String encode(MapTypeTranslatable mapType);
}
