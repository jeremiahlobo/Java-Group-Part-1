package Customer;

import java.net.URL;
import java.util.ResourceBundle;
import Base.Validator;
import Core.Customer;
import Core.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Base.Validator.*;

public class CustomerPage{

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
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSubmit;

    @FXML
    private ListView<Customer> lvCustomers;

    @FXML
    private Label lblMessage;

    @FXML
    private Button btnBack;

    @FXML
    private void OnBackClick(){
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void selectListItem(MouseEvent event) {
        Customer cust = lvCustomers.getSelectionModel().getSelectedItem();
        int custId = cust.getCustomerID();
        String Fname = cust.getCustFirstName();
        String Lname = cust.getCustLastName();
        String Address = cust.getCustAddress();
        String City = cust.getCustCity();
        String Prov = cust.getCustProv();
        String Postal = cust.getCustPostal();
        String Country = cust.getCustCountry();
        String HPhone = cust.getCustHomePhone();
        String BPhone = cust.getCustBusPhone();
        String Email = cust.getCustEmail();
        int AgentId = cust.getAgentId();

        tfCustid.setText(String.valueOf(custId));
        tfCustFname.setText(Fname);
        tfCustLName.setText(Lname);
        tfCustAddress.setText(Address);
        tfCustCity.setText(City);
        tfCustProv.setText(Prov);
        tfCustPostal.setText(Postal);
        tfCustCountry.setText(Country);
        tfCustHPhone.setText(HPhone);
        tfCustBPhone.setText(BPhone);
        tfCustEmail.setText(Email);
        tfAgentId.setText(String.valueOf(AgentId));

    }

    @FXML
    void initialize() {
        btnSave.setDisable(true);
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
        tfAgentId.setEditable(false);

        btnSubmit.setVisible(false);
        btnSave.setVisible(false);
        //load the list view

        btnSubmit.setVisible(false);
        btnSave.setVisible(false);

        loadListView();
    }

    @FXML
    void OnActionNewClick(ActionEvent event){
        tfCustid.clear();
        tfCustFname.clear();
        tfCustLName.clear();
        tfCustAddress.clear();
        tfCustCity.clear();
        tfCustProv.clear();
        tfCustPostal.clear();
        tfCustCountry.clear();
        tfCustHPhone.clear();
        tfCustBPhone.clear();
        tfCustEmail.clear();
        tfAgentId.clear();

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

        btnSubmit.setDisable(false);

        btnSubmit.setVisible(true);
        btnSave.setVisible(false);

    }
    @FXML
    void OnActionSubmitClick(ActionEvent event) {

        if(matchString(tfCustFname.getText()) == true && matchString(tfCustFname.getText()) == true && matchString(tfCustAddress.getText()) == true && matchString(tfCustCity.getText()) == true
        && matchProvince(tfCustProv.getText()) == true && matchPostalCode(tfCustPostal.getText()) == true && matchString(tfCustCountry.getText()) == true && matchPhoneNumber(tfCustHPhone.getText()) == true &&
        matchPhoneNumber(tfCustBPhone.getText()) == true && matchEmail(tfCustEmail.getText()) == true)
        {

            Connection conn = DBHelper.getConnection();//initialize connection again

            String insertsql = "INSERT Customers set CustFirstName=?, CustLastName=?,CustAddress=?,CustCity=?,CustProv=?,CustPostal=?,CustCountry=?,CustHomePhone=?,CustBusPhone=?,CustEmail=?, AgentId=?, username=?, password=?";
            int maxCustId = 0;
            try {
                //precompile the statement

                PreparedStatement stmt = conn.prepareStatement(insertsql);


                //these parameters equate to the sql string above, dont start at 0, start at 1

                stmt.setString(1, tfCustFname.getText());
                stmt.setString(2, tfCustLName.getText());
                stmt.setString(3, tfCustAddress.getText());
                stmt.setString(4, tfCustCity.getText());
                stmt.setString(5, tfCustProv.getText());
                stmt.setString(6, tfCustPostal.getText());
                stmt.setString(7, tfCustCountry.getText());
                stmt.setString(8, tfCustHPhone.getText());
                stmt.setString(9, tfCustBPhone.getText());
                stmt.setString(10, tfCustEmail.getText());
                stmt.setString(11, tfAgentId.getText());
                stmt.setString(12, txtUsername.getText());
                stmt.setString(13, txtPassword.getText());


                //  private int customerID;
                //    private String CustFirstName;
                //    private String CustLastName;
                //    private String CustAddress;
                //    private String CustCity;
                //    private String CustProv;
                //    private String CustPostal;
                //    private String CustCountry;
                //    private String CustHomePhone;
                //    private String CustBusPhone;
                //    private String CustEmail;
                //    private int AgentId;

                int numRows = stmt.executeUpdate();
                System.out.println(numRows);

                if (numRows == 0) {
                    //create a new alert
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were inserted. Contact Tech Support");
                    alert.showAndWait();
                } else {
                    //show rows were updated
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Rows were inserted.");
                    success.showAndWait();
                    loadListView();
                }
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred.");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bad value entered. Please check entered values.");
            alert.showAndWait();
        }
    }

    @FXML
    void OnActionEditClick(ActionEvent event) {
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
        btnSave.setVisible(true);//show the save button
    }

    //Updates customer and sends to database
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
            stmt.setString(7, tfCustPostal.getText());
            stmt.setString(8, tfCustCountry.getText());
            stmt.setString(9, tfCustHPhone.getText());
            stmt.setString(10, tfCustBPhone.getText());
            stmt.setString(11, tfCustEmail.getText());
            stmt.setInt(12, Integer.parseInt(tfAgentId.getText()));
            stmt.setInt(13, lvCustomers.getSelectionModel().getSelectedItem().getCustomerID());
            int numRows = stmt.executeUpdate();
            //brings a value that tells us how many rows modified
            lblMessage.setText("Customer successfully updated");

            if (numRows == 0) {
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

        //refresh the list view
        loadListView();
    }

    @FXML
    void OnActionDeleteClick(ActionEvent event) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "DELETE FROM customers WHERE customerId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(1, Integer.parseInt(tfCustid.getText()));

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
    //our array list for storing Customers
    ObservableList<Customer> data = FXCollections.observableArrayList();

    private void loadListView() {
        //start with clean list view
        lvCustomers.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from customers";
        try {
            Statement stmt = conn.createStatement();//creates statement object
            ResultSet rs = stmt.executeQuery(sql);//executes the statement, stores the return in rs
            while (rs.next()) {
                data.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));

            }
            lvCustomers.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}

