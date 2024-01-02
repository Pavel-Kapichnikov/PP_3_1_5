package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.models.Role;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long id;
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotBlank(message = "Name should not be empty")
    private String name;
    @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters")
    @NotBlank(message = "Last Name should not be empty")
    private String lastName;
    @NotNull(message = "Age should not be null")
    @Min(value = 0, message = "Age should be greater then 0")
    private Integer age;
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Username should be consist of Latin letters and numbers")
    private String username;
    @Size(min = 1, max = 60, message = "Password should be between 1 and 60 characters")
    @NotBlank(message = "Name should not be empty")
    private String password;
    private List<String> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String lastName, Integer age, String username, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public UserDTO(Long id, String name, String lastName, Integer age, String username, String password, List<String> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void addRole(String role) {this.roles.add(role);}

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}