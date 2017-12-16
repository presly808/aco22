package week1.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ENIAC on 10.11.2017.
 */
public class Salesman {

    private String Name;
    private String login;
    private String pass;


    private int countSoldProduct;

    private double salary;
    private List<Salesman> subsalesmans;
    private int sumOfClosedBills;

    private int id;

    public Salesman(int id, double salary, int sumOfClosedBills) {
        this.id = id;
        this.salary = salary;
        this.sumOfClosedBills = sumOfClosedBills;
        subsalesmans = new ArrayList<>();
    }

    public Salesman(String Name, String login, String pass, int id) {
        this.Name = Name;
        this.login = login;
        this.pass = pass;
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Salesman> getSubsalesmans() {
        return subsalesmans;
    }

    public void setSubsalesmans(List<Salesman> subsalesmans) {
        this.subsalesmans = subsalesmans;
    }

    public int getSumOfClosedBills() {
        return sumOfClosedBills;
    }

    public void setSumOfClosedBills(int sumOfClosedBills) {
        this.sumOfClosedBills = sumOfClosedBills;
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


    public void addSubSalesman(Salesman subSalesman){
        subsalesmans.add(subSalesman);
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

    public static void CalculateSalary() {
        System.out.println("ss");
    }
}


