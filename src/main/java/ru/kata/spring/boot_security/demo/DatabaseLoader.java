package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final UserService userService;

    public DatabaseLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        userService.createRole(adminRole);
        userService.createRole(userRole);

        User main = new User("Pavel", "RobotXiaomi", 22, "u", "u");
        main.addRole(adminRole);
        main.addRole(userRole);

        User ricardo = new User("Ricardo", "Milos", 31, "r", "r");
        ricardo.addRole(adminRole);
        ricardo.addRole(userRole);

        User natalia = new User("Natalia", "Marines", 43, "user", "user");
        natalia.addRole(userRole);

        User alex = new User("Alex", "Jeweler", 22, "a", "a");
        alex.addRole(userRole);

        User oleg = new User("Oleg", "GoodJob", 14, "o", "o");
        oleg.addRole(userRole);

        userService.createUser(main);
        userService.createUser(ricardo);
        userService.createUser(natalia);
        userService.createUser(alex);
        userService.createUser(oleg);

    }
}
