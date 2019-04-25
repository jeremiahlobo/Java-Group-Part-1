package Base;

import Core.DBHelper;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Main extends Application {

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button loginButton;

    @FXML
    void AllCustomerMouseClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Customer/customerPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AllPackagesMouseClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Package/Package.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSuccessfulLogin(){
        Stage stage = (Stage) loginButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void AllProductsMouseClick(MouseEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Product/productPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AllSuppliersMouseClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Supplier/supplierPage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionLogin(MouseEvent event) {
        String name = txtUser.getText();
        String pass = txtPass.getText();
        Boolean provided = false;
        if(!txtUser.getText().trim().isEmpty() && !txtPass.getText().trim().isEmpty())
        {
            provided = true;
        }

        String sql = "SELECT * FROM CUSTOMERS WHERE username =?;";
        String sql2 = "SELECT * FROM customers WHERE password=? and username=?;";

        Connection conn = DBHelper.getConnection();//initialize connection

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next() || provided == false) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correct user/pass please.");
                    alert.showAndWait();
                }
                else{
                PreparedStatement stmt2 = conn.prepareStatement(sql2);

                stmt2.setString(2,name);
                stmt2.setString(1,pass);

                ResultSet resultSet2 = stmt2.executeQuery();
                    if (!resultSet2.next()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Enter correct user/pass please.");
                        alert.showAndWait();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successful login.");
                        alert.showAndWait();

                        try {

                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basePage.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Dashboard");
                            stage.setScene(new Scene(root, 1000, 700));
                            stage.show();

                            onSuccessfulLogin();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
