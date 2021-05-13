package spring.springbootwebjpathymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.model.Analyst;

import java.util.List;

@Repository
public interface AnalystRepository extends JpaRepository<Analyst, Long> {
    @Query("SELECT l FROM Analyst l WHERE l.FIO LIKE %?1%"
            + " OR l.department LIKE %?1%")
    List<Analyst> search(String keyword);
}