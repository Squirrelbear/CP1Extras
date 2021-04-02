package Examples;

import java.util.Scanner;

public class SwitchExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter some text: ");
        String input = scan.nextLine();
        // switch based on the last character's value
        char lastChar = input.charAt(input.length()-1);
        switch(lastChar) {
            case 'y':
                System.out.println("Why so serious?");
                break;
            case 'b':
                System.out.println("Be happy!");
                break;
            case '1':
            case '2':
                System.out.println("12121212");
                break;
            default:
                System.out.println(lastChar + " was not important.");
                break;
        }
    }
}
