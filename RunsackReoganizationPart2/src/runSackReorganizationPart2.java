
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class runSackReorganizationPart2 {
	public static final String FILE_NAME = "runsack.txt";
	static Character[] lowercase = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	static Character[] uppercase = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	static int totalPriorities = 0;
	static int priorities = 0;
	static Stack firstLine = new Stack();
	static Stack secondLine = new Stack();
	static Stack thirdLine = new Stack();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner runsack = new Scanner(new File(FILE_NAME));
		//will run until no lines of code left in txt file
		while (runsack.hasNextLine()) {
			// initalize stacks
			Stack firstLine = new Stack();
			Stack secondLine = new Stack();
			Stack thirdLine = new Stack();

			//place the values of the first line into the firstLine stack
			String data = runsack.nextLine();
			for (int i = 0; i < data.length(); i++) {
				firstLine.push(data.charAt(i));
			}
			//place the values of the second line into the secondLine stack
			data = runsack.nextLine();
			for (int i = 0; i < data.length(); i++) {
				secondLine.push(data.charAt(i));
			}
			//place the values of the third line into the thirdLine stack
			data = runsack.nextLine();
			for (int i = 0; i < data.length(); i++) {
				thirdLine.push(data.charAt(i));
			}

			//sends the stacks to the findCommonCharacter
			findCommonCharacter(firstLine, secondLine, thirdLine);
		}
		System.out.print(totalPriorities);
	}

	//takes in the value of the character and runs through the alphabet to return the 
	//priority value. true indicates that the character is lowercase. false is for 
	//uppercase. 
	public static int getIndex(Character value, Character[] array, boolean x) {
		int num = 0;
		for (int i = 0; i < array.length; i++) {
			if (value.equals(array[i])) {
				//true indicates lowercase
				if (x == true) {
					num += (i + 1);
			    //true indicates uppercase
				} else {
					num += (i + 27);
				}
			}
		}
		return num;
	}
	//takes in the values from the stacks and then finds the value of the common 
	//character between them all (the common item between all the elves)
	public static void findCommonCharacter(Stack firstLine, Stack secondLine, Stack thirdLine) {
		while (!(firstLine.isEmpty())) {
			//takes in the first character from the stack and compares it
			//to the other two stacks to see if it matches
			Character value = (Character) firstLine.pop();
			if (secondLine.contains(value) && thirdLine.contains(value)) {
				//will return the value of the priority if lowercase
				priorities = getIndex(value, lowercase, true);
				
				//will return the value of the priority if uppercase
				//will only run if priorities is 0 meaning that the get indexmethod recognized
				//that the character is lowercase
				if (priorities == 0) {
					priorities = (getIndex(value, uppercase, false));
				}
			}
		}
		totalPriorities += priorities;
		priorities = 0;
	}
}
