package ua.artcode.market.model;

import java.util.ArrayList;
import java.util.List;

public class SalesMan {

    String fullName;
    String login;
    String pass;

    public SalesMan(String fullName, String login, String pass){

        this.fullName = fullName;
        this.login = login;
        this.pass = pass;
    }

    public static SalesMan createSalesMan(String fullName,String login,String pass){

        SalesMan salesMan = new SalesMan(fullName, login, pass);

        return salesMan;
    }

    public String getFullName() {
        return fullName;
    }

    public static ArrayList<SalesMan> initSalesMans(int countSalesMans){

        ArrayList<SalesMan> salesMans = new ArrayList<SalesMan>();

        for (int i = 1; i <= countSalesMans; i++) {
            salesMans.add(SalesMan.createSalesMan("SalesMan"+i, "SM"+i, ""+i));
        }

        return salesMans;
    }

    public static SalesMan findSalesMan(List<SalesMan> salesMans, String login, String pass) {

        for (SalesMan itemSalesMan: salesMans) {

            if (itemSalesMan.login.equals(login) && itemSalesMan.pass.equals(pass) ) {
                return itemSalesMan;
            }
        }

        System.out.println("SalesMan was not found!");

        return null;
    }


}
