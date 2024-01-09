package entities;

public class Product {
    private String prodName;
    private double prodPrice;
    private int cep;

    public String getProdName() {
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

    public static double frete(double sum) {
        if (sum > 600) {
            return sum * 0.07;
        } else return sum * 0.1;
    }
}
