package week1.interfaces;

import week1.model.Bill;
import week1.model.Seller;

import java.util.List;

public interface IAppDB {

    List<Bill> getAllBills();

    List<Seller> getAllSellers();

    int getCurrentSellerId();

    void setCurrentSeller(int currentSellerId);

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
