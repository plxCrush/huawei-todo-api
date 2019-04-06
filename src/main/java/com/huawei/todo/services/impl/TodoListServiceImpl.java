package com.huawei.todo.services.impl;

import com.huawei.todo.models.TodoList;
import com.huawei.todo.repositories.TodoListRepository;
import com.huawei.todo.services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListServiceImpl extends BasicServiceImpl<TodoList> implements TodoListService {

    @Autowired
    public TodoListServiceImpl(TodoListRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<TodoList> listByUser(long userId) {
        return ((TodoListRepository) repository).findAllByUserId(userId);
    }
}
