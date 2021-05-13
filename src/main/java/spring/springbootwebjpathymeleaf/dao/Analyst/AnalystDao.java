package spring.springbootwebjpathymeleaf.dao.Analyst;

import spring.springbootwebjpathymeleaf.dao.IOperations;
import spring.springbootwebjpathymeleaf.model.Analyst;

import java.util.List;


public interface AnalystDao extends IOperations<Analyst> {

    List<Analyst> findAll();
}
