package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static numbers.NumProps.*;

public class Main {
    private static final String ZERO = "0";
    private static final String SEVEN = "7";

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("\nEnter a request: ");
            String userInput = scanner.nextLine();
            if ("".equals(userInput)) {
                printInstructions();
                continue;
            }
            String[] strings = userInput.trim().split(" ");
            String strNum = strings[0];
            if (strings.length > 2) {
                String numOfNums = strings[1];
                String[] propNames = new String[strings.length - 2];
                System.arraycopy(strings, 2, propNames, 0, propNames.length);
                for (int i = 0; i < propNames.length; i++) {
                    propNames[i] = propNames[i].toUpperCase();
                }
                if (isNumNatural(strNum, true) && isNumNatural(numOfNums, false)
                        && arePropertiesValid(propNames)) {
                    long num = Long.parseLong(strNum);
                    if (num == 0) {
                        exit = true;
                        continue;
                    }
                    getSpecificNumProperties(num, Short.parseShort(numOfNums), propNames);
                }
            } else if (strings.length > 1) {
                String numOfNums = strings[1];
                if (isNumNatural(strNum, true) && isNumNatural(numOfNums, false)) {
                    long num = Long.parseLong(strNum);
                    if (num == 0) {
                        exit = true;
                        continue;
                    }
                    for (int i = 0; i < Short.parseShort(numOfNums); i++) {
                        System.out.println(getNumProperties(num + i));
                    }
                }
            } else {
                if (isNumNatural(strNum, true)) {
                    long num = Long.parseLong(strNum);
                    if (num == 0) {
                        exit = true;
                        continue;
                    }
                    System.out.println(getSingleNumProperties(num));
                }
            }
        }
        System.out.println("\nGoodbye!");
    }

    private static void printInstructions() {
        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    private static String getSingleNumProperties(long num) {
        boolean isEven = isEven(num);
        boolean isHappy = isHappy(num);
        return "\nProperties of " + num +
                "\n" + EVEN + ": " + isEven +
                "\n" + ODD + ": " + !isEven +
                "\n" + BUZZ + ": " + isBuzz(num) +
                "\n" + DUCK + ": " + isDuck(num) +
                "\n" + PALINDROMIC + ": " + isPalindromic(num) +
                "\n" + GAPFUL + ": " + isGapful(num) +
                "\n" + SPY + ": " + isSpy(num) +
                "\n" + SQUARE + ": " + isSquare(num) +
                "\n" + SUNNY + ": " + isSquare(num + 1) +
                "\n" + JUMPING + ": " + isJumping(num) +
                "\n" + HAPPY + ": " + isHappy +
                "\n" + SAD + ": " + !isHappy;
    }

    private static String getNumProperties(long num) {
        StringBuilder msg = new StringBuilder(String.valueOf(num)).append(" is ");
        if (isEven(num)) {
            msg.append(EVEN);
        } else {
            msg.append(ODD);
        }
        if (isBuzz(num)) {
            msg.append(", ").append(BUZZ);
        }
        if (isDuck(num)) {
            msg.append(", ").append(DUCK);
        }
        if (isPalindromic(num)) {
            msg.append(", ").append(PALINDROMIC);
        }
        if (isGapful(num)) {
            msg.append(", ").append(GAPFUL);
        }
        if (isSpy(num)) {
            msg.append(", ").append(SPY);
        }
        if (isSquare(num)) {
            msg.append(", ").append(SQUARE);
        }
        if (isSquare(num + 1)) {
            msg.append(", ").append(SUNNY);
        }
        if (isJumping(num)) {
            msg.append(", ").append(JUMPING);
        }
        msg.append(", ");
        if (isHappy(num)) {
            msg.append(HAPPY);
        } else {
            msg.append(SAD);
        }
        return msg.toString();
    }

    private static void getSpecificNumProperties(long num, short numOfNums, String[] propNames) {
        short matched = 0;
        while (matched != numOfNums) {
            short propNamesMatched = 0;
            for (String propName : propNames) {
                if (propName.startsWith("-")) {
                    propName = propName.substring(1);
                    if (propName.equals(EVEN.toString()) && !isEven(num)
                            || propName.equals(ODD.toString()) && isEven(num)
                            || propName.equals(BUZZ.toString()) && !isBuzz(num)
                            || propName.equals(DUCK.toString()) && !isDuck(num)
                            || propName.equals(PALINDROMIC.toString()) && !isPalindromic(num)
                            || propName.equals(GAPFUL.toString()) && !isGapful(num)
                            || propName.equals(SPY.toString()) && !isSpy(num)
                            || propName.equals(SQUARE.toString()) && !isSquare(num)
                            || propName.equals(SUNNY.toString()) && !isSquare(num + 1)
                            || propName.equals(JUMPING.toString()) && !isJumping(num)
                            || propName.equals(HAPPY.toString()) && !isHappy(num)
                            || propName.equals(SAD.toString()) && isHappy(num)) {
                        propNamesMatched++;
                    }
                } else if (propName.equals(EVEN.toString()) && isEven(num)
                        || propName.equals(ODD.toString()) && !isEven(num)
                        || propName.equals(BUZZ.toString()) && isBuzz(num)
                        || propName.equals(DUCK.toString()) && isDuck(num)
                        || propName.equals(PALINDROMIC.toString()) && isPalindromic(num)
                        || propName.equals(GAPFUL.toString()) && isGapful(num)
                        || propName.equals(SPY.toString()) && isSpy(num)
                        || propName.equals(SQUARE.toString()) && isSquare(num)
                        || propName.equals(SUNNY.toString()) && isSquare(num + 1)
                        || propName.equals(JUMPING.toString()) && isJumping(num)
                        || propName.equals(HAPPY.toString()) && isHappy(num)
                        || propName.equals(SAD.toString()) && !isHappy(num)) {
                    propNamesMatched++;
                }
            }
            if (propNamesMatched == propNames.length) {
                System.out.println(getNumProperties(num));
                matched++;
            }
            num++;
        }
    }

    private static boolean isNumNatural(String strNum, boolean isFirstNum) {
        long num;
        try {
            num = Long.parseLong(strNum);
        } catch (NumberFormatException e) {
            if (isFirstNum) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            } else {
                System.out.println("\nThe second parameter should be a natural number.");
            }
            return false;
        }
        if (num < 0) {
            if (isFirstNum) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            } else {
                System.out.println("\nThe second parameter should be a natural number.");
            }
            return false;
        }
        return true;
    }

    private static boolean arePropertiesValid(String[] propNames) {
        List<String> props = new ArrayList<>(List.of(propNames));
        List<String> validProps = Arrays.stream(NumProps.values())
                .map(Enum::toString)
                .toList();
        List<String> wrongProps = new ArrayList<>();
        for (String prop : props) {
            if (prop.startsWith("-")) {
                if (!validProps.contains(prop.substring(1))) {
                    wrongProps.add(prop);
                }
            } else if (!validProps.contains(prop)) {
                wrongProps.add(prop);
            }
        }
        int wrongPropsListSize = wrongProps.size();
        if (wrongPropsListSize > 1) {
            System.out.println("\nThe properties " + wrongProps + " are wrong.");
            System.out.println("Available properties: " + validProps);
            return false;
        } else if (wrongPropsListSize > 0) {
            System.out.println("\nThe property [" + wrongProps.get(0) + "] is wrong.");
            System.out.println("Available properties: " + validProps);
            return false;
        }
        boolean hasMutuallyExclusiveProps = false;
        String[] mutuallyExclusiveProps = new String[0];
        if (props.contains(EVEN.toString()) && props.contains(ODD.toString())) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{EVEN.toString(), ODD.toString()};
        } else if (props.contains(DUCK.toString()) && props.contains(SPY.toString())) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{DUCK.toString(), SPY.toString()};
        } else if (props.contains(SQUARE.toString()) && props.contains(SUNNY.toString())) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{SQUARE.toString(), SUNNY.toString()};
        } else if (props.contains(HAPPY.toString()) && props.contains(SAD.toString())) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{HAPPY.toString(), SAD.toString()};
        } else if (props.contains(EVEN.toString()) && props.contains("-".concat(EVEN.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{EVEN.toString(), "-".concat(EVEN.toString())};
        } else if (props.contains(ODD.toString()) && props.contains("-".concat(ODD.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{ODD.toString(), "-".concat(ODD.toString())};
        } else if (props.contains(BUZZ.toString()) && props.contains("-".concat(BUZZ.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{BUZZ.toString(), "-".concat(BUZZ.toString())};
        } else if (props.contains(DUCK.toString()) && props.contains("-".concat(DUCK.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{DUCK.toString(), "-".concat(DUCK.toString())};
        } else if (props.contains(PALINDROMIC.toString()) && props.contains("-".concat(PALINDROMIC.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{PALINDROMIC.toString(), "-".concat(PALINDROMIC.toString())};
        } else if (props.contains(GAPFUL.toString()) && props.contains("-".concat(GAPFUL.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{GAPFUL.toString(), "-".concat(GAPFUL.toString())};
        } else if (props.contains(SPY.toString()) && props.contains("-".concat(SPY.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{SPY.toString(), "-".concat(SPY.toString())};
        } else if (props.contains(SQUARE.toString()) && props.contains("-".concat(SQUARE.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{SQUARE.toString(), "-".concat(SQUARE.toString())};
        } else if (props.contains(SUNNY.toString()) && props.contains("-".concat(SUNNY.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{SUNNY.toString(), "-".concat(SUNNY.toString())};
        } else if (props.contains(JUMPING.toString()) && props.contains("-".concat(JUMPING.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{JUMPING.toString(), "-".concat(JUMPING.toString())};
        } else if (props.contains(HAPPY.toString()) && props.contains("-".concat(HAPPY.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{HAPPY.toString(), "-".concat(HAPPY.toString())};
        } else if (props.contains(SAD.toString()) && props.contains("-".concat(SAD.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{SAD.toString(), "-".concat(SAD.toString())};
        } else if (props.contains("-".concat(EVEN.toString())) && props.contains("-".concat(ODD.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{"-".concat(EVEN.toString()), "-".concat(ODD.toString())};
        } else if (props.contains("-".concat(HAPPY.toString())) && props.contains("-".concat(SAD.toString()))) {
            hasMutuallyExclusiveProps = true;
            mutuallyExclusiveProps = new String[]{"-".concat(HAPPY.toString()), "-".concat(SAD.toString())};
        }
        if (hasMutuallyExclusiveProps) {
            System.out.println("\nThe request contains mutually exclusive properties: " + Arrays.toString(mutuallyExclusiveProps));
            System.out.println("There are no numbers with these properties.");
            return false;
        }
        return true;
    }

    private static boolean isEven(long num) {
        return num % 2 == 0;
    }

    private static boolean isBuzz(long num) {
        String strNum = String.valueOf(num);
        String lastDigit = strNum.substring(strNum.length() - 1);
        return (num % 7 == 0 || SEVEN.equals(lastDigit));
    }

    private static boolean isDuck(long num) {
        String strNum = String.valueOf(num);
        return (!ZERO.equals(strNum.substring(Integer.parseInt(ZERO))) && strNum.contains(ZERO));
    }

    private static boolean isPalindromic(long num) {
        String strNum = String.valueOf(num);
        StringBuilder reversedStrNum = new StringBuilder();
        for (int i = 0; i < strNum.length(); i++) {
            reversedStrNum.insert(0, strNum.charAt(i));
        }
        return strNum.contentEquals(reversedStrNum);
    }

    private static boolean isGapful(long num) {
        String strNum = String.valueOf(num);
        if (strNum.length() < 3) {
            return false;
        } else {
            String divider = strNum.charAt(0) + "" + strNum.charAt(strNum.length() - 1);
            return num % Long.parseLong(divider) == 0;
        }
    }

    private static boolean isSpy(long num) {
        String strNum = String.valueOf(num);
        int digitsSum = 0;
        int digitsProduct = 1;
        for (int i = 0; i < strNum.length(); i++) {
            int digit = strNum.charAt(i) - '0';
            digitsSum += digit;
            digitsProduct *= digit;
        }
        return digitsSum == digitsProduct;
    }

    private static boolean isSquare(long num) {
        double sqrt = Math.sqrt(num);
        return (sqrt - Math.floor(sqrt) == 0);
    }

    private static boolean isJumping(long num) {
        String strNum = String.valueOf(num);
        if (strNum.length() == 1) {
            return true;
        }
        for (int i = 1; i < strNum.length(); i++) {
            int firstDigit = strNum.charAt(i) - '0';
            int secondDigit = strNum.charAt(i - 1) - '0';
            if (Math.abs(firstDigit - secondDigit) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isHappy(long num) {
        long sum, remainder;
        while (num != 1 && num != 4) {
            sum = 0;
            while (num > 0) {
                remainder = num % 10;
                sum += remainder * remainder;
                num /= 10;
            }
            num = sum;
        }
        return num == 1;
    }
}
