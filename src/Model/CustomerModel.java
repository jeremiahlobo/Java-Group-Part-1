package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel extends DB {

    //*************************************************************
    //* GET ALL Customers
    //*************************************************************
    public List getCustomers()
    {
        List<String> CustomerList = new ArrayList<>();

        try {
            //Connect to the DB
            Connection DBConnection = getConnection();
            //Create a statement
            Statement stmt = DBConnection.createStatement();
/*
            private int customerID;
            private String CustFirstName;
            private String CustLastName;
            private String CustAddress;
            private String CustCity;
            private String CustProv;
            private String CustPostal;
            private String CustCountry;
            private String CustHomePhone;
            private String CustBusPhone;
            private String CustEmail;
            private int AgentId;*/


            String sql = "SELECT customerID, CustFirstName, CustLastName, CustAddress, CustCity, CustProv, " +
                    "CustPostal,CustCountry, CustHomePhone, CustBusPhone, CustEmail, AgentId FROM `customers`";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CustomerList.add(rs.getString("customerID"));
                CustomerList.add(rs.getString("CustFirstName"));
                CustomerList.add(rs.getString("CustLastName"));
                CustomerList.add(rs.getString("CustAddress"));
                CustomerList.add(rs.getString("CustProv"));
                CustomerList.add(rs.getString("CustProv"));
                CustomerList.add(rs.getString("CustPostal"));
                CustomerList.add(rs.getString("CustCountry"));
                CustomerList.add(rs.getString("CustHomePhone"));
                CustomerList.add(rs.getString("CustBusPhone"));
                CustomerList.add(rs.getString("CustEmail"));
                CustomerList.add(rs.getString("AgentId"));
                System.out.println();
            }

        } catch (Exception se){
            se.getStackTrace();
        }
        return CustomerList;
    }

}
