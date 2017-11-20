package week1.model;


import java.util.Comparator;

public class Bill implements Comparable {

    int id;
    Product[] products = new Product[10];
    Salesman salesman;
    double amountPrice;
    String dataTime;

    public Bill(int id, Product[] products, Salesman salesman, double amountPrice, String dataTime) {
        this.id = id;
        this.products = products;
        this.salesman = salesman;
        this.amountPrice = 0.0;
        this.dataTime = dataTime;
    }

    public Bill() {
    }

    public Bill(int i, Salesman salesman) {
        this.salesman = salesman;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDataTime() {
        return dataTime;
    }

    public boolean addProduct(Product product) {
        for (int p = 0; p < products.length; p++) {
            if (products[p] == null) {
                products[p] = product;
                return true;
            }
        }
        return false;
    }

    public boolean setCloseBill() {
        if (dataTime == null) {
            dataTime = "20:15:15 25/08";
            return true;
        }
        System.out.println("This bill was closed");

        return false;
    }

    // you should not pass prodcuts as arr
    public double calculateAmountPrice() {
        double amountPrice = 0.0d;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null)break;
            amountPrice = amountPrice + products[i].getPrice();
        }
        return amountPrice;
    }

    // ret string or use toStr
    public boolean printBill() {
        System.out.println("Bill's number" + id);
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) break;
            System.out.print("Name " + products[i].getName() + " --------- ");
            System.out.println("Price " + products[i].getPrice());
        }
        System.out.println("Amount price " + calculateAmountPrice());
        System.out.println("Time: " + dataTime.toString());
        return true;

    }

    @Override
    public int compareTo(Object o) {
        return getId() - ((Bill) o).getId();

    }

}