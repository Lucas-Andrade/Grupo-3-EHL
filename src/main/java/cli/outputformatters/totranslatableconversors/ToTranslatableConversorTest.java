package main.java.cli.outputformatters.totranslatableconversors;

import static org.junit.Assert.*;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownResultTypeException;

import org.junit.Test;

public class ToTranslatableConversorTest
{

	@Test
	public void shouldNotGetAnTranslatable()
	{
		try
		{
			Object object = null;
			ToTranslatableConversor.convert( object );
		}
		catch( UnknownResultTypeException e )
		{
			assertEquals(e.getMessage(), "Cannot convert empty iterables to string.");
		}
	}
	
	@Test
	public void shouldGetAnStringTranslatable()
	{
		
//		if( object instanceof String )
//			return RESULTS_TYPES_MAP.get( stringClass ).convert(
//					((String)object).toString() );
		String str = "E o Sporting é o nosso grande amor";
		try
		{
			ToTranslatableConversor.convert( str );			
		}
		catch( UnknownResultTypeException e )
		{
			e.printStackTrace();
		}
	}

}
