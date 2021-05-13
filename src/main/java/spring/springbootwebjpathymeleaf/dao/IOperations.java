package spring.springbootwebjpathymeleaf.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IOperations<T extends Serializable> {


    Optional<T> findById(final long id);

    List<T>findAll();

    T save(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

}
