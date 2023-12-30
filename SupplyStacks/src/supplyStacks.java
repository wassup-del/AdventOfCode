import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class supplyStacks {

	public static final String FILE_NAME = "rearrangement.txt";
	static Stack<Character> one = new Stack<>();
	static Stack<Character> two = new Stack<>();
	static Stack<Character> three = new Stack<>();
	static Stack<Character> four = new Stack<>();
	static Stack<Character> five = new Stack<>();
	static Stack<Character> six = new Stack<>();
	static Stack<Character> seven = new Stack<>();
	static Stack<Character> eight = new Stack<>();
	static Stack<Character> nine = new Stack<>();
	static Stack<Character> ten = new Stack<>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner arrange = new Scanner(new File(FILE_NAME));
		
		  one.push('Z'); one.push('P'); one.push('M'); one.push('H'); one.push('R');
		  two.push('P'); two.push('C'); two.push('J'); two.push('B'); three.push('S');
		  three.push('N'); three.push('H'); three.push('G'); three.push('L');
		  three.push('C'); three.push('D'); four.push('F'); four.push('T');
		  four.push('M'); four.push('D'); four.push('Q'); four.push('S');
		  four.push('R'); four.push('L'); five.push('F'); five.push('S');
		  five.push('P'); five.push('Q'); five.push('B'); five.push('T');
		  five.push('Z'); five.push('M'); six.push('T'); six.push('F'); six.push('S');
		  six.push('Z'); six.push('B'); six.push('G'); seven.push('N');
		  seven.push('R'); seven.push('V'); eight.push('P'); eight.push('G');
		  eight.push('L'); eight.push('T'); eight.push('D'); eight.push('V');
		  eight.push('C'); eight.push('M'); nine.push('W'); nine.push('Q');
		  nine.push('N'); nine.push('J'); nine.push('F'); nine.push('M');
		  nine.push('L');
		 
		print();
		while (arrange.hasNextLine()) {
			arrange.next();
			int numBox = Integer.parseInt(arrange.next());
			arrange.next();
			int stack1 = Integer.parseInt(arrange.next());
			arrange.next();
			int stack2 = Integer.parseInt(arrange.next());

			rearrange(stack1, stack2, numBox);

		}
		print();
		System.out.print(one.pop());
		System.out.print(two.pop());
		System.out.print(three.pop());
		System.out.print(four.pop());
		System.out.print(five.pop());
		System.out.print(six.pop());
		System.out.print(seven.pop());
		System.out.print(eight.pop());
		System.out.print(nine.pop());

	}

	public static void print() {
		System.out.println("//////////////////////////");
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
		System.out.println(four);
		System.out.println(five);
		System.out.println(six);
		System.out.println(seven);
		System.out.println(eight);
		System.out.println(nine);
	}

	public static void rearrange(int stack1, int stack2, int numBox) {
		if (numBox == 1) {
			char storage = 0;
			if (stack1 == 1) {
				storage = one.pop();
			} else if (stack1 == 2) {
				storage = two.pop();
			} else if (stack1 == 3) {
				storage = three.pop();
			} else if (stack1 == 4) {
				storage = four.pop();
			} else if (stack1 == 5) {
				storage = five.pop();
			} else if (stack1 == 6) {
				storage = six.pop();
			} else if (stack1 == 7) {
				storage = seven.pop();
			} else if (stack1 == 8) {
				storage = eight.pop();
			} else if (stack1 == 9) {
				storage = nine.pop();
			}
			// add into the designated stack
			if (stack2 == 1) {
				one.push(storage);
			} else if (stack2 == 2) {
				two.push(storage);
			} else if (stack2 == 3) {
				three.push(storage);
			} else if (stack2 == 4) {
				four.push(storage);
			} else if (stack2 == 5) {
				five.push(storage);
			} else if (stack2 == 6) {
				six.push(storage);
			} else if (stack2 == 7) {
				seven.push(storage);
			} else if (stack2 == 8) {
				eight.push(storage);
			} else if (stack2 == 9) {
				nine.push(storage);
			}
			
		} else {
			Stack<Character> ten = new Stack<>();
			if (stack1 == 1) {
				for (int i = 0; i < numBox; i++) {
					ten.push(one.pop());
				}
			} else if (stack1 == 2) {
				for (int i = 0; i < numBox; i++) {
					ten.push(two.pop());
				}
			} else if (stack1 == 3) {
				for (int i = 0; i < numBox; i++) {
					ten.push(three.pop());
				}
			} else if (stack1 == 4) {
				for (int i = 0; i < numBox; i++) {
					ten.push(four.pop());
				}
			} else if (stack1 == 5) {
				for (int i = 0; i < numBox; i++) {
					ten.push(five.pop());
				}
			} else if (stack1 == 6) {
				for (int i = 0; i < numBox; i++) {
					ten.push(six.pop());
				}
			} else if (stack1 == 7) {
				for (int i = 0; i < numBox; i++) {
					ten.push(seven.pop());
				}
			} else if (stack1 == 8) {
				for (int i = 0; i < numBox; i++) {
					ten.push(eight.pop());
				}
			} else if (stack1 == 9) {
				for (int i = 0; i < numBox; i++) {
					ten.push(nine.pop());
				}
			}
			// add into the designated stack
			if (stack2 == 1) {
				while (!(ten.isEmpty())) {
					one.push(ten.pop());
				}
			} else if (stack2 == 2) {
				while (!(ten.isEmpty())) {
					two.push(ten.pop());
				}
			} else if (stack2 == 3) {
				while (!(ten.isEmpty())) {
					three.push(ten.pop());
				}
			} else if (stack2 == 4) {
				while (!(ten.isEmpty())) {
					four.push(ten.pop());
				}
			} else if (stack2 == 5) {
				while (!(ten.isEmpty())) {
					five.push(ten.pop());
				}
			} else if (stack2 == 6) {
				while (!(ten.isEmpty())) {
					six.push(ten.pop());
				}
			} else if (stack2 == 7) {
				while (!(ten.isEmpty())) {
					seven.push(ten.pop());
				}
			} else if (stack2 == 8) {
				while (!(ten.isEmpty())) {
					eight.push(ten.pop());
				}
			} else if (stack2 == 9) {
				while (!(ten.isEmpty())) {
					nine.push(ten.pop());
				}
			}
		}

	}
}