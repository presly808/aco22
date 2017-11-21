package week1.controller;

import week1.database.IAppDBImpl;
import week1.interfaces.IAppDB;
import week1.interfaces.ITerminalController;
import week1.model.Seller;

public class ITerminalControllerFactory {

    public static ITerminalController create() {

        IAppDB iAppDB = new IAppDBImpl();

        Seller seller1 = new Seller("worker1", "password1", "NadyaHoroshun");
        Seller seller2 = new Seller("worker2", "password2", "AntonVorobey");
        Seller seller3 = new Seller("worker3", "password3", "VasyaPupkin");
        Seller seller4 = new Seller("worker4", "password4", "AnyaTupova");
        Seller seller5 = new Seller("worker5", "password5", "ArtemShchepets");

        iAppDB.saveSeller(seller1);
        iAppDB.saveSeller(seller2);
        iAppDB.saveSeller(seller3);
        iAppDB.saveSeller(seller4);
        iAppDB.saveSeller(seller5);

        return new ITerminalControllerImpl(iAppDB);
    }
}
