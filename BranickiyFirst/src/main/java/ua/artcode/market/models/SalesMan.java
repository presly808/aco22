package src.main.java.ua.artcode.market.models;

import src.main.java.ua.artcode.market.models.Product;

public class Salesman {

   private String fullName, login, password;
   private int id;
   private  double sumOfAllSales;

    public Salesman(String fullName, String login, String password, int id){

        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.id = id;


    }
    public Salesman(String name) {
        this.fullName = name;
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
                password == other.password &&
                sumOfAllSales == other.sumOfAllSales ;
    }

    public String getPass() {
        return password;
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
}
