package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductModel extends DB {

    //*************************************************************
    //* GET ALL Products
    //*************************************************************
    public List getProducts()
    {
        List<String> ProductList = new ArrayList<>();

        try {
            //Connect to the DB
            Connection DBConnection = getConnection();
            //Create a statement
            Statement stmt = DBConnection.createStatement();
/*

    private int ProductId;
    private String ProdName;

            */


            String sql = "SELECT ProductId, ProdName FROM `products`";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductList.add(rs.getString("ProductId"));
                ProductList.add(rs.getString("ProdName"));

                System.out.println();
            }

        } catch (Exception se){
            se.getStackTrace();
        }
        return ProductList;
    }

}
