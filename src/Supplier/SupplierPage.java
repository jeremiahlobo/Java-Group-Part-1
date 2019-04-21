package Supplier;

import Core.DBHelper;
import Core.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;


public class SupplierPage {

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnNew;

    @FXML
    private javafx.scene.control.Button btnBack;

    @FXML
    private ListView<Supplier> lvSupplier;

    @FXML
    private void OnBackClick(){
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void selectListItem(MouseEvent event) {
        Supplier sup = lvSupplier.getSelectionModel().getSelectedItem();
        int prodIdTemp = sup.getSupplierId();
        String Fname = sup.getSupName();

        txtSupplierId.setText(String.valueOf(prodIdTemp));
        txtSupplierName.setText(Fname);
    }

    ObservableList<Supplier> suplist = FXCollections.observableArrayList();

    public void OnActionSaveClick(ActionEvent actionEvent) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE Products set SupName=? where SupplierId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(2, Integer.parseInt(txtSupplierId.getText()));
            stmt.setString(1, txtSupplierName.getText());

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
    }

    public void OnBackClick(ActionEvent actionEvent) {
        //window = primaryStage;
        //stage.close();
    }

    public void OnActionNewClick(ActionEvent actionEvent) {
        Connection conn = DBHelper.getConnection();//initialize connection again
        String insertsql = "INSERT Products set ProductId=?, ProdName=?;";
        int maxProductId=0;
        try {
            //precompile the statement

            PreparedStatement stmt = conn.prepareStatement(insertsql);
            stmt.setInt(1, maxProductId);
            stmt.setString(2, txtSupplierName.getText());

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
    }

    @FXML
    void initialize() {
        //btnSave.setDisable(true);
        assert txtSupplierId != null : "fx:id=\"txtSupplierId\" was not injected: check your FXML file 'SupplierPage.fxml'.";
        assert txtSupplierName != null : "fx:id=\"txtSupplierName\" was not injected: check your FXML file 'SupplierPage.fxml'.";

        txtSupplierId.setEditable(false);
        //load the list view
        loadListView();
    }

    ObservableList<Supplier> data = FXCollections.observableArrayList();

    private void loadListView() {
        //start with clean list view
        lvSupplier.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from products";
        try {
            Statement stmt = conn.createStatement();//creates statement object
            ResultSet rs = stmt.executeQuery(sql);//executes the statement, stores the return in rs
            while (rs.next()) {
                data.add(new Supplier(rs.getInt(1),rs.getString(2)));

            }
            lvSupplier.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void OnDeleteClick(ActionEvent event) {

        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "DELETE FROM Suppliers WHERE SupplierId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(1, Integer.parseInt(txtSupplierId.getText()));

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
            //alert.showAndWait();
        }

    }
}
