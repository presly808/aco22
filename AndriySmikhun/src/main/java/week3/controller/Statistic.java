package week3.controller;

import week3.appdb.IappDB;
import week3.model.*;

import java.util.List;

public class Statistic {


    public static double treeMan(IappDB db,Salesman root) {
        double sum = 0;
        List<Salesman> slaves = root.slaves;
        if (slaves == null) {
            return sumAllBillBySalesman(db,root);
        }
        for (Salesman slav : slaves) {
            sum = sum + treeMan(db, slav);
            sum += sumAllBillBySalesman(db,slav);
            System.out.println(slav.getFullName());

        }
        sum = sum* 0.02;
        return sum;
    }

    public static double sumAllBillBySalesman(IappDB db,Salesman salesman){
        double sum = 0.0;
        List<Bill> filteredBill = db.findBillBySalesman(salesman);
        for (Bill bill: filteredBill) {
            sum += bill.getEverageBill();

        }
        return sum;
    }
}
