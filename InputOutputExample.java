package Examples;

import java.util.Scanner;

public class InputOutputExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int ageInput = scan.nextInt();

        int result = ageInput + 1;
        System.out.println("Next year you will be " + result
                + " years old!");
    }
}
