import java.util.LinkedList;
import java.util.Queue;

public class Monkey {
	public Queue<Integer> items = new LinkedList<Integer>();
	public String operation;
	public int operationNum;
	public int test;
	public int trueMonkey;
	public int falseMonkey;
	public int inspectionNum;
	
	public Monkey(Queue<Integer> items, String operation, int operationNum, int test, int trueMonkey, int falseMonkey, int inspectionNum) {
		this.items = items;
		this.operationNum = operationNum;
	    this.test = test;
		this.operation = operation;
		this.trueMonkey = trueMonkey;
		this.falseMonkey = falseMonkey;
		this.inspectionNum = inspectionNum;
		
	}
	
	public int TestplusOpNum() {
		return test + operationNum;
	}
}