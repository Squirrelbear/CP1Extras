package Examples;

import java.util.Scanner;

public class SplitNumberExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a 5 digit number: ");
        int input = 12345; //scan.nextInt(); // Change this line to use input if you want

        // Example using 12345 as input separating into a variable for each digit
        int fifthDigit = input % 10; // fifthDigit = 5
        input = input / 10;          // input = 1234
        int fourthDigit = input % 10; // fourthDigit = 4
        input = input / 10;           // input = 123
        int thirdDigit = input % 10; // thirdDigit = 3
        input = input / 10;          // input = 12
        int secondDigit = input % 10; // secondDigit = 2
        input = input / 10;           // input = 1
        int firstDigit = input;       // Could skip this step and just use "input"

        System.out.println("The odd numbered digits are: ");
        System.out.println(fifthDigit + ", " + thirdDigit + ", " + firstDigit); // 5, 3, 1

        // Example using a loop to do the same thing:
        input = 12345;
        String result = ""; // String to gradually store the result in
        for(int i = 5; i >= 1; i--) { // step from digit 5 down to digit 1
            if(i % 2 != 0) { // if the index is odd
                result += (input % 10) + ", ";
            }
            input = input / 10;
        }
        System.out.println(result); // 5, 3, 1,

        // How about looping through all numbers regardless of length?
        input = 1234567;
        result = ""; // Result to store the number separated in order
        while(input > 0) {
            result = (input % 10) + ", " + result;
            input = input / 10;
        }
        // Note that one problem with this is any number that ends with 0s will not work!
        System.out.println(result); // 1, 2, 3, 4, 5, 6, 7,

        // How about if you just want to know a specific digit?
        input = 1234567;
        System.out.println("Second Digit is: " + ((input / 100000) % 10));
        // Simple divide by 1 followed by 0s for every number after the one you want, then % 10.
        System.out.println("Fifth Digit is: " + ((input / 100) % 10));
    }
}
