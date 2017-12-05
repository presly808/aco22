package week1.models;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class Salesman {

    private String Name;
    private String login;
    private String pass;

    private int countSoldProduct;

    private  int id;


    public Salesman(String Name, String login, String pass, int id) {
        this.Name = Name;
        this.login = login;
        this.pass = pass;
        this.id = id;
    }

    public int getCountSoldProduct() {
        return countSoldProduct;
    }

    public void setCountSoldProduct(int countSoldProduct) {
        this.countSoldProduct = countSoldProduct;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }


    @Override
    public String toString() {
        return "Salesman{" +
                "Name='" + Name + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", countSoldProduct=" + countSoldProduct +
                ", id=" + id +
                '}';
    }

}

