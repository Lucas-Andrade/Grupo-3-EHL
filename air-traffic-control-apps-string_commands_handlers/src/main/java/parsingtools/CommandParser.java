package parsingtools;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import parsingtools.commandfactories.CommandFactory;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * Class whose instances are responsible for translating string-commands into their strongly-typed
 * commands counterparts. Each instance of {@link CommandParser} provides two public methods: one
 * for registering string-commands, another for getting the commands correspondent to each
 * string-command.
 * 
 * <p>
 * <b>About string-commands and commands:</b>
 * </p>
 * <p>
 * <i>Commands</i> are {@link Callable} instances able to perform some action through their method
 * {@link Callable#call()}. <i>String-commands</i> are string representations of commands.
 * </p>
 * <p>
 * </p>
 * <p>
 * A string-command has the following syntax:
 * 
 * <pre>
 * {@literal<}string-command> -> {@literal<}method> {@literal<}path> [{@literal<}parameters-list>]
 *  
 *   {@literal<}method> -> GET | POST
 *  
 *   {@literal<}path> -> /{@literal<}path_component>[/{@literal<}path_component>]*
 *     {@literal<}path_component> -> {@literal<}fixed_component> | {@literal<}placeholder>
 *       {@literal<}fixed_component> -> {@literal<}string>
 *       {@literal<}placeholder> -> {{@literal<}string>}
 *  
 *   {@literal<}parameters-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
 *     {@literal<}name> -> {@literal<}string>
 *     {@literal<}value> -> {@literal<}string>
 * </pre>
 * 
 * <i>General string-command</i>s are abstractions of concrete string-commands. They are used in
 * registration, their {@code placeholder}s follow the syntax above and their parameters-list is
 * omitted since the {@code method} and {@code path} unambiguously identify different
 * string-commands. The following are examples of general string-commands:
 * <ul style="list-style-type:none">
 * <li><code>GET /users/{username}</code></li>
 * <li><code>PATCH /airships/{flightId}</code></li>
 * </ul>
 * In a <i>concrete string-command</i>, the placeholders are to be replaced with concrete values and
 * a parameters-list might be required. The text at the position of a placeholder <code>{ph}</code>
 * is the value of a parameter with name {@code ph}. For instance,
 * <ul>
 * <li>the string '{@code Jonathan}' in <center><code>GET /users/Jonathan</code></center> is the
 * value of the parameter with name ' {@code username}';</li>
 * <li>there are two parameters in the concrete string-command <center>
 * <code>PATCH /airships/id3 longitude=34.7</code></center>: the parameter with name '
 * {@code flightId}' and value ' {@code id3}' (a placeholder in the {@code path}) and the parameter
 * with name '{@code longitude}' and value '{@code 34.7}' (in the parameters-list).</li>
 * </ul>
 * Other examples of concrete string-commands:
 * <ul style="list-style-type:none">
 * <li><code>POST /users username=u1&password=p1&email=em@il</code></li>
 * <li><code>GET /airships/nbPassengers/100/bellow accept=text/html</code></li>
 * </ul>
 * </p>
 * <p>
 * <b>CommandParser instances usage (registration):</b>
 * </p>
 * <p>
 * String-commands are registered through method
 * {@link #registerCommand(String, String, CommandFactory)}. Each call to this method creates an
 * internal mapping between a general string-command and the factory able to produce commands
 * correspondent to concretions of this general string-command.<br/>
 * Some examples of registrations correspondent to the concrete string-commands given above are the
 * following lines
 * <ul style="list-style-type:none">
 * <li><code>registerCommand( &quot;POST&quot;, &quot;/users&quot;, new MyFactory1() );</code></li>
 * <li>
 * <code>registerCommand( &quot;GET&quot;, &quot;/airships/nbPassengers/{nbP}/bellow&quot;, new Factory2() );</code>
 * </li>
 * </ul>
 * where {@code MyFactory1} and {@code MyFactory2} would be the subclasses of {@link CommandFactory}
 * able to produce the commands that execute the operations of posting users and getting the
 * airships with less passengers than a certain threshold, respectively.
 * </p>
 * <p>
 * A {@link InvalidRegisterException} will be thrown if one of the following two cases happen:
 * <ol>
 * <li>one calls {@code registerCommand} twice using the same combination of {@code method} and
 * {@code path} or
 * <li>one calls {@code registerCommand} twice using the same {@code method} and using {@code path}s
 * that are equal until a certain path-component and then have different placeholders. <br/>
 * <b>E.g.</b> By trying to register two '{@code GET}' commands with path arguments <center>
 * <code>/fixed/fixed2/{ph1}/{ph}</code></center> and <center><code>/fixed/fixed2/{ph2}/{ph}</code>
 * </center>this exception would be thrown since the components after the component" {@code fixed2}"
 * are both placeholders and are different: <code>{ph1}</code> and <code>{ph2}</code>.</li>
 * </ol>
 * </p>
 * <p>
 * <b>CommandParser instances usage (getting commands):</b>
 * </p>
 * <p>
 * After registration of general string-commands, one may obtain the command associated with a
 * certain concrete string-command using the method {@link #getCommand(Map, String...)}. This method
 * must receive
 * <ul>
 * <li>a {@link Map} whose keys and values are, respectively, the parameters names and values of the
 * parameters contained in the {@code parameters-list} of a certain concrete string-command;</li>
 * <li>either two strings, three strings, an array of two strings or an array of three strings. The
 * first two strings must be (in this order) the {@code method} and the {@code path} of the same
 * concrete string-command. The third string is the {@code parameters-list}, it is optional.</li>
 * </ul>
 * Notes: (1) placeholders' names and values are not required in the map, they will be added
 * internally to the method; (2) the string {@code parameters-list} is an optional argument even if
 * the parameters are required for producing the command.
 * </p>
 * <p>
 * Among other situations, exceptions will be thrown when trying to get a command:
 * <ul>
 * <li>corresponding to a concrete string-command that is not a concretion of a previously
 * registered general string-command,</li>
 * <li>giving a map that does not contain all the parameters required to produce the command;</li>
 * <li>giving invalid values to some of those parameters or to a placeholder.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Usage example:</b>
 * </p>
 * <p>
 * Let us assume that instances of {@code Factory1} are able to create commands when given valid
 * values of the parameters with names {@code placeholder} and {@code paramName}. After registering
 * a general string-command by calling
 * 
 * <pre>
 * registerCommand( &quot;GET&quot;, &quot;/fixed/{placeholder}&quot;, new Factory1() );
 * </pre>
 * 
 * one may try to get a command using the instructions
 * 
 * <pre>
 * Map m = new HashMap();
 * m.put( &quot;paramName&quot;, &quot;paramValue&quot; );
 * getCommand( m, &quot;GET&quot;, &quot;/fixed/goodValue&quot;, &quot;paramName=paramValue&quot; );
 * </pre>
 * 
 * or the instructions
 * 
 * <pre>
 * Map m = new HashMap();
 * m.put( &quot;paramName&quot;, &quot;paramValue&quot; );
 * getCommand( m, &quot;GET&quot;, &quot;/fixed/goodValue&quot; );
 * </pre>
 * 
 * and expect success if the parameter values {@code goodValue} and {@code paramValue} are valid
 * values.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CommandParser {
    
    
    
    // INSTANCE FIELDS
    
    /**
     * The root of this {@link CommandParser}'s internal tree.
     */
    private final Node root = new Node( "/" );
    
    
    
    // INNER CLASS
    /**
     * Inner class whose instances represent tree nodes of an internal tree of string-commands
     * stored by a {@link CommandParser}.
     * <p>
     * <b>Nodes properties:</b>
     * </p>
     * <p>
     * <ul>
     * <li>Nodes may either be <i>fixed nodes</i> or <i>placeholder nodes</i>;</li>
     * <li>Each node stores a string {@link #content}; in the case of placeholder nodes, these
     * strings start with the character '{' and end with the character '}';</li>
     * <li>It is said that "node1 is a child of node2" if {@code node2.}{@link #fixedChildNodes}
     * {@code .containsValue(node1)} or {@code node2.}{@link #placeholderChildNode}
     * {@code .equals(node1)};
     * <li>Each node may
     * <ul>
     * <li>have several fixed child nodes;</li>
     * <li>have, at most, one placeholder child node;</li>
     * <li>store, at most, one {@link CommandFactory}.</li>
     * </ul>
     * </li>
     * </ul>
     * </p>
     * <p>
     * <b>CommandParser instances internal tree:</b>
     * </p>
     * <p>
     * <ul>
     * <li>
     * Each {@link CommandParser} instance stores string-commands as branches of its internal tree.
     * The internal tree is a virtual concept where branches are sets of sequential {@link Node}s.</li>
     * <li>A new branch is added to this tree every time a new string-command is registered (via
     * method {@link CommandParser#registerCommand(String, String, CommandFactory)}).</li>
     * <li>The tree's origin is the node {@link CommandParser#root}. The second node of each branch
     * (the child nodes of {@link CommandParser#root}) corresponds to the {@code method} of a
     * string-command. All the following child nodes of a branch correspond to the several
     * {@code path_component}s or the string-command represented by that branch.</li>
     * <li>{@link Node}s may be shared by several branches if they correspond to string-commands
     * whose {@code method}s are equal and whose {@code path}s are equal until a certain
     * {@code path_component}.</li>
     * <li>The factory given as the last argument in the method
     * {@link CommandParser#registerCommand(String, String, CommandFactory)} method is stored in the
     * last node of the tree-branch corresponding to the string-command being registered.</li>
     * <li>
     * <b>E.g.</b> if one registers the following commands:<br/>
     * <code>&nbsp;&nbsp;registerCommand( "M1", "/f1/{ph1}", new Factory1() );<br/>
     * &nbsp;&nbsp;registerCommand( "M2", "/f3/f5", new Factory2() );<br/>
     * &nbsp;&nbsp;registerCommand( "M1", "/f2", new Factory3() );<br/>
     * &nbsp;&nbsp;registerCommand( "M1", "/f1", new Factory4() );<br/>
     * &nbsp;&nbsp;registerCommand( "M3", "/f4/{ph2}", new Factory5() );</code><br/>
     * the internal tree looks like the following diagram:
     * 
     * <pre>
     *           ( root )
     *          /   |    \
     *      (M1)   (M2)   (M3)
     *     /   \      \       \
     *  (f1)*  (f2)*  (f3)   (f4)
     *   |             |       |
     * ({ph1})*     (f5)*   ({ph2})*
     * </pre>
     *
     * In this diagram,
     * <ul>
     * <li>the symbols '{@code (}' and '{@code )}' represent a node whose content is the string
     * between then;</li>
     * <li>the symbol '{@code *}' flags which nodes store factories and</li>
     * <li>the symbols '{@code /}', '{@code |}' and '{@code \}' mean that the nodes bellow them are
     * child node of the nodes above them.</li>
     * </ul>
     * For instance, {@code (f3)} is a fixed node of content "{@code f3}" storing no
     * {@link CommandFactory}; its child {@code (f5)} is also a fixed node but it stores a factory
     * of type {@code Factory2}.</li>
     * </ul>
     * </p>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     * @see CommandParser section "About string-commands and commands"
     */
    private static class Node {
        
        // INSTANCE FIELDS
        public final String content;
        public final Map< String, Node > fixedChildNodes;
        public Node placeholderChildNode;
        public CommandFactory< ? > factory;
        
        // CONSTRUCTOR
        public Node( String content ) {
            
            this.content = content;
            this.fixedChildNodes = new HashMap<>();
        }
        
        
        // PUBLIC METHODS
        
        /**
         * Checks whether this {@link Node} has a placeholder child.
         * 
         * @param currentContent
         *            The content of this node.
         * @return {@code true} if {@code this.placeholderChildNode!=null};<br/>
         *         {@code false} otherwise.
         */
        public boolean hasPlaceholderChild( String currentContent ) {
            
            return placeholderChildNode != null
                   && !placeholderChildNode.content.equals( currentContent );
        }
        
        /**
         * Returns a fixed node which is a child of {@code this} node and has the {@link #content}
         * {@code content}. If already existent, returns the existing one; otherwise, creates a new
         * node with this content and adds it to {@code this} node's {@link #fixedChildNodes}.
         * 
         * @param content
         *            The content of the node to get.
         * @return A fixed node which is a child of {@code this} node and has the content
         *         {@code content}.
         */
        public Node getFixedChildWithThisContent( String content ) {
            
            Node node = fixedChildNodes.get( content );
            if( node == null )
                node = addFixedChild( content );
            return node;
        }
        
        /**
         * Returns a placeholder which is a child of {@code this} node. If already existent, returns
         * the existing one; otherwise, creates a new node with this content and assigns it to
         * {@code this} node's {@link #placeholderChildNode}.
         * 
         * @param content
         *            The content of the new placeholder, if it does not exist.
         * @return A placeholder node which is a child of {@code this} node.
         */
        public Node getPlaceholderChild( String content ) {
            
            if( placeholderChildNode == null )
                placeholderChildNode = new Node( content );
            return placeholderChildNode;
        }
        
        /**
         * If existent, returns the fixed child node of {@code this} node with the {@link #content}
         * {@code content}; if not, returns the existing placeholder child node of {@code this}
         * node; if {@code this} has no placeholder either, returns {@code null}.
         * 
         * @param content
         *            The content of the fixed child node, if existent.
         * @return By this order of choice:<br/>
         *         the fixed child node of {@code this} node with the {@link #content}
         *         {@code content}, if existent; or<br/>
         *         the placeholder child node of {@code this} node, if existent; or<br/>
         *         {@code null}.
         */
        public Node getChildForContent( String content ) {
            
            Node n = fixedChildNodes.get( content );
            if( n == null )
                n = placeholderChildNode;
            return n;
        }
        
        
        // PRIVATE METHOD
        /**
         * Adds a fixed child to this node's {@link #fixedChildNodes} container. Attention: already
         * existing fixed child nodes with the same content will be replaced.
         * 
         * @param currentContent
         *            The content of this node.
         * @return The newly added {@link Node}.
         */
        private Node addFixedChild( String currentContent ) {
            
            Node n = new Node( currentContent );
            fixedChildNodes.put( currentContent, n );
            return n;
        }
        
    }
    
    
    
    // PUBLIC METHODS
    
    /**
     * Registers an association between a string-command and a {@link StringsToCommandsFactory}.
     * 
     * @param method
     *            The request method (i.e. <code>DELETE | GET | PATCH | POST</code>).
     * @param path
     *            The resource path. It must have the syntax<br/>
     *            <code>
     *             {@literal<}path> -> /{@literal<}path_component>[/{@literal<}path_component>]*<br/>
     *             {@literal<}path_component> -> {@literal<}fixed_component> | {@literal<}placeholder><br/>
     *             {@literal<}fixed_component> -> {@literal<}string><br/>
     *             {@literal<}placeholder> -> {{@literal<}string>}<br/>
     *            </code> and the {@code path-component}s with format
     *            <code>{{@literal<}string{@literal>}}</code> are placeholders, meaning they are
     *            symbols holding places to be subsequently replaced by string-values. </p>
     * @param cmdFactory
     *            The {@link StringsToCommandsFactory} responsible for producing the command
     *            represented by the string-command with this {@code method} and {@code path}.
     * @throws InvalidRegisterException
     *             If one of the following two cases happen:
     *             <ol>
     *             <li>one calls {@code registerCommand} twice using the same combination of
     *             {@code method} and {@code path} or
     *             <li>one calls {@code registerCommand} twice using the same {@code method} and
     *             using {@code path}s that are equal until a certain path-component and then have
     *             different placeholders. <br/>
     *             <b>E.g.</b> By trying to register two '{@code GET}' commands with path arguments
     *             <center> <code>/fixed/fixed2/{ph1}/{ph}</code></center> and <center>
     *             <code>/fixed/fixed2/{ph2}/{ph}</code> </center>this exception would be thrown
     *             since the components after the component" {@code fixed2}" are both placeholders
     *             and are different: <code>{ph1}</code> and <code>{ph2}</code>.</li>
     *             </ol>
     * @see CommandParser section "CommandParser instances usage (registration)"
     */
    public void registerCommand( String method, String path, CommandFactory< ? > cmdFactory )
        throws InvalidRegisterException {
        
        String[] treePathElementsArray = (method.trim() + path).split( "/" );
        updateSubtree( root, treePathElementsArray, 0, cmdFactory );
    }
    
    /**
     * Parses the given strings and produces the corresponding {@link Callable} instance. The
     * {@code path-component}s with format <code>{{@literal<}string>}</code> are placeholders. </p>
     * Arguments are composed of two or three elements. Examples are: POST /products/hotel
     * strs=5&name=Hotel1&description=hotel maravilha1&loginName=lfalcao&loginPassword=slb GET
     * /products/hotel
     * 
     * @param argsParametersMap
     *            The string-command's {@link Map} of parameters whose keys are the parameters names
     *            and whose values are the parameters values.
     * @param args
     *            The string-command to be parsed.
     * @return The corresponding {@link Callable} instance.
     * 
     * @throws InternalErrorException
     *             If an internal error occurred (not supposed to happen).
     * @throws InvalidArgumentException
     *             If {@code parameters==null}.
     * @throws InvalidCommandSyntaxException
     *             If {@code args.length} is not 2 or 3.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid.
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             If there is no user in {@link #postingUsersDatabase} whose username is the login
     *             name receive in the parameters map. The message of this exception is <i>«{login
     *             name} not found in {@code postingUsersDatabase.getDatabaseName()}»</i>.
     * @throws UnknownCommandException
     *             If the given string-command wasn't registered.
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     */
    public Callable< ? > getCommand( Map< String, String > argsParametersMap, String... args )
        throws MissingRequiredParameterException, InvalidArgumentException,
        InvalidCommandSyntaxException, WrongLoginPasswordException, UnknownCommandException,
        NoSuchElementInDatabaseException, InvalidParameterValueException {
        
        if( args.length < 2 || args.length > 3 )
            throw new InvalidCommandSyntaxException(
                                                     "Commands must have either 1 or 2 space-characters." );
        
        String cmd = args[0] + args[1];
        String[] methodAndPathElements = cmd.split( "/" );
        
        
        return internalGetCommand( root, methodAndPathElements, 0, argsParametersMap );
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
     *            The {@link CommandFactory} instance to be stored in the last node of the new
     *            branch.
     * @throws InvalidRegisterException
     *             If the given command cannot be registered (i.e. perhaps the grammar is not
     *             correct)
     */
    private void updateSubtree( Node rootNode, String[] methodAndPathElements, int pathStartIndex,
                                CommandFactory< ? > cmdFactory ) throws InvalidRegisterException {
        
        // stopping condition
        if( pathStartIndex == methodAndPathElements.length ) {
            if( rootNode.factory == null ) {
                rootNode.factory = cmdFactory;
                return;
            }
            else throw new InvalidRegisterException();
        }
        
        // create the new child for this rootNode
        String currentContent = methodAndPathElements[pathStartIndex];
        Node node;
        if( !isPlaceholderString( currentContent ) ) {
            node = rootNode.getFixedChildWithThisContent( currentContent );
        }
        else {
            if( rootNode.hasPlaceholderChild( currentContent ) )
                throw new InvalidRegisterException( currentContent,
                                                    rootNode.placeholderChildNode.content );
            node = rootNode.getPlaceholderChild( currentContent );
        }
        updateSubtree( node, methodAndPathElements, pathStartIndex + 1, cmdFactory );
    }
    
    // used in getCommand method
    /**
     * Returns a command.
     * <p>
     * Auxiliary method that recursively traverses the parser's internal tree of registered commands
     * in search for the command factory correspondent to the string-command whose method and path
     * are stored in the array {@code methodAndPathElements}. In the way, it completes
     * {@code parameters} with the names of the placeholders of the registered command and the
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
     * @return The {@link Callable} instance corresponding to the string-command.
     * 
     * @throws InternalErrorException
     *             If an internal error occurred (not supposed to happen).
     * @throws InvalidArgumentException
     *             If {@code parameters==null}.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid.
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the parameters required for
     *             instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             If there is no user in {@link #postingUsersDatabase} whose username is the login
     *             name receive in the parameters map. The message of this exception is <i>«{login
     *             name} not found in {@code postingUsersDatabase.getDatabaseName()}»</i>.
     * @throws UnknownCommandException
     *             If the given string-command wasn't registered.
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     */
    private Callable< ? > internalGetCommand( Node rootNode, String[] methodAndPathElements,
                                              int pathStartIndex, Map< String, String > parameters )
        throws WrongLoginPasswordException, InvalidArgumentException,
        MissingRequiredParameterException, UnknownCommandException,
        NoSuchElementInDatabaseException, InvalidParameterValueException {
        
        if( pathStartIndex == methodAndPathElements.length ) {
            if( rootNode.factory == null )
                throw new UnknownCommandException( "Unknown command!" );
            
            return rootNode.factory.newCommand( Collections.unmodifiableMap( parameters ) );
        }
        
        String currentContent = methodAndPathElements[pathStartIndex];
        Node child = rootNode.getChildForContent( currentContent );
        if( child == null )
            throw new UnknownCommandException( "Unknown command!" );
        
        if( isPlaceholderString( child.content ) )
            parameters.put( child.content.substring( 1, child.content.length() - 1 ),
                            currentContent );
        
        return internalGetCommand( child, methodAndPathElements, pathStartIndex + 1, parameters );
    }
    
    // used in updateSubtree and getCommandInternal methods
    /**
     * Checks whether the given string is relative to a placeholder.
     * 
     * @param currentContent
     *            The content to be evaluated
     * @return <code>true</code> if it is a placeholder node content;<br/>
     *         <code>false</code> otherwise.
     */
    private boolean isPlaceholderString( String currentContent ) {
        
        return currentContent.startsWith( "{" ) && currentContent.endsWith( "}" );
    }
    
}
