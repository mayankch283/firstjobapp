package com.mayankch283.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompany(@PathVariable long id) {
        Company company = companyService.findCompany(id);
        if (company!=null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id) {
        if (companyService.updateCompany(company, id)) {
            return new ResponseEntity("Company updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("No company found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity("Company created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        Company companyToBeDeleted = companyService.findCompany(id);
        if(companyToBeDeleted != null){
            companyService.deleteCompany(companyToBeDeleted);
            return new ResponseEntity("Company deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Company not found", HttpStatus.NOT_FOUND);
    }
}
