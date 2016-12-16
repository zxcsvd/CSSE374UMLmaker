
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UMLNode {
	private String name;
	private List<String> methods;
	private List<String> fields;
	private Map<String, String> args;
	

	public UMLNode(ClassNode cnode){
		this.methods = new LinkedList<String>();
		this.fields = new LinkedList<String>();
		this.args = new HashMap<String, String>();
		
	}
	
	public void addArg(String arg, String value){
		this.args.put(arg, value);
	}
}
