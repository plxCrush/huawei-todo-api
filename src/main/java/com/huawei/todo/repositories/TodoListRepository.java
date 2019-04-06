package com.huawei.todo.repositories;

import com.huawei.todo.models.TodoList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends BasicRepository<TodoList> {

    List<TodoList> findAllByUserId(long userId);
}
