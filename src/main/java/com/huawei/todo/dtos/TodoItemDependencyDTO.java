package com.huawei.todo.dtos;

import com.huawei.todo.models.TodoItem;
import com.huawei.todo.models.TodoList;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class TodoItemDependencyDTO extends BasicObjectDTO {

    String name;

    Date deadline;

    Boolean completed;

    long todoListId;

    public TodoItemDependencyDTO(TodoItem todoItem) {

        super(todoItem);
        this.name = todoItem.getName();
        this.deadline = todoItem.getDeadline();
        this.completed = todoItem.isCompleted();

        TodoList todoList = todoItem.getTodoList();
        this.todoListId = todoList.getId();
    }
}
