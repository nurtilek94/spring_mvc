package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import java.util.List;

public interface CompanyDao  {
    List<Company>getAllCompanies();

    void addCompany(Company company);

    Company getCompanyById(Long id);

    void update(Company company);

    void deleteCompany(Company company);


}
