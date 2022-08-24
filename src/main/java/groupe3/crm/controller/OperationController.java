package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.ListLength;
import groupe3.crm.model.Operation;
import groupe3.crm.service.IOperationService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/history")
public class OperationController {
    
    private final IOperationService operationService;
    
    @Autowired
    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }
    
     @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all operations", nickname = "Get all operations", response = Operation.class)
    public ResponseEntity getAllClients() {
        List<Operation> operations = this.operationService.getAll();
        try {
            ListLength l = new ListLength(operations.size());
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(l) + new ObjectMapper().writeValueAsString(operations));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }
    
}
