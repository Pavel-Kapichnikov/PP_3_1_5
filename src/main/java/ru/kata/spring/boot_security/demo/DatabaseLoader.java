package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final UserService userService;

    public DatabaseLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        User main = new User("Pavel", "Gaijin", 22, "u", "u");
        main.addRole(new Role("ROLE_ADMIN"));
        main.addRole(new Role("ROLE_USER"));

        User ricardo = new User("Ricardo", "Milos", 31, "admin", "admin");
        ricardo.addRole(new Role("ROLE_ADMIN"));

        User natalia = new User("Natalia", "Marines", 43, "user", "user");
        natalia.addRole(new Role("ROLE_USER"));

        User alex = new User("Alex", "Jeweler", 22, "a", "a");
        alex.addRole(new Role("ROLE_USER"));

        User oleg = new User("Oleg", "GoodJob", 14, "o", "o");
        oleg.addRole(new Role("ROLE_USER"));

        userService.createUser(main);
        userService.createUser(ricardo);
        userService.createUser(natalia);
        userService.createUser(alex);
        userService.createUser(oleg);

    }
}
