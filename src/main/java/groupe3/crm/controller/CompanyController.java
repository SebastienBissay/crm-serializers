package groupe3.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groupe3.crm.model.Client;
import groupe3.crm.model.Company;
import groupe3.crm.service.IClientService;
import groupe3.crm.service.ICompanyService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sebastien Bissay
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final ICompanyService companyService;
    private final IClientService clientService;

    @Autowired
    public CompanyController(ICompanyService companyService, IClientService clientService) {
        this.companyService = companyService;
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the list of all companies", nickname = "Get all companies", response = Company.class)
    public ResponseEntity getAllCompanys() {
        List<Company> companies = this.companyService.getAll();
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(companies));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @ApiOperation(value = "Returns the company with given id", nickname = "Get a company", response = Company.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Company> company;
        try {
            company = this.companyService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found.");
        }
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(company.get()));
        } catch (JsonProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new company", nickname = "Create a company")
    public ResponseEntity create(@RequestBody Company company) {
        try {
            Client client = this.clientService.getById(company.getClient().getId()).orElse(null);
            if (client == null) {
                throw new NotFoundException();
            }
            company.setClient(client);
            this.companyService.create(company);
        }catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Company successfully created.");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates the company with given id", nickname = "Update company")
    public ResponseEntity update(@RequestBody Company company, @PathVariable("id") Long id) {
        try {
            this.companyService.update(company, id);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        return ResponseEntity.ok("Company successfully modified.");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the company with given id", nickname = "Delete company")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Company> company;
        try {
            company = this.companyService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
        if (company.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found.");
        }
        this.companyService.delete(id);
        return ResponseEntity.ok("Company successfully deleted.");
    }
}
