package ua.artcode.market.utils;

import ua.artcode.market.controllers.BillController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static int billId;
    private static int terminalId;

    public static int generateBillID() {
        return ++billId;
    }
    public static int generateTerminalID() {
        return ++terminalId;
    }
    public static int generateProductID() {
        return (int)(Math.random()*100000);
    }

    public static Date getCurrentTime() {
        return new Date();
    }


}
