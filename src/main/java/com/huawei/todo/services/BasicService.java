package com.huawei.todo.services;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BasicService<T> {

    List<T> getAll();
    List<T> get(Specification specification);
    T get(long id);
    T save(T entity);
    void delete(T object);
}
