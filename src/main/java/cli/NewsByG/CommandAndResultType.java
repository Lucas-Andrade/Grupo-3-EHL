package main.java.cli.NewsByG;

import java.util.concurrent.Callable;

/**TODO
 * Class whose instances have a {@link Callable} and the {@code resultType} from its {@link Callable#call()}
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 */
public class CommandAndResultType <T>
{
	private final Callable< T > callable;
	private final String resultType;

	public CommandAndResultType(Callable< T > callable, String resultType)
	{
		this.callable = callable;
		this.resultType = resultType;
	}

	/**
	 * @return the callable
	 */
	public Callable< T > getCallable()
	{
		return callable;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType()
	{
		return resultType;
	}
	

}
