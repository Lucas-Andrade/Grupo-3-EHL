package utils;


import java.util.Map;


/**
 * Class whose instances represent lists of options. Each instance has a public
 * final field of type {@link Map} whose keys are the options and values are
 * their descriptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class OptionsList
{

    /**
     * The container of the options and their descriptions.
     */
    private final Map< String, String > options;

    /**
     * Creates a new {@link OptionList} whose names of the options are the keys
     * of {@code options} and whose descriptions are the values of
     * {@code options}.
     * 
     * @param options
     *        The {@link Map} whose keys are the names of the options and whose
     *        values are the descriptions.
     */
    public OptionsList( Map< String, String > options )
    {

        this.options = options;
    }

    /**
     * Returns the list of options.
     * 
     * @return The list of options.
     */
    public Map< String, String > getOptions()
    {

        return options;
    }

    /**
     * Returns the string representation of this list of options.
     */
    @Override
    public String toString()
    {

        return options.toString();
    }
}
