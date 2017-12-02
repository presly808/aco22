package week1.model;

public class SalesStatistic {

    private double minBillPrice;
    private double maxBillPrice;
    private int soldProducts;
    private Seller bestSalesMan;

    public double getMinBillPrice() {
        return minBillPrice;
    }

    public double getMaxBillPrice() {
        return maxBillPrice;
    }

    public int getSoldProducts() {
        return soldProducts;
    }

    public Seller getBestSalesMan() {
        return bestSalesMan;
    }

    public void setMinBillPrice(double minBillPrice) {
        this.minBillPrice = minBillPrice;
    }

    public void setMaxBillPrice(double maxBillPrice) {
        this.maxBillPrice = maxBillPrice;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

    public void setBestSalesMan(Seller bestSalesMan) {
        this.bestSalesMan = bestSalesMan;
    }

    @Override
    public String toString() {
        return "SalesStatistic{" +
                "minBillPrice=" + minBillPrice +
                ", maxBillPrice=" + maxBillPrice +
                ", soldProducts=" + soldProducts +
                ", bestSalesMan=" + bestSalesMan +
                '}';
    }
}
