package controllers;

import exceptions.IncorrectBillException;
import exceptions.NoSuchSalesmanException;
import exceptions.UnableToAddProductToBillException;
import interfaces.ITerminal;
import models.Bill;
import models.Product;
import models.Salesman;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalController implements ITerminal{

    private BillController billController;

    public TerminalController(BillController billController) {
        this.billController = billController;
    }

    public Bill createBill(Bill bill) throws IncorrectBillException {
        if (bill == null) {
            throw new IncorrectBillException("Bill can not be null");
        }
        billController.getBillSet().add(bill);
        return bill;
    }

    public Product addProduct(Bill bill, String productName) throws UnableToAddProductToBillException {
        if (bill == null) {
            throw new IllegalArgumentException("BillController not created");
        }
        return billController.addProductToBill(bill, productName);
    }

    public void closeAndSaveBill(Bill bill) {
        billController.closeBill(bill);
    }

    public Bill findBillById(long id) throws NoSuchElementException{
        return billController.getBillSet().
                stream().filter(bill -> bill.getId() == id).findFirst().get();
    }

    public Salesman findSalesmanByLoginOrFullName(String fullName, String login)
                                                    throws NoSuchSalesmanException{
        Set<Bill> billSet = billController.getBillSet();

        try {
            return billSet.stream().filter((Bill bill) -> {
                Salesman salesman = bill.getSalesman();
                String fullname = salesman.getFullname();
                String salesmanLogin = salesman.getLogin();

                return !(fullname == null && salesmanLogin == null) &&
                        (fullname.equals(fullName) || salesmanLogin.equals(login));
            }).collect(Collectors.toList()).get(0).getSalesman();
        } catch (Exception e){
            throw new NoSuchSalesmanException("Salesman not found");
        }
    }

    public List<Bill> sortBillListByDateCreation() {
        List<Bill> list = new ArrayList<>();
        Set<Bill> set = billController.getBillSet();
        list.addAll(set);
        Collections.sort(list, Comparator.comparing(Bill::getCreationDate));
        return list;
    }

    public List<Bill> getBillsByStartAndEndDates(Date startDate, Date endDate) {
        return getBillSet().stream().filter(bill ->
                bill.getCreationDate().compareTo(startDate) >=0 &&
                        bill.getCreationDate().compareTo(endDate) <= 0).
                                collect(Collectors.toList());
    }

    public List<Bill> getBillsByCreator(Salesman salesman) {
        return getBillSet().stream().filter(bill -> bill.getSalesman().
                compareTo(salesman) == 1).collect(Collectors.toList());

    }

    public Set<Bill> getBillSet() {
        return billController.getBillSet();
    }
}
