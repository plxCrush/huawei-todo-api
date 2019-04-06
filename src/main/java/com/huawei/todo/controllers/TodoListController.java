package com.huawei.todo.controllers;

import com.huawei.todo.Application;
import com.huawei.todo.controllers.exceptions.ApiErrorMessage;
import com.huawei.todo.controllers.exceptions.ApiException;
import com.huawei.todo.controllers.exceptions.ApiValidationException;
import com.huawei.todo.dtos.TodoListDTO;
import com.huawei.todo.dtos.TodoListFilter;
import com.huawei.todo.models.TodoList;
import com.huawei.todo.models.User;
import com.huawei.todo.security.JwtUser;
import com.huawei.todo.services.TodoListService;
import com.huawei.todo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todo-lists")
public class TodoListController {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final TodoListService todoListService;
    private final UserService userService;

    public TodoListController(TodoListService todoListService, UserService userService) {
        this.todoListService = todoListService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity list(TodoListFilter filter, Authentication authentication) throws ApiException {

        JwtUser principal = (JwtUser) authentication.getPrincipal();
        User user = userService.get(principal.getUsername());
        if (user == null) {
            throw new ApiException(ApiErrorMessage.USER_NOT_FOUND);
        }

        filter.setUserId(user.getId());

        List<TodoList> todoLists = todoListService.search(filter);
        return ResponseEntity.ok(
                todoLists.stream()
                .map(TodoListDTO::new)
                .toArray()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable long id) {

        TodoList todoList = todoListService.get(id);
        return ResponseEntity.ok(new TodoListDTO(todoList));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TodoListDTO dto, Errors errors, Authentication authentication) throws ApiException {

        if (errors.hasErrors()) {
            throw new ApiValidationException(errors);
        }

        JwtUser principal = (JwtUser) authentication.getPrincipal();
        User user = userService.get(principal.getUsername());
        if (user == null) {
            throw new ApiException(ApiErrorMessage.USER_NOT_FOUND);
        }

        TodoList todoList = new TodoList();
        todoList.setUser(user);
        todoList.setName(dto.getName());
        todoList = todoListService.save(todoList);

        return ResponseEntity.ok(new TodoListDTO(todoList));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@Valid @RequestBody TodoListDTO dto, @PathVariable long id, Errors errors, Authentication authentication) throws ApiException {

        if (errors.hasErrors()) {
            throw new ApiValidationException(errors);
        }

        TodoList todoList = todoListService.get(id);
        if (todoList == null) {
            throw new ApiException(ApiErrorMessage.TODOLIST_NOT_FOUND);
        }

        JwtUser principal = (JwtUser) authentication.getPrincipal();
        User user = userService.get(principal.getUsername());
        if (user == null) {
            throw new ApiException(ApiErrorMessage.USER_NOT_FOUND);
        }

        todoList.setName(dto.getName());
        todoList = todoListService.save(todoList);

        return ResponseEntity.ok(new TodoListDTO(todoList));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {

        TodoList todoList = todoListService.get(id);
        todoListService.delete(todoList);
        return new ResponseEntity(HttpStatus.OK);
    }
}