package groupe3.crm.service.implementation;

import groupe3.crm.model.Command;
import groupe3.crm.model.Product;
import groupe3.crm.repository.CommandRepository;
import groupe3.crm.service.ICommandService;
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
public class CommandServiceImplementation implements ICommandService{

    private CommandRepository commandRepository;
    
    @Autowired
    public CommandServiceImplementation(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }
    
    @Override
    public List<Command> getAll() {
        return this.commandRepository.findAll();
    }

    @Override
    public Optional<Command> getById(Long id) {
        return this.commandRepository.findById(id);
    }

    @Override
    public void create(Command command) {
        this.commandRepository.save(command);
    }

    @Override
    public void addProduct(Long id, Product product) throws NotFoundException{
        Command command = this.commandRepository.findById(id).orElse(null);
        if (command == null) {
            throw new NotFoundException();
        }
        command.addProduct(product);
        this.commandRepository.save(command);
    }

    @Override
    public void removeProduct(Long id, Product product) {
        Command command = this.commandRepository.findById(id).orElse(null);
        if (command == null) {
            throw new NotFoundException();
        }
        command.removeProduct(product);
        this.commandRepository.save(command);
    }

    @Override
    public void delete(Long id) {
        this.commandRepository.deleteById(id);
    }

    @Override
    public List<Command> getByLabel(String label) {
        return this.commandRepository.findByLabelContainingIgnoringCase(label);
    }
}
