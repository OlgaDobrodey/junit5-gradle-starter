package com.itrex.junit.service.user;

import com.itrex.junit.dto.User;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class UserService {

    List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public void add(User... users) {
        this.users.addAll(Arrays.asList(users));
    }

    public Optional<User> login(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("username or password is null");
        }

        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Map<Integer, User> getAllConvertedById() {
//        Map<Integer, User> userMap = new HashMap<>();
//        users.forEach(user -> userMap.put(user.getId(),user));
//        return userMap;
        return users.stream().collect(toMap(User::getId, t -> t));
        //Function.identity() == t->t
    }
}
