package Package;
//import models
import Base.Validator;
import Core.DBHelper;
import Core.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;


public class PackageController {
//Author: Helen Lin

    @FXML
    private ListView<Package> lvPackages;

    @FXML
    private TextField txtPackageId;

    @FXML
    private TextField txtPackageName;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtBasePrice;

    @FXML
    private TextField txtComission;

    @FXML
    private TextField txtDescription;

    @FXML
    private Button btnBack;


    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSubmit;

    @FXML
    public Label nameLabel;

    @FXML
    public Label dateLabel;

    @FXML
    public Label endDateLabel;

    @FXML
    public Label comissionLabel;

    @FXML
    public Label priceLabel;

    @FXML
    public Label descriptionLabel;
    @FXML
    private void OnBackClick(){
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void feildFull(){
        Boolean name = Validator.textFieldnotEmpty(txtPackageName, nameLabel, "Name is required!");
        Boolean startDate = Validator.textFieldnotEmpty(txtStartDate, dateLabel, "Date is required!");
        Boolean endDate = Validator.textFieldnotEmpty(txtEndDate, endDateLabel, "Date is required!");
        Boolean comission = Validator.textFieldnotEmpty(txtComission, comissionLabel, "Comission is required!");
        Boolean price = Validator.textFieldnotEmpty(txtBasePrice, priceLabel, "Price is required!");
        Boolean description = Validator.textFieldnotEmpty(txtDescription, descriptionLabel, "Description is required!");
    }

    @FXML
    void selectListItem(MouseEvent event) {

        Package pack = lvPackages.getSelectionModel().getSelectedItem();
        int PkId = pack.getPackageId();
        String PkName = pack.getPkgName();
        String PkStart = pack.getPkgStartDate();
        String PkEnd = pack.getPkgEndDate();
        String PkDesc = pack.getPkgDesc();
        double PkBase = pack.getPkgBasePrice();
        double PkAg = pack.getPkgAgencyCommission();

        //change
        txtPackageId.setText(String.valueOf(PkId));
        txtPackageName.setText(PkName);
        txtStartDate.setText(PkStart);
        txtEndDate.setText(PkEnd);
        txtDescription.setText(PkDesc);
        txtBasePrice.setText(String.valueOf(PkBase));
        txtComission.setText(String.valueOf(PkAg));

        btnNew.setVisible(false);
        btnNew.setDisable(false);
        btnSubmit.setVisible(false);
        btnSubmit.setDisable(false);

        btnEdit.setDisable(false);
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnEdit.setVisible(true);
        btnSave.setVisible(true);
        btnDelete.setVisible(true);
    }

    public static ObservableList<Package> pkList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert lvPackages != null : "fx:id=\"lvPackages\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtPackageName != null : "fx:id=\"txtPackageName\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtBasePrice != null : "fx:id=\"txtBasePrice\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtComission != null : "fx:id=\"txtComission\" was not injected: check your FXML file 'Package.fxml'.";
        assert txtDescription != null : "fx:id=\"txtDescription\" was not injected: check your FXML file 'Package.fxml'.";

        //btnSave.setDisable(true);

        txtPackageId.setEditable(false);
        txtPackageName.setEditable(false);
        txtStartDate.setEditable(false);
        txtEndDate.setEditable(false);
        txtDescription.setEditable(false);
        txtBasePrice.setEditable(false);
        txtComission.setEditable(false);

        btnSubmit.setVisible(false);
        btnSave.setVisible(false);
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnSubmit.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        //load the list view
        loadListView();
    }


    @FXML
    void OnEditClick(ActionEvent event) {
        txtPackageId.setEditable(true);
        txtPackageName.setEditable(true);
        txtStartDate.setEditable(true);
        txtEndDate.setEditable(true);
        txtDescription.setEditable(true);
        txtBasePrice.setEditable(true);
        txtComission.setEditable(true);
        btnSave.setDisable(false);
        btnSave.setVisible(true);
    }

    @FXML
    void OnDeleteClick(ActionEvent event) {

        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "DELETE FROM packages WHERE PackageId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1
            stmt.setInt(1, Integer.parseInt(txtPackageId.getText()));

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

                //in neutral buttons
                btnNew.setVisible(true);
                btnNew.setDisable(false);
                btnSubmit.setVisible(false);
                btnSubmit.setDisable(true);
                btnEdit.setDisable(true);
                btnSave.setDisable(true);
                btnDelete.setDisable(true);
                btnEdit.setVisible(false);
                btnSave.setVisible(false);
                btnDelete.setVisible(false);
                //in neutral fields
                txtPackageId.clear();
                txtPackageName.clear();
                txtStartDate.clear();
                txtEndDate.clear();
                txtDescription.clear();
                txtBasePrice.clear();
                txtComission.clear();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occured.");
            alert.showAndWait();
        }

    }

    public void OnNewClick(ActionEvent actionEvent) {
        //clear fields

        txtPackageId.clear();
        txtPackageName.clear();
        txtStartDate.clear();
        txtEndDate.clear();
        txtDescription.clear();
        txtBasePrice.clear();
        txtComission.clear();

        //Editable

        txtPackageName.setEditable(true);
        txtStartDate.setEditable(true);
        txtEndDate.setEditable(true);
        txtDescription.setEditable(true);
        txtBasePrice.setEditable(true);
        txtComission.setEditable(true);
        //turn on submit button
        btnSubmit.setDisable(false);
        btnSubmit.setVisible(true);

    }

    public void OnSubmitClick(ActionEvent actionEvent){
        feildFull();
        Boolean passes = false;
        /*if (txtPackageName.getText().matches("^[a-zA-Z]+$")&&Validator.textFieldNotEmpty(TextField){
            passes = true;
        }

        if (passes == true) {*/


            Connection conn = DBHelper.getConnection();//initialize connection again
            //String maxProductIDsql = "SELECT MAX(PackageId)+1 FROM Packages";
            //System.out.println(maxProductIDsql);
            //int translation = Integer.parseInt(maxProductIDsql);
            //int newInt = translation + 1;
            String insertsql = "INSERT Packages set PkgName=?, PkgStartDate=?, PkgEndDate=?, PkgDesc=?, PkgBasePrice=?, PkgAgencyCommission=?;";
            //int maxPackageId = 0;
            try {
                //precompile the statement

                PreparedStatement stmt = conn.prepareStatement(insertsql);

                stmt.setString(1, txtPackageName.getText());
                stmt.setString(2, txtStartDate.getText());
                stmt.setString(3, txtEndDate.getText());
                stmt.setString(4, txtDescription.getText());
                stmt.setString(5, txtBasePrice.getText());
                stmt.setString(6, txtComission.getText());

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

                    //in neutral buttons
                    btnNew.setVisible(true);
                    btnNew.setDisable(false);
                    btnSubmit.setVisible(false);
                    btnSubmit.setDisable(true);
                    btnEdit.setDisable(true);
                    btnSave.setDisable(true);
                    btnDelete.setDisable(true);
                    btnEdit.setVisible(false);
                    btnSave.setVisible(false);
                    btnDelete.setVisible(false);

                    //in neutral fields
                    txtPackageId.clear();
                    txtPackageName.clear();
                    txtStartDate.clear();
                    txtEndDate.clear();
                    txtDescription.clear();
                    txtBasePrice.clear();
                    txtComission.clear();
                }
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Try using the save command instead.");
                alert.showAndWait();
            }
        /*}else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Try using the save command instead.");
            alert.showAndWait();
        }*/}


    @FXML
    void OnActionEditClick(ActionEvent event) {
        btnEdit.setDisable(true);
        txtDescription.setEditable(true);
        txtComission.setEditable(true);
        txtEndDate.setEditable(true);
        txtStartDate.setEditable(true);
        txtPackageName.setEditable(true);
        txtPackageId.setEditable(true);
        txtBasePrice.setEditable(true);
        btnSave.setDisable(false); //enable the save button
        btnSave.setVisible(true);//show the save button

    }

    public void OnActionSubmitClick(ActionEvent actionEvent) {
        feildFull();
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE Packages set PkgName=?, PkgStartDate=?,PkgEndDate=?, PkgDesc=?,PkgBasePrice=?,PkgAgencyCommission=? where PackageId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1


            stmt.setString(1, txtPackageName.getText());
            stmt.setString(1, txtStartDate.getText());
            stmt.setString(1, txtEndDate.getText());
            stmt.setString(1, txtDescription.getText());
            stmt.setInt(2, Integer.parseInt(txtBasePrice.getText()));
            stmt.setInt(2, Integer.parseInt(txtComission.getText()));

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

                //in neutral buttons
                btnNew.setVisible(true);
                btnNew.setDisable(false);
                btnSubmit.setVisible(false);
                btnSubmit.setDisable(true);
                btnEdit.setDisable(true);
                btnSave.setDisable(true);
                btnDelete.setDisable(true);
                btnEdit.setVisible(false);
                btnSave.setVisible(false);
                btnDelete.setVisible(false);

                //in neutral fields
                txtPackageId.clear();
                txtPackageName.clear();
                txtStartDate.clear();
                txtEndDate.clear();
                txtDescription.clear();
                txtBasePrice.clear();
                txtComission.clear();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OnSaveClick(ActionEvent actionEvent) {
        feildFull();
        Connection conn = DBHelper.getConnection();//initialize connection again
        String sql = "UPDATE packages set PkgName=?, PkgStartDate=?,PkgEndDate=?, PkgDesc=?,PkgBasePrice=?,PkgAgencyCommission=? where PackageId=?;";
        try {
            //precompile the statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            //these parameters equate to the sql string above, dont start at 0, start at 1

            stmt.setString(1, txtPackageName.getText());
            stmt.setString(2, txtStartDate.getText());
            stmt.setString(3, txtEndDate.getText());
            stmt.setString(4, txtDescription.getText());
            stmt.setDouble(5, Double.parseDouble(txtBasePrice.getText()));
            stmt.setDouble(6, Double.parseDouble(txtComission.getText()));
            stmt.setInt(7,Integer.parseInt(txtPackageId.getText()));
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

                //in neutral buttons
                btnNew.setVisible(true);
                btnNew.setDisable(false);
                btnSubmit.setVisible(false);
                btnSubmit.setDisable(true);
                btnEdit.setDisable(true);
                btnSave.setDisable(true);
                btnDelete.setDisable(true);
                btnEdit.setVisible(false);
                btnSave.setVisible(false);
                btnDelete.setVisible(false);

                //in neutral fields
                txtPackageId.clear();
                txtPackageName.clear();
                txtStartDate.clear();
                txtEndDate.clear();
                txtDescription.clear();
                txtBasePrice.clear();
                txtComission.clear();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtPackageId.setEditable(false);
        txtPackageName.setEditable(false);
        txtStartDate.setEditable(false);
        txtEndDate.setEditable(false);
        txtDescription.setEditable(false);
        txtBasePrice.setEditable(false);
        txtComission.setEditable(false);
    }



    ObservableList<Package> data = FXCollections.observableArrayList();

    private void loadListView() {
        //start with clean list view
        lvPackages.getItems().clear();
        Connection conn = DBHelper.getConnection();
        String sql = "select * from packages";
        try {
            Statement stmt = conn.createStatement();//creates statement object
            ResultSet rs = stmt.executeQuery(sql);//executes the statement, stores the return in rs
            while (rs.next()) {
                data.add(new Package(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6),rs.getInt(7)));

            }
            lvPackages.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
