package Customer;

import java.net.URL;
import java.util.ResourceBundle;

import Core.Customer;
import Core.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.scene.control.Alert;

public class CustomerPage {

    /* Author: Helen Lin
    */
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfCustid;

    @FXML
    private TextField tfCustFname;

    @FXML
    private TextField tfCustLName;

    @FXML
    private TextField tfCustAddress;

    @FXML
    private TextField tfCustCity;

    @FXML
    private TextField tfCustProv;

    @FXML
    private TextField tfCustPostal;

    @FXML
    private TextField tfCustCountry;

    @FXML
    private TextField tfCustHPhone;

    @FXML
    private TextField tfCustBPhone;

    @FXML
    private TextField tfCustEmail;

    @FXML
    private TextField tfAgentId;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private ListView<Customer> lvCustomers;

    @FXML
    void initialize() {
        assert tfCustid != null : "fx:id=\"tfCustid\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustFname != null : "fx:id=\"tfCustFname\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustLName != null : "fx:id=\"tfCustLName\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustAddress != null : "fx:id=\"tfCustAddress\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustCity != null : "fx:id=\"tfCustCity\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustProv != null : "fx:id=\"tfCuustProv\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustPostal != null : "fx:id=\"tfCustPostal\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustCountry != null : "fx:id=\"tfCustCountry\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustHPhone != null : "fx:id=\"tfCustHPhone\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfCustBPhone != null : "fx:id=\"tfCustBPhone\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert tfAgentId != null : "fx:id=\"tfAgentId\" was not injected: check your FXML file 'customerPage.fxml'.";

        //load the list view
        loadListView();
    }

    //When edit button clicked
    @FXML
    void OnActionEditClick(ActionEvent event) {
        //Need to be able to edit the Cust information here
        //when someone clicks edit, disable the edit button
        btnEdit.setDisable(true);
        tfCustFname.setEditable(true);
        tfCustLName.setEditable(true);
        tfCustAddress.setEditable(true);
        tfCustCity.setEditable(true);
        tfCustProv.setEditable(true);
        tfCustPostal.setEditable(true);
        tfCustCountry.setEditable(true);
        tfCustHPhone.setEditable(true);
        tfCustBPhone.setEditable(true);
        tfCustEmail.setEditable(true);
        tfAgentId.setEditable(true);
        btnSave.setDisable(false); //enable the save button
    }

    @FXML
    void OnActionSaveClick(ActionEvent event) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE Customers set CustomerId=?, CustFirstName=?, CustLastName=?, CustAddress=?, CustCity=?, CustProv=?, CustPostal=?, CustCountry=?,  CustHomePhone=?, CustBusPhone=?, CustEmail=?, AgentId=? where CustomerId=?;";

        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(1, Integer.parseInt(tfCustid.getText()));
            stmt.setString(2, tfCustFname.getText());
            stmt.setString(3, tfCustLName.getText());
            stmt.setString(4, tfCustAddress.getText());
            stmt.setString(5, tfCustCity.getText());
            stmt.setString(6, tfCustProv.getText());
            stmt.setString(7, tfCustCountry.getText());
            stmt.setString(8, tfCustHPhone.getText());
            stmt.setString(9, tfCustBPhone.getText());
            stmt.setString(10, tfCustEmail.getText());
            stmt.setInt(11, Integer.parseInt(tfAgentId.getText()));
            int numRows = stmt.executeUpdate();
            //brings a value that tells us how many rows modified
            if(numRows==0)
            {
                //create a new alert
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated. Contact Tech Support");
                alert.showAndWait();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
//after they save, turn fields back to read only
        tfCustid.setEditable(false);
        tfCustFname.setEditable(false);
        tfCustLName.setEditable(false);
        tfCustAddress.setEditable(false);
        tfCustCity.setEditable(false);
        tfCustProv.setEditable(false);
        tfCustPostal.setEditable(false);
        tfCustCountry.setEditable(false);
        tfCustHPhone.setEditable(false);
        tfCustBPhone.setEditable(false);
        tfCustEmail.setEditable(false);
        tfAgentId.setEditable(false);

        //enable the edit button again
        btnEdit.setDisable(false);
    }

    //our array list for storing Customers
    ObservableList<Customer> data = FXCollections.observableArrayList();

    private void loadListView(){
        //start with clean list view
        lvCustomers.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from customers";
        try {
            Statement stmt = conn.createStatement();//creates statement object
            ResultSet rs = stmt.executeQuery(sql);//executes the statement, stores the return in rs
            while (rs.next())
            {
                data.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12)));

            }
            lvCustomers.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

