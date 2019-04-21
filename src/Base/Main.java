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
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    @FXML
    private Button loginButton;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

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


        //Parent root = new FXMLLoader(getClass().getResource("basePage.fxml"));
        //primaryStage.setTitle("Dashboard");
        //primaryStage.setScene(new Scene(root, 650, 400));
        //primaryStage.show();


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

        try {
            Connection conn = DBHelper.getConnection();//initialize connection again
            Statement stmt = conn.createStatement();
            String name = txtUser.getText();
            String pass = txtPass.getText();
            String sql = "SELECT * FROM customers WHERE username='" + name + "' and password='" + pass + '"';
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                //create a new alert
                System.out.println("THis worked quite well");
//                try{
//                    String sql2 = "SELECT";
//                }
//                catch{}
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No rows were deleted. Contact Tech Support");
                alert.showAndWait();
            }
            else{
                //show rows were updated
                Alert failure = new Alert(Alert.AlertType.ERROR, "Wrong username please try again.");
                failure.showAndWait();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Try using the save command instead.");
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("basePage.fxml"));
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
