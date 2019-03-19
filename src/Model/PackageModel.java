package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PackageModel extends DB{

    //*************************************************************
    //* GET ALL PACKAGES
    //*************************************************************
    public List getPackages()
    {
        List<String> PackageList = new ArrayList<>();

        try {
            //Connect to the DB
            Connection DBConnection = getConnection();
            //Create a statement
            Statement stmt = DBConnection.createStatement();

            String sql = "SELECT PackageId, PkgName, PkgStartDate, PkgEndDate, PkgDesc, PkgBasePrice, " +
                    "PkgAgencyCommission FROM `packages`";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                PackageList.add(rs.getString("PackageId"));
                PackageList.add(rs.getString("PkgName"));
                PackageList.add(rs.getString("PkgStartDate"));
                PackageList.add(rs.getString("PkgEndDate"));
                PackageList.add(rs.getString("PkgDesc"));
                PackageList.add(rs.getString("PkgBasePrice"));
                System.out.println();
            }

        } catch (Exception se){
            se.getStackTrace();
        }
        return PackageList;
    }
}
