package com.huawei.todo;

import com.huawei.todo.models.TodoItem;
import com.huawei.todo.models.TodoList;
import com.huawei.todo.models.User;
import com.huawei.todo.repositories.TodoItemRepository;
import com.huawei.todo.repositories.TodoListRepository;
import com.huawei.todo.repositories.UserRepository;
import com.huawei.todo.services.TodoItemService;
import com.huawei.todo.services.TodoListService;
import com.huawei.todo.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    TodoListService todoListService;

    @MockBean
    TodoListRepository todoListRepository;

    @Autowired
    TodoItemService todoItemService;

    @MockBean
    TodoItemRepository todoItemRepository;


    @Test
    public void contextLoads() {
    }

    @Test
    public void saveUser() {

        User user = new User();
        user.setId(1);
        user.setUsername("test_user");
        user.setName("Test User");

        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.save(user));
    }

    @Test
    public void saveTodoList() {

        User user = new User();
        user.setId(1);
        user.setUsername("test_user");
        user.setName("Test User");

        TodoList todoList = new TodoList();
        todoList.setId(1);
        todoList.setUser(user);
        todoList.setName("list 1");

        when(todoListRepository.save(todoList)).thenReturn(todoList);
        assertEquals(todoList, todoListService.save(todoList));
    }

    @Test
    public void saveTodoItem() {

        User user = new User();
        user.setId(1);
        user.setUsername("test_user");
        user.setName("Test User");

        TodoList todoList = new TodoList();
        todoList.setId(1);
        todoList.setUser(user);
        todoList.setName("list 1");

        TodoItem todoItem = new TodoItem();
        todoItem.setTodoList(todoList);
        todoItem.setCompleted(false);
        todoItem.setDeadline(new Date());

        when(todoItemRepository.save(todoItem)).thenReturn(todoItem);
        assertEquals(todoItem, todoItemRepository.save(todoItem));
    }

}
