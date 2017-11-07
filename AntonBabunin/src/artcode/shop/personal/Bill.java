package artcode.shop.personal;


import artcode.shop.product.Product;

public class Bill {
    private int id;
    private Product[] products;
    private String salesMan;
    private double amountPrice;
    private String closeTime;

    private boolean isClosed = false;
    private int firstFreePositionAtProducts;
    private int nextFreePositionAtProducts;

    public Bill(int id){

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

    private void calculateAmountPrice() {
        double amountPrice = 0.0;
        for (Product prod : products) {
            if (prod != null) {
                amountPrice += prod.getPrice();
            }
        }
        this.amountPrice = amountPrice;
    }

    public void printBill() {

    }

    public int getId () {
        return this.id;
    }






    public Product[] getProducts() {
        return products;
    }

    private void setProducts() {
        int size = 20;
        this.products = new Product[size];
        this.firstFreePositionAtProducts = 0;
        this.nextFreePositionAtProducts = 1;
    }

    private double getAmountPrice() {
        return amountPrice;
    }

    private String getSeller() {
        return salesMan;
    }

    private void setSeller(String seller) {
        this.salesMan = seller;
    }

    private String getTime() {
        return closeTime;
    }

    private void setTime(String closeTime) {
        this.closeTime = closeTime;
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


}

