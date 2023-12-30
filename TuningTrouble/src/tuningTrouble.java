import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class tuningTrouble {
	public static final String FILE_NAME = "transmission.txt";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner transmission = new Scanner(new File(FILE_NAME));
		String data = transmission.nextLine();
		char[] chars = data.toCharArray();
		int marker = 0;
		Set<Character> code = new HashSet<Character>();
		for (int i = 0; i < chars.length; i++) {
			for (int j = i; j < 14 + i; j++) {
				code.add(chars[j]);
			}
			// System.out.println(code.size());
			if (code.size() == 14) {
				System.out.println(i + 14);
				break;
			}
			code.clear();

		}

	}
}
