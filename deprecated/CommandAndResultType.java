package main.java.cli.utils;


import java.util.concurrent.Callable;


/**
 * Class whose instances gather an instance of {@link Callable} and a string
 * representation of the concrete type of the instance returned by method
 * {@link Callable#call()} applied on that specific instance.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 */
public class CommandAndResultType< T >
{
	
	private final Callable< T > callable;
	private final String resultType;
	
	public CommandAndResultType( Callable< T > callable, String resultType ) {
		this.callable = callable;
		this.resultType = resultType;
	}
	
	/**
	 * @return the callable
	 */
	public Callable< T > getCallable() {
		return callable;
	}
	
	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}
	
	
}
