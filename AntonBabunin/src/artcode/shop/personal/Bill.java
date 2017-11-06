package artcode.shop.personal;


import artcode.shop.product.Product;

public class Bill {
    private Product[] products;
    private double amountPrice;
    private String seller;
    private String closeTime;
    private boolean isClosed = false;
    private int id;


    private int firstFreePositionAtProducts;
    private int nextFreePositionAtProducts;
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
        int size = 20;
        this.products = new Product[size];
        this.firstFreePositionAtProducts = 0;
        this.nextFreePositionAtProducts = 1;
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
        return closeTime;
    }

    public void setTime(String closeTime) {
        this.closeTime = closeTime;
    }

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
                if (i < this.firstFreePositionAtProducts) {
                    this.nextFreePositionAtProducts = this.firstFreePositionAtProducts;
                    firstFreePositionAtProducts = i;
                } else {
                    this.nextFreePositionAtProducts = i;
                }
                break;
            }
        }
    }

    public void closeBill () {
        this.setIsClosed();
        this.calculate();
    }
}

