package com.huawei.todo.dtos;

import com.huawei.todo.models.TodoList;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class TodoListDTO extends BasicObjectDTO {

    @NotBlank(message = "Name cannot be blank.")
    String name;

    public TodoListDTO(TodoList todoList) {

        super(todoList);
        this.name = todoList.getName();
    }
}
