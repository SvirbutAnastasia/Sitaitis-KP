package spring.springbootwebjpathymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.FIO LIKE %?1%"
			+ " OR u.post LIKE %?1%"
			+ " OR u.email LIKE %?1%"
			+ " OR u.efficiencyPercent LIKE %?1%"
			+ " OR u.problemAreas LIKE %?1%"
	)
	List<User> search(String keyword);
	User findByEmail(String email);
}
