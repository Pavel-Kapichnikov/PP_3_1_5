package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext()
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql).getResultList();
    }

    @Override
    public void createUser(User user) {
//        for (Role role : user.getRoles()) {
//            em.persist(role);
//        }

        em.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        return em.createQuery(jpql, User.class).setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void editUser(Long id, User user) {
        User userToBeEdit = em.find(User.class, id);
        userToBeEdit.setName(user.getName());
        userToBeEdit.setLastName(user.getLastName());
        userToBeEdit.setAge(user.getAge());
        userToBeEdit.setUsername(user.getUsername());
        userToBeEdit.setPassword(user.getPassword());
        for (Role role : userToBeEdit.getRoles()) {
            em.remove(role);
        }
        userToBeEdit.setRoles(user.getRoles());
        em.merge(userToBeEdit);
    }

    @Override
    public void deleteUser(long id) {
        em.remove(em.find(User.class, id));
    }

}
