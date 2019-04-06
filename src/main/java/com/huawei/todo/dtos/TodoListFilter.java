package com.huawei.todo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoListFilter {

    String keyword;

    Long userId;
}
