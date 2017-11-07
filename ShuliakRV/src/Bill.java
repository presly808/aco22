public class Bill {

    Product[] arr = new Product[10];
    int numprod;
    String saler;
    String time;
    double sumprice;
    boolean isopen = true;

    public void addProduct(Product p) {
        if (isopen)
            arr[numprod++] = p;
    }

    public String showInfo() {

        String str = "";

        for (int i = 0; i < numprod; i++) {
            str += arr[i].showInfo() + "; ";
        }

        str += "Saler: " + saler + "; " + "Time: " + time + "; " + "Sum: " + sumprice + ".";
        return str;
    }

    public void closeBill() {

        for (int i = 0; i < numprod; i++) {
            sumprice += arr[i].price;
        }

        isopen = false;

    }
}
