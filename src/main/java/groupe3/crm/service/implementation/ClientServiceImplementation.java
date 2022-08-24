package groupe3.crm.service.implementation;

import groupe3.crm.model.Client;
import groupe3.crm.model.Operation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import groupe3.crm.repository.ClientRepository;
import groupe3.crm.repository.OperationRepository;
import groupe3.crm.service.IClientService;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class ClientServiceImplementation implements IClientService {

    private final ClientRepository clientRepository;
    private final OperationRepository operationRepository;

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository, OperationRepository operationRepository) {
        this.clientRepository = clientRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public void create(Client client, String user) {
        this.clientRepository.save(client);
        this.operationRepository.save(new Operation(user, "CREATE", Client.class.getSimpleName(), client.getId()));
    }

    @Override
    public void delete(Long id, String user) {
        this.clientRepository.deleteById(id);
        this.operationRepository.save(new Operation(user, "DELETE", Client.class.getSimpleName(), id));
    }

    @Override
    public void update(Client client, Long id, String user) throws NotFoundException {
        Client oldClient = this.clientRepository.findById(id).orElse(null);
        if (oldClient == null) {
            throw new NotFoundException();
        }
        oldClient.copy(client);
        this.clientRepository.save(oldClient);
        this.operationRepository.save(new Operation(user, "MODIFY", Client.class.getSimpleName(), id));
    }

    @Override
    public List<Client> getByName(String name) {
        return this.clientRepository.findByFirstNameContainingOrLastNameContainingAllIgnoringCase(name, name);
    }

}
