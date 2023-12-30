import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class noSpaceLeftOnDevice {
	public static final String FILE_NAME = "FileSystemtxt.txt";

	static DirectoryNode currentDirectory;
	// sets first directory as outermost
	static DirectoryNode outermost = new DirectoryNode("/");
	static ArrayList<DirectoryNode> directories = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileSystem = new Scanner(new File(FILE_NAME));
		currentDirectory = outermost;
		directories.add(outermost);
		while (fileSystem.hasNextLine()) {
			String data = fileSystem.nextLine();
			String[] split = data.split(" ");

			// Step 1: add directories to currentDirectory
			if (split[0].equals("dir")) {
				// get new directory and split to get different values
				String[] words = data.split(" ");
				// add the newDirectory as a child of the currentDirectory
				currentDirectory.childrenNodes.add(new DirectoryNode(words[1]));
				for (int i = 0; i < currentDirectory.childrenNodes.size(); i++) {
					if (currentDirectory.childrenNodes.get(i).name == words[1]) {
						directories.add(currentDirectory.childrenNodes.get(i));
					}
				}
			}
			// Step 3: incorporate changing Directories
			else if (split[1].equals("cd")) {
				String[] words = data.split(" ");
				// moves currentDirectory to the outermost
				if (data.contains("/")) {
					currentDirectory = outermost;
				}
				// moves the currentDirectory out one level
				else if (data.contains("..")) {
					currentDirectory = currentDirectory.parent;
				}

				// handles the cd cases
				else {
					// size of currentDirectory child List
					int size = currentDirectory.childrenNodes.size();
					// stores the index of the child List that should become the
					// new currentDirectory
					int value = 0;
					// checks each value in the child List for the name given
					for (int i = 0; i < size; i++) {
						if (currentDirectory.childrenNodes.get(i).name.equals(words[2])) {
							value = i;
						}
					}
					currentDirectory.childrenNodes.get(value).parent = currentDirectory;
					currentDirectory = currentDirectory.childrenNodes.get(value);
				}
			}

			// Step 2: add files to currentDirectory
			else if (split.length == 2 && !(split[0].equals("$"))) {
				String[] words = data.split(" ");
				currentDirectory.childrenFiles.add(Integer.parseInt(words[0]));
				// outermost.value += Integer.parseInt(words[0]);
				// currentDirectory.value += Integer.parseInt(words[0]);
				// case of "ls"
			}

			else if (data.contains("$") && !(data.contains("cd "))) {
				// do nothing
			}

		}
		System.out.println(directories);
		int finalSum = 0;
		for (DirectoryNode node : directories) {
			if (getValue(node) <= 100000) {
				finalSum += getValue(node);
			}
		}
		System.out.println("Part 1: " + finalSum);
		int Storage = 1000000000;
		int unused = (70000000 - getValue(outermost));
		int deleteAmount = 30000000 - unused;
		System.out.println(deleteAmount);
		ArrayList<Integer> values = new ArrayList<>();
		for (DirectoryNode node : directories) {
			int value = getValue(node);
			if (value >= deleteAmount) {
				values.add(getValue(node));
			}
		}
		Collections.sort(values);
		int num = values.get(0);
		System.out.println("Part 2: " + num);
	}

	public static int getValue(DirectoryNode node) {
		int sum = 0;
		// goes through the directoryNode
		for (int i = 0; i < node.childrenFiles.size(); i++) {
			sum += node.childrenFiles.get(i);
		}
		if (!(node.childrenNodes.isEmpty())) {
			for (int i = 0; i < node.childrenNodes.size(); i++) {
				sum += getValue(node.childrenNodes.get(i));
			}
		}
		return sum;
	}
}