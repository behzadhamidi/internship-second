package product;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private ProductService productService;

    public ProductController(Connection connection) {
        this.productService = new ProductService(connection);
    }

    public void addProduct(Product product) throws SQLException {
        productService.addProduct(product);
    }

    public Product getProduct(int productId) throws SQLException {
        return productService.getProduct(productId);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productService.getAllProducts();
    }

    public void updateProduct(Product product) throws SQLException {
        productService.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        productService.deleteProduct(productId);
    }
}
