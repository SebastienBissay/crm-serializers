package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Product;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import groupe3.crm.service.IProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all products", nickname = "Get all products", response = Product.class)
    public ResponseEntity getAllProducts() {
        List<Product> products = this.productService.getAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(products));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @ApiOperation(value = "Returns the product with given id", nickname = "Get a product", response = Product.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Product> product;
        try {
            product = this.productService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(product.get()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new product", nickname = "Create a product")
    public ResponseEntity create(@RequestBody Product product) {
        try {
            this.productService.create(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully created.");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates the product with given id", nickname = "Update product")
    public ResponseEntity update(@RequestBody Product product, @PathVariable("id") Long id) {
        try {
            this.productService.update(product, id);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Product successfully modified.");
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the product with given id", nickname = "Delete product")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Product> product;
        try {
            product = this.productService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        this.productService.delete(id);
        return ResponseEntity.ok("Product successfully deleted.");
    }
}