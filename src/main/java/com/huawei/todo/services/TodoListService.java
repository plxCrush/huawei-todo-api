package com.huawei.todo.services;

import com.huawei.todo.dtos.TodoListFilter;
import com.huawei.todo.models.TodoList;

import java.util.List;

public interface TodoListService extends BasicService<TodoList> {

    List<TodoList> search(TodoListFilter filter);
}
