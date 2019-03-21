package Customer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class CustomerPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tvCustomers; //scenebuilder

    @FXML
    void initialize() {
        assert tvCustomers != null : "fx:id=\"tvCustomers\" was not injected: check your FXML file 'customerPage.fxml'.";

    }
}
