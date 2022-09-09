package groupe3.crm.service.implementation;

import groupe3.crm.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import groupe3.crm.service.IAbstractService;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sebastien Bissay
 * @param <TEntity>
 * @param <TRepository>
 */
public abstract class AbstractServiceImplementation<TEntity extends AbstractEntity<TEntity>, TRepository extends JpaRepository<TEntity, Long>> implements IAbstractService<TEntity> {

    @Autowired
    protected TRepository tRepository;
    protected String tEntityClass;

    @Override
    public List<TEntity> getAll() {
        return this.tRepository.findAll();
    }

    @Override
    public Optional<TEntity> getById(Long id) {
        return this.tRepository.findById(id);
    }

    @Override
    public void create(TEntity tEntity) {
        this.tRepository.save(tEntity);
    }

    @Override
    public void update(Long id, TEntity tEntity) {
        TEntity existingEntity = this.tRepository.findById(id).orElse(null);
        if (existingEntity == null) {
            throw new NotFoundException();
        }
        existingEntity.copy(tEntity);
        this.tRepository.save(existingEntity);
    }

    @Override
    public void delete(Long id) {
        this.tRepository.deleteById(id);
    }
}
