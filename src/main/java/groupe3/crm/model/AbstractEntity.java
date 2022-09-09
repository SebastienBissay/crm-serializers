package groupe3.crm.model;

/**
 *
 * @author Sebastien Bissay
 */
public abstract class AbstractEntity<TEntity> {
    
    public abstract Long getId();
    
    public abstract void copy(TEntity tEntity);
    
}
