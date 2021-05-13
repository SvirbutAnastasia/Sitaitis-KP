package spring.springbootwebjpathymeleaf.service.Monitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.springbootwebjpathymeleaf.model.Monitoring;
import spring.springbootwebjpathymeleaf.repository.MonitoringRepository;

import java.util.List;

@Service

public class MonitoringServiceImpl implements MonitoringService {
    @Autowired
    private MonitoringRepository monitoringRepository;
    @Override
    public List<Monitoring> getAllMonitorings() {
        return monitoringRepository.findAll();
    }
    @Override
    public void save(Monitoring monitoring) {
        monitoringRepository.save(monitoring);
    }
    @Override
    public Monitoring getById(Long id) { return monitoringRepository.findById(id).orElseThrow(RuntimeException::new); }
    @Override
    public void deleteById(Long id) {
        monitoringRepository.deleteById(id);
    }

    @Override
    public List<Monitoring> listAll(String name) {
        if (name != null && !name.isEmpty())
        {
            return monitoringRepository.search(name);
        }
        return monitoringRepository.findAll();
    }

    @Override
    public Page<Monitoring> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return monitoringRepository.findAll(pageable);
    }
}
