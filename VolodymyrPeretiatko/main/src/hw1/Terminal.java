package hw1;

import java.util.ArrayList;


public class Terminal {

    private static Terminal uniqueInstance;

    private ArrayList<Bill> bills;
    private ArrayList<Salesman> salesmen;

    public static synchronized Terminal getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Terminal();
        }
        return uniqueInstance;
    }

    private Terminal(){
        bills = new ArrayList<>();
        salesmen = new ArrayList<>();
    }

    public boolean addSalesman(Salesman salesman){
        return salesmen.add(salesman);
    }

}
