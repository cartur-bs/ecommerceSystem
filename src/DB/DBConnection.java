package DB;

import entities.Product;

import java.sql.*;
public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/Products";
    private static final  String uname = "root";
    private static final String password="root1234";

    private static Connection con = null;
    public static Connection getConnection() throws SQLException {
        if(con==null){
            try{
                con = DriverManager.getConnection(url, uname, password);
            }catch(SQLException e){
                throw new SQLException(e.getMessage());
            }}
        return con;
    }

    public static void consultProducts() throws SQLException{
        String product = "SELECT productName, productPrice FROM ProductsList;" ;
        Statement st = con.createStatement();
        ResultSet rst;

        try{
            rst = st.executeQuery(product);
            while(rst.next()){
                System.out.println(rst.getString("productName")  +
                        " | Preço: " + rst.getDouble("productPrice"));
            }
} catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    public static double getProdPrice(String prodName) throws SQLException {
        String getProdPriceSet = "SELECT productPrice from ProductsList WHERE productName= "+ "'" + prodName+"';";
        Statement st = con.createStatement();
        ResultSet rst;
        try{
            rst = st.executeQuery(getProdPriceSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rst.next()){
            return rst.getInt("productPrice");
        }else return 0;
    }

    //sends the products data to database
    public static void sendProd(String prodName, double prodPrice, int quantity, int zipCode){
        String setCommand = "INSERT INTO productsToShip VALUES (?,?,?,?)";
        try{
            PreparedStatement pst = con.prepareStatement(setCommand);
            pst.setString(1, prodName);
            pst.setDouble(2,prodPrice);
            pst.setInt(3,quantity);
            pst.setInt(4,zipCode);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
