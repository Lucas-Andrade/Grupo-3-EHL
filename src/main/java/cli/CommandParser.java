package main.java.cli;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.DuplicateParametersException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.exceptions.commandparserexceptions.UnknownCommandException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;

/**
 * Class whose instances are responsible for translating string-commands into their {@link Callable}
 * instance counterparts.
 * 
 * <p style="font-size:16">
 * <b>Usage of {@link CommandParser} instances:</b>
 * </p>
 * A concrete string-command has the following syntax:
 * 
 * <pre>
 * {@literal<}string-command> -> {@literal<}method> {@literal<}path> [{@literal<}parameter-list>]
 *  
 *  {@literal<}method> -> GET | POST
 *  
 *  {@literal<}path> -> /{@literal<}path_component>[/{@literal<}path_component>]*
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
 * {@link #registerCommand(String, String, StringsToCommandsFactory) registerCommand}; this method
 * must be given the string {@code method}, the string {@code path} (in the formats given above) and
 * a factory that is able to produce a {@link Callable} instance. The {@code path-component}s with
 * format <code>{{@literal<}string>}</code> are placeholders; the placeholder means that, in a
 * concrete command, the text at that position corresponds to the value of a parameter with name. A
 * {@link CommandRegisterException register exception} will be thrown if one of the following two
 * cases happen:
 * <ol>
 * <li>one calls {@link #registerCommand(String, String, StringsToCommandsFactory) registerCommand}
 * twice with the same string arguments {@code method} and {@code path} or
 * <li>one makes two calls of {@link #registerCommand(String, String, StringsToCommandsFactory)
 * registerCommand} that share the same {@code method} argument and whose {@code path} arguments are
 * equal until a certain component, and their next components are both placeholders but are
 * different (e.g. by trying to register two ' {@code GET}'commands with path arguments "
 * <code>/fixed/fixed2/{ph1}/{ph}</code>" and " <code>/fixed/fixed2/{ph2}/{ph}</code>
 * ", this exception would be thrown since the components after the component" {@code fixed2}" are
 * both placeholders and are different: <code>{ph1}</code> and <code>{ph2}</code>).</li>
 * </ol>
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

	/**
	 * A map container that stores the registered commands.
	 */
	private final Map<String, String> register = new HashMap<>();

	// INNER CLASS
	
	/**
	 * Inner class whose instances represent tree nodes of an internal tree of commands stored by a
	 * {@link CommandParser command parser}.
	 * <p>
	 * Each {@link CommandParser} instance stores string-commands as branches of the internal tree.
	 * The tree's origin is the {@link Node node} {@link CommandParser#root}; a new branch is added
	 * to this tree every time a new string-command is registered. The factory given as the last
	 * argument in the
	 * {@link CommandParser#registerCommand(String, String, StringsToCommandsFactory)
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
		public StringsToCommandsFactory<?> factory;

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

		/**
		 * Creates a child fixed node node with {@link #content} {@code currentContent} or returns
		 * the already existing child fixed node with this {@link #content}.
		 * 
		 * @param currentContent
		 * @return A fixed node.
		 */
		public Node createAndGetFixedNodeWithThisContentOrGetExistingOne(String currentContent) {

			Node node = fixedChilds.get(currentContent);
			if (node == null)
				node = addFixedChild(currentContent);
			return node;
		}

		/**
		 * Creates the child placeholder node with {@link #content} {@code currentContent} or
		 * returns the already existing child placeholder.
		 * 
		 * @param currentContent
		 * @return A placeholder node.
		 */
		public Node createAndGetPlaceholderNodeOrGetExistingOne(String currentContent) {

			if (placeholderChild == null)
				placeholderChild = new Node(currentContent);
			return placeholderChild;
		}

		/**
		 * Returns the fixed node child with the {@link #content} {@code content} if existent; if
		 * not returns or {@code null} if not found.
		 * <p>
		 * Implementation details: This method first tries to find the value in fixedNodes. If it
		 * finds it, returns that node. if it does not, returns the placeholderChild, that may be
		 * null;
		 * </p>
		 * 
		 * @param currentContent
		 *            the current content to find
		 * @return The child with the specified content or {@code null} if not found
		 */
		public Node getChildForContent(String content) {

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
	 * Method used to register an association between a string-command and its associated
	 * {@link StringsToCommandsFactory factory}. This method must be given the string {@code method}
	 * with the syntax
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
	 * and the factory associated with these {@code method} and {@code path}. The
	 * {@code path-component}s with format <code>{{@literal<}string>}</code> are placeholders.
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
	 *             <li>one calls {@link #registerCommand(String, String, StringsToCommandsFactory)
	 *             registerCommand} twice with the same string arguments {@code method} and
	 *             {@code path} or
	 *             <li>one makes two calls of
	 *             {@link #registerCommand(String, String, StringsToCommandsFactory)
	 *             registerCommand} that share the same {@code method} argument and whose
	 *             {@code path} arguments are equal until a certain component, and their next
	 *             components are both placeholders but are different (e.g. by trying to register
	 *             two ' {@code GET}'commands with path arguments "
	 *             <code>/fixed/fixed2/{ph1}/{ph}</code>" and "
	 *             <code>/fixed/fixed2/{ph2}/{ph}</code>
	 *             ", this exception would be thrown since the components after the component"
	 *             {@code fixed2}" are both placeholders and are different: <code>{ph1}</code> and
	 *             <code>{ph2}</code>).</li>
	 *             </ol>
	 */
	public void registerCommand(String method, String path, StringsToCommandsFactory<?> cmdFactory)
			throws InvalidRegisterException {

		String[] treePathElementsArray = (method.trim() + path).split("/");
		updateSubtree(root, treePathElementsArray, 0, cmdFactory);
		register.put(method + " " + path, cmdFactory.getCommandsDescription());
	}

	/**
	 * Parses the given strings and produces the corresponding {@link Callable} instance. The
	 * {@code path-component}s with format <code>{{@literal<}string>}</code> are placeholders. </p>
	 * Arguments are composed of two or three elements. Examples are: POST /products/hotel
	 * strs=5&name=Hotel1&description=hotel maravilha1&loginName=lfalcao&loginPassword=slb GET
	 * /products/hotel
	 * 
	 * @param args
	 *            The command's {@link String string} representation that is to be parsed.
	 * @return The corresponding {@link Callable} instance
	 * @throws Exception
	 *             If the user who is posting is not in the {@code postingUsersDatabase}. The
	 *             concrete type of this exception is {@link NoSuchElementInDatabaseException} and
	 *             its message is <i>«{login name} not found in {database's name}.»</i>.
	 * @throws WrongLoginPasswordException
	 *             If the login password received does not match the login username's password.
	 */
	public Callable<?> getCommand(String... args) throws WrongLoginPasswordException, Exception {

		if (args.length < 2 || args.length > 3)
			throw new InvalidCommandSyntaxException(
					"Commands must have either 1 or 2 space-characters.");

		String cmd = args[0] + args[1];
		String[] methodAndPathElements = cmd.split("/");

		Map<String, String> parametersMap = (args.length == 2) ? new HashMap<String, String>()
				: getParametersFromParametersList(args[2]);

		Callable<?> command = getCommandInternal(root, methodAndPathElements, 0, parametersMap);
		return command;
	}

	/**
	 * Returns an unmodifiable view of a map whose keys are the string-commands and whose values are
	 * a description of the commands produced by the factories assigned to those string-commands.
	 * 
	 * @return An unmodifiable view of a map whose keys are the string-commands and whose values are
	 *         a description of the commands produced by the factories assigned to those
	 *         string-commands.
	 */
	public Map<String, String> getRegisteredCommands() {

		return Collections.unmodifiableMap(register);
	}

	// AUXILIARY PRIVATE METHODS

	// used in registerCommand method
	/**
	 * Recursively updates the internal tree whenever a new command is registered. Every time a new
	 * node is created by this method, the method calls itself again; in this call the
	 * {@code rootNode} is the node created in the last call. In the end of this method the tree has
	 * a new branch whose last node stores the {@code cmdFactory}.
	 * 
	 * @param rootNode
	 *            The subtree's root.
	 * @param methodAndPathElements
	 *            An array containing the elements of the method and the path (i.e.
	 *            <code>GET /users/{username}</code> results in an array containing "GET", "users"
	 *            and "{username}").
	 * @param pathStartIndex
	 *            The start index of the array {@code pathElements} (to enable recursion).
	 * @param cmdFactory
	 *            The {@link StringsToCommandsFactory} instance to be stored in the last node of the
	 *            new branch.
	 * @throws InvalidRegisterException
	 *             If the given command cannot be registered (i.e. perhaps the grammar is not
	 *             correct)
	 */
	private void updateSubtree(Node rootNode, String[] methodAndPathElements, int pathStartIndex,
			StringsToCommandsFactory<?> cmdFactory) throws InvalidRegisterException {

		// stopping condition
		if (pathStartIndex == methodAndPathElements.length) {
			if (rootNode.factory == null) {
				rootNode.factory = cmdFactory;
				return;
			} else
				throw new InvalidRegisterException();
		}

		// create the new child for this rootNode
		String currentContent = methodAndPathElements[pathStartIndex];
		Node node;
		if (!isPlaceHolderString(currentContent)) {
			node = rootNode.createAndGetFixedNodeWithThisContentOrGetExistingOne(currentContent);
		} else {
			if (rootNode.hasPlaceholderChild(currentContent))
				throw new InvalidRegisterException(currentContent,
						rootNode.placeholderChild.content);
			node = rootNode.createAndGetPlaceholderNodeOrGetExistingOne(currentContent);
		}
		updateSubtree(node, methodAndPathElements, pathStartIndex + 1, cmdFactory);
	}

	// used in getCommand method
	/**
	 * Interprets the string {@code parameters} that contains a parameters-list written with the
	 * syntax
	 * 
	 * <pre>
	 *  {@literal<}parameters-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
	 *  {@literal<}name> -> {@literal<}string>
	 *  {@literal<}value> -> {@literal<}string>
	 * </pre>
	 * 
	 * and produces a {@link Map} whose keys are the "{@code names}" and whose values are the "
	 * {@code values}".
	 * 
	 * @throws InvalidCommandParametersSyntaxException
	 *             If the parameters are not correctly separated by '{@code &}' or a certain
	 *             parameter has not the format <code>{@literal <}name>={@literal <}value></code>.
	 * @throws DuplicateParametersException
	 *             If several values for the same parameter have been received in the
	 *             parameters-list.
	 */
	private Map<String, String> getParametersFromParametersList(String parameters)
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
	 * Returns a command.
	 * <p>
	 * Auxiliary method that recursively traverses the parser's internal tree of registered commands
	 * in search for the command factory correspondent to the string-command whose method and path
	 * are stored in the array {@code methodAndPathElements}. In the way, it completes
	 * {@code parameters} with the names of the placeholders of the registered command and
	 * correspondent values in the string-command. Returns the instance of {@link Callable} produced
	 * by the factory found.
	 * </p>
	 * 
	 * @param rootNode
	 *            The root of the subtree being traversed in the current iteration of this method.
	 * @param methodAndPathElements
	 *            The array containing the command's method and path elements.
	 * @param pathStartIndex
	 *            The index of the entry of {@code methodAndPathElements} whose corresponding node
	 *            is to be found in the current iteration of this method.
	 * @return The {@link Callable} instance corresponding .
	 * @throws Exception
	 *             If the user who is posting is not in the {@code postingUsersDatabase}. The
	 *             concrete type of this exception is {@link NoSuchElementInDatabaseException} and
	 *             its message is <i>«{login name} not found in {database's name}.»</i>.
	 * @throws WrongLoginPasswordException
	 *             If the login password received does not match the login username's password.
	 * @throws InvalidArgumentException
	 *             If {@code parameters==null}.
	 * @throws RequiredParameterNotPresentException
	 *             If some parameters needed to create the new instance are missing.
	 */
	private Callable<?> getCommandInternal(Node rootNode, String[] methodAndPathElements,
			int pathStartIndex, Map<String, String> parameters) throws WrongLoginPasswordException,
			Exception, InvalidArgumentException, MissingRequiredParameterException {

		if (pathStartIndex == methodAndPathElements.length) {
			if (rootNode.factory == null)
				throw new UnknownCommandException("Unknown command!");

			return rootNode.factory.newInstance(parameters);
		}

		String currentContent = methodAndPathElements[pathStartIndex];
		Node child = rootNode.getChildForContent(currentContent);
		if (child == null)
			throw new UnknownCommandException("Unknown command!");

		if (isPlaceHolderString(child.content))
			parameters.put(child.content.substring(1, child.content.length() - 1), currentContent);

		return getCommandInternal(child, methodAndPathElements, pathStartIndex + 1, parameters);
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