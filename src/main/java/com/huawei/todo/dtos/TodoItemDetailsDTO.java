package com.huawei.todo.dtos;

import com.huawei.todo.models.TodoItem;
import com.huawei.todo.models.TodoList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TodoItemDetailsDTO extends TodoItemDTO {

    String todoListName;

    String description;

    List<TodoItemDTO> dependencies;

    public TodoItemDetailsDTO(TodoItem todoItem) {

        super(todoItem);
        this.description = todoItem.getDescription();

        TodoList todoList = todoItem.getTodoList();
        this.todoListName = todoList.getName();

        this.dependencies = todoItem.getDependencies()
                .stream()
                .map(TodoItemDTO::new)
                .collect(Collectors.toList());
    }
}
