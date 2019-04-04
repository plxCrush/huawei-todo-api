package com.huawei.todo.services.impl;

import com.huawei.todo.models.User;
import com.huawei.todo.repositories.UserRepository;
import com.huawei.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BasicServiceImpl<User> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {

        this.repository = repository;
    }

    @Override
    public User get(String username) {

        return ((UserRepository) repository).findByUsernameIgnoreCase(username);
    }

}
