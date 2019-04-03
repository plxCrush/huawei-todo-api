package com.huawei.todo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "todolists")
@NoArgsConstructor
public class TodoList extends BasicObject {

    @Column(nullable = false)
    String name;
}
