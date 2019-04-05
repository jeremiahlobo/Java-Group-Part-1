package Customer;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPage<Customer> implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadData();
    }

    private void initTable(){
        initCols();
    }


    private void initCols(){
        col_CustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_FirstName.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        col_LastName.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        col_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        col_City.setCellValueFactory(new PropertyValueFactory<>("City"));
        col_Province.setCellValueFactory(new PropertyValueFactory<>("Prov"));
        col_Postal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
        col_Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
        col_HomePhone.setCellValueFactory(new PropertyValueFactory<>("HPhone"));
        col_BusPhone.setCellValueFactory(new PropertyValueFactory<>("Bphone"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        col_AgentId.setCellValueFactory(new PropertyValueFactory<>("Agid"));
        editableCols();
    }

    private void editableCols() {

        col_FirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        col_LastName.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Address.setCellFactory(TextFieldTableCell.forTableColumn());
        col_City.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Province.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Postal.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Country.setCellFactory(TextFieldTableCell.forTableColumn());
        col_HomePhone.setCellFactory(TextFieldTableCell.forTableColumn());
        col_BusPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Email.setCellFactory(TextFieldTableCell.forTableColumn());
        col_AgentId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col_FirstName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustName(e.getNewValue());

            tvCustomers.setEditable(true);

        });//editablecols end

    }

    //   col_CustId.setCellValueFactory(new PropertyValueFactory<>("id"));
    //        col_FirstName.setCellValueFactory(new PropertyValueFactory<>("Fname"));
    //        col_LastName.setCellValueFactory(new PropertyValueFactory<>("Lname"));
    //        col_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
    //        col_City.setCellValueFactory(new PropertyValueFactory<>("City"));
    //        col_Province.setCellValueFactory(new PropertyValueFactory<>("Prov"));
    //        col_Postal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
    //        col_Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
    //        col_HomePhone.setCellValueFactory(new PropertyValueFactory<>("HPhone"));
    //        col_BusPhone.setCellValueFactory(new PropertyValueFactory<>("Bphone"));
    //        col_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
    //        col_AgentId.setCellValueFactory(new PropertyValueFactory<>("Agid"));
    private void loadData()
    {
        ObservableList<Customer> data_table = FXCollections.observableArrayList();
        for (int i = 0; i<7; i++){
            data_table.add(new Customer(String.valueOf(i), "id "+i, "Fname "+i, "Lname "+i,"Address "+i,"City "+i,"Prov "+i,"Postal "+i,"Country "+i,"HPhone "+i,"Bphone "+i,"Email "+i,"Agid "+i, new Button("update")));
        }

        tvCustomers.setItems(data_table);
    }


}
