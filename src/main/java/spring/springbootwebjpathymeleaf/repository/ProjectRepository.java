package spring.springbootwebjpathymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.model.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT r FROM Project r WHERE r.name LIKE %?1%"
            + " OR r.readyPercent LIKE %?1%"
            + " OR r.FIOEmployee LIKE %?1%")
    List<Project> search(String keyword);
}