package ua.artcode.market;

public class Product {

    public int code;
    public String name;
    public double price;

    Product(int code, String name, double price){

        this.code = code;
        this.name = name;
        this.price = price;

    }

    public static Product createProduct(int code, String name, double price){

        Product product = new Product(code, name, price);

        return product;

    }

    public static Product[] initProductsList(int countProducts){

        Product[] productsList = new Product[countProducts];

        for (int i = 0; i < productsList.length; i++) {
            productsList[i] = Product.createProduct(i+1, "Goods"+(i+1), Math.rint((Math.random()*10)*100)/100);
        }

        return productsList;
    }

    public static Product findByCode(Product[] productslist, int productCode) {

        for (int i = 0; i < productslist.length; i++) {
            if (productslist[i].code == productCode) {
                return productslist[i];
            }
        }

        System.out.println("It is not possible to identify the product by code <<" + productCode + ">>");


        return new Product(0,"", 0);
    }

    public static void printFullInfo(Product[] productslist){

        System.out.println("\n" + fulInfoOfProdukts(productslist));
    }

    public static String fulInfoOfProdukts(Product[] productslist) {

        String textMessage = "";

        textMessage += "\nPRICE OF GOODS\n" + "Code\t\t"+"Goods\t\t"+"Price";

        for (int i = 0; i < productslist.length; i++) {

            textMessage += "\n" + productslist[i].code + "\t\t" + productslist[i].name + "\t\t" + productslist[i].price;

        }

        return textMessage+"\n";
    }
}



