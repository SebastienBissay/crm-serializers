package groupe3.crm.service;

import groupe3.crm.model.Client;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastien Bissay
 */
public interface IClientService {

    public List<Client> getAll();

    public Optional<Client> getById(Long id);

    public void create(Client client);

    public void update(Client client, Long id);

    public void delete(Long id);
    
    public List<Client> getByName(String name);
}
