package com.huawei.todo.repositories;

import com.huawei.todo.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BasicRepository<User> {

    User findByUsernameIgnoreCase(String username);
}
