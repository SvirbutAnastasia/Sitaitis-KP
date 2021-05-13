package spring.springbootwebjpathymeleaf.dao.Analyst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.model.Analyst;
import spring.springbootwebjpathymeleaf.repository.AnalystRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnalystDaoImpl implements AnalystDao {
    @Autowired
    private AnalystRepository analystRepository;

    @Override
    public Optional<Analyst> findById(long id) {
        return analystRepository.findById(id);
    }

    @Override
    public List<Analyst> findAll() {
        return analystRepository.findAll();
    }


    @Override
    public Analyst save(Analyst entity) {return analystRepository.save(entity);}

    @Override
    public Analyst update(Analyst entity) {
        return analystRepository.save(entity);
    }

    @Override
    public void delete(Analyst entity) {
    analystRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
    analystRepository.deleteById(entityId);
    }


}
