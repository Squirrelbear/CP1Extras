package Examples;

import java.text.DecimalFormat;

public class SaleItemExample {
    public static void main(String[] args) {
        SaleItemExample saleItemExample = new SaleItemExample();
        saleItemExample.task1();
        saleItemExample.task2();
        saleItemExample.task3();
    }

    public void task1() {
        System.out.println("Task 1:");
        SaleItem chocolate = new SaleItem();
        // Can comment out invalid lines after making changes
        // In this case once the variables become private they
        // are no longer valid.
        //chocolate.name = "Chocolate";
        //chocolate.cost = 9.5;
        chocolate.print();
    }

    public void task2() {
        System.out.println("\nTask 2:");
        SaleItem chocolate = new SaleItem("Chocolate", 9.5);
        SaleItem bread = new SaleItem("Bread", 3.5);
        chocolate.print();
        bread.print();
        chocolate.setCost(8.5);
        chocolate.print();
    }

    public void task3() {
        System.out.println("\nTask 3:");
        SaleItem chocolate = new SaleItem("Chocolate", 9.5);
        System.out.println(chocolate); // This will call the toString() method
        double discountedPrice = chocolate.getDiscountedCost(0.4);
        DecimalFormat fmt = new DecimalFormat("0.00");
        System.out.println("Price at 40% off: $" + fmt.format(discountedPrice));
    }
}
