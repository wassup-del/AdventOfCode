import java.util.ArrayList;

public class DirectoryNode {
	public String name;
	public ArrayList<DirectoryNode> childrenNodes = new ArrayList<>();
	public ArrayList<Integer> childrenFiles = new ArrayList<>();
	public DirectoryNode parent;	
	
	public DirectoryNode(String name) {
		this.name = name;
	}
	public DirectoryNode(String name, ArrayList children) {
		this.name = name;
		this.childrenNodes = childrenNodes;
		this.childrenFiles = childrenFiles;
		this.parent = null;
	}
}
