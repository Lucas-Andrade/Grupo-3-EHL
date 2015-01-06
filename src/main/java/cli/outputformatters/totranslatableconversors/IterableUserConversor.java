//package main.java.cli.outputformatters.totranslatableconversors;
//
//
//import java.util.HashMap;
//import java.util.Map;
//import main.java.cli.model.users.User;
//import main.java.cli.outputformatters.Translatable;
//import main.java.cli.utils.ResultsTypesStringsDictionary;
//
//
///**
// * TODO ?!?!?! Iterable generic -> <?> or <Element> Como fazemos o iterable?!?!
// * p_q
// * 
// *
// * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
// */
//class IterableUserConversor extends Converter
//{
//	
//	Translatable convert( Object iterableOfUsers ) {
//		// TODO: handle ClassCastException
//		Iterable< User > it = (Iterable< User >)iterableOfUsers;
//		
//		
//		Map< String, Object > propertiesBag = new HashMap< String, Object >();
//		
//		for( User user : it )
//		{
//			propertiesBag.put(
//					user.getIdentification(),
//					ToTranslatableConversor.convert( user,
//							ResultsTypesStringsDictionary.USER )
//							.getPropertiesBag() );
//		}
//		
//		return new Translatable( "tag", null, null, null, propertiesBag, null );
//	}
//	
//}
