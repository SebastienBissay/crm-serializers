package groupe3.crm.service.implementation;

import groupe3.crm.model.Company;
import groupe3.crm.repository.CompanyRepository;
import groupe3.crm.service.ICompanyService;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class CompanyServiceImplementation implements ICompanyService{
    
    private final CompanyRepository companyRepository;
    
    @Autowired
    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Optional<Company> getById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public void create(Company company) {
        this.companyRepository.save(company);
    }

    @Override
    public void update(Company company, Long id) throws NotFoundException{
        Company oldCompany = this.companyRepository.findById(id).orElse(null);
        if (oldCompany == null) {
            throw new NotFoundException();
        }
        oldCompany.copy(company);
        this.companyRepository.save(oldCompany);
    }

    @Override
    public void delete(Long id) {
        this.companyRepository.deleteById(id);
    }
    
}
