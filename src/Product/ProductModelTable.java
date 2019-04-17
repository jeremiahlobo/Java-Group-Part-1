package Product;

public class ProductModelTable {


    String ProductId, ProdName;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }



    public ProductModelTable(String productId, String prodName) {
        ProductId = productId;
        ProdName = prodName;
    }
}
