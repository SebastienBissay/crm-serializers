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

    public void create(Client client, String user);

    public void update(Client client, Long id, String user);

    public void delete(Long id, String user);
    
    public List<Client> getByName(String name);
}
