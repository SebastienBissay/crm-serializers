package groupe3.crm.service;

import groupe3.crm.model.Command;
import groupe3.crm.model.Product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastien Bissay
 */
public interface ICommandService {
    
    public List<Command> getAll();
    
    public Optional<Command> getById(Long id);
    
    public void create(Command command);
    
    public void addProduct(Long id, Product product);
    
    public void removeProduct(Long id, Product product);
    
    public void delete(Long id);
    
    public List<Command> getByLabel(String label);
}
