package ua.artcode.market.Interface;

import ua.artcode.market.Model.Bill;

public interface IBill {

    void questionForClosingBill(Bill currentBill);

    void choseProduct(Bill currentBill);

    void allProductsSelected(Bill currentBill);

}
