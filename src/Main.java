import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input: ");
        String input = scanner.nextLine();

        String[] msv = input.split("[-+*/]");
        String firstValue = msv[0];
        String secondValue = msv[1];

        if (msv.length != 2) {
            throw new IllegalArgumentException("Неверный оператор");
        }
        
        int secondValueInt = 0;

        System.out.println("Output:");

        int condition = checkInputCondition(firstValue, secondValue);
        if (condition == 1) {
            String calculateString = calculateString(input, firstValue, secondValue);
            System.out.println(calculateString);
        } else if (condition == 2) {
            String calculateInt = calculateInt(input, firstValue, secondValue, secondValueInt);
            System.out.println(calculateInt);
        }

    }

    static String calculateString(String input, String firstValue, String secondValue) throws Exception {
        firstValue = firstValue.replaceAll("[\"]", "");
        secondValue = secondValue.replaceAll("[\"]", "");
        firstValue = firstValue.trim();
        secondValue = secondValue.trim();
        if (input.contains("+")) {
            return firstValue.concat(secondValue);
        } else if (input.contains("-")) {
            return firstValue.contains(secondValue) ? firstValue.replace(secondValue, "") : firstValue;
        } else {
            return "Невозможное вычисление";
        }
    }

    static String calculateInt(String input, String firstValue, String secondValue, int secondValueInt) throws Exception {
        if (input.contains("*")) {
            firstValue = firstValue.replaceAll("\"", "");
            secondValue = secondValue.trim();
            firstValue = firstValue.trim();
            secondValueInt = Integer.parseInt(secondValue);
            String repeated = firstValue.repeat(secondValueInt);
            if (repeated.length() > 40) {
                repeated = repeated.substring(0, 40) + "...";
                return repeated;
            } else {
                return repeated;
            }
        } else if (input.contains("/")) {
            secondValue = secondValue.replaceAll(" ", "");
            secondValueInt = Integer.parseInt(secondValue);
            int divide = firstValue.length() / secondValueInt;
            return firstValue.substring(1, divide);
        } else throw new Exception("Недопустимая операция");
    }

    static int checkInputCondition(String firstValue, String secondValue) throws Exception {
        if (firstValue.contains("\"") && secondValue.contains("\"")) {
            return firstValue.length() < 10 && secondValue.length() < 10 ? 0 : 1;
        } else if (firstValue.contains("\"") && !secondValue.contains("\"")) {
            secondValue = secondValue.replaceAll(" ", "");

            int secondValueInt = Integer.parseInt(secondValue);

            if (secondValueInt >= 1 && secondValueInt <= 10) {
                return 2;
            } else {
                throw new Exception("Только числа от 1 до 10 включительно");
            }
        } else throw new Exception("Неверный формат ввода");
    }
}
