package com.huawei.todo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "todoItem_dependencies",
            joinColumns = @JoinColumn(name = "todoItem_id"),
            inverseJoinColumns = @JoinColumn(name = "dependency_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"todoItem_id", "dependency_id"}))
    List<TodoItem> dependencies = new ArrayList<>();

    public boolean isExpired() {
        return !this.completed &&
                (this.deadline != null && this.deadline.compareTo(new Date()) < 0);
    }

}
