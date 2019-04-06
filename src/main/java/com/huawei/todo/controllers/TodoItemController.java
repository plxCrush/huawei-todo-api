package com.huawei.todo.controllers;

import com.huawei.todo.Application;
import com.huawei.todo.controllers.exceptions.ApiErrorMessage;
import com.huawei.todo.controllers.exceptions.ApiException;
import com.huawei.todo.controllers.exceptions.ApiValidationException;
import com.huawei.todo.dtos.TodoItemDTO;
import com.huawei.todo.dtos.TodoItemFilter;
import com.huawei.todo.models.TodoItem;
import com.huawei.todo.models.TodoList;
import com.huawei.todo.models.User;
import com.huawei.todo.security.JwtUser;
import com.huawei.todo.services.TodoItemService;
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
@RequestMapping("/api/todo-items")
public class TodoItemController {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final TodoItemService todoItemService;
    private final TodoListService todoListService;
    private final UserService userService;

    public TodoItemController(TodoItemService todoItemService, TodoListService todoListService, UserService userService) {
        this.todoItemService = todoItemService;
        this.todoListService = todoListService;
        this.userService = userService;
    }

    private User checkUser(Authentication authentication) throws ApiException {

        JwtUser principal = (JwtUser) authentication.getPrincipal();
        User user = userService.get(principal.getUsername());
        if (user == null) {
            throw new ApiException(ApiErrorMessage.USER_NOT_FOUND);
        } else {
            return user;
        }
    }

    @GetMapping
    public ResponseEntity list(TodoItemFilter filter, Authentication authentication) throws ApiException {

        if (filter.getTodoListId() == null) {
            throw new ApiException(ApiErrorMessage.TODOLIST_NOT_FOUND);
        }

        TodoList todoList = todoListService.get(filter.getTodoListId());

        if (todoList == null) {
            throw new ApiException(ApiErrorMessage.TODOLIST_NOT_FOUND);
        }

        User user = this.checkUser(authentication);

        if (user.getId() != todoList.getUser().getId()) {
            throw new ApiException(ApiErrorMessage.NOT_YOUR_TODOLIST);
        }

        List<TodoItem> todoItems = todoItemService.search(filter);
        return ResponseEntity.ok(
                todoItems.stream()
                        .map(TodoItemDTO::new)
                        .toArray()
        );
    }

    @GetMapping("all")
    public ResponseEntity all() {

        List<TodoItem> todoItems = todoItemService.getAll();
        return ResponseEntity.ok(
                todoItems.stream()
                        .map(TodoItemDTO::new)
                        .toArray()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable long id) {

        TodoItem todoList = todoItemService.get(id);
        return ResponseEntity.ok(new TodoItemDTO(todoList));
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TodoItemDTO dto, Errors errors, Authentication authentication) throws ApiException {

        if (errors.hasErrors()) {
            throw new ApiValidationException(errors);
        }

        TodoList todoList = todoListService.get(dto.getTodoListId());
        if (todoList == null) {
            throw new ApiException(ApiErrorMessage.TODOLIST_NOT_FOUND);
        }

        User user = this.checkUser(authentication);

        TodoItem todoItem = new TodoItem();
        todoItem.setTodoList(todoList);
        todoItem.setName(dto.getName());
        todoItem.setDescription(dto.getDescription());
        todoItem.setDeadline(dto.getDeadline());
        todoItem = todoItemService.save(todoItem);

        return ResponseEntity.ok(new TodoItemDTO(todoItem));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {

        TodoItem todoList = todoItemService.get(id);

        todoItemService.delete(todoList);
        return new ResponseEntity(HttpStatus.OK);
    }
}