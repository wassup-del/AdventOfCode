import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ropeBridge {
	public static final String FILE_NAME = "movements.txt";
	static Set<String> positions = new HashSet<String>();

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File(FILE_NAME));
		int[] Head = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] one = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] two = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] three = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] four = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] five = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] Six = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] Seven = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] Eight = new int[2];
		Head[0] = 0;
		Head[1] = 0;
		int[] Tail = new int[2];
		Tail[0] = 0;
		Tail[1] = 0;
		int[] Starting = new int[2];
		Starting[0] = 0;
		Starting[1] = 0;

		positions.add("00");
		while (scan.hasNextLine()) {
			String data = scan.nextLine();
			String[] move = data.split(" ");
			int walk = Integer.parseInt(move[1]);

			for (int i = 0; i < walk; i++) {
				if (move[0].equals("R")) {
					Head[0] = Head[0] + 1;
				} else if (move[0].equals("L")) {
					Head[0] = Head[0] - 1;
				} else if (move[0].equals("U")) {
					Head[1] = Head[1] + 1;
				} else if (move[0].equals("D")) {
					Head[1] = Head[1] - 1;
				}

				movePosition(Head, one);
				movePosition(one, two);
				movePosition(two, three);
				movePosition(three, four);
				movePosition(four, five);
				movePosition(five, Six);
				movePosition(Six, Seven);
				movePosition(Seven, Eight);
				movePosition(Eight, Tail);

				int Xcoor = Tail[0];
				int Ycoor = Tail[1];
				String coor = String.valueOf(Xcoor) + String.valueOf(Ycoor);
				positions.add(coor);

			}
		}
		System.out.print(positions.size());
		System.out.print(positions);
	}

	public static void movePosition(int[] Head, int[] Tail) {
		if ((Math.abs(Head[0] - Tail[0])) > 1 && (Math.abs(Head[1] - Tail[1])) > 1) {
			if (Head[0] > Tail[0]) {
				Tail[0] = Head[0] - 1;
			} else {
				Tail[0] = Head[0] + 1;
			}

			if (Head[1] > Tail[1]) {
				Tail[1] = Head[1] - 1;

			}
			else {
				Tail[1] = Head[1] + 1;
			}
		}
		if ((Math.abs(Head[0] - Tail[0])) > 1) {
			if (Head[0] < Tail[0]) {
				Tail[0] = Head[0] + 1;
			} else {
				Tail[0] = Head[0] - 1;
			}

			Tail[1] = Head[1];
		}
		if ((Math.abs(Head[1] - Tail[1])) > 1) {
			if (Head[1] < Tail[1]) {
				Tail[1] = Head[1] + 1;
			} else {
				Tail[1] = Head[1] - 1;
			}

			Tail[0] = Head[0];
		}
	}
}
