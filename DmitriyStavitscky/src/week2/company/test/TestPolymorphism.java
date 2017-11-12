package week2.company.test;

import week2.company.controller.CompanyController;
import week2.company.model.Director;
import week2.company.model.Worker;

public class TestPolymorphism {

    public static void main(String[] args) {
        Director director = new Director();
        CompanyController.startWork(director);

        Worker w1 = new Director();

        w1.getOwnSelf();
    }
}
