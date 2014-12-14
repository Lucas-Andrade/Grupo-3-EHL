package appForConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandParserExceptions.DuplicateParametersException;
import appForConsole.exceptions.commandParserExceptions.InvalidCommandParametersSyntaxException;
import appForConsole.exceptions.commandParserExceptions.InvalidRegisterException;
import appForConsole.exceptions.commandParserExceptions.UnknownCommandException;

/**
 * Class whose instances are responsible for translating string-commands into their {@link Callable}
 * instance counterparts.
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
 * String-commands are string representations of {@link Callable} instances that are able to perform
 * some action through their method {@link Callable#call()}.
 * <p>
 * String-commands are registered through method
 * {@link #registerCommand(String, String, FactoryOfStringParametrizedCallables) registerCommand};
 * this method must be given the string {@code method}, the string {@code path} (in the formats
 * given above) and a factory that is able to produce a {@link Callable} instance. The
 * {@code path-component}s with format <code>{{@literal<}string>}</code> are placeholders.
 * </p>
 * <p>
 * After registration of string-commands, one may obtain the {@link Callable} instance associated
 * from a certain concrete string-command using the method {@link #getCommand(String...) getCommand}
 * . This method must receive either two strings, three strings, an array of two strings or an array
 * of three strings. In either case, the first two strings must be (in this order) the
 * {@code method} and the {@code path} of an already registered string-command; this {@code path}'s
 * placeholders are to be replaced with concrete values. The third string is optional, it is the
 * {@code parameters-list} with the parameters needed for the factory to produce the
 * {@link Callable} instance.
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
 * and the instance {@code myFactory} is supposed to be able to create a {@link Callable} instance
 * using the given parameter values {@code goodValue} and {@code paramValue}, if they are valid
 * values. If so, that instance is returned, if not, some exception will be thrown.
 * </p>
 */
public class CommandParser {

	// INSTANCE FIELDS
	
	/**
	 * The root of the parser's internal tree.
	 */
	private final Node root = new Node("/");

	// INNER CLASS
	
	/**
	 * Inner class whose instances represent tree nodes of an internal tree of commands stored by a
	 * {@link CommandParser command parser}.
	 * <p>
	 * Each {@link CommandParser} instance stores string-commands as branches of the internal tree.
	 * The tree's origin is the {@link Node node} {@link CommandParser#root}; a new branch is added
	 * to this tree every time a new string-command is registered. The factory given as the last
	 * argument in the
	 * {@link CommandParser#registerCommand(String, String, FactoryOfStringParametrizedCallables)
	 * registerCommand} method is stored in the last nodes of the tree-branch corresponding to the
	 * string-command being registered.
	 * </p>
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	private static class Node {

		public final String content;
		public final Map<String, Node> fixedChilds;
		public Node placeholderChild;
		public CommandFactory factory;

		public Node(String content) {

			this.content = content;
			this.fixedChilds = new HashMap<>();
		}

		public boolean hasPlaceholderChild(String currentContent) {

			return placeholderChild != null && !placeholderChild.content.equals(currentContent);
		}

		public Node addFixedChild(String currentContent) {

			Node n;
			fixedChilds.put(currentContent, n = new Node(currentContent));
			return n;

		}

		public Node createAndGetFixedNodeWithThisContentOrGetExistingOne(String currentContent) {

			Node node = fixedChilds.get(currentContent);
			if (node == null)
				node = addFixedChild(currentContent);
			return node;
		}

		/**
		 * Creates a placeholder node with {@link #content} {@code currentContent} or returns
		 * 
		 * @param currentContent
		 * @return
		 */
		public Node createAndGetPlaceholderNodeOrGetExistingOne(String currentContent) {

			if (placeholderChild == null)
				placeholderChild = new Node(currentContent);
			return placeholderChild;
		}

		/**
		 * Returns the child with the specified content or {@code null} if not found. Implementation
		 * details: This method first tries to find the value in fixedNodes. If it finds it, returns
		 * that node. if it does not, returns the placeholderChild, that may be null;
		 * 
		 * @param currentContent
		 *            the current content to find
		 * @return
		 */
		public Node getChildForValue(String content) {

			Node n = fixedChilds.get(content);
			if (n == null) {
				n = placeholderChild;
			}
			return n;
		}
	}

	// CONSTRUTOR
	
	/**
	 * Creates a new instance of {@link CommandParser}.
	 */
	public CommandParser() {

	}

	// PUBLIC METHODS

	/**
	 * Method used to register an association between the string and its associated command factory.
	 * 
	 * @param method
	 *            The request method (i.e. GET, POST)
	 * @param path
	 *            The resource path (e.g. /users/{username}) template. The template may have fixed
	 *            parts and placeholder delimited by {} (e.g /users/{userId}. The placeholder means
	 *            that in a concrete command, the placeholder text corresponds to the argument name
	 *            and the command text at that position, corresponds to its value.
	 * @param cmdFactory
	 *            The command factory instance
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the grammar is not
	 *             correct)
	 */
	public void registerCommand(String method, String path, CommandFactory cmdFactory)
			throws InvalidRegisterException {

		String[] treePathElementsArray = (method.trim() + path).split("/");
		updateSubtree(root, treePathElementsArray, 0, cmdFactory);
	}

	/**
	 * Parses the given arguments and produces the corresponding {@link Command} instance. Arguments
	 * are composed of two or three elements. Examples are: POST /products/hotel
	 * strs=5&name=Hotel1&description=hotel maravilha1&loginName=lfalcao&loginPassword=slb GET
	 * /products/hotel
	 * 
	 * @param args
	 *            The command's {@link String} representation that is to be parsed
	 * @return The corresponding {@link Command} instance
	 * @throws UnknownCommandException
	 *             if the command is not recognized
	 * @throws InvalidCommandParametersSyntaxException
	 *             if the command's arguments syntax is invalid
	 * @throws DuplicateParametersException
	 *             if the command has duplicated arguments
	 */
	public Command getCommand(String... args) throws UnknownCommandException,
			DuplicateParametersException, InvalidCommandParametersSyntaxException {

		if (args.length < 2 || args.length > 3)
			throw new UnknownCommandException("args must have 2 or three elements");

		String cmd = args[0] + args[1];
		String[] pathElements = cmd.split("/");

		Map<String, String> parametersMap = (args.length == 2) ? new HashMap<String, String>()
				: getParameters(args[2]);

		Command command = getCommandInternal(root, pathElements, 0, parametersMap);
		return command;
	}

	// AUXILIAR PRIVATE METHODS

	// used in registerCommand method
	/**
	 * Recursively updates the internal tree whenever a new command is registered. Every time a new
	 * node is created by this method, the method calls itself again; in this call the
	 * {@code rootNode} is the node created in the last call. In the end of this method the tree has
	 * a new branch whose last node stores the {@code cmdFactory}.
	 * 
	 * @param rootNode
	 *            The subtree's root.
	 * @param pathElements
	 *            An array containing the elements of the method and the path (i.e.
	 *            <code>GET /users/{username}</code> results in an array containing "GET", "users"
	 *            and "{username}").
	 * @param pathStartIndex
	 *            The start index of the array {@code pathElements} (to enable recursion).
	 * @param cmdFactory
	 *            The {@link FactoryOfStringParametrizedCallables} instance to be stored in the last
	 *            node of the new branch.
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the grammar is not
	 *             correct)
	 */
	private void updateSubtree(Node rootNode, String[] pathElements, int pathStartIndex,
			CommandFactory cmdFactory) throws InvalidRegisterException {

		// stopping condition
		if (pathStartIndex == pathElements.length) {
			rootNode.factory = cmdFactory;
			return;
		}

		// create the new child for this rootNode
		String currentContent = pathElements[pathStartIndex];
		Node node;
		if (!isPlaceHolderString(currentContent)) {
			node = rootNode.createAndGetFixedNodeWithThisContentOrGetExistingOne(currentContent);
		} else {
			if (rootNode.hasPlaceholderChild(currentContent))
				throw new InvalidRegisterException(currentContent,
						rootNode.placeholderChild.content);
			node = rootNode.createAndGetPlaceholderNodeOrGetExistingOne(currentContent);
		}
		updateSubtree(node, pathElements, pathStartIndex + 1, cmdFactory);
	}

	// used in getCommand method
	/**
	 * Produces the {@link Map} with the parameters
	 * 
	 * @throws InvalidCommandParametersSyntaxException
	 * @throws DuplicateParametersException
	 */
	private Map<String, String> getParameters(String parameters)
			throws InvalidCommandParametersSyntaxException, DuplicateParametersException {

		Map<String, String> parametersMap = new HashMap<>();

		if (parameters != null) {
			String[] parametersElements = parameters.split("&");
			for (String parameterElement : parametersElements) {
				String[] keyAndValue = parameterElement.split("=");
				if (keyAndValue.length != 2)
					throw new InvalidCommandParametersSyntaxException(
							"Invalid syntax in parameteres list!");
				if (parametersMap.containsKey(keyAndValue[0]))
					throw new DuplicateParametersException(keyAndValue[0]);
				parametersMap.put(keyAndValue[0], keyAndValue[1]);
			}
		}
		return parametersMap;
	}

	// used in getCommand method
	/**
	 * Helper method that recursively traverses the parser tree to search for the command
	 * 
	 * @param rootNode
	 *            The root of the tree to be considered in the search
	 * @param pathElements
	 *            The array containing the command's path elements
	 * @param pathStartIndex
	 *            The start index of the path elements array that are yet to be processed
	 * @return The corresponding {@link Command} instance
	 * @throws UnknownCommandException
	 *             if the command is not recognized
	 */
	private Command getCommandInternal(Node rootNode, String[] pathElements, int pathStartIndex,
			Map<String, String> parameters) throws UnknownCommandException {

		if (pathStartIndex == pathElements.length) {
			if (rootNode.factory == null)
				throw new UnknownCommandException("Current node has no command factory!");

			return rootNode.factory.newInstance(parameters);
		}

		String currentContent = pathElements[pathStartIndex];
		Node child = rootNode.getChildForValue(currentContent);
		if (child == null)
			throw new UnknownCommandException("Command path not found!");

		if (isPlaceHolderString(child.content))
			parameters.put(child.content.substring(1, child.content.length() - 1), currentContent);

		return getCommandInternal(child, pathElements, pathStartIndex + 1, parameters);
	}

	// used in updateSubtree and getCommandInternal methods
	/**
	 * Checks whether the given string is relative to a placeholder.
	 * 
	 * @param currentContent
	 *            The content to be evaluated
	 * @return <code>true</code> if it is a placeholder node content, <code>false</code> otherwise
	 */
	private boolean isPlaceHolderString(String currentContent) {

		return currentContent.startsWith("{") && currentContent.endsWith("}");
	}
}