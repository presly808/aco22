package ua.artcode.market.models;

public class Statistic {

    private double maxAmountofBill;
    private double minAmountofBill;
    private double averageAmountofBill;
    private int countSoldProducts;

    public Statistic(double maxAmountofBill, double minAmountofBill,
                     double averageAmountofBill, int countSoldProducts) {
        this.maxAmountofBill = maxAmountofBill;
        this.minAmountofBill = minAmountofBill;
        this.averageAmountofBill = averageAmountofBill;
        this.countSoldProducts = countSoldProducts;
    }


    public double getMaxAmountofBill() {
        return maxAmountofBill;
    }

    public double getMinAmountofBill() {
        return minAmountofBill;
    }

    public double getAverageAmountofBill() {
        return averageAmountofBill;
    }

    public int getCountSoldProducts() {
        return countSoldProducts;
    }

    @Override
    public String toString() {
        return String.format("MaxAmountofBill: %.2f, MinAmountofBill: %.2f,"+
                " AverageAmountofBill: %.2f, CountSoldProducts: %s; %n ",
                maxAmountofBill,minAmountofBill,averageAmountofBill,
                countSoldProducts);
    }
}


