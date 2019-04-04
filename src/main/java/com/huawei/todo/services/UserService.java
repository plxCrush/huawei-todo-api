package com.huawei.todo.services;

import com.huawei.todo.models.User;

public interface UserService extends BasicService<User> {

    User get(String username);
}
