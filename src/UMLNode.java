import org.objectweb.asm.tree.ClassNode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UMLNode {
	private String name;
	/**
	 * Text information on methods of the class.
	 * The class name, with a number prefixed, maps to a map containing the argument names to their values. 
	 * The map will also map "return" to the return type.
	 * 
	 * ex.:
	 * 
	 * public int foo(); public int foo(String bar) will be mapped as:
	 * ["0foo"->["return"->"int"],"1foo"->["bar"->"String","return"->"int"]]
	 * 
	 */
	private Map<String, Map<String, String>> methods;
	private List<String> fields;
	private Map<String, String> args;
	

	public UMLNode(ClassNode cnode){
		this.methods = new HashMap<String, Map<String, String>>();
		this.fields = new LinkedList<String>();
		this.args = new HashMap<String, String>();
//		this.methods = cnode
	}
	
	public void addArg(String arg, String value){
		this.args.put(arg, value);
	}
}
