package artcode.shop;

public class Bill {

    private int id;
    private Product[] products;
    private Salesman salesman;
    private double amountPrice;
    private String closeTime;

    private boolean isClosed;
    private int firstFreePositionAtProducts;
    private int nextFreePositionAtProducts;

    public void addProduct (Product product) {
        if (!this.isClosed()) {
            if (product == null || product.getName() == null || product.getName().equals(null)) {
//                product = Product.setProductNull(product);
                return;
            } else {

                if (this.getProducts() == null || this.getProducts().equals(null)) {
                    setProducts();
                }

                this.getProducts()[this.firstFreePositionAtProducts] = product;
                this.firstFreePositionAtProducts = this.nextFreePositionAtProducts;

                for (int i = firstFreePositionAtProducts + 1; i < this.getProducts().length; i++) {
                    if (this.getProducts()[i] == null) {
                        this.nextFreePositionAtProducts = i;
                        break;
                    }
                }
            }
        }
        else return;
    }

    private void setProducts() {
        int size = 20;
        this.products = new Product[size];
        this.firstFreePositionAtProducts = 0;
        this.nextFreePositionAtProducts = 1;
    }

    public void closeBill () {
        this.setIsClosed();
        this.calculateAmountPrice();
    }

    private void setIsClosed() {
        this.isClosed = true;
    }
    public boolean isClosed() {
        return this.isClosed;
    }

    public double calculateAmountPrice(){
        double amountPrice = 0.0;
        if (products != null) {
            for (Product product : products) {
                if (product != null) {
                    amountPrice += product.getPrice();
                    System.out.println("price " + product.getPrice() + "\n" + "amountPrice " + amountPrice);
                }
            }
        }
        return amountPrice;
    }

    private void printBill(){

    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(double amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }


    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
