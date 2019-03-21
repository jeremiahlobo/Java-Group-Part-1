package Product;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ProductPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tvProd;

    @FXML
    void initialize() {
        assert tvProd != null : "fx:id=\"tvProd\" was not injected: check your FXML file 'productPage.fxml'.";

    }
}
