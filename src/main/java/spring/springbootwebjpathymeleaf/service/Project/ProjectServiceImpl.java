package spring.springbootwebjpathymeleaf.service.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.springbootwebjpathymeleaf.dao.Project.ProjectDao;
import spring.springbootwebjpathymeleaf.model.Project;
import spring.springbootwebjpathymeleaf.repository.ProjectRepository;

import java.util.List;

@Service

public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }

//    @Override
//    public List<Project> getAllProjects() {
//        return projectDao.deleteById(id);
//    }

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }
    @Override
    public Project getById(Long id) { return projectDao.findById(id).orElseThrow(RuntimeException::new); }
    @Override
    public void deleteById(Long id) {
        projectDao.deleteById(id);
    }

    @Override
    public List<Project> listAll(String name) {
        if (name != null && !name.isEmpty())
        {
            return projectRepository.search(name);
        }
        return projectRepository.findAll();
    }

    @Override
    public Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return projectRepository.findAll(pageable);
    }
}