package spring.springbootwebjpathymeleaf.service.Monitoring;

import org.springframework.data.domain.Page;
import spring.springbootwebjpathymeleaf.model.Monitoring;

import java.util.List;

public interface MonitoringService {
    List<Monitoring> getAllMonitorings();
    void save(Monitoring monitoring);
    Monitoring getById(Long id);
    void deleteById(Long id);
    List<Monitoring> listAll (String name);
    Page<Monitoring> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
