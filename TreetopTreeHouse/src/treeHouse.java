import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class treeHouse {
	public static final String FILE_NAME = "ForestGrid.txt";
	static int visible = 0;
	static ArrayList<ArrayList> arrayStorage = new ArrayList<ArrayList>();

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File(FILE_NAME));
		String data = scan.nextLine();
		String[] numbersArray = data.split("(?<=\\G\\d)");
		ArrayList<Tree> lineOne = new ArrayList<Tree>();
		for (int i = 0; i < numbersArray.length; i++) {
			lineOne.add(new Tree(numbersArray[i], false));
		}
		//ArrayList<Tree> lineOne = toArray(data);
		visible += lineOne.size();

		for (int i = 0; i < data.length(); i++) {
			ArrayList<Tree> nums = new ArrayList<Tree>();
			System.out.println(lineOne.get(i));
			nums.add(lineOne.get(i));
			arrayStorage.add(nums);
		}

		while (scan.hasNextLine()) {
			data = scan.nextLine();
			ArrayList<Tree> lineTwo = toArray(data);
			if (!(scan.hasNextLine())) {
				visible = visible + data.length();
				for (int i = 0; i < data.length(); i++) {
					arrayStorage.get(i).add(lineTwo.get(i));
				}
				break;
			}

			// go from left to right
			Tree tallestTree = lineTwo.get(0);
			for (int i = 0; i < lineTwo.size() - 1; i++) {
				if (i == 0) {
					visible++;
				} else if (lineTwo.get(i).value > tallestTree.value) {
					visible++;
					tallestTree = lineTwo.get(i);
				}
			}
			// go from right to left
			tallestTree = lineTwo.get(lineTwo.size() - 1);
			for (int i = lineTwo.size(); i > 1; i--) {
				//if first value in the array then you are always visible
				if (i == lineTwo.size()) {
					visible++;
				} else if (lineTwo.get(i - 1).value > tallestTree.value) {
					visible++;
					tallestTree = lineTwo.get(i - 1);
				}
			}

			for (int i = 0; i < data.length(); i++) {
				arrayStorage.get(i).add(lineTwo.get(i));
			}

			lineOne = lineTwo;
			System.out.println(visible + "");
		}
		for (ArrayList<Tree> array : arrayStorage) {

		}
		System.out.print(visible);
	}

	public static ArrayList toArray(String data) {
		String[] numbersArray = data.split("(?<=\\G\\d)");
		Stream<String> numbersStream = Arrays.stream(numbersArray);
		IntStream intStream = numbersStream.mapToInt(Integer::parseInt);
		ArrayList<Integer> numbersList = intStream.boxed().collect(Collectors.toCollection(ArrayList::new));
		return numbersList;
	}

}
