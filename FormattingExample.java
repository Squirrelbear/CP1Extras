package Examples;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FormattingExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an Integer: ");
        double input = scan.nextDouble();

        /* Example using Math.round
            Use this when you want to keep the value as a number.*/
        double exampleOne = Math.round(input * 10) / 10.0;
        System.out.println("One Decimal Place: " + exampleOne);

        double exampleTwo = Math.round(input * 100) / 100.0;
        System.out.println("Two Decimal Places: " + exampleTwo);

        double exampleThree = Math.round(input * 1000) / 1000.0;
        System.out.println("Three Decimal Places: " + exampleThree);

        /* Example using DecimalFormat
            Use this when outputting as part of a println statement. */
        DecimalFormat formatOne = new DecimalFormat("0.0"); // Always show 1 decimal place
        DecimalFormat formatTwo = new DecimalFormat("0.00"); // Always show 2 decimal places
        DecimalFormat formatThree = new DecimalFormat("0.##"); // Up to 2 decimal places

        System.out.println("One decimal place always: " + formatOne.format(input));
        System.out.println("Two decimal places always: " + formatTwo.format(input));
        System.out.println("Up to 2 decimal places: " + formatThree.format(input));

        // Extra examples. The first will make the decimal part disappear entirely
        System.out.println("Up to 2 decimal places (12.0): " + formatThree.format(12.0));
        System.out.println("Up to 2 decimal places (12.4): " + formatThree.format(12.4));
        System.out.println("Up to 2 decimal places (12.456): " + formatThree.format(12.456));
    }
}
