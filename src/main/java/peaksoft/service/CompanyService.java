package peaksoft.service;

import peaksoft.model.Company;

import java.util.List;


public interface CompanyService{

    List<Company> getAllCompanies();


    void addCompany(Company company);


    Company getCompanyById(Long id);


    void update(Company company);


    void deleteCompanyById(Company company);
}
