package com.huawei.todo.dtos;

import com.huawei.todo.models.TodoItem;
import com.huawei.todo.models.TodoList;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class TodoItemDTO extends BasicObjectDTO {

    String name;

    Date deadline;

    Boolean completed;

    Boolean expired;

    Date createdAt;

    long todoListId;

    public TodoItemDTO(TodoItem todoItem) {

        super(todoItem);
        this.name = todoItem.getName();
        this.deadline = todoItem.getDeadline();
        this.completed = todoItem.isCompleted();
        this.expired = todoItem.isExpired();
        this.createdAt = todoItem.getCreatedAt();

        TodoList todoList = todoItem.getTodoList();
        this.todoListId = todoList.getId();
    }
}
