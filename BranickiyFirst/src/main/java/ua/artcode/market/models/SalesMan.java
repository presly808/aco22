package src.main.java.ua.artcode.market.models;

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
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
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
                password != null && password.equals(other.password) &&
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

    private double getSumOfAllSales() {
        return sumOfAllSales;
    }

    private void setSumOfAllSales(double sumOfAllSales) {
        this.sumOfAllSales = sumOfAllSales;
    }

    public int getId() {
        return id;
    }
}
