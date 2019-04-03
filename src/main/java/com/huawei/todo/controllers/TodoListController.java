package com.huawei.todo.controllers;

import com.huawei.todo.Application;
import com.huawei.todo.models.TodoList;
import com.huawei.todo.services.TodoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo-lists")
public class TodoListController {

    static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    TodoListService todoListService;

    @GetMapping("all")
    public ResponseEntity all() {

        logger.info("find all todo lists...");
        List todoLists = todoListService.getAll();
        return ResponseEntity.ok(todoLists);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable long id) {

        TodoList todoList = todoListService.get(id);
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TodoList todoListDto) {

        TodoList todoList = new TodoList();
        todoList.setName(todoListDto.getName());
        todoList = todoListService.save(todoList);

        return ResponseEntity.ok(todoList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {

        TodoList todoList = todoListService.get(id);

        todoListService.delete(todoList);
        return new ResponseEntity(HttpStatus.OK);
    }
}