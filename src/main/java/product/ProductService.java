package product;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(Connection connection) {
        this.productRepository = new ProductRepository(connection);
    }

    public void addProduct(Product product) throws SQLException {
        productRepository.addProduct(product);
    }

    public Product getProduct(int productId) throws SQLException {
        return productRepository.getProduct(productId);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productRepository.getAllProducts();
    }

    public void updateProduct(Product product) throws SQLException {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        productRepository.deleteProduct(productId);
    }
}
