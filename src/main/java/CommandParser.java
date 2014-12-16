package main.java;


import java.util.HashMap;
import java.util.Map;

import main.java.commands.Command;
import main.java.commands.CommandFactory;
import main.java.exceptions.commandParserExceptions.DuplicateParametersException;
import main.java.exceptions.commandParserExceptions.InvalidCommandParametersSyntaxException;
import main.java.exceptions.commandParserExceptions.InvalidRegisterException;
import main.java.exceptions.commandParserExceptions.UnknownCommandException;


/**
 * Class whose instances are responsible for translating string-commands into
 * their {@link Command} instance counterparts.
 * 
 * <p style="font-size:16">
 * <b>Usage of {@link CommandParser} instances:</b>
 * </p>
 * A string-command has the following syntax:
 * 
 * <pre>
 * {@literal<}command> -> {@literal<}method> {@literal<}path> {@literal<}parameter-list>
 *  
 *  {@literal<}method> -> GET | POST
 *  
 *  {@literal<}path> -> /{@literal<}path_component>{/{@literal<}path_component>}*
 *  {@literal<}path_component> -> {@literal<}string> | {{@literal<}string>}
 *  
 *  {@literal<}parameters-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
 *  {@literal<}name> -> {@literal<}string>
 *  {@literal<}value> -> {@literal<}string>
 * </pre>
 * 
 * String-commands are string representations of {@link Command} instances that
 * are able to perform some action through their method
 * {@link Command#execute()}.
 * <p>
 * String-commands are registered through method
 * {@link #registerCommand(String, String, CommandFactory) registerCommand};
 * this method must be given the string {@code method}, the string {@code path}
 * (in the formats given above) and a factory that is able to produce a
 * {@link Command} instance. The {@code path-component}s with format
 * <code>{{@literal<}string>}</code> are placeholders.
 * </p>
 * <p>
 * After registration of string-commands, one may obtain the {@link Command}
 * instance associated from a certain concrete string-command using the method
 * {@link #getCommand(String...) getCommand} . This method must receive either
 * two strings, three strings, an array of two strings or an array of three
 * strings. In either case, the first two strings must be (in this order) the
 * {@code method} and the {@code path} of an already registered string-command;
 * this {@code path}'s placeholders are to be replaced with concrete values. The
 * third string is optional, it is the {@code parameters-list} with the
 * parameters needed for the factory to produce the {@link Command} instance.
 * </p>
 * <p>
 * E.g. after calling
 * <ul>
 * <code>registerCommand( "GET", "/fixed/{placeholder}", myFactory )</code>
 * </ul>
 * one may call
 * <ul>
 * <code>getCommand( "GET", "/fixed/goodValue", "paramName=paramValue" )</code>
 * </ul>
 * 
 * and the instance {@code myFactory} is supposed to be able to create a
 * {@link Command} instance using the given parameter values {@code goodValue}
 * and {@code paramValue}, if they are valid values. If so, that instance is
 * returned, if not, some exception will be thrown.
 * </p>
 */
public class CommandParser
{
	
	// INSTANCE FIELDS
	/**
	 * The root of the parser's internal tree.
	 */
	private final Node root = new Node( "/" );
	
	// INNER CLASS
	/**
	 * Inner class whose instances represent tree nodes of an internal tree of
	 * commands stored by a {@link CommandParser command parser}.
	 * <p>
	 * Each {@link CommandParser} instance stores string-commands as branches of
	 * the internal tree. The tree's origin is the {@link Node node}
	 * {@link CommandParser#root}; a new branch is added to this tree every time
	 * a new string-command is registered. The factory given as the last
	 * argument in the
	 * {@link CommandParser#registerCommand(String, String, CommandFactory)
	 * registerCommand} method is stored in the last nodes of the tree-branch
	 * corresponding to the string-command being registered.
	 * </p>
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	private static class Node
	{
		
		public final String content;
		public final Map< String, Node > fixedChilds;
		public Node placeholderChild;
		public CommandFactory factory;
		
		public Node( String content ) {
			
			this.content = content;
			this.fixedChilds = new HashMap<>();
		}
		
		public boolean hasPlaceholderChild( String currentContent ) {
			
			return placeholderChild != null
					&& !placeholderChild.content.equals( currentContent );
		}
		
		public Node addFixedChild( String currentContent ) {
			
			Node n;
			fixedChilds.put( currentContent, n = new Node( currentContent ) );
			return n;
			
		}
		
		/**
		 * Creates a child fixed node node with {@link #content}
		 * {@code currentContent} or returns the already existing child fixed
		 * node with this {@link #content}.
		 * 
		 * @param currentContent
		 * @return A fixed node.
		 */
		public Node createAndGetFixedNodeWithThisContentOrGetExistingOne(
				String currentContent ) {
			
			Node node = fixedChilds.get( currentContent );
			if( node == null )
				node = addFixedChild( currentContent );
			return node;
		}
		
		/**
		 * Creates the child placeholder node with {@link #content}
		 * {@code currentContent} or returns the already existing child
		 * placeholder.
		 * 
		 * @param currentContent
		 * @return A placeholder node.
		 */
		public Node createAndGetPlaceholderNodeOrGetExistingOne(
				String currentContent ) {
			
			if( placeholderChild == null )
				placeholderChild = new Node( currentContent );
			return placeholderChild;
		}
		
		/**
		 * Returns the child with the specified content or {@code null} if not
		 * found.
		 * <p>
		 * Implementation details: This method first tries to find the value in
		 * fixedNodes. If it finds it, returns that node. if it does not,
		 * returns the placeholderChild, that may be null;
		 * </p>
		 * 
		 * @param currentContent
		 *            the current content to find
		 * @return The child with the specified content or {@code null} if not
		 *         found
		 */
		public Node getChildForValue( String content ) {
			
			Node n = fixedChilds.get( content );
			if( n == null )
			{
				n = placeholderChild;
			}
			return n;
		}
	}
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of {@link CommandParser}.
	 */
	public CommandParser() {
		
	}
	
	
	
	// PUBLIC METHODS
	
	/**
	 * Method used to register an association between a string-command and its
	 * associated {@link CommandFactory factory}. This method must be given the
	 * string {@code method} with the syntax
	 * <p>
	 * <code>{@literal<}method> -> GET | POST</code>
	 * </p>
	 * , the string {@code path} with the syntax
	 * <p>
	 * <code>
	 *  {@literal<}path> -> /{@literal<}path_component>[/{@literal<}path_component>]*
	 *  {@literal<}path_component> -> {@literal<}string> | {{@literal<}string>}
	 * </code>
	 * </p>
	 * <p>
	 * and the factory associated with these {@code method} and {@code path}.
	 * The {@code path-component}s with format <code>{{@literal<}string>}</code>
	 * are placeholders.
	 * </p>
	 * 
	 * @param method
	 *            The request method (i.e. GET or POST).
	 * @param path
	 *            The resource path.
	 * @param cmdFactory
	 *            The factory instance.
	 * @throws InvalidRegisterException
	 *             It will be thrown if one of the following two cases happen:
	 *             <ol>
	 *             <li>one calls
	 *             {@link #registerCommand(String, String, CommandFactory)
	 *             registerCommand} twice with the same string arguments
	 *             {@code method} and {@code path} or
	 *             <li>one makes two calls of
	 *             {@link #registerCommand(String, String, CommandFactory)
	 *             registerCommand} that share the same {@code method} argument
	 *             and whose {@code path} arguments are equal until a certain
	 *             component, and their next components are both placeholders
	 *             but are different (e.g. by trying to register two '
	 *             {@code GET}'commands with path arguments "
	 *             <code>/fixed/fixed2/{ph1}/{ph}</code>" and "
	 *             <code>/fixed/fixed2/{ph2}/{ph}</code>
	 *             ", this exception would be thrown since the components after the component"
	 *             {@code fixed2}" are both placeholders and are different:
	 *             <code>{ph1}</code> and <code>{ph2}</code>).</li>
	 *             </ol>
	 */
	public void registerCommand( String method, String path,
			CommandFactory cmdFactory ) throws InvalidRegisterException {
		
		String[] treePathElementsArray = (method.trim() + path).split( "/" );
		updateSubtree( root, treePathElementsArray, 0, cmdFactory );
	}
	
	/**
	 * Parses the given strings and produces the corresponding {@link Command}
	 * instance. The {@code path-component}s with format
	 * <code>{{@literal<}string>}</code> are placeholders.
	 * 
	 * @param args
	 *            The command's {@link String string} representation that is to
	 *            be parsed.
	 * @return The corresponding {@link Command} instance
	 * @throws InvalidCommandParametersSyntaxException
	 *             If the parameters from parameters-list are not correctly
	 *             separated by '{@code &}' or a certain parameter has not the
	 *             format <code>{@literal <}name>={@literal <}value></code>.
	 * @throws DuplicateParametersException
	 *             if the command has duplicated arguments
	 * @throws UnknownCommandException
	 *             If the given command is not in the register.
	 */
	public Command getCommand( String... args ) throws UnknownCommandException,
			DuplicateParametersException,
			InvalidCommandParametersSyntaxException {
		
		if( args.length < 2 || args.length > 3 )
			throw new UnknownCommandException(
					"args must have 2 or three elements" );
		
		String cmd = args[0] + args[1];
		String[] pathElements = cmd.split( "/" );
		
		Map< String, String > parametersMap = (args.length == 2)
				? new HashMap< String, String >()
				: getParameters( args[2] );
		
		Command command = getCommandInternal( root, pathElements, 0,
				parametersMap );
		return command;
	}
	
	
	
	// AUXILIARY PRIVATE METHODS
	
	// used in registerCommand method
	/**
	 * Recursively updates the internal tree whenever a new command is
	 * registered. Every time a new node is created by this method, the method
	 * calls itself again; in this call the {@code rootNode} is the node created
	 * in the last call. In the end of this method the tree has a new branch
	 * whose last node stores the {@code cmdFactory}.
	 * 
	 * @param rootNode
	 *            The subtree's root.
	 * @param pathElements
	 *            An array containing the elements of the method and the path
	 *            (i.e. <code>GET /users/{username}</code> results in an array
	 *            containing "GET", "users" and "{username}").
	 * @param pathStartIndex
	 *            The start index of the array {@code pathElements} (to enable
	 *            recursion).
	 * @param cmdFactory
	 *            The {@link CommandFactory} instance to
	 *            be stored in the last node of the new branch.
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the
	 *             grammar is not correct)
	 */
	private void updateSubtree( Node rootNode, String[] pathElements,
			int pathStartIndex, CommandFactory cmdFactory )
			throws InvalidRegisterException {
		
		// stopping condition
		if( pathStartIndex == pathElements.length )
		{
			rootNode.factory = cmdFactory;
			return;
		}
		
		// create the new child for this rootNode
		String currentContent = pathElements[pathStartIndex];
		Node node;
		if( !isPlaceHolderString( currentContent ) )
		{
			node = rootNode
					.createAndGetFixedNodeWithThisContentOrGetExistingOne( currentContent );
		}
		else
		{
			if( rootNode.hasPlaceholderChild( currentContent ) )
				throw new InvalidRegisterException( currentContent,
						rootNode.placeholderChild.content );
			node = rootNode
					.createAndGetPlaceholderNodeOrGetExistingOne( currentContent );
		}
		updateSubtree( node, pathElements, pathStartIndex + 1, cmdFactory );
	}
	
	// used in getCommand method
	/**
	 * Interprets the string {@code parameters} that contains a parameters-list
	 * written with the syntax
	 * 
	 * <pre>
	 *  {@literal<}parameters-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
	 *  {@literal<}name> -> {@literal<}string>
	 *  {@literal<}value> -> {@literal<}string>
	 * </pre>
	 * 
	 * and produces a {@link Map} whose keys are the "{@code names}" and whose
	 * values are the "{@code values}".
	 * 
	 * @throws InvalidCommandParametersSyntaxException
	 *             If the parameters are not correctly separated by '{@code &}'
	 *             or a certain parameter has not the format
	 *             <code>{@literal <}name>={@literal <}value></code>.
	 * @throws DuplicateParametersException
	 *             If several values for the same parameter have been received
	 *             in the parameters-list.
	 */
	private Map< String, String > getParameters( String parameters )
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException {
		
		Map< String, String > parametersMap = new HashMap<>();
		
		if( parameters != null )
		{
			String[] parametersElements = parameters.split( "&" );
			for( String parameterElement : parametersElements )
			{
				String[] keyAndValue = parameterElement.split( "=" );
				if( keyAndValue.length != 2 )
					throw new InvalidCommandParametersSyntaxException(
							"Invalid syntax in parameteres list!" );
				if( parametersMap.containsKey( keyAndValue[0] ) )
					throw new DuplicateParametersException( keyAndValue[0] );
				parametersMap.put( keyAndValue[0], keyAndValue[1] );
			}
		}
		return parametersMap;
	}
	
	// used in getCommand method
	/**
	 * Helper method that recursively traverses the parser tree to search for
	 * the command
	 * 
	 * @param rootNode
	 *            The root of the tree to be considered in the search
	 * @param pathElements
	 *            The array containing the command's path elements
	 * @param pathStartIndex
	 *            The start index of the path elements array that are yet to be
	 *            processed
	 * @return The corresponding {@link Command} instance
	 * @throws UnknownCommandException
	 *             if the command is not recognized
	 */
	private Command getCommandInternal( Node rootNode, String[] pathElements,
			int pathStartIndex, Map< String, String > parameters )
			throws UnknownCommandException {
		
		if( pathStartIndex == pathElements.length )
		{
			if( rootNode.factory == null )
				throw new UnknownCommandException(
						"Current node has no command factory!" );
			
			return rootNode.factory.newInstance( parameters );
		}
		
		String currentContent = pathElements[pathStartIndex];
		Node child = rootNode.getChildForValue( currentContent );
		if( child == null )
			throw new UnknownCommandException( "Command path not found!" );
		
		if( isPlaceHolderString( child.content ) )
			parameters.put(
					child.content.substring( 1, child.content.length() - 1 ),
					currentContent );
		
		return getCommandInternal( child, pathElements, pathStartIndex + 1,
				parameters );
	}
	
	// used in updateSubtree and getCommandInternal methods
	/**
	 * Checks whether the given string is relative to a placeholder.
	 * 
	 * @param currentContent
	 *            The content to be evaluated
	 * @return <code>true</code> if it is a placeholder content,
	 *         <code>false</code> otherwise
	 */
	private boolean isPlaceHolderString( String currentContent ) {
		
		return currentContent.startsWith( "{" )
				&& currentContent.endsWith( "}" );
	}
}