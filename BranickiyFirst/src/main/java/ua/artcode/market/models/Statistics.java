package src.main.java.ua.artcode.market.models;

import src.main.java.ua.artcode.market.models.Salesman;

public class Statistics {

    private Salesman salesmanWithMaxAmount;
    private double maxAmount;
    private Salesman salesmanWithMinAmount;
    private double minAmount;
    private double averageAmountInOneCheck;
    private double sumOfAllSales;

    public Statistics(Salesman salesmanWithMaxAmount, double maxAmount,
                      Salesman salesmanWithMinAmount, double minAmount,
                      double averageAmountInOneCheck, double sumOfAllSalles) {
        this.salesmanWithMaxAmount = salesmanWithMaxAmount;
        this.maxAmount = maxAmount;
        this.salesmanWithMinAmount = salesmanWithMinAmount;
        this.minAmount = minAmount;
        this.averageAmountInOneCheck = averageAmountInOneCheck;
        this.sumOfAllSales = sumOfAllSalles;
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
                "Sum of all sales" + sumOfAllSales + " } ";
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
                sumOfAllSales == other.sumOfAllSales);


    }
}
