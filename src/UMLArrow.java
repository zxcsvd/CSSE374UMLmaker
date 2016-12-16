import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UMLArrow {

	private UMLNode from, to;
	private Map<String, String> args;
	
	public UMLArrow(UMLNode from, UMLNode to){
		this.from = from;
		this.to = to;
		this.args = new HashMap<String,String>();
		addArg("arrowhead", "hollow");
		addArg("arrowbody", "full");
	}
	
	public void addArg(String arg, String value){
		this.args.put(arg, value);
	}
	
}
