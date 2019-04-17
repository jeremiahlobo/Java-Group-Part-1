package Product;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProductModelTable> tvProd;

    @FXML
    private TableColumn<ProductModelTable, String> ProductId;

    @FXML
    private TableColumn<ProductModelTable, String> ProdName;


    ObservableList<ProductModelTable> oblist = FXCollections.observableArrayList();



    @FXML
    void initialize() {
        assert tvProd != null : "fx:id=\"tvProd\" was not injected: check your FXML file 'productPage.fxml'.";

        Connection con = null;
        try {
            con = DB.getConnection();
            ResultSet rs = con.createStatement().executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ProductId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        ProdName.setCellValueFactory(new PropertyValueFactory<>("ProdName"));
    }


}
