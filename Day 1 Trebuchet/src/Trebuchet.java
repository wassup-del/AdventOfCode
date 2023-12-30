import java.util.Scanner;

public class Trebuchet {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter calibration numbers (type 'exit' to finish):");

        var sum = 0;

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            var calibNums = getCalibNums(line);
            sum += Integer.parseInt(calibNums);
            System.out.println("Current Sum: " + sum);
        }

        System.out.println("Final Sum: " + sum);
    }

    private static String getCalibNums(String line) {
        String[] digits = {"-", "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine"};
        var nums = "";
        var chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                nums += chars[i];
                continue;
            }
            var substr = line.substring(i, Math.min(5 + i, line.length()));
            for (int j = 0; j < digits.length; j++) {
                if (substr.startsWith(digits[j])) {
                    i += digits[j].length() - 2;
                    nums += j;
                    break;
                }
            }
        }
        return "" + nums.charAt(0) + nums.charAt(nums.length() - 1);
    }
}