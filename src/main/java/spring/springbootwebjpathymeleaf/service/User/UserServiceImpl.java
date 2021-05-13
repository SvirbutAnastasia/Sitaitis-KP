package spring.springbootwebjpathymeleaf.service.User;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.springbootwebjpathymeleaf.dao.User.UserDao;
import spring.springbootwebjpathymeleaf.dto.UserRegistrationDto;
import spring.springbootwebjpathymeleaf.model.Role;
import spring.springbootwebjpathymeleaf.model.User;
import spring.springbootwebjpathymeleaf.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetails{

	private UserRepository userRepository;
	private User user;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;
	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	@Override
	public User getById(Long id) { return userDao.findById(id).orElseThrow(RuntimeException::new); }
	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public List<User> listAll(String name) {
		if (name != null && !name.isEmpty())
		{
			return userRepository.search(name);
		}
		return userRepository.findAll();
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return userRepository.findAll(pageable);
	}

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFIO(),
				registrationDto.getPost(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if(user == null) {
			throw new UsernameNotFoundException("Неверное имя пользователя или пароль");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = (Set<Role>) user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}