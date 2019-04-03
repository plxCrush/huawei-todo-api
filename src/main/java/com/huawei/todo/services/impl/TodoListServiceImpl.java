package com.huawei.todo.services.impl;

import com.huawei.todo.models.TodoList;
import com.huawei.todo.repositories.TodoListRepository;
import com.huawei.todo.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoListServiceImpl extends BasicServiceImpl<TodoList> implements TodoListService {

    @Autowired
    public TodoListServiceImpl(TodoListRepository repository) {

        this.repository = repository;
    }

}
