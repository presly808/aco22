public class Bill {

    int id;
    Product[] products = new Product[20];
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

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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

    public String getCloseTime() {
        return closeTime;
    }

    public void addProduct(Product[] product){

    }
    public String closeBill(){
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
    public void printBill(){
        System.out.println("Bill's number" + id);
        for (int i = 0; i < products.length; i++){
            System.out.print("Name " + products[i].getName() + " --------- ");
            System.out.println("Price " + products[i].getPrice());
        }
        System.out.println("Amount price " + calculateAmountPrice(products));
        System.out.println("Time: " + closeBill());
    }

}
