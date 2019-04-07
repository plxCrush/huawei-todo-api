package com.huawei.todo.dtos;

import com.huawei.todo.models.TodoItem;
import com.huawei.todo.models.TodoList;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TodoItemDTO extends BasicObjectDTO {

    @NotBlank(message = "Name cannot be blank.")
    String name;

    String description;

    Date deadline;

    Date createdAt;

    Boolean completed;

    Boolean expired;

    @NotNull(message = "Todo list id cannot be blank.")
    long todoListId;

    String todoListName;

    List<TodoItemDependencyDTO> dependencies;

    public TodoItemDTO(TodoItem todoItem) {

        super(todoItem);
        this.name = todoItem.getName();
        this.description = todoItem.getDescription();
        this.deadline = todoItem.getDeadline();
        this.completed = todoItem.isCompleted();
        this.createdAt = todoItem.getCreatedAt();
        this.expired = todoItem.isExpired();

        TodoList todoList = todoItem.getTodoList();
        this.todoListId = todoList.getId();
        this.todoListName = todoList.getName();

        this.dependencies = todoItem.getDependencies()
                .stream()
                .map(TodoItemDependencyDTO::new)
                .collect(Collectors.toList());
    }
}
