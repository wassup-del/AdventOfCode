import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class monkeyInTheMiddle {
	public static final String FILE_NAME = "Monkeys.txt";
	public static int bigMod = 1;
	
	public static Monkey[] line = new Monkey[4];

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(FILE_NAME));

		

		for (int i = 0; i < line.length; i++) {
			scan.nextLine();
			scan.next();
			scan.next();
			String li = scan.nextLine();
			String[] arrOfStr = li.split(" ");
			Queue<Integer> dummy = new LinkedList<Integer>();
			for (int j = 1; j < arrOfStr.length; j++) {
				dummy.add(Integer.parseInt(arrOfStr[j]));
			}
			li = scan.nextLine();
			arrOfStr = li.split(" ");
			String operation = arrOfStr[6];
			int operationNum;
			if (arrOfStr[7].equals("old")) {
				operationNum = -1;
			} else {
				operationNum = Integer.parseInt(arrOfStr[7]);
			}
			li = scan.nextLine();
			arrOfStr = li.split(" ");
			int test = Integer.parseInt(arrOfStr[5]);
			li = scan.nextLine();
			arrOfStr = li.split(" ");
			int trueCase = Integer.parseInt(arrOfStr[9]);
			li = scan.nextLine();
			arrOfStr = li.split(" ");
			int falseCase = Integer.parseInt(arrOfStr[9]);

			bigMod = test * bigMod;
			
			line[i] = new Monkey(dummy, operation, operationNum, test, trueCase, falseCase, 0);
			if (scan.hasNextLine()) {
				scan.nextLine();
			} else {
				break;
			}
		}

		// 20 rounds
		for (int r = 0; r < 20; r++) {
			// each monkey
			for (Monkey currMonkey : line) {
				int size = currMonkey.items.size();	
				currMonkey.inspectionNum += size;
				inspectItems(size, currMonkey);
				
				System.out.println(currMonkey.TestplusOpNum());

			}
		}
		ArrayList<Integer> monkeyBusiness = new ArrayList<>();
		for (Monkey currMonkey : line) {
			monkeyBusiness.add(currMonkey.inspectionNum);
		}
		Collections.sort(monkeyBusiness);
		int numOne = monkeyBusiness.get(monkeyBusiness.size() - 2);
		int numTwo = monkeyBusiness.get(monkeyBusiness.size() - 1);
		long finalNum = numOne * numTwo;
		System.out.println(Math.abs(finalNum));
		
		
	}
	
	
	public static void inspectItems(int size, Monkey currMonkey) {
		for (int j = 0; j < size; j++) {
			int worry = currMonkey.items.remove();
			int newWorry = (conductOperation(currMonkey, worry));
			newWorry = newWorry % bigMod;
			newWorry = newWorry / 3;
			if (newWorry % currMonkey.test == 0) {
				line[currMonkey.trueMonkey].items.add(newWorry);
			} else {
				line[currMonkey.falseMonkey].items.add(newWorry);
			}
		}
	}
	// method that conducts the operation for each value of the monkey and returns
	// the value after worry level changes
	public static int conductOperation(Monkey currMonkey, int item) {

		// if operating with old
		if (currMonkey.operation.equals("*")) {
			if (currMonkey.operationNum != -1) {
				item = item * currMonkey.operationNum;
			} else {
				item = item * item;
			}
		} else if (currMonkey.operation.equals("/")) {
			if (currMonkey.operationNum != -1) {
				item = item / currMonkey.operationNum;
			} else {
				item = item / item;
			}

		} else if (currMonkey.operation.equals("+")) {
			if (currMonkey.operationNum != -1) {
				item = item + currMonkey.operationNum;
			} else {
				item = item + item;
			}

		} else if (currMonkey.operation.equals("-")) {
			if (currMonkey.operationNum != -1) {
				item = item - currMonkey.operationNum;
			} else {
				item = item - item;
			}

		}
		return item;
	}
}
