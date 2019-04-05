package com.huawei.todo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoItemFilter {

    Long todoListId;

    String keyword;

    Boolean completed;

    Boolean expired;

    String orderBy;

    String orderDirection;
}