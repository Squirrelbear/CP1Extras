package Examples;

import java.text.DecimalFormat;

public class SaleItem {
    private String name;
    private double cost;

    // Default Constructor
    public SaleItem() {
        name = "NAME_NOT_SET";
        cost = 0;
    }

    // Constructor with both parameters
    public SaleItem(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    // Setter for the name instance variable
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the name instance variable
    public String getName() {
        return name;
    }

    // Setter for the cost instance variable
    public void setCost(double cost) {
        this.cost = cost;
    }

    // Getter for the cost instance variable
    public double getCost() {
        return cost;
    }

    // Print the sale item
    public void print() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        System.out.println(name + " costs $" + fmt.format(cost));
    }

    // The toString method is called when you try to print an object
    public String toString() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        return name + " costs $" + fmt.format(cost);
    }

    // Takes discount as a value like 0.2 to represent 20% off
    public double getDiscountedCost(double discount) {
        return cost * (1-discount);
    }
}
