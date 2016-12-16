
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.ListUI;

public class UMLListMaker {
	private ClassNode data;
	private UMLListMaker superNode;
	private List<UMLListMaker> interfaces;
	private UMLNode UMLdata;
	private List<UMLListMaker> fields;
	
	public UMLListMaker(ClassNode cnode){
		this.data=cnode;
		this.UMLdata=new UMLNode(cnode);
		CodeReader cr = new CodeReader();
		this.superNode=new UMLListMaker(cr.getSuper(cnode));
		List<ClassNode> listCNodes = cr.getInterfaces(cnode);
		this.interfaces=new LinkedList();
		for(ClassNode c : listCNodes){
			this.interfaces.add(new UMLListMaker(c));
		}
		listCNodes = cr.getFields(cnode);
		this.fields=new LinkedList();
		for(ClassNode c : listCNodes){
			this.fields.add(new UMLListMaker(c));
		}
		
	}
	
	public List<UMLNode> getUMLNodeList(){
		List<UMLNode> l = new LinkedList<>();
		l.add(this.UMLdata);
		List<UMLNode> superList = superNode.getUMLNodeList();
		l.addAll(superList);
		for(UMLListMaker inf : interfaces){
			superList = inf.getUMLNodeList();
			l.addAll(superList);
		}
		return l;
	}
	
	public List<UMLArrow> getArrowList(){
		
		return null;
	}
	
	public ClassNode getData(){
		return data;
	}
}
