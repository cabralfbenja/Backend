package ar.edu.frc.utn.bda3k4.northwind.services.interfaces;

import java.util.List;

public interface Service<T, ID> {
    T add(T entity);
    T update(ID id, T entity);
    T delete(ID id);
    T findById(ID id);
    List<T> findAll();
}
