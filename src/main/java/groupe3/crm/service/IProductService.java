package groupe3.crm.service;

import groupe3.crm.model.Product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastien Bissay
 */
public interface IProductService{
    
    public List<Product> getAll();
    
    public Optional<Product> getById(Long id);
    
    public void create(Product product);
    
    public void update(Product product, Long id);
    
    public void delete(Long id);
}
