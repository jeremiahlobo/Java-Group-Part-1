package Product;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleIntegerProperty ProductId;
    private SimpleStringProperty ProdName;

    public int getProductId() {
        return ProductId.get();
    }

    public SimpleIntegerProperty productIdProperty() {
        return ProductId;
    }

    public void setProductId(int productId) {
        this.ProductId.set(productId);
    }

    public String getProdName() {
        return ProdName.get();
    }

    public SimpleStringProperty prodNameProperty() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        this.ProdName.set(prodName);
    }

    public Product(int productId, String prodName) {
        this.ProductId = new SimpleIntegerProperty(productId);
        this.ProdName = new SimpleStringProperty(prodName);
    }

}