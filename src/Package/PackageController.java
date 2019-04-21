package Package;
//import models
import Core.Package;
import Core.DBHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PackageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtProdId;

    @FXML
    private TextField txtProdName;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNew;

    @FXML
    private ListView<Package> lvProducts;

    @FXML
    void selectListItem(MouseEvent event) {


        Package pack = lvPackages.getSelectionModel().getSelectedItem();
        int PkId = pack.getPackageId();
        String PkName = pack.getPkgName();
        String PkStart = pack.getPkgStartDate();
        String PkEnd = pack.getPkgEndDate();
        String PkDesc = pack.getPkgDesc();
        double PkBase = pack.getPkgBasePrice();
        double PkAg = pack.getPkgAgencyCommission();

        //change
        txtPkId.setText(String.valueOf(PkId));
        txtPkName.setText(PkName);
        txtPkStart.setText(PkStart);
        txtPkEnd.setText(PkEnd);
        txtPkDesc.setText(PkDesc);
        txtPkBase.setText(String.valueOf(PkBase));
        txtPkAg.setText(String.valueOf(PkAg));
    }

    ObservableList<Package> pkList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        //btnSave.setDisable(true);
        assert txtProdId != null : "fx:id=\"txtProdId\" was not injected: check your FXML file 'productPage.fxml'.";
        assert txtProdName != null : "fx:id=\"txtProdName\" was not injected: check your FXML file 'productPage.fxml'.";

        txtProdId.setEditable(false);
        //load the list view
        loadListView();
    }

    //@FXML
    //void OnActionEditClick(ActionEvent event) {
    //Need to be able to edit the Cust information here
    //when someone clicks edit, disable the edit button
    //OnActionEditClick is referenced in code properties of button
    //btnEdit.setDisable(true);
    //txtProdName.setEditable(true);
    //btnSave.setDisable(false); //enable the save button
    //}

    @FXML
    void OnDeleteClick(ActionEvent event) {

        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "DELETE FROM packages WHERE PackageId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(1, Integer.parseInt(txtProdId.getText()));

            int numRows = stmt.executeUpdate();


            if (numRows == 0) {
                //create a new alert
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were deleted. Contact Tech Support");
                alert.showAndWait();
            }
            else{
                //show rows were updated
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Row was deleted.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            //Alert alert = new Alert(Alert.AlertType.ERROR, "Try using the save command instead.");
            //`alert.showAndWait();
        }

    }


    public void OnActionNewClick(ActionEvent actionEvent) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        //String maxProductIDsql = "SELECT MAX(ProductId) FROM Products";
        String insertsql = "INSERT Packages set PkgName=?, PkgStartDate=?, PkgEndDate=?, PkgDesc=?, PkgBasePrice=?, PkgAgencyCommission=?;";
        int maxPackageId=0;
        try {
            //precompile the statement

            PreparedStatement stmt = conn.prepareStatement(insertsql);
            //PreparedStatement stmt2 = conn.prepareStatement(insertsql);
            //ResultSet rs =stmt2.executeQuery(maxProductIDsql);
            //System.out.println(rs);

//            while(rs.next()){
//                //System.out.println("MAX(user_id)="+rs.getInt("MAX(user_id)"));
//                maxProductId = rs.getInt("MAX(ProductId)") + 1;
//            }
            // close ResultSet rs
            //rs.close();

            //these parameters equate to the sql string above, dont start at 0, start at 1
            //stmt.setInt(1, Integer.parseInt(txtProdId.getText()));
            stmt.setInt(1, maxPackageId);
            stmt.setString(2, txtProdName.getText());

            int numRows = stmt.executeUpdate();
            System.out.println(numRows);

            if (numRows == 0) {
                //create a new alert
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were inserted. Contact Tech Support");
                alert.showAndWait();
            }
            else{
                //show rows were updated
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Rows were inserted.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Try using the save command instead.");
            alert.showAndWait();
        }
//after they save, turn fields back to read only
        //txtProdName.setEditable(false);
        //txtProdId.setEditable(false);

        //enable the edit button again
        //btnEdit.setDisable(false);
    }

            /*
            seven
    int PackageId;
    String PkgName;
    String PkgStartDate;
    String PkgEndDate;
    String PkgDesc;
    double PkgBasePrice;
    double PkgAgencyCommission;

*/

    public void OnActionSaveClick(ActionEvent actionEvent) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE Packages set PkgName=?, PkgStartDate=?,PkgEndDate=?, PkgDesc=?,PkgBasePrice=?,PkgAgencyCommission=? where PackageId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1


            stmt.setString(1, txtPackName.getText());
            stmt.setString(1, txtPackStart.getText());
            stmt.setString(1, txtPackEnd.getText());
            stmt.setString(1, txtPackDesc.getText());
            stmt.setInt(2, Integer.parseInt(txtBase.getText()));
            stmt.setInt(2, Integer.parseInt(txtAgency.getText()));

            int numRows = stmt.executeUpdate();


            if (numRows == 0) {
                //create a new alert
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated. Contact Tech Support");
                alert.showAndWait();
            }
            else{
                //show rows were updated
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Rows were updated.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
//after they save, turn fields back to read only
        //txtProdName.setEditable(false);
        //txtProdId.setEditable(false);

        //enable the edit button again
        //btnEdit.setDisable(false);
    }


    ObservableList<Package> data = FXCollections.observableArrayList();

    private void loadListView() {
        //start with clean list view
        lvPackages.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from packages";
        try {
            Statement stmt = conn.createStatement();//creates statement object
            ResultSet rs = stmt.executeQuery(sql);//executes the statement, stores the return in rs
            while (rs.next()) {
                data.add(new Package(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getInt(7)));

            }
            lvPackages.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
