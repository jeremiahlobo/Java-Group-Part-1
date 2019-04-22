package Product;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Core.DBHelper;
import Core.Product;
import Model.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProductPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtProdId;

    @FXML
    private TextField txtProdName;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNew;

    @FXML
    private ListView<Product> lvProducts;

    @FXML
    private Button btnBack;

    @FXML
    private void OnBackClick() {
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void selectListItem(MouseEvent event) {
        Product prod = lvProducts.getSelectionModel().getSelectedItem();
        int prodIdTemp = prod.getProductId();
        String Fname = prod.getProdName();

        txtProdId.setText(String.valueOf(prodIdTemp));
        txtProdName.setText(Fname);
    }

    ObservableList<Product> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        btnSave.setDisable(true);
        assert txtProdId != null : "fx:id=\"txtProdId\" was not injected: check your FXML file 'productPage.fxml'.";
        assert txtProdName != null : "fx:id=\"txtProdName\" was not injected: check your FXML file 'productPage.fxml'.";

        txtProdId.setEditable(false);
//        txtProdName.setEditable(false);
        //load the list view
        loadListView();
    }

    @FXML
    void OnDeleteClick(ActionEvent event) {

        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "DELETE FROM Products WHERE ProductId=?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtProdId.getText()));

            int numRows = stmt.executeUpdate();


            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were deleted. Contact Tech Support");
                alert.showAndWait();
            } else {
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Row was deleted.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred");
            alert.showAndWait();

        }
    }

    public void OnActionNewClick(ActionEvent actionEvent) {

    Boolean passes = false;

        if(txtProdName.getText().

    matches("^[a-zA-Z]+$"))

    {
        passes = true;
    }

        if(passes ==true)

    {

        Connection conn = DBHelper.getConnection();
        String insertsql = "INSERT Products set ProductId=?, ProdName=?;";
        int maxProductId = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(insertsql);

            stmt.setInt(1, maxProductId);
            stmt.setString(2, txtProdName.getText());

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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Try using the save command instead.");
            alert.showAndWait();
        }
    }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bad input. Please insert a string.");
            alert.showAndWait();
        }
}
    }

    public void OnActionSaveClick(ActionEvent actionEvent) {
        Boolean passes = false;

        if (txtProdName.getText().

                matches("^[a-zA-Z]+$")) {
            passes = true;
        }

        if (passes == true) {
            Connection conn = DBHelper.getConnection();//initialize connection again
            String sql = "UPDATE Products set ProdName=? where ProductId=?;";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(2, Integer.parseInt(txtProdId.getText()));
                stmt.setString(1, txtProdName.getText());

                int numRows = stmt.executeUpdate();


                if (numRows == 0) {
                    //create a new alert
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated. Contact Tech Support");
                    alert.showAndWait();
                } else {
                    //show rows were updated
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Rows were updated.");
                    success.showAndWait();
                    loadListView();
                }
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Bad insert. Please enter a String.");
            success.showAndWait();
        }
    }

    ObservableList<Product> data = FXCollections.observableArrayList();

    private void loadListView() {
        //start with clean list view
        lvProducts.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from products";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                data.add(new Product(rs.getInt(1), rs.getString(2)));

            }
            lvProducts.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

