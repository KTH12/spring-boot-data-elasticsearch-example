package com.springbootes001.es001;

import org.elasticsearch.repositories.Repository;

import java.util.Optional;

public interface CrudRepository<T, ID> extends Repository {

    <S extends T> S save(S entity);

    Optional<T> findById(ID primaryKey);

    Iterable<T> findAll();

    long count();

    void delete(T entity);

    boolean existsById(ID primaryKey);

}
