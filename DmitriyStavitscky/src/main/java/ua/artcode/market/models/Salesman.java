package ua.artcode.market.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Salesman {

    private String fullName;
    private String login;
    private int pass;
    private int id;

    private double sumOfAllSales;
    private double salary;

    private boolean director;
    private List<Salesman> subordinates = new ArrayList<>();

    public Salesman() {
    }

    public Salesman(String name) {
        this.fullName = name;
    }

    public Salesman(String fullName, String login, int pass, int id) {
        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
        this.id = id;
    }

    public Salesman(String fullName, String login, int pass, int id, boolean isDirector) {
        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
        this.id = id;
        this.director = isDirector;
    }

    public void addSum(double sum) {
        this.sumOfAllSales += sum;
    }

    @Override
    public String toString() {
        return String.format("name: %s, sum of all sales: %.2f", fullName, sumOfAllSales);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Salesman other = (Salesman) obj;

        return (fullName != null && fullName.equals(other.fullName)) &&
                (login != null && login.equals(other.login)) &&
                pass == other.pass &&
                sumOfAllSales == other.sumOfAllSales &&
                salary == other.salary &&
                subordinates.size() == other.subordinates.size() &&
                subordinates.containsAll(other.subordinates);
    }

    public int getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public double getSumOfAllSales() {
        return sumOfAllSales;
    }

    public void setSumOfAllSales(double sumOfAllSales) {
        this.sumOfAllSales = sumOfAllSales;
    }

    public int getId() {
        return id;
    }

    public List<Salesman> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Salesman> subordinates) {
        this.subordinates = subordinates;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isDirector() {
        return director;
    }

    public void setDirector(boolean director) {
        this.director = director;
    }

    public static class SumOfAllSalesComparator implements Comparator<Salesman> {

        @Override
        public int compare(Salesman o1, Salesman o2) {
            double res = o1.sumOfAllSales - o2.sumOfAllSales;

            return res > 0 ? 1 :
                    res < 0 ? -1 : 0;
        }
    }

}