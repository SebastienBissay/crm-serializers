package groupe3.crm.service;

import groupe3.crm.model.Client;
import groupe3.crm.repository.ClientRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastien Bissay
 */
@Service
@Transactional
public class ClientServiceImplementation extends AbstractServiceImplementation<Client, ClientRepository> {

    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clienttRepository) {
        super();
        this.clientRepository = clienttRepository;
        this.tEntityClass = Client.class.getSimpleName();
    }

    public List<Client> getByName(String name) {
        return this.clientRepository.findByFirstNameContainingOrLastNameContainingAllIgnoringCase(name, name);
    }
}
