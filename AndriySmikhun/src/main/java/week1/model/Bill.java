package week1.model;


import java.util.Comparator;

public class Bill implements Comparable{

    private int id;
    private Product[] products;
    private Salesman salesman;
    private double amountPrice;
    private MyDataTime dataTime = new MyDataTime();

    public Bill(int id, Product[] products, Salesman salesman, double amountPrice, MyDataTime dataTime) {
        this.id = id;
        this.products = new Product[20];
        this.salesman = salesman;
        this.amountPrice = 0.0;
        this.dataTime = null;
    }

    public Bill() {
    }

    public Bill(Salesman salesman) {
        this.salesman = salesman;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }


    public int getId() {
        return id;
    }

    public Product[] getProducts() {
        return products;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public MyDataTime getDataTime() {
        return dataTime;
    }

    public void addProduct(Product[] product){


    }
    public boolean setCloseBill(){
        if (dataTime == null){
            dataTime.setDay(1);
            dataTime.setMonth(12);
            dataTime.setHour(15);
            dataTime.setMinute(25);
            dataTime.setSecond(35);
            return true;
        }
        System.out.printf("This bill was closed");

        return false;
    }
    public double calculateAmountPrice(Product[] product ){
        double amountPrice = 0.0d;
        for (int i = 0; i < products.length; i++){

            amountPrice = products[i].getPrice();
        }
        return amountPrice;
    }
    public void printBill(){
        System.out.println("Bill's number" + id);
        for (int i = 0; i < products.length; i++){
            if (products[i] == null) break;
            System.out.print("Name " + products[i].getName() + " --------- ");
            System.out.println("Price " + products[i].getPrice());
        }
        System.out.println("Amount price " + calculateAmountPrice(products));
        System.out.println("Time: " + dataTime.toString());
    }

    @Override
    public int compareTo(Object o) {
        return getId() - ((Bill)o).getId();

    }

    public static class SortByDateComparator implements Comparator<Bill>{
        @Override
        public int compare(Bill o1, Bill o2) {
            return 0;
        }
    }

}