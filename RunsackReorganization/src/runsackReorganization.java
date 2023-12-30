import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class runsackReorganization {
	public static final String FILE_NAME = "runsack.txt";
	static Character[] lowercase = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	static Character[] uppercase = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	static int totalPriorities = 0;
	static int priorities = 0;
	static Stack firstHalf = new Stack();
	static Stack secondHalf = new Stack();
	public static void main(String[] args) throws FileNotFoundException {
		Scanner runsack = new Scanner(new File(FILE_NAME));
		while (runsack.hasNextLine()) {
			// initalize stacks
			Stack firstHalf = new Stack();
			Stack secondHalf = new Stack();

			// get the value of the runsack
			String data = runsack.nextLine();

			// take the first half and put into stack1
			for (int i = 0; i < data.length() / 2; i++) {
				firstHalf.push(data.charAt(i));
			}
			// take the second half and put into stack2
			for (int i = data.length() / 2; i < data.length(); i++) {
				secondHalf.push(data.charAt(i));
			}
			getPriorities(firstHalf, secondHalf);
		}
		System.out.print(totalPriorities);
	}
	public static int getpriority(Character value, Character[] array, boolean x) {
		int num = 0;
		for (int i = 0; i < array.length; i++) {
			if (value.equals(array[i])) {
				if (x == true) {
					num += (i + 1);
				} else {
					num += (i + 27);
				}
			}
		}
		return num;
	}
	public static void getPriorities(Stack firstHalf, Stack secondHalf) {
	while (!(firstHalf.isEmpty())) {
		Character value = (Character) firstHalf.pop();
		if (secondHalf.contains(value)) {
			// checks if value is a lowercase
			priorities = getpriority(value, lowercase, true);
			// checks if value is uppercase
			if (priorities == 0) {
			priorities = (getpriority(value, uppercase, false));
			}
		}
	}
	totalPriorities += priorities;
	priorities = 0;
	}
}
