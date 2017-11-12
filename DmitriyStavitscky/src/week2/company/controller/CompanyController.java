package week2.company.controller;

import week2.company.model.Worker;

public class CompanyController {

    // must be worker, override method work
    public static void startWork(Worker worker){
        worker.work();
    }
}
