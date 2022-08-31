package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Company;
import peaksoft.repository.CompanyDao;
import peaksoft.service.CompanyService;

import java.util.List;

@Service


public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;
    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }

    @Override
    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyDao.getCompanyById(id);
    }

    @Override
    public void update(Company company) {
    companyDao.update(company);
    }

    @Override
    public void deleteCompanyById(Company company) {
    companyDao.deleteCompany(company);
    }
}
