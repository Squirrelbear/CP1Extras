package Examples;

public class ArraysExample {
    public static void main(String[] args) {
        // Create an array with a specific size and fill with 5
        int[] numbers = new int[5];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = 5;
        }

        // Create an array with an initailiser list and print values
        String[] words = {"Example", "Words", "Here"};
        for(int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

        // Create an array based on the size of another array and fill with values
        int[] numberLetters = new int[words.length];
        for(int i = 0; i < numberLetters.length; i++) {
            numberLetters[i] = words[i].length();
        }

        for(int i = 0; i < words.length; i++) {
            System.out.println("The word \"" + words[i]
                               + "\" has length: " + numberLetters[i]);
        }
    }
}
