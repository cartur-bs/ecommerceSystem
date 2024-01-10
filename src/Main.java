import DB.DBConnection;
import entities.Product;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        DBConnection.getConnection();
        DBConnection.consultProducts();
        System.out.println("Produtos em estoque:");
        System.out.println("Vamos consultar seu CEP");
        int cep = sc.nextInt();
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://api.postmon.com.br/v1/cep/" + cep)).build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            System.out.println(responseBody);

            System.out.println("Quantos produtos diferentes você deseja comprar?");
            int loopSize = sc.nextInt();
            List<Product> productList = new ArrayList<>();

            //gets the price from database, the product and quantity and prints the information
            for (int i = 0; i < loopSize; i++) {
                System.out.println("Produto " + (i+1) );
                String prodName = sc.next();
                System.out.println("Quantidade: ");
                sc.nextLine();
                int quantity = sc.nextInt();
                double price = DBConnection.getProdPrice(prodName);
                if (price > 0) {
                    productList.add(new Product(prodName, price,quantity,cep));
                } else{ System.out.println("Reinicie o programa e insira um valor válido!");
                }
            }

            //to calculate the delivery fee and total price
            double total = 0.0;
            for (Product e : productList) {
                System.out.println(e.getProdName() + " - R$" + e.getProdPrice() + " X " + e.getQuantity());
                total = total + e.getProdPrice() * e.getQuantity();
                DBConnection.sendProd(DBConnection.getProdCode(e.getProdName()), e.getProdPrice(), e.getQuantity(),cep );
            }
            System.out.printf("Seu total é: R$%.2f%n + frete = R$%.2f%n ",total, Product.frete(total) );
        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
        sc.close();
        DBConnection.closeConnection();
    }
}