package ua.artcode.market.model;

public class ProductBill {

    private int productCode;
    private int productQuontity;

    public ProductBill(int productCode, int productQuontity) {
        this.productCode = productCode;
        this.productQuontity = productQuontity;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getProductQuontity() {
        return productQuontity;
    }

    public void setProductQuontity(int productQuontity) {
        this.productQuontity = productQuontity;
    }

}
