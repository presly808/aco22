package ua.artcode.market.product;


public class Product extends ProductAbstract {

    public Product(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof Product &&
                this.getId() == ((Product) object).getId() &&
                this.getName().equals(((Product) object).getName()) &&
                this.getPrice() == ((Product) object).getPrice();
    }

    @Override
    public String toString() {
        return  String.format("Product: id: %d name: %s price: %.2f \n",
                this.getId(), this.getName(), this.getPrice());
    }
}
