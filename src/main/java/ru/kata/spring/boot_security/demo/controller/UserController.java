package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails ud = (UserDetails) authentication.getPrincipal();

        Set<Role> allRoles = new HashSet<>();
            allRoles.add(new Role("ROLE_USER"));
            allRoles.add(new Role("ROLE_ADMIN"));

        model.addAttribute("allRoles", allRoles);
        model.addAttribute("currentUser", userService.getUserByUsername(ud.getUsername()));
        model.addAttribute("users", userService.getAllUsers());
        //model.addAttribute("allRoles", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "admin";
    }

    @GetMapping("/user")
    public String userPage(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/create")
    public String creator(Model model) {
        model.addAttribute("user", new User());
        return "creator";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(name = "ROLE_USER", defaultValue = "false") boolean userRole,
                         @RequestParam(name = "ROLE_ADMIN", defaultValue = "false") boolean adminRole) {
        Set<Role> tempRoles = new HashSet<>();
        if (userRole) {
            tempRoles.add(new Role("ROLE_USER"));
        }
        if (adminRole) {
            tempRoles.add(new Role("ROLE_ADMIN"));
        }
        user.setRoles(tempRoles);
        userService.createUser(user);
        return "redirect:/admin";
    }

    /*
    @GetMapping("/edit")
    public String editor(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user1", userService.getUserById(id));
            for (Role role : userService.getUserById(id).getRoles()) {
                if ("ROLE_USER".equals(role.getName())) {
                    model.addAttribute("userRole", true);
                }
                if ("ROLE_ADMIN".equals(role.getName())) {
                    model.addAttribute("adminRole", true);
                }
            }
        return "admin";
    }*/

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(value = "id") long id,
                       @RequestParam("selectedRoles") List<String> selectedRoles) {
        System.out.println("Received User: " + user);
        System.out.println("Received ID: " + id);
        System.out.println("Received User Name: " + user.getName());
        System.out.println("Received Selected Roles: " + selectedRoles);

        Set<Role> tempRoles = new HashSet<>();
        for (String role : selectedRoles) {
            tempRoles.add(new Role(role));
        }

        user.setRoles(tempRoles);
        userService.editUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

/*

@PostMapping("/admin/edit")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(value = "id") long id,
                       @RequestParam(name = "ROLE_USER", defaultValue = "false") boolean userRole,
                       @RequestParam(name = "ROLE_ADMIN", defaultValue = "false") boolean adminRole) {
        Set<Role> tempRoles = new HashSet<>();
        if (userRole) {
            tempRoles.add(new Role("ROLE_USER"));
        }
        if (adminRole) {
            tempRoles.add(new Role("ROLE_ADMIN"));
        }
        user.setRoles(tempRoles);
        userService.editUser(id, user);
        return "redirect:/admin";
    }

 */