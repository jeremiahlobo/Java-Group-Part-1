package Customer;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.converter.IntegerStringConverter;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;


//Controller for Customer
//Author: Helen Lin
public class CustomerPage{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TableColumn<Customer,Integer> col_CustId;

    @FXML
    private TableColumn<Customer,String> col_FirstName;

    @FXML
    private TableColumn<Customer,String> col_LastName;

    @FXML
    private TableColumn<Customer,String> col_Address;

    @FXML
    private TableColumn<Customer,String> col_City;

    @FXML
    private TableColumn<Customer,String> col_Province;

    @FXML
    private TableColumn<Customer,String> col_Postal;

    @FXML
    private TableColumn<Customer,String> col_Country;

    @FXML
    private TableColumn<Customer,String> col_HomePhone;

    @FXML
    private TableColumn<Customer,String> col_BusPhone;

    @FXML
    private TableColumn<Customer,String> col_Email;

    @FXML
    private TableColumn<Customer,Integer> col_AgentId;

    @FXML
    private TableColumn<Customer,Button> col_update;

    public static ObservableList<Customer> data = FXCollections.observableArrayList();


    @FXML
     void initialize() {
         assert tvCustomers != null : "fx:id=\"tvCustomers\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_CustId != null : "fx:id=\"col_CustId\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_FirstName != null : "fx:id=\"col_FirstName\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_LastName != null : "fx:id=\"col_LastName\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_Address != null : "fx:id=\"col_Address\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_City != null : "fx:id=\"col_City\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_Province != null : "fx:id=\"col_Province\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_Postal != null : "fx:id=\"col_Postal\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_Country != null : "fx:id=\"col_Country\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_HomePhone != null : "fx:id=\"col_HomePhone\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_BusPhone != null : "fx:id=\"col_BusPhone\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_Email != null : "fx:id=\"col_Email\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_AgentId != null : "fx:id=\"col_AgentId\" was not injected: check your FXML file 'customerPage.fxml'.";
         assert col_update != null : "fx:id=\"col_update\" was not injected: check your FXML file 'customerPage.fxml'.";

        initCols();

    }
    //populate the table view
    private void initCols(){

            try {
                Class.forName("com.mysql.jdbc.Driver");//get the driver from the connection we added

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");
                String sql = "select * from Customers";
                Statement stmt = conn.createStatement(); //make sure for statement you pick java.sql
                ResultSet rs = stmt.executeQuery(sql);//use executeQuery as it will return a result set, also we imported ResultSet class and named it rs
                while (rs.next())//move the cursor to new row in result set
                {
                    data.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11), rs.getInt(12)));
                }
                //green quote parameters have to be the exact same as the Customer class variables
                col_CustId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
                col_FirstName.setCellValueFactory(new PropertyValueFactory<Customer,String>("custFirstName"));
                col_LastName.setCellValueFactory(new PropertyValueFactory<Customer,String>("custLastName"));
                col_Address.setCellValueFactory(new PropertyValueFactory<Customer,String>("custAddress"));
                col_City.setCellValueFactory(new PropertyValueFactory<Customer,String>("custCity"));
                col_Province.setCellValueFactory(new PropertyValueFactory<Customer,String>("custProv"));
                col_Postal.setCellValueFactory(new PropertyValueFactory<Customer,String>("custPostal"));
                col_Country.setCellValueFactory(new PropertyValueFactory<Customer,String>("custCountry"));
                col_HomePhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("custHomePhone"));
                col_BusPhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("custBusPhone"));
                col_Email.setCellValueFactory(new PropertyValueFactory<Customer,String>("custEmail"));
                col_AgentId.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("agentId"));
               //button
                col_update.setCellValueFactory(new PropertyValueFactory<>("update"));

            tvCustomers.setItems(data);
            tvCustomers.setEditable(true);


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            editableCols();
    }

    public void editableCols() {
        col_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        col_FirstName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustFirstName(e.getNewValue());

        }); //end of setonEditCommit

        //
        col_LastName.setCellFactory(TextFieldTableCell.forTableColumn());

        col_LastName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustLastName(e.getNewValue());

        }); //end of setonEditCommit
        //
        col_Address.setCellFactory(TextFieldTableCell.forTableColumn());

        col_Address.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustAddress(e.getNewValue());

        }); //end of setonEditCommit
        col_City.setCellFactory(TextFieldTableCell.forTableColumn());

        col_City.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustCity(e.getNewValue());

        }); //end of setonEditCommit
        col_Province.setCellFactory(TextFieldTableCell.forTableColumn());

        col_Province.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustProv(e.getNewValue());

        }); //end of setonEditCommit

        col_Country.setCellFactory(TextFieldTableCell.forTableColumn());

        col_Country.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustCountry(e.getNewValue());

        }); //end of setonEditCommit

        col_HomePhone.setCellFactory(TextFieldTableCell.forTableColumn());

        col_HomePhone.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustHomePhone(e.getNewValue());

        }); //end of setonEditCommit

         col_BusPhone.setCellFactory(TextFieldTableCell.forTableColumn());

        col_BusPhone.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustBusPhone(e.getNewValue());

        }); //end of setonEditCommit

        col_Email.setCellFactory(TextFieldTableCell.forTableColumn());

        col_Email.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustEmail(e.getNewValue());

        }); //end of setonEditCommit

        col_AgentId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col_AgentId.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAgentId(e.getNewValue());

        });


        tvCustomers.setEditable(true);
    }//end of editableCols

}//end of class
