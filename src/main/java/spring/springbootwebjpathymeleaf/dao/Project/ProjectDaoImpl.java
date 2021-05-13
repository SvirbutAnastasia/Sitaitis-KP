package spring.springbootwebjpathymeleaf.dao.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.model.Project;
import spring.springbootwebjpathymeleaf.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Optional<Project> findById(long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project save(Project entity) {return projectRepository.save(entity);}

    @Override
    public Project update(Project entity) {
        return projectRepository.save(entity);
    }

    @Override
    public void delete(Project entity) {
    projectRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
    projectRepository.deleteById(entityId);
    }

}
