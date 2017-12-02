package main.java.ua.artcode.market.models;

public class Statistics {

    private SalesMan salesmanWithMaxAmount;
    private double maxAmount;
    private SalesMan salesmanWithMinAmount;
    private double minAmount;
    private double averageAmountInOneCheck;
    private double sumOfAllSalles;

    public Statistics(SalesMan salesmanWithMaxAmount, double maxAmount,
                      SalesMan salesmanWithMinAmount, double minAmount,
                      double averageAmountInOneCheck, double sumOfAllSalles) {
        this.salesmanWithMaxAmount = salesmanWithMaxAmount;
        this.maxAmount = maxAmount;
        this.salesmanWithMinAmount = salesmanWithMinAmount;
        this.minAmount = minAmount;
        this.averageAmountInOneCheck = averageAmountInOneCheck;
        this.sumOfAllSalles = sumOfAllSalles;
    }

    @Override
    public String toString() {

        return "Statistics {" + ", Salesman with max amount ="
                + salesmanWithMaxAmount +
                "Max Amount =" + maxAmount +
                ", Salesman with min amount =" + salesmanWithMinAmount +
                "Min Amount =" + minAmount +
                "Average amount in one check =" +
                averageAmountInOneCheck +
                "Sum of all sales" + sumOfAllSalles + " } ";
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
