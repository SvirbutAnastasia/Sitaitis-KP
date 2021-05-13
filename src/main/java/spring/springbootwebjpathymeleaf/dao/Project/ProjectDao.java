package spring.springbootwebjpathymeleaf.dao.Project;

import spring.springbootwebjpathymeleaf.dao.IOperations;
import spring.springbootwebjpathymeleaf.model.Project;

import java.util.List;


public interface ProjectDao extends IOperations<Project> {

    List<Project> findAll();
}
