package com.huawei.todo.services;

import com.huawei.todo.dtos.TodoItemFilter;
import com.huawei.todo.models.TodoItem;

import java.util.List;

public interface TodoItemService extends BasicService<TodoItem> {

    List<TodoItem> search(TodoItemFilter filter);
}