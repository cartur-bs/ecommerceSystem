package entities;

public class Product {
    private String prodName;
    private double prodPrice;
    private int cep;
    private int quantity;

    public String getProdName() {
        return prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product(String prodName, double prodPrice, int quantity, int cep) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.quantity = quantity;
        this.cep = cep;
    }

    public static double frete(double sum) {
        if (sum > 1000) {
            return sum * 0.07;
        } else return sum * 0.1;
    }
}
