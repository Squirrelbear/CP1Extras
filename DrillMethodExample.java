package Examples;

public class DrillMethodExample {
    public static void main(String[] args) {
        DrillMethodExample drillMethodExample = new DrillMethodExample();
        drillMethodExample.testEvenNumbersBetween();
    }

    public void testEvenNumbersBetween() {
        // 2,4,6,8,10,
        System.out.println(evenNumbersBetween(1,10));

        // -2,0,2,4,
        System.out.println(evenNumbersBetween(-3,4));

        // Empty String expected
        System.out.println(evenNumbersBetween(1,1));
    }

    public String evenNumbersBetween(int start, int end) {
        String result = "";
        for(int i = start; i <= end; i++) {
            if(i % 2 == 0) {
                result += i + ",";
            }
        }
        return result;
    }
}
