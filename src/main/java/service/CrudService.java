package service;

import java.util.List;

public interface CrudService<T, ID> {

    T create(T entity);

    T update(T entity);

    void delete(ID entityId);

    T getById(ID entityId);

    List<T> getAll();
}