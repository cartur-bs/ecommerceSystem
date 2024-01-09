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
        System.out.println("Produtos em estoque:");
        DBConnection.getConnection();
        DBConnection.consultProducts();
        System.out.println("Vamos consultar seu CEP");
        int cep = sc.nextInt();
        try{
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.postmon.com.br/v1/cep/" + cep)).build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            System.out.println(responseBody);
        System.out.println("Quantos produtos você deseja comprar?");
        int loopSize = sc.nextInt();
        List<Product> productList = new ArrayList<>();
        for(int i=0; i<loopSize; i++){
            System.out.println("Produto "+ (i+1));
            String prodName = sc.next();
            double price = DBConnection.getProdPrice(prodName);
            if(price>0){
                productList.add(new Product(prodName,price , cep));
            }else System.out.println("Insira um valor válido!");
        }
        //ultimo instanciando que aparece o preço
           /* for(Product e: productList){
                System.out.println(e.getProdName() + e.getProdPrice());
            }*/
            for (Product e : productList) {
                System.out.println(e.getProdName() + " - R$" + e.getProdPrice());
            }
      //calculo do frete
        }
        catch (IOException | InterruptedException e){
            e.getMessage();
        }
        sc.close();

    }
}