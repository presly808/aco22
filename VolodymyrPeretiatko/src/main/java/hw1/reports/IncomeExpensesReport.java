package hw1.reports;

import java.util.Date;

public class IncomeExpensesReport {

    private Date startDate;
    private Date finishDate;
    private Double income;
    private Double expenses;

    public IncomeExpensesReport(Date startDate, Date finishDate, Double income, Double expenses) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.income = income;
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncomeExpensesReport)) return false;

        IncomeExpensesReport that = (IncomeExpensesReport) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!finishDate.equals(that.finishDate)) return false;
        if (!income.equals(that.income)) return false;
        return expenses.equals(that.expenses);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + income.hashCode();
        result = 31 * result + expenses.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IncomeExpensesReport{" +
                "startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", income=" + income +
                ", expenses=" + expenses +
                '}';
    }
}
