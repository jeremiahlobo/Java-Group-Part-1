package Customer;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;


import javafx.beans.property.SimpleObjectProperty;

import java.sql.SQLOutput;

public class Customer {




    private SimpleIntegerProperty customerID;
    private SimpleStringProperty custFirstName;
    private SimpleStringProperty custLastName;
    private SimpleStringProperty custAddress;
    private SimpleStringProperty custCity;
    private SimpleStringProperty custProv;
    private SimpleStringProperty custPostal;
    private SimpleStringProperty custCountry;
    private SimpleStringProperty custHomePhone;
    private SimpleStringProperty custBusPhone;
    private SimpleStringProperty custEmail;
    private SimpleIntegerProperty agentId;
    private Button update;

    //our customer
    public Customer(int customerID, String custFirstName, String custLastName, String custAddress, String custCity, String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone, String custEmail, int agentId) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.custFirstName = new SimpleStringProperty(custFirstName);
        this.custLastName = new SimpleStringProperty(custLastName);
        this.custAddress = new SimpleStringProperty(custAddress);
        this.custCity = new SimpleStringProperty(custCity);
        this.custProv = new SimpleStringProperty(custProv);
        this.custPostal = new SimpleStringProperty(custPostal);
        this.custCountry = new SimpleStringProperty(custCountry);
        this.custHomePhone = new SimpleStringProperty(custHomePhone);
        this.custBusPhone = new SimpleStringProperty(custBusPhone);
        this.custEmail = new SimpleStringProperty(custEmail);
        this.agentId = new SimpleIntegerProperty(agentId);
        this.update = new Button("update"); //this addition creates a button within the columns

      /*  update.setOnAction(e ->{
           CustomerPage.
        });*/

    }//end constructor

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public int getCustomerID() {
        return customerID.get();
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public String getCustFirstName() {
        return custFirstName.get();
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName.set(custFirstName);
    }

    public String getCustLastName() {
        return custLastName.get();
    }

    public void setCustLastName(String custLastName) {
        this.custLastName.set(custLastName);
    }

    public String getCustAddress() {
        return custAddress.get();
    }

    public void setCustAddress(String custAddress) {
        this.custAddress.set(custAddress);
    }

    public String getCustCity() {
        return custCity.get();
    }

    public void setCustCity(String custCity) {
        this.custCity.set(custCity);
    }

    public String getCustProv() {
        return custProv.get();
    }

    public void setCustProv(String custProv) {
        this.custProv.set(custProv);
    }

    public String getCustPostal() {
        return custPostal.get();
    }

    public void setCustPostal(String custPostal) {
        this.custPostal.set(custPostal);
    }

    public String getCustCountry() {
        return custCountry.get();
    }

    public void setCustCountry(String custCountry) {
        this.custCountry.set(custCountry);
    }

    public String getCustHomePhone() {
        return custHomePhone.get();
    }

    public void setCustHomePhone(String custHomePhone) {
        this.custHomePhone.set(custHomePhone);
    }

    public String getCustBusPhone() {
        return custBusPhone.get();
    }

    public void setCustBusPhone(String custBusPhone) {
        this.custBusPhone.set(custBusPhone);
    }

    public String getCustEmail() {
        return custEmail.get();
    }

    public void setCustEmail(String custEmail) {
        this.custEmail.set(custEmail);
    }

    public int getAgentId() {
        return agentId.get();
   }

    public void setAgentId(int agentId) {
        this.agentId.set(agentId);
    }

}