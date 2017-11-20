package ua.artcode.market.models;

public class Statistics {

    private double maxAmount;
    private Salesman salesmanWithMaxAmount;
    private double minAmount;
    private Salesman salesmanWithMinAmount;
    private double averageAmountInOneCheck;
    private double sumOfAllSalles;

    public Statistics(double maxAmount, Salesman salesmanWithMaxAmount, double minAmount, Salesman salesmanWithMinAmount, double averageAmountInOneCheck, double sumOfAllSalles) {
        this.maxAmount = maxAmount;
        this.salesmanWithMaxAmount = salesmanWithMaxAmount;
        this.minAmount = minAmount;
        this.salesmanWithMinAmount = salesmanWithMinAmount;
        this.averageAmountInOneCheck = averageAmountInOneCheck;
        this.sumOfAllSalles = sumOfAllSalles;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "maxAmount=" + maxAmount +
                ", salesmanWithMaxAmount=" + salesmanWithMaxAmount +
                ", minAmount=" + minAmount +
                ", salesmanWithMinAmount=" + salesmanWithMinAmount +
                ", averageAmountInOneCheck=" + averageAmountInOneCheck +
                ", sumOfAllSalles=" + sumOfAllSalles +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Statistics other = (Statistics) obj;

        return (maxAmount == other.maxAmount &&
                salesmanWithMaxAmount != null &&
                salesmanWithMaxAmount.equals(other.salesmanWithMaxAmount) &&
                minAmount == other.minAmount &&
                salesmanWithMinAmount != null &&
                salesmanWithMinAmount.equals(other.salesmanWithMinAmount) &&
                averageAmountInOneCheck == other.averageAmountInOneCheck &&
                sumOfAllSalles == other.sumOfAllSalles);
    }
}
