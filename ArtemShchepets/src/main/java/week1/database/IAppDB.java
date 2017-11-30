package week1.database;

import week1.model.Bill;
import week1.model.Seller;

import java.util.List;
import java.util.logging.Logger;

public interface IAppDB {

    Logger getLogger();

    List<Bill> getAllBills();

    List<Seller> getAllSellers();

    Bill findByBillId(int billId);

    Seller findBySellerLoginOrFullName(String loginOrFullName);

    Seller findBySellerLoginAndPassword(String login, String password);

    Bill saveBill(Bill bill);

    Seller saveSeller(Seller seller);

    Bill removeBill(int billId);

    Seller removeSeller(String login);

    Bill updateBill(Bill bill);

    Seller updateSeller(Seller seller);
}
