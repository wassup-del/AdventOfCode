import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class treetop {
	public static final String FILE_NAME = "ForestGrid.txt";
	static int visible = 0;
	static ArrayList<ArrayList> arrayStorage = new ArrayList<ArrayList>();

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File(FILE_NAME));
		
		while (scan.hasNextLine()) {
			String data = scan.nextLine();
			String[] numbersArray = data.split("(?<=\\G\\d)");
			ArrayList<Tree> lineOne = new ArrayList<Tree>();
			for (int i = 0; i < numbersArray.length; i++) {
				lineOne.add(new Tree(numbersArray[i], false));
				lineOne.get(i).value = Integer.parseInt(numbersArray[i]);
			}
			arrayStorage.add(lineOne);
		}
		for (int i = 0; i < arrayStorage.size(); i++) {
			for (int j = 0; j < arrayStorage.get(i).size(); j++) {
					getVisisble(i, j);
					
				}
			}
		}
	public static void getVisisble(int row, int coll) {
		ArrayList<Tree> storage = arrayStorage.get(row);
		if (row == 0 || coll == 0 || row == arrayStorage.size() - 1 || coll == arrayStorage.get(0).size() - 1) {
			storage.get(coll).visible = true;
			visible++;
		} else {
			//for loop that checks to the left
			
			//for loop that checks to the right
			storage = arrayStorage.get(row);
			int value = storage.get(coll).value;
			for (int i = coll; i < storage.size() - coll; i++) {
				if (storage.get(i).value >= value) {
					break;
				}
			}
			storage.get(coll).visible = true;
			arrayStorage.add(row, storage);
			//for loop that checks down
			//for loop that chekcs up
		} 
	}

	

}
