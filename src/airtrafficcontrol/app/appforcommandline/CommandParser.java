package airtrafficcontrol.app.appforcommandline;


import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.DuplicateParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidCommandParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.UnknownCommandException;


/**
 * Class whose instances are responsible for translating command strings into
 * their {@link Command} instance counterparts.
 * 
 * Implementation notes: A command execution has the following syntax:
 * 
 * <pre>
 * {@code
 *  {@literal<}command> -> {@literal<}method> {@literal<}path> {@literal<}parameter-list>
 *  {@literal<}method> -> GET | POST
 *  {@literal<}path> -> /{@literal<}path_component>{/{@literal<}path_component>}*
 *  {@literal<}path_component> -> {@literal<}string> | {{@literal<}string>}
 *  {@literal<}parameter-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
 *  {@literal<}name> -> {@literal<}string>
 *  {@literal<}value> -> {@literal<}string>
 * }
 * </pre>
 * 
 */
public class CommandParser
{
	
	/**
	 * The root of the parser tree, which is updated every time a new command is
	 * registered
	 */
	private final Node root = new Node( "/" );
	
	/**
	 * Class whose instances represent the parser tree nodes
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
		
		public Node getAndCreateIfDoesNotExitFixedNode( String currentContent ) {
			Node node = fixedChilds.get( currentContent );
			if( node == null )
				node = addFixedChild( currentContent );
			return node;
		}
		
		public Node getAndCreateIfDoesNotExitPlacehoderNode(
				String currentContent ) {
			if( placeholderChild == null )
			{
				placeholderChild = new Node( currentContent );
			}
			return placeholderChild;
		}
		
		/**
		 * Returns the child with the specified content or {@code null} if not
		 * found. Implementation details: This method first tries to find the
		 * value in fixedNodes. If it finds it, returns that node. if it does
		 * not, returns the placeholderChild, that may be null;
		 * 
		 * @param currentContent
		 *            the current content to find
		 * @return
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
	
	/**
	 * Checks whether the given content is relative to a placeholder node.
	 * 
	 * @param currentContent
	 *            The content to be evaluated
	 * @return <code>true</code> if it is a placeholder node content,
	 *         <code>false</code> otherwise
	 */
	private boolean isPlaceHolderNode( String currentContent ) {
		return currentContent.startsWith( "{" )
				&& currentContent.endsWith( "}" );
	}
	
	/**
	 * Produces the {@link Map} with the parameters
	 * 
	 * @throws InvalidCommandParametersException
	 * @throws DuplicateParametersException
	 */
	private Map< String, String > getParameters( String parameters )
			throws InvalidCommandParametersException,
			DuplicateParametersException {
		Map< String, String > parametersMap = new HashMap<>();
		
		if( parameters != null )
		{
			String[] parametersElements = parameters.split( "&" );
			for( String parameterElement : parametersElements )
			{
				String[] keyAndValues = parameterElement.split( "=" );
				if( keyAndValues.length != 2 )
					throw new InvalidCommandParametersException();
				if( parametersMap.containsKey( keyAndValues[0] ) )
					throw new DuplicateParametersException();
				parametersMap.put( keyAndValues[0], keyAndValues[1] );
			}
		}
		return parametersMap;
	}
	
	/**
	 * Helper method that recursively updates the parser tree whenever a new
	 * command is registered.
	 * 
	 * @param rootNode
	 *            The tree's root
	 * @param pathElements
	 *            An array containing the elements of the path (i.e.
	 *            /users/{username}) results in an array containing "users" and
	 *            "{username}"
	 * @param pathStartIndex
	 *            The start index of the array (to enable recursion)
	 * @param cmdFactory
	 *            The {@link CommandFactory} instance
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the
	 *             grammar is not correct)
	 */
	private void updateSubtree( Node rootNode, String[] pathElements,
			int pathStartIndex, CommandFactory cmdFactory )
			throws InvalidRegisterException {
		if( pathStartIndex == pathElements.length )
		{
			rootNode.factory = cmdFactory;
			return;
		}
		
		String currentContent = pathElements[pathStartIndex];
		Node node;
		if( !isPlaceHolderNode( currentContent ) )
		{
			node = rootNode.getAndCreateIfDoesNotExitFixedNode( currentContent );
		}
		else
		{
			if( rootNode.hasPlaceholderChild( currentContent ) ) { throw new InvalidRegisterException(
					MessageFormat
							.format(
									"Command registred with a placeholder with name '{0}', at node with an already existant placeholder child with name '{1}'",
									currentContent,
									rootNode.placeholderChild.content ) ); }
			node = rootNode
					.getAndCreateIfDoesNotExitPlacehoderNode( currentContent );
		}
		updateSubtree( node, pathElements, pathStartIndex + 1, cmdFactory );
	}
	
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
		
		if( isPlaceHolderNode( child.content ) )
			parameters.put(
					child.content.substring( 1, child.content.length() - 1 ),
					currentContent );
		
		return getCommandInternal( child, pathElements, pathStartIndex + 1,
				parameters );
	}
	
	
	/**
	 * Initiates an instance with no active logging
	 */
	public CommandParser() {}
	
	/**
	 * Method used to register an association between the string and its
	 * associated command factory.
	 * 
	 * @param method
	 *            The request method (i.e. GET, POST)
	 * @param path
	 *            The resource path (e.g. /users/{username}) template. The
	 *            template may have fixed parts and placeholder delimited by {}
	 *            (e.g /users/{userId}. The placeholder means that in a concrete
	 *            command, the placeholder text corresponds to the argument name
	 *            and the command text at that position, corresponds to its
	 *            value.
	 * @param cmdFactory
	 *            The command factory instance
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the
	 *             grammar is not correct)
	 */
	public void registerCommand( String method, String path,
			CommandFactory cmdFactory ) throws InvalidRegisterException {
		path = method.trim() + path;
		String[] treePathElementsArray = path.split( "/" );
		updateSubtree( root, treePathElementsArray, 0, cmdFactory );
	}
	
	/**
	 * Parses the given arguments and produces the corresponding {@link Command}
	 * instance. Arguments are composed of two or three elements. Examples are:
	 * POST /products/hotel strs=5&name=Hotel1&description=hotel
	 * maravilha1&loginName=lfalcao&loginPassword=slb GET /products/hotel
	 * 
	 * @param args
	 *            The command's {@link String} representation that is to be
	 *            parsed
	 * @return The corresponding {@link Command} instance
	 * @throws UnknownCommandException
	 *             if the command is not recognized
	 * @throws InvalidCommandParametersException
	 *             if the command's arguments syntax is invalid
	 * @throws DuplicateParametersException
	 *             if the command has duplicated arguments
	 */
	public Command getCommand( String... args ) throws UnknownCommandException,
			DuplicateParametersException, InvalidCommandParametersException {
		if( args.length < 2 || args.length > 3 ) { throw new IllegalArgumentException(
				"args must have 2 or three elements" ); }
		
		String cmd = args[0] + args[1];
		String[] pathElements = cmd.split( "/" );
		
		Map< String, String > parametersMap = (args.length == 2) ? new HashMap< String, String >()
				: getParameters( args[2] );
		
		Command command = getCommandInternal( root, pathElements, 0,
				parametersMap );
		return command;
	}
}
