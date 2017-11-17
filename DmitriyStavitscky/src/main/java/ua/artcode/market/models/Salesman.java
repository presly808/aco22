package ua.artcode.market.models;

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

    @Override
    public String toString() {
        return String.format("name: %s, sum of all sales: %.2f", fullName, sumOfAllSales);

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if(obj == null || obj.getClass() != getClass()){
            return false;
        }

        Salesman other = (Salesman) obj;

        return (fullName != null && fullName.equals(other.fullName)) &&
                (login != null && login.equals(other.login)) &&
                pass == other.pass &&
                sumOfAllSales == other.sumOfAllSales;
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
