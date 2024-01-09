package entities;

public class Product {
    private static String prodName;
    private double prodPrice;
    private int cep;

    public static String getProdName() {
        return prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public Product() {
    }

    public Product(String prodName, double prodPrice, int cep) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.cep = cep;
    }
}
