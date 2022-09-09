package groupe3.crm.service;

import groupe3.crm.model.AbstractEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastien Bissay
 */
public abstract interface IAbstractService<TEntity extends AbstractEntity<TEntity>> {
    
    public List<TEntity> getAll();
    
    public Optional<TEntity> getById(Long Id);
    
    public void create(TEntity tEntity);
    
    public void update(Long id, TEntity tEntity);
    
    public void delete(Long id);
}
