package com.itrex.junit.service.mockito.dao;

import com.itrex.junit.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class UserDaoSpy extends UserDao {

    private final UserDao userDao;
    private Map<Integer, Boolean> answers = new HashMap<>();

    public UserDaoSpy(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean delete(Integer userId) {
        return answers.getOrDefault(userId,userDao.delete(userId));
    }

}
