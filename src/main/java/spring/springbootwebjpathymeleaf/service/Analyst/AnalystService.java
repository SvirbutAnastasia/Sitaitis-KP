package spring.springbootwebjpathymeleaf.service.Analyst;

import org.springframework.data.domain.Page;
import spring.springbootwebjpathymeleaf.model.Analyst;

import java.util.List;

public interface AnalystService {
    List<Analyst> getAllAnalysts();
    void save(Analyst analyst);
    Analyst getById(Long id);
    void deleteById(Long id);
    List<Analyst> listAll (String name);
    Page<Analyst> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
