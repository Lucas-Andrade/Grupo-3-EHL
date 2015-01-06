package main.java.cli;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.NewsByG.CivilAirshipConverter;
import main.java.cli.NewsByG.Converter;
import main.java.cli.NewsByG.MilitaryAirshipConverter;
import main.java.cli.NewsByG.UserConverter;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ResultTypesStringsDictionary
{
	public static final String USER = "user";
	public static final String CIVIL = "civil";
	public static final String MILITARY = "military";
	
	public static final Map<String, Converter<?>> RESULTS_TYPES_MAP = new HashMap< String, Converter<?> >(); 
	static
	{
		RESULTS_TYPES_MAP.put( USER, new UserConverter() );
		RESULTS_TYPES_MAP.put( CIVIL, new CivilAirshipConverter() );
		RESULTS_TYPES_MAP.put( MILITARY, new MilitaryAirshipConverter() );
	}
}
