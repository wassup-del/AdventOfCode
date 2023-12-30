import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CubeConundrum {

	public static final String FILE_NAME = "PuzzleInput.txt";
	public static int possibleIDSUM = 0;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File(FILE_NAME));

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] strArray = line.split(" ");
			int blueMin = 0;
			int redMin = 0;
			int greenMin = 0;
			String user = strArray[1];
			int spaceCounter = 0;

			String[] parts = user.split(":");
			String gameID = parts[0];

			String[] arr = { "green", "red", "blue" };
			boolean add = true;

			for (int i = 2; i < strArray.length; i += 2) {
				int num = Integer.parseInt(strArray[i]);
				String element = strArray[i + 1];

				if (element.contains("green")) {
					if (num > greenMin) {
						greenMin = num;
					}
				}
				if (element.contains("red")) {
					if (num > redMin) {
						redMin = num;
					}
				}
				if (element.contains("blue")) {
					if (num > blueMin) {
						blueMin = num;
					}
				}

			}
			
			possibleIDSUM += (blueMin * redMin * greenMin);

		}
		System.out.print(possibleIDSUM);
	}

}
