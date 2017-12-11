package week1.controller;

import week1.model.Seller;
import week1.utils.InitUtils;

public class ITerminalControllerFactory {

    public static ITerminalController create() {

        return new ITerminalControllerImpl(InitUtils.initDb());
    }

    public static ITerminalController create(Seller mainSeller) {

        return new ITerminalControllerImpl(InitUtils.initSellerDb());
    }
}
