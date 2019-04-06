package com.huawei.todo.services;

import com.huawei.todo.models.TodoList;

import java.util.List;

public interface TodoListService extends BasicService<TodoList> {

    List<TodoList> listByUser(long userId);
}
