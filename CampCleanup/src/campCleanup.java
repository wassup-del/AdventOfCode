import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class campCleanup {

	public static final String FILE_NAME = "cleanup.txt";

	static int overlapcount = 0;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner cleanup = new Scanner(new File(FILE_NAME));

		while (cleanup.hasNextLine()) {
			String data = cleanup.nextLine();
			// will get the index of the "-"
			int index1 = 0;
			int index2 = 0;
			int commaIndex = 0;
			int i = 0;
			for (i = 0; i < data.length(); i++) {
				if (data.charAt(i) == '-') {
					index1 = i;
					break;
				}
			}
			for (int q = i + 1; q < data.length(); q++) {
				if (data.charAt(q) == '-') {
					index2 = q;
				}
			}
			for (int u = 0; u < data.length(); u++) {
				if (data.charAt(u) == ',') {
					commaIndex = u;
					break;
				}
			}
			int value1 = 0;
			int value2 = 0;
			int value3 = 0;
			int value4 = 0;

			// stores the firstValue of the first pair
			if (index1 == 1) {
				value1 = Integer.parseInt(String.valueOf(data.charAt(0)));

			} else {
				value1 = Integer.parseInt(String.valueOf(data.charAt(0))) * 10;
				value1 += Integer.parseInt(String.valueOf(data.charAt(1)));

			}
			// stores the secondValue of the first pair
			if (index1 == 1 && commaIndex == 4) {
				value2 = Integer.parseInt(String.valueOf(data.charAt(2))) * 10;
				value2 += Integer.parseInt(String.valueOf(data.charAt(3)));
			} else if (index1 == 2 && commaIndex == 5) {
				value2 = Integer.parseInt(String.valueOf(data.charAt(3))) * 10;
				value2 += Integer.parseInt(String.valueOf(data.charAt(4)));

			} else if (index1 == 1 && commaIndex == 3) {
				value2 = Integer.parseInt(String.valueOf(data.charAt(2)));
			}

			// stores the thirdValue of the second pair
			if (index2 - commaIndex == 2) {
				value3 = Integer.parseInt(String.valueOf(data.charAt((commaIndex + 1))));
			} else {
				value3 = Integer.parseInt(String.valueOf(data.charAt((commaIndex + 1)))) * 10;
				value3 += Integer.parseInt(String.valueOf(data.charAt((commaIndex + 2))));
			}

			// stores the fourthValue of the second pair
			if (data.length() - index2 == 3) {
				value4 = Integer.parseInt(String.valueOf(data.charAt((index2 + 1)))) * 10;
				value4 += Integer.parseInt(String.valueOf(data.charAt((index2 + 2))));
			} else {
				value4 += Integer.parseInt(String.valueOf(data.charAt((index2 + 1))));
			}
			// System.out.println(value1 + " " + value2 + " " + value3 + " " + value4);
			/*
			 * if (value1 <= value3 && value2 >= value4) { overlapcount++; } else if (value3
			 * <= value1 && value4 >= value2) { overlapcount++; }
			 */

			Stack firstPair = new Stack();
			Stack secondPair = new Stack();

			for (int j = 0; j < (value2 - value1) + 1; j++) {
				firstPair.push(value1 + j);
			}
			for (int j = 0; j < (value4 - value3) + 1; j++) {
				secondPair.push(value3 + j);
			}
			while (!(firstPair.isEmpty())) {
				int value = (int) firstPair.pop();
				if (secondPair.contains(value)) {
					overlapcount++;
					break;
				}

			}
			
		}
		System.out.println(overlapcount);
	}
}