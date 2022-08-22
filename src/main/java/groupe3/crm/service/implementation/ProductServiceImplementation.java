package groupe3.crm.service.implementation;

import groupe3.crm.model.Product;
import groupe3.crm.repository.ProductRepository;
import groupe3.crm.service.IProductService;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class ProductServiceImplementation implements IProductService{
    
    private ProductRepository productRepository;
    
    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void create(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void update(Product product, Long id) throws NotFoundException{
        Product oldProduct = this.productRepository.findById(id).orElse(null);
        if (oldProduct == null) {
            throw new NotFoundException();
        }
        oldProduct.copy(product);
        this.productRepository.save(oldProduct);
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }
    
}
