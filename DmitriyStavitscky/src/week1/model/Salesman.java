package week1.model;

public class Salesman {

    private String fullName;
    private String login;
    private int pass;
    private double sumOfAllSales;

    public Salesman(String name){
        this.fullName = name;
    }

    public Salesman(String fullName, String login, int pass) {
        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
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
        this.sumOfAllSales += sumOfAllSales;
    }
}
