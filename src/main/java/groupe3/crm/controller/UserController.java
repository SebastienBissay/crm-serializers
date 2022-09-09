package groupe3.crm.controller;

import groupe3.crm.model.Client;
import groupe3.crm.model.Order;
import groupe3.crm.model.User;
import groupe3.crm.repository.UserRepository;
import groupe3.crm.service.implementation.UserServiceImplementation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController extends AbstractController<User, UserRepository, UserServiceImplementation>{
    
    @Autowired
    public UserController(UserServiceImplementation userService) {
        super(userService);
        this.tEntityClass = Order.class.getSimpleName();
    }
    
        @ApiOperation(value = "Returns the list of all users", nickname = "Get all users", response = User.class)
    @Override
    public ResponseEntity getAll() {
        return super.getAll();
    }

    @ApiOperation(value = "Returns the user with given id", nickname = "Get an user", response = User.class)
    @Override
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @ApiOperation(value = "Creates a new order", nickname = "Create an order")
    @Override
    public ResponseEntity create(@RequestBody User user) {
        return super.create(user);
    }

    @ApiOperation(value = "Updates the user with given id", nickname = "Update user", response = User.class)
    @Override
    public ResponseEntity update(@RequestBody User user, @PathVariable("id") Long id) {
        return super.update(user, id);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the user with given id", nickname = "Delete user")
    @Override
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}
