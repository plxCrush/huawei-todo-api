package com.huawei.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class User extends BasicObject {

    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Column(nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String username;

    @JsonIgnore
    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TodoList> todoLists = new ArrayList<>();

    public boolean verifyPassword(String password) {

        return passwordEncoder.matches(password, this.password);
    }

    public void hashPassword(String password) {

        this.password = passwordEncoder.encode(password);
    }
}
