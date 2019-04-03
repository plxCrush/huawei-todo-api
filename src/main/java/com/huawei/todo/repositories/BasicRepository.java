package com.huawei.todo.repositories;

import com.huawei.todo.models.BasicObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasicRepository<T extends BasicObject> extends JpaRepository<T, Long> {

    List<T> findAll();
    List<T> findAll(Specification spec);
}
