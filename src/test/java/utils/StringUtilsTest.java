package test.java.utils;


import static org.junit.Assert.assertEquals;
import main.java.utils.StringUtils;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.MissingRequiredParameterException;
import org.junit.Test;


/**
 * Test case that targets the class {@link StringUtils}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class StringUtilsTest {
    
    
    @Test
    public void parameterToBooleanShouldReturnCorrectBoolean()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        assertEquals( true, StringUtils.parameterToBoolean( "name", "True" ) );
        assertEquals( true, StringUtils.parameterToBoolean( "name", "1" ) );
        assertEquals( true, StringUtils.parameterToBoolean( null, "YES" ) );
        assertEquals( true, StringUtils.parameterToBoolean( "name", "y" ) );
        assertEquals( false, StringUtils.parameterToBoolean( "name", "falSe" ) );
        assertEquals( false, StringUtils.parameterToBoolean( null, "0" ) );
        assertEquals( false, StringUtils.parameterToBoolean( "name", "nO" ) );
        assertEquals( false, StringUtils.parameterToBoolean( "name", "n" ) );
    }
    
    @Test
    public void parameterToDoubleShouldReturnCorrectDouble()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        assertEquals( new Double( 12.0d ), StringUtils.parameterToDouble( "name", "12" ) );
        assertEquals( new Double( 12.0d ), StringUtils.parameterToDouble( "name", "12.0" ) );
        assertEquals( new Double( 0.12d ), StringUtils.parameterToDouble( "name", ".12" ) );
        assertEquals( new Double( -12.01d ), StringUtils.parameterToDouble( "name", "-12.01" ) );
    }

    @Test
    public void parameterToIntegerShouldReturnCorrectInteger()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        assertEquals( new Integer( 12 ), StringUtils.parameterToInteger( "name", "12" ) );
        assertEquals( new Integer( -12 ), StringUtils.parameterToInteger( "name", "-12" ) );
    }
    
    
    // Exceptional Behaviors
    
    
    @Test( expected = MissingRequiredParameterException.class )
    public void parameterToBooleanShouldThrowExceptionIfNullValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToBoolean( "name", null );
    }
    
    @Test( expected = MissingRequiredParameterException.class )
    public void parameterToBooleanShouldThrowExceptionIfEmptyStringValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToBoolean( "name", "" );
    }
    
    @Test( expected = InvalidParameterValueException.class )
    public void parameterToBooleanShouldThrowExceptionIfInvalidStringValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToBoolean( "name", "value" );
    }
    
    
    @Test( expected = MissingRequiredParameterException.class )
    public void parameterToDoubleShouldThrowExceptionIfNullValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToDouble( "name", null );
    }
    
    @Test( expected = MissingRequiredParameterException.class )
    public void parameterToDoubleShouldThrowExceptionIfEmptyStringValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToDouble( "name", "" );
    }
    
    @Test( expected = InvalidParameterValueException.class )
    public void parameterToDoubleShouldThrowExceptionIfInvalidStringValueIsGiven1()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToDouble( "name", "value" );
    }

    @Test( expected = InvalidParameterValueException.class )
    public void parameterToDoubleShouldThrowExceptionIfInvalidStringValueIsGiven2()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToDouble( "name", "12,0" );
    }
    
    
    @Test( expected = MissingRequiredParameterException.class )
    public void parameterToIntegerShouldThrowExceptionIfNullValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToInteger( "name", null );
    }
    
    @Test( expected = MissingRequiredParameterException.class )
    public void parameterToIntegerShouldThrowExceptionIfEmptyStringValueIsGiven()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToInteger( "name", "" );
    }
    
    @Test( expected = InvalidParameterValueException.class )
    public void parameterToIntegerShouldThrowExceptionIfInvalidStringValueIsGiven1()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToInteger( "name", "value" );
    }

    @Test( expected = InvalidParameterValueException.class )
    public void parameterToIntegerShouldThrowExceptionIfInvalidStringValueIsGiven2()
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        StringUtils.parameterToInteger( "name", "12.0" );
    }
    
}
