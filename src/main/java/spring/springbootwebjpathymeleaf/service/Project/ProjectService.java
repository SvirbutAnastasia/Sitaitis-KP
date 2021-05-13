package spring.springbootwebjpathymeleaf.service.Project;

import org.springframework.data.domain.Page;
import spring.springbootwebjpathymeleaf.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    void save(Project project);
    Project getById(Long id);
    void deleteById(Long id);
    List<Project> listAll (String name);
    Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}