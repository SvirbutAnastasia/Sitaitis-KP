package spring.springbootwebjpathymeleaf.service.User;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import spring.springbootwebjpathymeleaf.dto.UserRegistrationDto;
import spring.springbootwebjpathymeleaf.model.User;

import java.util.List;

public interface UserService extends UserDetailsService{
	List<User> getAllUsers();
	void save(User user);
	User getById(Long id);
	void deleteById(Long id);
	List<User> listAll (String name);
	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	User save(UserRegistrationDto registrationDto);
}
