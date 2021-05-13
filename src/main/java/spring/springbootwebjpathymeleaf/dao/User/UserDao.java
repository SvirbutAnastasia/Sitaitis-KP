package spring.springbootwebjpathymeleaf.dao.User;

import spring.springbootwebjpathymeleaf.dao.IOperations;
import spring.springbootwebjpathymeleaf.model.User;

import java.util.List;


public interface UserDao extends IOperations<User> {

    List<User> findAll();
}
