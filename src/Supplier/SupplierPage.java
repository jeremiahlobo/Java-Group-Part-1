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
<<<<<<< HEAD
        Connection conn = DBHelper.getConnection();
        String sql = "UPDATE Products set SupName=? where SupplierId=?;";
=======
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE suppliers set SupName=? where SupplierId=?;";
>>>>>>> master
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, Integer.parseInt(txtSupplierId.getText()));
            stmt.setString(1, txtSupplierName.getText());
            int numRows = stmt.executeUpdate();

            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were updated. Contact Tech Support");
                alert.showAndWait();
            } else {
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Rows were updated.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OnActionNewClick(ActionEvent actionEvent) {
<<<<<<< HEAD
        Connection conn = DBHelper.getConnection();
        String insertsql = "INSERT Products set ProductId=?, ProdName=?;";
        int maxProductId=0;
=======
        Connection conn = DBHelper.getConnection();//initialize connection again
        String insertsql = "INSERT Suppliers set SupplierId=?, SupName=?;";
        int maxSupplierId=0;
>>>>>>> master
        try {

            PreparedStatement stmt = conn.prepareStatement(insertsql);
            stmt.setInt(1, maxSupplierId);
            stmt.setString(2, txtSupplierName.getText());

            int numRows = stmt.executeUpdate();
            System.out.println(numRows);

            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were inserted. Contact Tech Support");
                alert.showAndWait();
            }
            else{
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
        assert txtSupplierId != null : "fx:id=\"txtSupplierId\" was not injected: check your FXML file 'SupplierPage.fxml'.";
        assert txtSupplierName != null : "fx:id=\"txtSupplierName\" was not injected: check your FXML file 'SupplierPage.fxml'.";

        txtSupplierId.setEditable(false);
        loadListView();
    }

    ObservableList<Supplier> data = FXCollections.observableArrayList();

    private void loadListView() {
        lvSupplier.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from suppliers";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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

        Connection conn = DBHelper.getConnection();
        String sql = "DELETE FROM Suppliers WHERE SupplierId=?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtSupplierId.getText()));
            int numRows = stmt.executeUpdate();

            if (numRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were deleted. Contact Tech Support");
                alert.showAndWait();
            }
            else{
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Success. Row was deleted.");
                success.showAndWait();
                loadListView();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
