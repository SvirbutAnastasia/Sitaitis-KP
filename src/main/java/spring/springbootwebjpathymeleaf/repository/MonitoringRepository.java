package spring.springbootwebjpathymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.model.Monitoring;

import java.util.List;

@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {
    @Query("SELECT r FROM Monitoring r WHERE r.FIOAnalyst LIKE %?1%"
            + " OR r.projectName LIKE %?1%"
            + " OR r.risk LIKE %?1%")
    List<Monitoring> search(String keyword);
}