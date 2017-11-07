public class Bill {

    int id;
    Product[] products;
    Salesman salesman;
    double amountPrice;
    String closeTime;

    public Bill(int id, Product[] products, Salesman salesman, double amountPrice, String closeTime) {
        this.id = id;
        this.products = products;
        this.salesman = salesman;
        this.amountPrice = amountPrice;
        this.closeTime = closeTime;
    }

    public Bill() {
    }

    public void addProduct(Product[] product){

    }
    public void closeBill(){}
    public void calculateAmountPrice(){}
    public void printBill(){}

}
