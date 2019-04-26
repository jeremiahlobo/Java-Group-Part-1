package Supplier;

import Base.Validator;
import Core.DBHelper;
import Core.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;


public class SupplierPage {

    @FXML
    private TextField txtSupplierId;

    @FXML
    private Label lblSupName;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

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

        btnNew.setVisible(true);
        btnNew.setDisable(false);
        btnSubmit.setVisible(true);
        btnSubmit.setDisable(false);

        btnDelete.setDisable(false);
        btnDelete.setVisible(true);
    }

    ObservableList<Supplier> suplist = FXCollections.observableArrayList();



    public void OnActionSaveClick(ActionEvent actionEvent) {

        Boolean name = Validator.textFieldnotEmpty(txtSupplierName, lblSupName, "Name is required!");
        if(Validator.matchString(txtSupplierName.getText()) == true) {

            Connection conn = DBHelper.getConnection();//initialize connection again
            String sql = "UPDATE suppliers set SupName=? where SupplierId=?;";

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
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bad input. Please enter a string.");
            alert.showAndWait();
        }
    }

    public void OnActionNewClick(ActionEvent actionEvent) {
        txtSupplierName.setEditable(true);

        btnSubmit.setVisible(true);
        btnSubmit.setDisable(false);

        btnEdit.setVisible(false);
        btnSave.setVisible(false);
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
    }

    public void onActionSubmitClick(ActionEvent actionEvent) {

        Boolean name = Validator.textFieldnotEmpty(txtSupplierName, lblSupName, "Name is required!");
            if(Validator.matchString(txtSupplierName.getText()) == true) {

            Connection conn = DBHelper.getConnection();//initialize connection again
                // String maxProductIDsql = "SELECT MAX(PackageId)+1 FROM Packages";
                int maxProduct = Integer.parseInt("SELECT MAX(SupplierId)+1 FROM Suppliers;");
            String insertsql = "INSERT Suppliers set SupplierId= max, SupName=?;";
            int maxSupplierId = 0;

            try {

                PreparedStatement stmt = conn.prepareStatement(insertsql);
                stmt.setInt(1, maxProduct);
                stmt.setString(2, txtSupplierName.getText());

                int numRows = stmt.executeUpdate();
                System.out.println(numRows);

                if (numRows == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No rows were inserted. Contact Tech Support");
                    alert.showAndWait();
                } else {
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
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bad input. Please enter a string.");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        assert txtSupplierId != null : "fx:id=\"txtSupplierId\" was not injected: check your FXML file 'SupplierPage.fxml'.";
        assert txtSupplierName != null : "fx:id=\"txtSupplierName\" was not injected: check your FXML file 'SupplierPage.fxml'.";

        btnSubmit.setVisible(false);
        btnSubmit.setDisable(true);
        btnSave.setVisible(false);
        btnSave.setDisable(true);
        btnEdit.setVisible(false);
        btnEdit.setDisable(true);
        btnDelete.setVisible(false);
        btnDelete.setDisable(true);


        txtSupplierName.setEditable(false);
        txtSupplierId.setEditable(true);
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
