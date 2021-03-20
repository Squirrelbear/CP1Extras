package Examples;

import java.util.Scanner;

public class BooleanExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int input = scan.nextInt();
        boolean result = (input == 42);
        System.out.println("The input is the answer to everything: " + result);
        /* Outputs either:
         The input is the answer to everything: true
         or:
         The input is the answer to everything: false
         */

        // Alternative with if statements
        System.out.print("You entered the answer to ");
        if(input == 42) {
            System.out.println("everything!");
        } else {
            System.out.println("nothing important.");
        }
    }
}
