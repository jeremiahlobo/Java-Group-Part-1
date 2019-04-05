package Core;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;


import javafx.beans.property.SimpleObjectProperty;

public class Customer {

    Button update;

  /*  int customerId;

    String CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustPostal, CustCountry,CustHomePhone, CustBusPhone, CustEmail;
    int AgentId;

    //constructor
    public Customer(int customerId, String custFirstName, String custLastName, String custAddress, String custCity, String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone, String custEmail, int agentId) {
        this.customerId = customerId;
        CustFirstName = custFirstName;
        CustLastName = custLastName;
        CustAddress = custAddress;
        CustCity = custCity;
        CustProv = custProv;
        CustPostal = custPostal;
        CustCountry = custCountry;
        CustHomePhone = custHomePhone;
        CustBusPhone = custBusPhone;
        CustEmail = custEmail;
        AgentId = agentId;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustFirstName() {
        return CustFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        CustFirstName = custFirstName;
    }

    public String getCustLastName() {
        return CustLastName;
    }

    public void setCustLastName(String custLastName) {
        CustLastName = custLastName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getCustCity() {
        return CustCity;
    }

    public void setCustCity(String custCity) {
        CustCity = custCity;
    }

    public String getCustProv() {
        return CustProv;
    }

    public void setCustProv(String custProv) {
        CustProv = custProv;
    }

    public String getCustPostal() {
        return CustPostal;
    }

    public void setCustPostal(String custPostal) {
        CustPostal = custPostal;
    }

    public String getCustCountry() {
        return CustCountry;
    }

    public void setCustCountry(String custCountry) {
        CustCountry = custCountry;
    }

    public String getCustHomePhone() {
        return CustHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        CustHomePhone = custHomePhone;
    }

    public String getCustBusPhone() {
        return CustBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        CustBusPhone = custBusPhone;
    }

    public String getCustEmail() {
        return CustEmail;
    }

    public void setCustEmail(String custEmail) {
        CustEmail = custEmail;
    }

    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }*/


   private SimpleIntegerProperty customerID;
    private SimpleStringProperty CustFirstName;
    private SimpleStringProperty CustLastName;
    private SimpleStringProperty CustAddress;
    private SimpleStringProperty CustCity;
    private SimpleStringProperty CustProv;
    private SimpleStringProperty CustPostal;
    private SimpleStringProperty CustCountry;
    private SimpleStringProperty CustHomePhone;
    private SimpleStringProperty CustBusPhone;
    private SimpleStringProperty CustEmail;
    private SimpleIntegerProperty AgentId;

    public Customer(int customerID, String custFirstName, String custLastName, String custAddress, String custCity, String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone, String custEmail, int agentId) {
        this.customerID = SimpleIntegerProperty(customerID);
        CustFirstName = SimpleStringProperty(custFirstName);
        CustLastName = SimpleStringProperty(custLastName);
        CustAddress = SimpleStringProperty(custAddress);
        CustCity = SimpleStringProperty(custCity);
        CustProv = SimpleStringProperty(custProv);
        CustPostal = SimpleStringProperty(custPostal);
        CustCountry = SimpleStringProperty(custCountry);
        CustHomePhone = SimpleStringProperty(custHomePhone);
        CustBusPhone = SimpleStringProperty(custBusPhone);
        CustEmail = SimpleStringProperty(custEmail);
        AgentId = SimpleIntegerProperty(agentId);
    }

    public SimpleIntegerProperty getCustomerID() {
        return customerID;
    }

    public void setCustomerID(SimpleIntegerProperty customerID) {
        this.customerID = customerID;
    }

    public SimpleStringProperty getCustFirstName() {
        return CustFirstName;
    }

    public void setCustFirstName(SimpleStringProperty custFirstName) {
        CustFirstName = custFirstName;
    }

    public SimpleStringProperty getCustLastName() {
        return CustLastName;
    }

    public void setCustLastName(SimpleStringProperty custLastName) {
        CustLastName = custLastName;
    }

    public SimpleStringProperty getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(SimpleStringProperty custAddress) {
        CustAddress = custAddress;
    }

    public SimpleStringProperty getCustCity() {
        return CustCity;
    }

    public void setCustCity(SimpleStringProperty custCity) {
        CustCity = custCity;
    }

    public SimpleStringProperty getCustProv() {
        return CustProv;
    }

    public void setCustProv(SimpleStringProperty custProv) {
        CustProv = custProv;
    }

    public SimpleStringProperty getCustPostal() {
        return CustPostal;
    }

    public void setCustPostal(SimpleStringProperty custPostal) {
        CustPostal = custPostal;
    }

    public SimpleStringProperty getCustCountry() {
        return CustCountry;
    }

    public void setCustCountry(SimpleStringProperty custCountry) {
        CustCountry = custCountry;
    }

    public SimpleStringProperty getCustHomePhone() {
        return CustHomePhone;
    }

    public void setCustHomePhone(SimpleStringProperty custHomePhone) {
        CustHomePhone = custHomePhone;
    }

    public SimpleStringProperty getCustBusPhone() {
        return CustBusPhone;
    }

    public void setCustBusPhone(SimpleStringProperty custBusPhone) {
        CustBusPhone = custBusPhone;
    }

    public SimpleStringProperty getCustEmail() {
        return CustEmail;
    }

    public void setCustEmail(SimpleStringProperty custEmail) {
        CustEmail = custEmail;
    }

    public SimpleIntegerProperty getAgentId() {
        return AgentId;
   }

    public void setAgentId(SimpleIntegerProperty agentId) {
        AgentId = agentId;
    }

}