package com.huawei.todo.services.impl;

import com.huawei.todo.models.BasicObject;
import com.huawei.todo.repositories.BasicRepository;
import com.huawei.todo.services.BasicService;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class BasicServiceImpl<T extends BasicObject> implements BasicService<T> {

    protected BasicRepository<T> repository;

    @Override
    public List<T> getAll() {

        return repository.findAll();
    }

    @Override
    public List<T> get(Specification specification) {

        return repository.findAll(specification);
    }

    @Override
    public T get(long id) {

        return repository.getOne(id);
    }

    @Override
    public T save(T entity) {

        return repository.save(entity);
    }

    @Override
    public void delete(T object) {

        repository.delete(object);
    }
}