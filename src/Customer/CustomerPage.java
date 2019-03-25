package Customer;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.text.TableView;

public class CustomerPage {

    @FXML
    private TableView<?> tvCustomers;

    @FXML
    private TableColumn<?, ?> col_CustId;

    @FXML
    private TableColumn<?, ?> col_FirstName;

    @FXML
    private TableColumn<?, ?> col_lastName;

    @FXML
    private TableColumn<?, ?> col_Address;

    @FXML
    private TableColumn<?, ?> col_Province;

    @FXML
    private TableColumn<?, ?> col_Postal;

    @FXML
    private TableColumn<?, ?> col_Country;

    @FXML
    private TableColumn<?, ?> col_HomePhone;

    @FXML
    private TableColumn<?, ?> col_BusPhone;

    @FXML
    private TableColumn<?, ?> col_Email;

    @FXML
    private TableColumn<?, ?> col_AgentId;

}
