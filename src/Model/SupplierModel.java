package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel extends DB  {

    //*************************************************************
    //* GET ALL Suppliers
    //*************************************************************
    public List getSuppliers()
    {
        List<String> SupplierList = new ArrayList<>();

        try {
            //Connect to the DB
            Connection DBConnection = getConnection();
            //Create a statement
            Statement stmt = DBConnection.createStatement();
/*

            */


            String sql = "SELECT SupplierId, SupName FROM `suppliers`";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SupplierList.add(rs.getString("SupplierId"));
                SupplierList.add(rs.getString("SupName"));

                System.out.println();
            }

        } catch (Exception se){
            se.getStackTrace();
        }
        return SupplierList;
    }

}
