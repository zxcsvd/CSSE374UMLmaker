import org.objectweb.asm.tree.ClassNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UMLListMaker {
	private ClassNode data;
	private UMLListMaker superNode;
	private List<UMLListMaker> interfaces;
	private UMLNode UMLdata;
	private List<UMLListMaker> fields;
	private List<UMLListMaker> methods;

	public UMLListMaker(ClassNode cnode) {
		this(cnode, new HashMap<ClassNode, UMLListMaker>());
	}

	private UMLListMaker(ClassNode cnode, Map<ClassNode, UMLListMaker> map) {
		map.put(cnode, this);
		this.data = cnode;
		this.UMLdata = new UMLNode(cnode);
		CodeReader cr = new CodeReader();
		ClassNode superClassNode = cr.getSuper(cnode);
		if (map.containsKey(superClassNode)) {
			this.superNode = map.get(superClassNode);
		} else {
			this.superNode = new UMLListMaker(superClassNode, map);
		}

		List<ClassNode> listCNodes = cr.getInterfaces(cnode);
		this.interfaces = new LinkedList<UMLListMaker>();
		for (ClassNode c : listCNodes) {
			if (map.containsKey(c)) {
				this.interfaces.add(map.get(c));
			} else {
				this.interfaces.add(new UMLListMaker(c, map));
			}
		}
		
		listCNodes = cr.getFields(cnode);
		this.fields = new LinkedList<UMLListMaker>();
		for (ClassNode c : listCNodes) {
			if (map.containsKey(c)) {
				this.fields.add(map.get(c));
			} else {
				this.fields.add(new UMLListMaker(c, map));
			}
		}
		
		listCNodes = cr.getMethods(cnode);
		this.methods = new LinkedList<UMLListMaker>();
		for (ClassNode c : listCNodes) {
			if (map.containsKey(c)) {
				this.methods.add(map.get(c));
			} else {
				this.methods.add(new UMLListMaker(c, map));
			}
		}
	}

	public List<UMLNode> getUMLNodeList() {
		List<UMLNode> l = new LinkedList<>();
		l.add(this.UMLdata);
		List<UMLNode> superList = superNode.getUMLNodeList();
		l.addAll(superList);
		for (UMLListMaker inf : interfaces) {
			superList = inf.getUMLNodeList();
			l.addAll(superList);
		}
		return l;
	}

	public List<UMLArrow> getArrowList() {

		return null;
	}

	public ClassNode getData() {
		return data;
	}
}
