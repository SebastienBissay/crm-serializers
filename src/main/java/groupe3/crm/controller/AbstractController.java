package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.AbstractEntity;
import groupe3.crm.service.AbstractServiceImplementation;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Sebastien Bissay
 * @param <TEntity>
 * @param <TRepository>
 * @param <TService>
 */
@CrossOrigin(origins = "*")
public abstract class AbstractController<TEntity extends AbstractEntity<TEntity>, TRepository extends JpaRepository<TEntity, Long>, TService extends AbstractServiceImplementation<TEntity, TRepository>> {

    protected TService tService;
    protected String tEntityClass;

    @Autowired
    public AbstractController(TService tService) {
        this.tService = tService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getAll() {
        List<TEntity> entities = this.tService.getAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(entities));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<TEntity> tEntity;
        try {
            tEntity = this.tService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (tEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.tEntityClass + " not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(tEntity.get()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity create(@RequestBody TEntity tEntity) {
        try {
            this.tService.create(tEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseEntity.EMPTY);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity update(@RequestBody TEntity tEntity, @PathVariable("id") Long id) {
        try {
            this.tService.update(id, tEntity);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok(this.tService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<TEntity> tEntity;
        try {
            tEntity = this.tService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (tEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.tEntityClass + " not found.");
        }
        this.tService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseEntity.EMPTY);
    }
}
