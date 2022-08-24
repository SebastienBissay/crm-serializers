package groupe3.crm.service.implementation;

import groupe3.crm.model.Operation;
import groupe3.crm.repository.OperationRepository;
import groupe3.crm.service.IOperationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Sebastien Bissay
 */
@Service
public class OperationServiceImplementation implements IOperationService{
    
    private final OperationRepository operationRepository;
    
    @Autowired
    public OperationServiceImplementation(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Operation> getAll() {
        return this.operationRepository.findAll();
    }
    
    
}
