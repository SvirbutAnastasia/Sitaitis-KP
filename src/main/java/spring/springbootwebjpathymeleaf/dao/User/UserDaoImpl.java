package spring.springbootwebjpathymeleaf.dao.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.springbootwebjpathymeleaf.dao.User.UserDao;
import spring.springbootwebjpathymeleaf.model.User;
import spring.springbootwebjpathymeleaf.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity) {return userRepository.save(entity);}

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
    userRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
    userRepository.deleteById(entityId);
    }

}
