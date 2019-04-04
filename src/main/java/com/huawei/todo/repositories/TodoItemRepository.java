package com.huawei.todo.repositories;

import com.huawei.todo.models.TodoItem;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends BasicRepository<TodoItem> {

}