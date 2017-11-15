package ua.artcode.market.models;


public class Product extends ProductAbstract {

    public Product() {
        super();
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
