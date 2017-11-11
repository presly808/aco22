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
    public String closeBill(Bill bill){
        if (closeTime == null){
            closeTime = "12:30";
        }else {
            System.out.printf("This bill was closed");
        }
        return closeTime;
    }
    public double calculateAmountPrice(Product[] product ){
        double amountPrice = 0.0d;
        for (int i = 0; i < product.length; i++){
            amountPrice = product[i].getPrice();
        }
        return amountPrice;
    }
    public void printBill(Bill bill){
        System.out.println("Bill's number" + id);
        for (int i = 0; i < products.length; i++){
            System.out.print("Name " + products[i].getName() + " --------- ");
            System.out.println("Price " + products[i].getPrice());
        }
        System.out.println("Amount price " + calculateAmountPrice(bill.products));
        System.out.println("Time: " + closeBill(bill));
    }

}
