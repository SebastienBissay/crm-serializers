package groupe3.crm.service;

import groupe3.crm.model.Company;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastien Bissay
 */
public interface ICompanyService {
    
    public List<Company> getAll();
    
    public Optional<Company> getById(Long id);
    
    public void create(Company company);
    
    public void update(Company company, Long id);
    
    public void delete(Long id);
}
