package main.java.market;
//import

public class Bill {

    private boolean lockedBill;
    private int counter;

    private int id;
    private Salesman salesman;
    private Product[] productArray; //20
    private int amountPrice;
    private BillTime closeTime;

    public Bill(int id, Salesman salesman, int amountPrice, BillTime closeTime) {
        this.id = id;
        this.salesman = salesman;
        this.productArray = new Product[20];
        this.amountPrice = amountPrice;
        this.closeTime = closeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Product[] getProductArray() {
        return productArray;
    }

    public void setProductArray(Product[] productArray) {
        this.productArray = productArray;
    }

    public int getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(int amountPrice) {
        this.amountPrice = amountPrice;
    }

    public BillTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(BillTime closeTime) {
        this.closeTime = closeTime;
    }


    public void addProduct(Product productAdd){
        //  проверка каунт или на нулл
        int arrLength = getProductArray().length;
        if (!lockedBill) {

            for (int i = 0; i < arrLength; i++) {
                if (productArray[i] == null) {
                    productArray[i] = productAdd;
                    break;
                }
                //System.out.println("Your bill is full.");
            }

        } else {
            System.out.println("Your bill is locked.");
        }
    }

    public void closeBill(){
        lockedBill = true;
    }

    public int calculateAmountPrice(){
        int result = 0;
        if (lockedBill) {
            int i = 0;
            while (i < productArray.length && productArray[i] != null) {
                result += productArray[i].price;
                i++;
            }
        }
        return result;
    }

    public void printBill(){
        if (lockedBill){
            System.out.println("Salesman: " + salesman.fullName + ";");

            System.out.println("product name \t cost (uah)");
            for(int i = 0; i < productArray.length; i++) {
                if (productArray[i] != null) {
                    Product.printInfo(productArray[i]);
                }else {
                    break;
                }
            }

            System.out.println("Summ: \t \t \t " + calculateAmountPrice() + ";\n");

            BillTime.showTime(closeTime);

        }

    }

}
