package groupe3.crm.repository;

import groupe3.crm.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sebastien Bissay
 */
public interface CompanyRepository extends JpaRepository<Company, Long>{
    
}
