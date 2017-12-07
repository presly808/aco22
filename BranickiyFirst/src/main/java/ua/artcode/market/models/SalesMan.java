package src.main.java.ua.artcode.market.models;

import src.main.java.ua.artcode.market.models.Product;

public class Salesman {

   private String fullName, login, password;
   private int id;
   private  double amountOfAllSales;

   public Salesman(String fullName, String login, String password, int id){

       this.fullName = fullName;
       this.login = login;
       this.password = password;
       this.id = id;


   }
    /*
    public void addSum(double sum){

       this.amountOfAllSales += sum;
    }
    */

   public void addSum(double amountOfAllSales){
       this.amountOfAllSales += amountOfAllSales;
   }


    @Override
    public String toString() {
        return String.format("name: %s, sum of all sales: %.2f" , fullName, amountOfAllSales);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (obj == null || obj.getClass() != getClass()) return false;

        Product other = (Product) obj;

        return (fullName != null && fullName.equals(other.fullName)) &&
                (login != null && login.equals(other.login)) &&
                password == other.password &&
                amountOfAllSales == other.amountOfAllSales;

    }


    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public double getAmountOfAllSales() {
        return amountOfAllSales;
    }

    public void setAmountOfAllSales(double amountOfAllSales) {
        this.amountOfAllSales = amountOfAllSales;
    }
}
