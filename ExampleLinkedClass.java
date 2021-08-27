public class ExampleLinkedClass {
    private String name;
    private ExampleLinkedClass previous;

    public ExampleLinkedClass(String name, ExampleLinkedClass previous) {
        this.name = name;
        this.previous = previous;
    }

    public ExampleLinkedClass getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        return name;
    }
}
