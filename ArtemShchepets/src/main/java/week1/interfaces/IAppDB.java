package week1.interfaces;

import week1.model.Bill;
import week1.model.Product;
import week1.model.Seller;

import java.util.List;

public interface IAppDB {

    List<Bill> getAllBills();

    List<Product> getAllProducts();

    List<Seller> getAllSellers();

    int getCurrentSellerId();

    void setCurrentSeller(int currentSellerId);

    Bill findByBillId(int billId);
    Product findByProductId(int productId);
    Seller findBySellerLoginOrFullName(String loginOrFullName);
    Seller findBySellerLoginAndPassword(String login, String password);

    Bill saveBill(Bill bill);
    Product saveProduct(Product product);
    Seller saveSeller(Seller seller);

    Bill removeBill(int billId);
    Product removeProduct(int productId);
    Seller removeSeller(String login);

    Bill updateBill(Bill bill);
    Seller updateSeller(Seller seller);
}
