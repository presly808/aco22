package week3.AppDB;

import week3.model.Bill;
import week3.model.Product;
import week3.model.Salesman;

import java.util.List;

public interface IappDB {

    List<Bill> getAllBill();
    List<Salesman> getAllSalesman();
    List<Product> getAllSaleman();

    Bill getBill();
    Bill updateDill();
    Bill removeBill();
    Bill findeBillByID();
    Bill saveBill(Bill bill); //return bill in array with id


    Salesman getSaleman();
    Salesman saveSaleman();
    Salesman findSaleman();
    Salesman removeSaleman();
    Salesman updateSalemen();
}
