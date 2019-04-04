package com.huawei.todo.services.impl;

import com.huawei.todo.models.TodoItem;
import com.huawei.todo.repositories.TodoItemRepository;
import com.huawei.todo.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl extends BasicServiceImpl<TodoItem> implements TodoItemService {

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository repository) {

        this.repository = repository;
    }

}

