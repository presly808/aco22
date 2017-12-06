package week3.appDB;

import week3.model.Bill;
import week3.model.Salesman;

import java.util.List;

public interface IappDB {


    boolean updateBill(Bill newBill, Bill oldBill);
    boolean removeBill(int id);
    Bill findeBillByID(int id);
    boolean saveBill(Bill bill); //return bill in array with id


    Salesman findSalemanById(int id);
    boolean saveSaleman(Salesman salesman);
    Salesman findSalemanByName(String fullName);
    boolean removeSaleman(Salesman salesman);
    boolean updateSalemen(Salesman newSalesman, Salesman oldSalesman);
    List findBillBySalesman(Salesman salesman);
}
