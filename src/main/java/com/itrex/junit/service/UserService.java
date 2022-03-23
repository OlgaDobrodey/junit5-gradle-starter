package com.itrex.junit.service;

import com.itrex.junit.dto.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    List<User> users = new ArrayList<>();

    public List<User> getAll() {
        return users;
    }

    public boolean add(User user) {
        return users.add(user);
    }
}
