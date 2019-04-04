package com.huawei.todo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "todoLists")
@NoArgsConstructor
public class TodoList extends BasicObject {

    @Column(nullable = false)
    String name;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    User user;
}
