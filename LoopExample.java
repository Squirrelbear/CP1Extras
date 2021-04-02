package Examples;

import java.util.Scanner;

public class LoopExample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /*  for(init; condition; increment)
            int i = 0; Initialise
            i < 5;  Condition. (Loop while true)
            i++     Increment (end of every loop) */
        for(int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while(condition) loop
        System.out.print("Eat cake? ");
        String input = scan.nextLine();
        while(!input.equalsIgnoreCase("yes")) {
            System.out.print("Surely you want to eat cake? ");
            input = scan.nextLine();
        }
        System.out.println("Excellent! Much cake will be eaten.");

        // do while loop
        do {
            System.out.print("What noise does a dog make? ");
            input = scan.nextLine();
        } while(!input.equalsIgnoreCase("woof"));

        // for-each loop: for(Type varName : array)
        String[] colours = new String[] { "Red", "Green", "Blue" };
        // For each colour in colours
        for(String colour : colours) {
            System.out.println(colour);
        }
    }
}
