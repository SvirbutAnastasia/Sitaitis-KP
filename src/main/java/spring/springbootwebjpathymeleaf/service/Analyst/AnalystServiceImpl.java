package spring.springbootwebjpathymeleaf.service.Analyst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.springbootwebjpathymeleaf.dao.Analyst.AnalystDao;
import spring.springbootwebjpathymeleaf.model.Analyst;
import spring.springbootwebjpathymeleaf.repository.AnalystRepository;

import java.util.List;

@Service

public class AnalystServiceImpl implements AnalystService {
    @Autowired
    private AnalystDao analystDao;
    @Autowired
    private AnalystRepository analystRepository;
    @Override
    public List<Analyst> getAllAnalysts() {
        return analystDao.findAll();
    }

    @Override
    public void save(Analyst analyst) {
        analystDao.save(analyst);
    }
    @Override
    public Analyst getById(Long id) { return analystDao.findById(id).orElseThrow(RuntimeException::new); }
    @Override
    public void deleteById(Long id) {
        analystDao.deleteById(id);
    }

    @Override
    public List<Analyst> listAll(String name) {
        if (name != null && !name.isEmpty())
        {
            return analystRepository.search(name);
        }
        return analystRepository.findAll();
    }

    @Override
    public Page<Analyst> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return analystRepository.findAll(pageable);
    }
}
