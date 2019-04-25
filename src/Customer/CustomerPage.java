package Customer;

import Base.Validator;
import Core.Customer;
import Core.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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
    private Label agentLabel;

    @FXML
    private Label frstNameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label homePhoneLabel;

    @FXML
    private Label businessPhoneLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private Label provinceLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button btnBack;

    @FXML
    private void OnBackClick(){
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void validate() {
        Boolean name = Validator.textFieldnotEmpty(tfAgentId, agentLabel, "Name is required!");
        Boolean first = Validator.textFieldnotEmpty(tfAgentId, frstNameLabel, "Name is required!");
        Boolean adress = Validator.textFieldnotEmpty(tfAgentId, addressLabel, "Adress is required!");
        Boolean city = Validator.textFieldnotEmpty(tfAgentId, cityLabel, "City is required!");
        Boolean country = Validator.textFieldnotEmpty(tfAgentId, countryLabel, "Country is required!");
        Boolean phone = Validator.textFieldnotEmpty(tfAgentId, homePhoneLabel, "Phone is required!");
        Boolean busphone = Validator.textFieldnotEmpty(tfAgentId, businessPhoneLabel, "Phone is required!");
        Boolean lname = Validator.textFieldnotEmpty(tfAgentId, lastNameLabel, "Name is required!");
        Boolean postcode = Validator.textFieldnotEmpty(tfAgentId, postalCodeLabel, "Code is required!");
        Boolean prov = Validator.textFieldnotEmpty(tfAgentId, provinceLabel, "Province is required!");
        Boolean email = Validator.textFieldnotEmpty(tfAgentId, emailLabel, "Email is required!");
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

        loadListView();





        ObservableList names =
                FXCollections.observableArrayList();
        ObservableList data =
                FXCollections.observableArrayList();


        ListView lvCustomers = new ListView(data);


        names.addAll(
                "Adam", "Alex", "Alfred", "Albert",
                "Brenda", "Connie", "Derek", "Donny",
                "Lynne", "Myrtle", "Rose", "Rudolph",
                "Tony", "Trudy", "Williams", "Zach"
        );

        for (int i = 0; i < 18; i++) {
            data.add("anonym");
        }

        lvCustomers.setItems(data);
        lvCustomers.setCellFactory(ComboBoxListCell.forListView(names));



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

        validate();
        try {
            Connection conn = DBHelper.getConnection();//initialize connection again

            String insertsql = "INSERT Customers set CustFirstName=?, CustLastName=?,CustAddress=?,CustCity=?,CustProv=?,CustPostal=?,CustCountry=?,CustHomePhone=?,CustBusPhone=?,CustEmail=?, AgentId=?, username=?, password=?";
            //precompile the statement

            PreparedStatement stmt = conn.prepareStatement(insertsql);

            String username="";


            if (matchString(tfCustFname.getText()) == true && matchString(tfCustFname.getText()) == true && matchString(tfCustAddress.getText()) == true && matchString(tfCustCity.getText()) == true
                    && matchProvince(tfCustProv.getText()) == true && matchPostalCode(tfCustPostal.getText()) == true && matchString(tfCustCountry.getText()) == true && matchPhoneNumber(tfCustHPhone.getText()) == true &&
                    matchPhoneNumber(tfCustBPhone.getText()) == true && matchEmail(tfCustEmail.getText()) == true) {

                int maxCustId = 0;
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


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bad value entered. Please check entered values.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred.");
            alert.showAndWait();
        }
    }
    @FXML
    void OnActionEditClick (ActionEvent event){
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
    void OnActionSaveClick (ActionEvent event){
        validate();
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
    void OnActionDeleteClick (ActionEvent event){
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
            } else {
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

    //tester list
    ObservableList names =
            FXCollections.observableArrayList();

    class XCell extends ListCell<Customer> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("  Print PDF  ");
        Customer lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //put what happens when you click button
                    /* we need to insert the cust id into the api to retrieve the data */

                    try {

                        /* set this property to the location of the cert file
                        System.setProperty("javax.net.ssl.trustStore","C:/Documents and Settings/bhattdr/Desktop/-.energystar.gov.der")


                        String username = "yy777PPP";
                        String password = "yy777PPP";
                        String userpass = "";*/

                        URL url = new URL("http://localhost:8080/api.travelexperts.com/rest/customersbookings/info/" + lastItem.getCustomerID());
//      URLConnection uc = url.openConnection();
                        HttpURLConnection uc = (HttpURLConnection) url.openConnection();


                        System.out.println("sending request...");

                        uc.setRequestMethod("GET");
                        uc.setAllowUserInteraction(false);
                        uc.setDoOutput(true);
                        uc.setRequestProperty("Content-type", "application/json");


                        System.out.println(uc.getRequestProperties());


                        int rspCode = uc.getResponseCode();

                        if (rspCode == 200) {
                            InputStream is = uc.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(isr);
                            System.out.println(isr);

                            String nextLine = br.readLine();
                            while (nextLine != null) {
                                System.out.println(nextLine);
                                nextLine = br.readLine();
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    //end of test
                    System.out.println(lastItem.getCustomerID());
                }
            });
        }


        @Override
        protected void updateItem(Customer item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item != null ? String.valueOf(item) : "<null>");
                setGraphic(hbox);
            }
        }
    }
    private void loadListView(){


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
            //sets the cell
            lvCustomers.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {
                @Override
                public ListCell<Customer> call(ListView<Customer> param) {
                    return new XCell();
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
