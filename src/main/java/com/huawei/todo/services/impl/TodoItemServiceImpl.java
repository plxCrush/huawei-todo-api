package com.huawei.todo.services.impl;

import com.huawei.todo.dtos.TodoItemFilter;
import com.huawei.todo.models.TodoItem;
import com.huawei.todo.repositories.TodoItemRepository;
import com.huawei.todo.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoItemServiceImpl extends BasicServiceImpl<TodoItem> implements TodoItemService {

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<TodoItem> search(TodoItemFilter filter) {

        Specification spec = prepareSpecification(filter);
        return repository.findAll(spec);
    }

    private Specification prepareSpecification(TodoItemFilter filter) {

        return (Specification<TodoItem>) (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            String keyword = filter.getKeyword();
            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(
                        cb.like(cb.lower(root.get("name")), String.format("%%%s%%", keyword.toLowerCase()))
                );
            }

            Boolean completed = filter.getCompleted();
            if (completed != null) {
                predicates.add(cb.equal(root.get("completed"), filter.getCompleted()));
            }

            Boolean expired = filter.getExpired();
            if (expired != null) {
                if (expired) {
                    predicates.add(cb.greaterThan(root.get("deadline"), new Date()));
                } else {
                    predicates.add(cb.or(
                            cb.isNull(root.get("deadline")),
                            cb.lessThan(root.get("deadline"), new Date())
                    ));
                }
            }

            //TODO: Implement order by other parameters.
            query.orderBy(cb.asc(root.get("name")));

            return cb.and(predicates.toArray(new Predicate[] {}));
        };
    }
}

