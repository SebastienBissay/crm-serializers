package groupe3.crm.service.implementation;

import groupe3.crm.model.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import groupe3.crm.repository.ClientRepository;
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

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
    public void create(Client client) {
        this.clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public void update(Client client, Long id) throws NotFoundException {
        Client oldClient = this.clientRepository.findById(id).orElse(null);
        if (oldClient == null) {
            throw new NotFoundException();
        }
        oldClient.copy(client);
        this.clientRepository.save(oldClient);
    }

    @Override
    public List<Client> getByName(String name) {
        return this.clientRepository.findByFirstNameContainingOrLastNameContainingAllIgnoringCase(name, name);
    }

}
