package com.mayankch283.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    Company findCompany(long id);
    void deleteCompany(Company company);
}
