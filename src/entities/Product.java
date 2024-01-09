package entities;

import DB.DBConnection;

public class Product {
    private static String prodName;
    final double prodPrice;
    private int cep;

    public static String getProdName() {
        return prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public Product(String prodName, double prodPrice, int cep) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Product{" + getProdName()+
                "prodPrice=" + prodPrice +
                ", cep=" + cep +
                '}';
    }
}
