package com.huawei.todo.config;

import com.huawei.todo.Application;
import com.huawei.todo.models.User;
import com.huawei.todo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) {

        logger.info("Running database seeder...");

        User huawei = userService.get("huawei");
        if (huawei == null) {
            huawei = new User();
            huawei.setName("Huawei User");
            huawei.setUsername("huawei");
            huawei.hashPassword("huawei");
            userService.save(huawei);
        }

        User emre = userService.get("emre.gonen");
        if (emre == null) {
            emre = new User();
            emre.setName("Emre Gönen");
            emre.setUsername("emre.gonen");
            emre.hashPassword("123");
            userService.save(emre);
        }

    }
}
