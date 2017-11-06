package artcode.shop;


public class Bill {
    private Product[] products;
    private double amountPrice;
    private String seller;
    private String time;
    private boolean isClosed = false;

    public Bill(){

    }

//    public Bill(Product[] products, double amountPrice, String seller, String time) {
//        this.products = products;
//        this.amountPrice = amountPrice;
//        this.seller = seller;
//        this.time = time;
//    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts() {
        this.products = new Product[20];
    }

    public double getAmountPrice() {
        return amountPrice;
    }

    private void calculate() {
        double amountPrice = 0.0;
        for (Product prod : products) {
            if (prod != null) {
                amountPrice += prod.getPrice();
            }
        }
        this.amountPrice = amountPrice;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void addProduct (Product product) {
        if (!this.isClosed()) {
            if (this.getProducts() == null || this.getProducts().equals(null)) {
                setProducts();
            }
            if (product != null) {
                addProductToArr(product);
            } else {
                product = Product.setProductNull(product);
                addProductToArr(product);
            }
        }
        else return;
    }

    private void addProductToArr (Product product) {
        for (int i = 0; i < this.products.length; i++) {
            if (this.getProducts()[i] == null) {
                this.getProducts()[i] = product;
                return;
            }
        }
    }

    public void closeBill () {
        this.setIsClosed();
        this.calculate();
    }

    private void setIsClosed() {
        this.isClosed = true;
    }
    private boolean isClosed() {
        return this.isClosed;
    }

    public void delFromBill (Product product) {
        for (int i = 0; i < this.products.length; i++) {
            if (product.equals(this.products[i])) {
                this.products[i] = null;
                break;
            }
        }
    }
}

/*
    public void addProduct (Product product) {
        if (this.getProducts() == null || this.getProducts().equals(null)){
            setProducts();
            if (product != null && !isClosed()) {
                addProductToArr(product);
            }
        }   else {
            addProductToArr(product);
        }
    }
 */