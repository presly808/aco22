package week1.model;

public class IncomeExpenses {

    private double income;
    private double expenses;

    public IncomeExpenses() {
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "IncomeExpenses{" +
                "income=" + income +
                ", expenses=" + expenses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncomeExpenses that = (IncomeExpenses) o;

        if (Double.compare(that.income, income) != 0) return false;
        return Double.compare(that.expenses, expenses) == 0;
    }
}
