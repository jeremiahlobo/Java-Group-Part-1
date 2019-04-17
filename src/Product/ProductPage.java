package Product;

import Product.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.sql.*;

public class ProductPage {

    @FXML
    private TableView<Product> tvProd;

    @FXML
    private TableColumn<Product,Integer> col_ProductId;

    @FXML
    private TableColumn<Product,String> col_ProdName;

    public static ObservableList<Product> data = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert col_ProductId != null : "fx:id=\"ProductId\" was not injected: check your FXML file 'ProductPage.fxml'.";
        assert col_ProdName != null : "fx:id=\"ProdName\" was not injected: check your FXML file 'ProductPage.fxml'.";

        initCols();
    }

    private void initCols(){

        try {
            Class.forName("com.mysql.jdbc.Driver");//get the driver from the connection we added

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "harv", "password");
            String sql = "select * from Products";
            Statement stmt = conn.createStatement(); //make sure for statement you pick java.sql
            ResultSet rs = stmt.executeQuery(sql);//use executeQuery as it will return a result set, also we imported ResultSet class and named it rs
            while (rs.next())//move the cursor to new row in result set
            {
                data.add(new Product(rs.getInt(1),rs.getString(2)));
            }
            //green quote parameters have to be the exact same as the Customer class variables
            col_ProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ProductId"));
            col_ProdName.setCellValueFactory(new PropertyValueFactory<Product,String>("ProdName"));

            tvProd.setItems(data);
            tvProd.setEditable(true);


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        editableCols();
    }

    public void editableCols() {

        col_ProdName.setCellFactory(TextFieldTableCell.forTableColumn());

        col_ProdName.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setProdName(e.getNewValue());

        }); //end of setonEditCommit

        tvProd.setEditable(true);
    }//end of editableCols
}

