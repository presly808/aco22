package ua.artcode.market.interfaces;

import ua.artcode.market.controllers.AppDBImpl;
import ua.artcode.market.model.Bill;
import ua.artcode.market.model.Terminal;
import ua.artcode.market.view.InterfaceServices;

import java.io.IOException;
import java.util.Date;

public interface IBill {

    public void closeBill(Bill currentBill);
}
