import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

	public static final String FILE_NAME = "GearRatios.txt";
	public static int total = 0;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File(FILE_NAME));

		Pattern pattern = Pattern.compile("\\d+");
		Pattern pattern2 = Pattern.compile("[^0-9.]");

		LinkedList<Number> numbers = new LinkedList<Number>();
		LinkedList<Symbol> symbols = new LinkedList<Symbol>();

		int yCoord = 0;

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			Matcher matcher = pattern.matcher(line);
			Matcher matcher2 = pattern2.matcher(line);

			while (matcher.find()) {
				int number = Integer.parseInt(matcher.group());
				int startIndex = matcher.start();
				int endIndex = matcher.end() - 1;
				System.out.println("Found number: " + number + " at indexes [" + startIndex + ", " + endIndex + "]");

				Number num = new Number(number, startIndex, endIndex, yCoord);
				numbers.add(num);
			}

			while (matcher2.find()) {
				char symbol = line.charAt(matcher2.start());
				int xCoord = matcher2.start();
				System.out.println("Symbol '" + symbol + "' found at index: " + xCoord + " and y-coord: " + yCoord);

				Symbol sym = new Symbol(xCoord, yCoord, new LinkedList<Integer>());
				symbols.add(sym);
			}

			yCoord++;
		}

		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < symbols.size(); j++) {

				if (adjacent(numbers.get(i), symbols.get(j))) {

					symbols.get(j).list.add(numbers.get(i).value);

				}

			}
		}
		
		for (int i = 0; i < symbols.size(); i++) {
			if (symbols.get(i).list.size() == 2) {
				total += ((symbols.get(i).list.get(0)) * (symbols.get(i).list.get(1)));
				
			} 
		}

		System.out.print(total);
	}

	public static boolean adjacent(Number num, Symbol sym) {
		boolean adjacent = false;

		if (Math.abs(num.yCoord - sym.yCoord) > 1) {
			adjacent = false;
		} else {
			if (sym.xCoord >= num.xCoordInitial - 1 && sym.xCoord <= num.xCoordFinal + 1) {
				adjacent = true;
			}
		}

		return adjacent;

	}

}
