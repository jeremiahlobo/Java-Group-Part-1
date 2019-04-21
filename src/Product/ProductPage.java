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
        //btnSave.setDisable(true);
        assert txtProdId != null : "fx:id=\"txtProdId\" was not injected: check your FXML file 'productPage.fxml'.";
        assert txtProdName != null : "fx:id=\"txtProdName\" was not injected: check your FXML file 'productPage.fxml'.";

        txtProdId.setEditable(false);
        //load the list view
        loadListView();
    }

    //@FXML
    //void OnActionEditClick(ActionEvent event) {
        //Need to be able to edit the Cust information here
        //when someone clicks edit, disable the edit button
        //OnActionEditClick is referenced in code properties of button
        //btnEdit.setDisable(true);
        //txtProdName.setEditable(true);
        //btnSave.setDisable(false); //enable the save button
    //}

    @FXML
    void OnDeleteClick(ActionEvent event) {

        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "DELETE FROM Products WHERE ProductId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(1, Integer.parseInt(txtProdId.getText()));

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


    public void OnActionNewClick(ActionEvent actionEvent) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        //String maxProductIDsql = "SELECT MAX(ProductId) FROM Products";
        String insertsql = "INSERT Products set ProductId=?, ProdName=?;";
        int maxProductId=0;
        try {
            //precompile the statement

            PreparedStatement stmt = conn.prepareStatement(insertsql);
            //PreparedStatement stmt2 = conn.prepareStatement(insertsql);
            //ResultSet rs =stmt2.executeQuery(maxProductIDsql);
            //System.out.println(rs);

//            while(rs.next()){
//                //System.out.println("MAX(user_id)="+rs.getInt("MAX(user_id)"));
//                maxProductId = rs.getInt("MAX(ProductId)") + 1;
//            }
            // close ResultSet rs
            //rs.close();

            //these parameters equate to the sql string above, dont start at 0, start at 1
            //stmt.setInt(1, Integer.parseInt(txtProdId.getText()));
            stmt.setInt(1, maxProductId);
            stmt.setString(2, txtProdName.getText());

            int numRows = stmt.executeUpdate();
            System.out.println(numRows);

            if (numRows == 0) {
                //create a new alert
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were inserted. Contact Tech Support");
                alert.showAndWait();
            }
            else{
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
//after they save, turn fields back to read only
        //txtProdName.setEditable(false);
        //txtProdId.setEditable(false);

        //enable the edit button again
        //btnEdit.setDisable(false);
    }

    public void OnActionSaveClick(ActionEvent actionEvent) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE Products set ProdName=? where ProductId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(2, Integer.parseInt(txtProdId.getText()));
            stmt.setString(1, txtProdName.getText());

            int numRows = stmt.executeUpdate();


            if (numRows == 0) {
                //create a new alert
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated. Contact Tech Support");
                alert.showAndWait();
            }
            else{
                //show rows were updated
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Rows were updated.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
//after they save, turn fields back to read only
        //txtProdName.setEditable(false);
        //txtProdId.setEditable(false);

        //enable the edit button again
        //btnEdit.setDisable(false);
    }


    ObservableList<Product> data = FXCollections.observableArrayList();

    private void loadListView() {
        //start with clean list view
        lvProducts.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from products";
        try {
            Statement stmt = conn.createStatement();//creates statement object
            ResultSet rs = stmt.executeQuery(sql);//executes the statement, stores the return in rs
            while (rs.next()) {
                data.add(new Product(rs.getInt(1), rs.getString(2)));

            }
            lvProducts.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //private TableView<?> tvProd;
}

