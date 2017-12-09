package week1.controller;

import week1.utils.InitUtils;

public class ITerminalControllerFactory {

    public static ITerminalController create() {

        return new ITerminalControllerImpl(InitUtils.initSellerDb());
    }
}
