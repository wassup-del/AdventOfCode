
public class File {
	public String name;
	public int size;
	public DirectoryNode parent;
	
	
	public File(String name, int size, DirectoryNode parent) {
		this.name = name;
		this.size = size;
		this.parent = parent;
	}
}
