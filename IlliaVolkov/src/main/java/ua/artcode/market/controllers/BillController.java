package ua.artcode.market.controllers;

import ua.artcode.market.interfaces.IBill;
import ua.artcode.market.model.*;
import ua.artcode.market.view.InterfaceServices;

import java.io.IOException;
import java.util.Date;

public class BillController implements IBill{

    @Override
    public void closeBill(Bill currentBill){

        currentBill.closed = true;
        currentBill.closeTime = new Date();
    }
}
