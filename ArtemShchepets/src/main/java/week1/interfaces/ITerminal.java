package week1.interfaces;

import week1.model.Seller;

public interface ITerminal extends IBill {

    void signIn(String login, String password);

    Seller findSalesmanByLoginOrFullname(String loginOrNameOfSalesMan);

    Seller[] getTopNofSalesMan(int quantityOfTopSellers);

    String doSomeStatisticStuff();
}
