package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Company> getAllCompanies() {
        return entityManager
                .createQuery("select c from Company c",Company.class)
                .getResultList();
    }

    @Override
    public void addCompany(Company company) {
        entityManager.persist(company);

    }

    @Override
    public Company getCompanyById(Long id) {
        return entityManager.find(Company.class,id);
    }

    @Override
    public void update(Company company) {
        entityManager.merge(company);

    }

    @Override
    public void deleteCompany(Company company) {
        entityManager.remove(entityManager.contains(company)?company:entityManager.merge(company));

    }
}
