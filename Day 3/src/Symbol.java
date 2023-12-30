import java.util.LinkedList;

public class Symbol {
	
	LinkedList<Integer> list = new LinkedList<Integer>();
	int xCoord;
	int yCoord;

	public Symbol (int xCoord, int yCoord, LinkedList list) {
		
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.list = list;
		
	}

}
