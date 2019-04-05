package com.huawei.todo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "todoItems")
@NoArgsConstructor
public class TodoItem extends BasicObject {

    @Column(nullable = false)
    String name;

    String description;

    Date deadline;

    @Column(nullable = false)
    Date createdAt = new Date();

    @Column(nullable = false)
    boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "todoList_id", updatable = false, nullable = false)
    TodoList todoList;
}
