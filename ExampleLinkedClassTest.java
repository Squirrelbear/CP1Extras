public class ExampleLinkedClassTest {
    public static void main(String[] args) {
        ExampleLinkedClass example1 = new ExampleLinkedClass("Bob", null);
        ExampleLinkedClass example2 = new ExampleLinkedClass("Peter", example1);
        ExampleLinkedClass example3 = new ExampleLinkedClass("Terry", example2);
        ExampleLinkedClass example4 = new ExampleLinkedClass("Lucy", example3);
        ExampleLinkedClass example5 = new ExampleLinkedClass("Georgia", example4);

        ExampleLinkedClass currentReference = example5;
        while(currentReference != null) {
            System.out.println("Name is: " + currentReference);
            currentReference = currentReference.getPrevious();
        }
    }
}
