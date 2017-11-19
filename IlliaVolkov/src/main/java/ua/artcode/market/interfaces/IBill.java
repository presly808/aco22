package ua.artcode.market.interfaces;

import ua.artcode.market.model.Bill;

public interface IBill {

    void questionForClosingBill(Bill currentBill);

    void choseProduct(Bill currentBill);

    void allProductsSelected(Bill currentBill);

}
