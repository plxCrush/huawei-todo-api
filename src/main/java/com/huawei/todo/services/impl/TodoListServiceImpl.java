package com.huawei.todo.services.impl;

import com.huawei.todo.dtos.TodoListFilter;
import com.huawei.todo.models.TodoList;
import com.huawei.todo.repositories.TodoListRepository;
import com.huawei.todo.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListServiceImpl extends BasicServiceImpl<TodoList> implements TodoListService {

    @Autowired
    public TodoListServiceImpl(TodoListRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<TodoList> search(TodoListFilter filter) {

        Specification spec = prepareSpecification(filter);
        return repository.findAll(spec);
    }

    private Specification prepareSpecification(TodoListFilter filter) {

        return (Specification<TodoList>) (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("user"), filter.getUserId()));

            String keyword = filter.getKeyword();
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(
                        cb.like(cb.lower(root.get("name")), String.format("%%%s%%", keyword.toLowerCase()))
                );
            }

            query.orderBy(cb.asc(root.get("name")));

            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }
}
